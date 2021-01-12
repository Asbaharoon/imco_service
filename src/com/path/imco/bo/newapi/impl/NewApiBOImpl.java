package com.path.imco.bo.newapi.impl;

import java.math.BigDecimal;
import java.util.List;

import com.path.bo.common.ConstantsCommon;
import com.path.dbmaps.vo.IM_IMAL_APIVO;
import com.path.imco.bo.newapi.NewApiBO;
import com.path.imco.dao.newapi.NewApiDAO;
import com.path.imco.vo.newapi.NewApiCO;
import com.path.imco.vo.newapi.NewApiSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.StringUtil;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * NewApiBOImpl.java used to
 */

public class NewApiBOImpl extends BaseBO implements NewApiBO
{
    NewApiDAO newApiDAO;

    /**
     * Method used to return Count of NewApi
     *
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws BaseException
     */

    @Override
    public int returnNewApiListCount(NewApiSC criteria) throws BaseException
    {
	return newApiDAO.returnNewApiListCount(criteria);
    }

    /**
     * Method used to return List of NewApi
     *
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws BaseException
     */
    @Override
    public List returnNewApiList(NewApiSC criteria) throws BaseException
    {
	return newApiDAO.returnNewApiList(criteria);
    }

    public NewApiDAO getNewApiDAO()
    {
	return newApiDAO;
    }

    public void setNewApiDAO(NewApiDAO newApiDAO)
    {
	this.newApiDAO = newApiDAO;
    }

    @Override
    public NewApiCO returnApiDetails(NewApiCO newapiCO) throws BaseException
    {

	if(newapiCO != null && newapiCO.getImImalApiVO() != null
		&& !NumberUtil.isEmptyDecimal(newapiCO.getImImalApiVO().getAPI_CODE()))
	{
	    NewApiSC newapiSC = new NewApiSC();
	    newapiSC.setApiCode(newapiCO.getImImalApiVO().getAPI_CODE());
	    return newApiDAO.returnApiDetails(newapiSC);

	}

	return newapiCO;

    }

    @Override
    public NewApiCO saveNew(NewApiCO newapiCO) throws BaseException
    {
	IM_IMAL_APIVO imImalApiVO = newapiCO.getImImalApiVO();

	// IM_IMAL_APIVO newapiVO = (IM_IMAL_APIVO)
	// genericDAO.selectByPK(imImalApiVO);
	if(ConstantsCommon.YES.equals(newapiCO.getUpdateMode()))
	{
	    genericDAO.update(imImalApiVO);
	    NewApiCO auditObj = (NewApiCO) newapiCO.getAuditObj();
	   // auditBO.callAudit(auditObj.getImImalApiVO(), imImalApiVO, newapiCO.getAuditRefCO());
	}
	else
	{
		BigDecimal maxApiCode = newApiDAO.getNewDynApiCode(newapiCO);
		imImalApiVO.setAPI_CODE(maxApiCode);
	    genericDAO.insert(imImalApiVO);
	    //auditBO.callAudit(null, imImalApiVO, newapiCO.getAuditRefCO());
	}
	NewApiSC criteria = new NewApiSC();
	criteria.setApiCode(imImalApiVO.getAPI_CODE());
	newApiDAO.deleteArgumentsGridData(criteria);

	int i=1;
	for(NewApiCO newApiCoLocal : newapiCO.getArgumentsList())
	{
	    newApiCoLocal.getImApiArgumentVO().setAPI_CODE(imImalApiVO.getAPI_CODE());
	    newApiCoLocal.getImApiArgumentVO().setARG_ID(new BigDecimal(i++));
	    // genericDAO.delete(newApiCoLocal.getImApiUsersVO());
	    genericDAO.insert(newApiCoLocal.getImApiArgumentVO());
	}

	//auditBO.insertAudit(newapiCO.getAuditRefCO());
	return newapiCO;
    }

    @Override
    public NewApiCO deleteApi(NewApiCO newapiCO) throws BaseException
    {
	IM_IMAL_APIVO imImalApiVO = newapiCO.getImImalApiVO();
	if(NumberUtil.isEmptyDecimal(imImalApiVO.getAPI_CODE()))
	{
	    throw new BOException("please_select_api_code_key");
	}

	NewApiSC criteria = new NewApiSC();
	criteria.setApiCode(imImalApiVO.getAPI_CODE());
	newApiDAO.deleteArgumentsGridData(criteria);
	
	genericDAO.delete(imImalApiVO);
	
	return newapiCO;
    }

    @Override
    public NewApiCO onChangeApiID(NewApiCO newapiCO) throws BaseException
    {
	IM_IMAL_APIVO imImalApiVO = newapiCO.getImImalApiVO();

	IM_IMAL_APIVO channelVO = (IM_IMAL_APIVO) genericDAO.selectByPK(imImalApiVO);
	if(channelVO != null)
	{
	    throw new BOException("cannot_proceed_api_id_already_defined_key");
	}
	return newapiCO;
    }

    @Override
    public Integer returnUsersGridCount(NewApiSC criteria) throws BaseException
    {
	return newApiDAO.returnUsersGridCount(criteria);
    }

    @Override
    public List returnUsersGridList(NewApiSC criteria) throws BaseException
    {
	return newApiDAO.returnUsersGridList(criteria);
    }

    @Override
    public Integer returnMachGridCount(NewApiSC criteria) throws BaseException
    {
	return newApiDAO.returnMachGridCount(criteria);
    }

    @Override
    public List returnMachineGridList(NewApiSC criteria) throws BaseException
    {
	return newApiDAO.returnMachineGridList(criteria);
    }

    @Override
    public Integer returnArgumentsGridCount(NewApiSC criteria) throws BaseException
    {
	return newApiDAO.returnArgumentsGridCount(criteria);
    }

    @Override
    public List returnArgumentsGridList(NewApiSC criteria) throws BaseException
    {
	return newApiDAO.returnArgumentsGridList(criteria);
    }
    
    @Override
    public List returnNewApiParams(NewApiCO newapiCO) throws BaseException
    {
		String schemaName = newApiDAO.returnSchemaName();
		
		if( StringUtil.nullToEmpty(schemaName).isEmpty())
		{
			throw new BOException("Schema name is null");
		}
		
		newapiCO.setSchemaName(schemaName);
		
		return newApiDAO.returnNewApiParams(newapiCO);
    }

}
