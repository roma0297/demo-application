package ru.raiffeisen.demoapplication.controllers;

import com.raiffeisen.javahack.core.operation.OperationValueResult;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<SampleDto> getSample() {
		OperationValueResult<SampleDto> result = sampleFacade.getSample();

		if (result.isSuccess()) {
			return ResponseEntity.ok(result.getValue());
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
