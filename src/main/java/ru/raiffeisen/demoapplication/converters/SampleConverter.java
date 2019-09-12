package ru.raiffeisen.demoapplication.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.raiffeisen.demoapplication.dtos.SampleDto;
import ru.raiffeisen.demoapplication.model.SampleModel;

@Component
public class SampleConverter implements Converter<SampleModel, SampleDto> {
	
	@Override
	public SampleDto convert(SampleModel source) {
		SampleDto sampleDto = new SampleDto();
		sampleDto.setName(source.getName());
		return sampleDto;
	}
}
