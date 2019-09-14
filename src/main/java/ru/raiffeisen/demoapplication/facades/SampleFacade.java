package ru.raiffeisen.demoapplication.facades;

import com.raiffeisen.javahack.core.operation.OperationValueResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import ru.raiffeisen.demoapplication.dtos.SampleDto;
import ru.raiffeisen.demoapplication.services.SampleService;

@Component
public class SampleFacade {
	private static final Logger LOG = LoggerFactory.getLogger(SampleFacade.class);

	private ConversionService conversionService;
	private SampleService sampleService;

	public SampleFacade(ConversionService conversionService, SampleService sampleService) {
		this.conversionService = conversionService;
		this.sampleService = sampleService;
	}
	
	public OperationValueResult<SampleDto> getSample() {
		try {
			return OperationValueResult.Companion.success(conversionService.convert(sampleService.getSample(), SampleDto.class));
		} catch (ConversionException e) {
			LOG.error("An error occurred during conversion: ", e);

			String errorMessage = e.getMessage();
			if (errorMessage == null) {
				errorMessage = "Conversion error";
			}
			return OperationValueResult.Companion.failure(errorMessage);
		}
	}
}
