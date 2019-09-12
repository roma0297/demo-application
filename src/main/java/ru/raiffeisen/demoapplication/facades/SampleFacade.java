package ru.raiffeisen.demoapplication.facades;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import ru.raiffeisen.demoapplication.dtos.SampleDto;
import ru.raiffeisen.demoapplication.services.SampleService;

@Component
public class SampleFacade {
	private ConversionService conversionService;
	private SampleService sampleService;

	public SampleFacade(ConversionService conversionService, SampleService sampleService) {
		this.conversionService = conversionService;
		this.sampleService = sampleService;
	}
	
	public SampleDto getSample() {
		return conversionService.convert(sampleService.getSample(), SampleDto.class);
	}
}
