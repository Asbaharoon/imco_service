/**
 * PWSGenerationBO.java - Oct 17, 2018  
 *
 * Copyright 2018, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Raed Saad
 * 
 *User Story #740995 PWS generation From DB Procedure -screen
 */
package com.path.imco.bo.pwsgeneration;

import java.util.List;

import com.path.imco.vo.pwsgeneration.PWSGenerationCO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;

public interface PWSGenerationBO {
	
	public List<PWSGenerationCO> returnPWSGenerationAdaptersList(Object obj) throws BaseException;
	public Integer returnPWSGenerationAdapterListCount(Object obj) throws BaseException;	
	public void saveAdapterData(Object obj) throws BaseException;

	public Integer returnProcedureArgumentsCount(Object obj) throws BaseException;
	public List<PWSGenerationCO> loadProcedureArguments(Object obj) throws BaseException;
	public Integer checkIfProcedureExists(Object obj) throws BaseException;
	public void validateProcedure(Object obj) throws BaseException;
	
	
	public Object returnSavedRecordsQuery(Object obj) throws BaseException;
	public List<PWSGenerationCO> returnSavedProcedureArguments(Object obj) throws BaseException; 
	//public List<PWS_OPERATION_FIELDSVO> returnOperationFields(Object obj) throws BaseException;
	public void insertOperationData(Object obj) throws BaseException;
	
	public void saveRecord(Object obj) throws BaseException;
	public void approveRecord(Object obj) throws BaseException;
	public void updateAfterApproveRecord(Object obj) throws BaseException;
	public void deploy(Object obj) throws BaseException;
	
	public PWSGenerationCO loadPWSRecord(Object obj) throws BaseException;
	public Integer returnIRPConnectionCount(Object obj) throws BaseException;
	public PWSGenerationCO loadIRPConnection(Object obj) throws BaseException;
	public List<PWSGenerationCO>loadIRPConnectionList(Object obj) throws BaseException;
	
   // public List<PWS_OPERATIONVO> returnProcedureParams(Object obj) throws BaseException;
    public Integer returnProcedureParamsCount(Object obj) throws BaseException;
	public void uploadBPEL(PWSGenerationCO pwsGenerationCO) throws BOException;
	
	public String applyScreenVisiblity(PWSGenerationCO pwsGenerationCO) throws BOException;
	
	public void generateWsdlAdapter(Object obj) throws BOException;
	
	

}
