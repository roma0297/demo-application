package ru.raiffeisen.demoapplication.repositories;

import org.springframework.stereotype.Repository;
import ru.raiffeisen.demoapplication.model.SampleModel;

@Repository
public class SampleRepository {
	
	public SampleModel getSample() {
		SampleModel sampleModel = new SampleModel();
		
		sampleModel.setId(12);
		sampleModel.setName("Roman");
		
		return sampleModel;
	}
}
