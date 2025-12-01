package com.hrms.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.hrms.dto.req.ResetPasswordReq;
import com.hrms.dto.req.VerifyOtpReq;
import com.hrms.entity.User;
import com.hrms.repository.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	@Value("${SECRET_KEY}")
	private String jwtSecret;

	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;

	@Autowired
	private CustomUserDetailServiceImpl customUserDetailService;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Key key;

	@PostConstruct
	private void init() {
		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
		log.info("JWT secret key initialized successfully.");
	}

	public String generateJwtToken(Authentication authentication) {
		CustomeUserDetails userPrincipal = (CustomeUserDetails)authentication.getPrincipal();

		log.info("Generating JWT token for user: {}", userPrincipal.getUsername());
		log.debug("User authorities: {}", userPrincipal.getAuthorities());
		log.debug("User ID: {}", userPrincipal.getUser().getId());

		System.out.println("user_id " + userPrincipal.getUsername());
		String token = Jwts.builder().setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date((new Date()).getTime() + jwtExpirationMs))
				.claim("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()))
				.claim("user_id", userPrincipal.getUser().getId())
				.signWith(key, SignatureAlgorithm.HS256).compact();

		log.info("JWT token generated successfully for user: {}", userPrincipal.getUsername());
		return token;
	}
	
	public String generateOtpToken(String email, String otp) {
	    return Jwts.builder()
	            .setSubject(email)
	            .claim("otp", otp)
	            .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5 min expiry
	            .signWith(key,SignatureAlgorithm.HS256)
	            .compact();
	}


	public String getUserNameFromJwtToken(Claims claims) {
		log.debug("Extracting username from JWT claims");
		String username = claims.getSubject();

		log.info("Username extracted from JWT: {}", username);
		return username;
	}

	/*
	 * parseClaimsJws - throws:UnsupportedJwtException -if the JWT body | payload
	 * does not represent any Claims JWSMalformedJwtException - if the JWT body |
	 * payload is not a valid JWSSignatureException - if the JWT signature
	 * validation fails ExpiredJwtException - if the specified JWT is expired
	 * IllegalArgumentException - if the JWT claims body | payload is null or empty
	 * or only whitespace
	 */
	public Claims validateJwtToken(String jwtToken) {
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
		return claims;
	}
	
	public boolean verifyOtp(VerifyOtpReq req) {

	    Claims claims = Jwts.parserBuilder()
	            .setSigningKey(key)
	            .build()
	            .parseClaimsJws(req.getOtpToken())
	            .getBody();

	    String tokenEmail = claims.getSubject();
	    String tokenOtp = (String) claims.get("otp");

	    if (!tokenEmail.equals(req.getEmail()))
	        throw new RuntimeException("Email mismatch");

	    return tokenOtp.equals(req.getOtp());
	}

	
	public boolean verifyOtp(VerifyOtpReq req) {

	    Claims claims = Jwts.parserBuilder()
	            .setSigningKey(key)
	            .build()
	            .parseClaimsJws(req.getOtpToken())
	            .getBody();

	    String tokenEmail = claims.getSubject();
	    String tokenOtp = (String) claims.get("otp");

	    if (!tokenEmail.equals(req.getEmail()))
	        throw new RuntimeException("Email mismatch");

	    return tokenOtp.equals(req.getOtp());
	}


	private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
		log.debug("Converting authorities to string...");

		String authorityString = authorities.stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		log.info("Authorities converted to string: {}", authorityString);
		return authorityString;
	}

	public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
		String authString = (String) claims.get("authorities");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
		authorities.forEach(System.out::println);
		return authorities;
	}

	public Long getUserIdFromJwtToken(Claims claims) {
		System.out.println("claims user id " + claims.get("user_id"));
		return Long.valueOf((int) claims.get("user_id"));
	}

	public Authentication populateAuthenticationTokenFromJWT(String jwt) {

		Claims payloadClaims = validateJwtToken(jwt);
		String email = getUserNameFromJwtToken(payloadClaims);
		List<GrantedAuthority> authorities = getAuthoritiesFromClaims(payloadClaims);
		Long userId = getUserIdFromJwtToken(payloadClaims);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, userId, authorities);
		System.out.println("is authenticated " + token.isAuthenticated());
		return token;

	}
	
	public String resetPassword(ResetPasswordReq req) {

	    Claims claims = Jwts.parserBuilder()
	            .setSigningKey(key)
	            .build()
	            .parseClaimsJws(req.getOtpToken())
	            .getBody();

	    if (!claims.getSubject().equals(req.getEmail()))
	        throw new RuntimeException("Email mismatch");
	    
	    Date expiration = claims.getExpiration();
	    if (expiration.before(new Date())) {
	        throw new RuntimeException("OTP token expired");
	    }

	    User user = userRepo.findByEmail(req.getEmail())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    user.setPassword(passwordEncoder.encode(req.getNewPassword()));
	    userRepo.save(user);

	    return "Password updated successfully";
	}


}
