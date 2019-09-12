package ru.raiffeisen.demoapplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.demoapplication.dtos.SampleDto;
import ru.raiffeisen.demoapplication.facades.SampleFacade;

@RestController
public class SampleController {
	private SampleFacade sampleFacade;

	public SampleController(SampleFacade sampleFacade) {
		this.sampleFacade = sampleFacade;
	}
	
	@GetMapping("/sample")
	public SampleDto getSample() {
		return sampleFacade.getSample();
	}
}
