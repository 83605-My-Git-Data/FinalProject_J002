package com.project.service;

import java.util.List;

import com.project.dto.ApiResponse;
import com.project.dto.ServicesDto;
import com.project.dto.ServicesRespDto;

public interface ServicesService {
	
	public ApiResponse addAService(ServicesDto servicesDto);
	public List<ServicesRespDto> getServiceForPhotographer(Long userId);

}
