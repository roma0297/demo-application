package ru.raiffeisen.demoapplication.configuration;

import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConversionConfiguration implements WebMvcConfigurer {

	private List<Converter> converters;

	public ConversionConfiguration(List<Converter> converters) {
		this.converters = converters;
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		CollectionUtils.emptyIfNull(converters).forEach(registry::addConverter);
	}
}
