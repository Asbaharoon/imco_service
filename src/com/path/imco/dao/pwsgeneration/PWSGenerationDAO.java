/**
 * PWSGenerationDAO.java - Oct 24, 2018  
 *
 * Copyright 2018, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Raed Saad
 *
 */
package com.path.imco.dao.pwsgeneration;

import java.util.List;

import com.path.imco.vo.pwsgeneration.PWSGenerationCO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;

public interface PWSGenerationDAO{
	public List<PWSGenerationCO> returnPWSGenerationAdaptersList(Object obj) throws DAOException;
	public Integer returnPWSGenerationAdapterListCount(Object obj) throws DAOException;
	
	public void saveAdapterData(Object obj) throws BaseException;

	public Object returnSavedRecordsQuery(Object obj) throws DAOException;

	public Integer returnProcedureArgumentsCount(Object obj) throws DAOException;
	public Object loadProcedureArguments(Object obj) throws DAOException;
	public Integer checkIfProcedureExists(Object obj) throws DAOException;
	public List<PWSGenerationCO> returnSavedProcedureArguments(Object obj) throws DAOException; 
//	public List<PWS_OPERATION_FIELDSVO> returnOperationFields(Object obj) throws DAOException;
	public void insertOperationData(Object obj) throws DAOException;
	public Integer returnIRPConnectionCount(Object obj) throws DAOException;
	public PWSGenerationCO loadIRPConnection(Object obj) throws DAOException;
	public List<PWSGenerationCO> loadIRPConnectionList(Object obj) throws DAOException;
//    public List<PWS_OPERATIONVO> returnProcedureParams(Object obj) throws DAOException;
    public Integer returnProcedureParamsCount(Object obj) throws DAOException;
}