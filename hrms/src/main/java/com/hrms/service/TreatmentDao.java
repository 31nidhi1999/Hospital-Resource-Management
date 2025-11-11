package com.hrms.service;

import java.util.List;

import com.hrms.dto.req.TreatmentReqDto;
import com.hrms.dto.res.TreatmentResDto;

public interface TreatmentDao {
	TreatmentResDto createTreatment(TreatmentReqDto dto);
    TreatmentResDto getTreatmentById(Long id);
    List<TreatmentResDto> getAllTreatments();
    TreatmentResDto updateTreatment(Long id, TreatmentReqDto dto);
    void deleteTreatment(Long id);
}
