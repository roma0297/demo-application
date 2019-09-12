package ru.raiffeisen.demoapplication.services;

import org.springframework.stereotype.Service;
import ru.raiffeisen.demoapplication.model.SampleModel;
import ru.raiffeisen.demoapplication.repositories.SampleRepository;

@Service
public class SampleService {
	private SampleRepository sampleRepository;

	public SampleService(SampleRepository sampleRepository) {
		this.sampleRepository = sampleRepository;
	}
	
	public SampleModel getSample() {
		return sampleRepository.getSample();
	}
}
