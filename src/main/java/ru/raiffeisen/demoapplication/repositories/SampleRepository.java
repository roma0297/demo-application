package ru.raiffeisen.demoapplication.repositories;

import ru.raiffeisen.demoapplication.common.operation.OperationValueResult;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.demoapplication.model.SampleModel;

@Repository
public class SampleRepository {
	
	public OperationValueResult<SampleModel> getSample() {
		SampleModel sampleModel = new SampleModel();
		
		sampleModel.setId(12);
		sampleModel.setName("Roman");
		
		return OperationValueResult.Companion.success(sampleModel);
	}
}
