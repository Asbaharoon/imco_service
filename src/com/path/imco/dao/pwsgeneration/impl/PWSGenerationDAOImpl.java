/**
 * PWSGenerationDAOImpl.java - Oct 24, 2018  
 *
 * Copyright 2018, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Raed Saad
 *
 */
package com.path.imco.dao.pwsgeneration.impl;

import java.util.List;

import com.path.dbmaps.vo.DGTL_GTW_WS_ADAPTERVO;
import com.path.imco.dao.pwsgeneration.PWSGenerationDAO;
import com.path.imco.vo.pwsgeneration.PWSGenerationCO;
import com.path.imco.vo.pwsgeneration.PWSGenerationSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;
import com.path.lib.common.util.StringUtil;

public class PWSGenerationDAOImpl extends BaseDAO implements PWSGenerationDAO{
	
	@Override
	public List<PWSGenerationCO> returnPWSGenerationAdaptersList(Object obj) throws DAOException {
		return getSqlMap().queryForList("pwsGenerationMapper.returnPWSGenerationAdaptersList", (PWSGenerationSC)obj);
	}

	@Override
	public Integer returnPWSGenerationAdapterListCount(Object obj) throws DAOException {
		return (Integer)getSqlMap().queryForObject("pwsGenerationMapper.returnPWSGenerationAdaptersListCount", (PWSGenerationSC)obj);
	}
	
	@Override
	public void saveAdapterData(Object obj) throws BaseException {
		getSqlMap().insert("pwsGenerationMapper.saveAdapterData", obj);
	}
	
	
	@Override
	public Object loadProcedureArguments(Object obj) throws DAOException 
	{
		return getSqlMap().queryForList("pwsGenerationMapper.returnProcedureParamsQuery", (PWSGenerationSC)obj);
	}
	
	@Override
	public Object returnSavedRecordsQuery(Object obj) throws DAOException {
		return getSqlMap().queryForList("pwsGenerationMapper.returnSavedRecordsQuery", obj);
	}


	@Override
	public Integer returnProcedureArgumentsCount(Object obj) throws DAOException {

		DAOHelper.fixGridMaps((PWSGenerationSC)obj, getSqlMap(), "pwsGenerationMapper.pwsGenerationMaintMap");
		//return (Integer) getSqlMap().queryForObject("pwsGenerationMapper.returnProcedureParamsQuery",(PWSGenerationSC)obj);
		return 10;
	}

	@Override
	public Integer checkIfProcedureExists(Object obj) throws DAOException {
		return (Integer) getSqlMap().queryForObject("pwsGenerationMapper.checkIfProcedureExist",(PWSGenerationCO)obj);
	}

//	@Override
//	public List<PWS_OPERATION_FIELDSVO> returnOperationFields(Object obj) throws DAOException {
//		return getSqlMap().queryForList("pwsGenerationMapper.returnOperationFields", (PWSGenerationCO)obj);
//	}

	@Override
	public void insertOperationData(Object obj) throws DAOException {
		getSqlMap().insert("pwsGenerationMapper.recordOperationData", (PWSGenerationCO)obj);
	}

	@Override
	public List<PWSGenerationCO> returnSavedProcedureArguments(Object obj) throws DAOException {
		return getSqlMap().queryForList("pwsGenerationMapper.returnSavedProcedureArguments", (PWSGenerationCO)obj);
	}

	@Override
	public PWSGenerationCO loadIRPConnection(Object obj) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PWSGenerationCO> loadIRPConnectionList(Object obj) throws DAOException {
		return getSqlMap().queryForList("pwsGenerationMapper.getIrpConnectionsList", (PWSGenerationSC)obj);
	}

	@Override
	public Integer returnIRPConnectionCount(Object obj) throws DAOException {
		return (Integer)getSqlMap().queryForObject("pwsGenerationMapper.getIrpConnectionsListCount", (PWSGenerationSC)obj);
	}

	@Override
	public Integer returnProcedureParamsCount(Object obj) throws DAOException {
		PWSGenerationSC pwsGenerationSC = (PWSGenerationSC) obj;
		DAOHelper.fixGridMaps(pwsGenerationSC, getSqlMap(), "pwsGenerationMapper.returnProcedureParams");
		return (Integer) getSqlMap().queryForObject("pwsGenerationMapper.returnProcedureParams",
				pwsGenerationSC);
	}
    
//	@Override
//	public List<PWS_OPERATIONVO> returnProcedureParams(Object obj) throws DAOException
//	{
//		PWSGenerationSC pwsGenerationSC = (PWSGenerationSC)obj;
//		if(StringUtil.nullToEmpty(pwsGenerationSC.getSidx()).isEmpty())
//		{
//		    pwsGenerationSC.setSord("DESC");
//		}
//		/*
//		 * set number of rows to be displayed in the page of grid, and the total
//		 * number of records
//		 */
//		DAOHelper.fixGridMaps(pwsGenerationSC, getSqlMap(), "pwsGenerationMapper.pwsGenerationOperations");
//
//		if(pwsGenerationSC.getNbRec() == -1)
//		{
//		    return getSqlMap().queryForList("pwsGenerationMapper.returnProcedureParams", pwsGenerationSC);
//		}
//		else
//		{
//		    return getSqlMap().queryForList("pwsGenerationMapper.returnProcedureParams", pwsGenerationSC,
//			    pwsGenerationSC.getRecToskip(), pwsGenerationSC.getNbRec());
//		}
//	 }

}
