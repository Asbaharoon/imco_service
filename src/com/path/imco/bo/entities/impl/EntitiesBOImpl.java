package com.path.imco.bo.entities.impl;

import java.util.List;

import com.path.dbmaps.vo.SYNC_ENTITYVO;
import com.path.imco.bo.entities.EntitiesBO;
import com.path.imco.dao.entities.EntitiesDAO;
import com.path.imco.vo.entities.EntitiesCO;
import com.path.imco.vo.entities.EntitiesSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.NumberUtil;
/**
 *
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 *
 * EntitiesBOImpl.java used to
 */
public class EntitiesBOImpl extends BaseBO implements EntitiesBO
{
    EntitiesDAO entitiesDAO;
    /**
     * Method used to return Count of Entities
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws BaseException
     */
    @Override
    public int returnEntitiesListCount(EntitiesSC criteria) throws BaseException
    {
	return entitiesDAO.returnEntitiesListCount(criteria);
    }
    /**
     * Method used to return List of Entities
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws BaseException
     */
    @Override
    public List returnEntitiesList(EntitiesSC criteria) throws BaseException
    {
	return entitiesDAO.returnEntitiesList(criteria);
    }
    public EntitiesDAO getEntitiesDAO()
    {
	return entitiesDAO;
    }
    public void setEntitiesDAO(EntitiesDAO entitiesDAO)
    {
	this.entitiesDAO = entitiesDAO;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.bo.entities.EntitiesBO#returnEntitiesDetails(com.path.imco.
     * vo.entities.EntitiesCO)
     */


    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.bo.entities.EntitiesBO#saveNew(com.path.imco.vo.entities.
     * EntitiesCO)
     */
    @Override
    public EntitiesCO saveNew(EntitiesCO entitiesCO) throws BaseException
    {
    	
		SYNC_ENTITYVO syncEntityVO = entitiesCO.getSyncEntityVO();
		if(NumberUtil.isEmptyDecimal(syncEntityVO.getENTITY_CODE()))
		{
		    throw new BaseException("please_specify_entity_code_key");
		}
		SYNC_ENTITYVO oldSYNC_ENTITYVO = (SYNC_ENTITYVO)genericDAO.selectByPK(entitiesCO.getSyncEntityVO());
		genericDAO.update(syncEntityVO);
		auditBO.callAudit(oldSYNC_ENTITYVO, syncEntityVO,entitiesCO.getAuditRefCO());
		auditBO.insertAudit(entitiesCO.getAuditRefCO());
		return entitiesCO;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.bo.entities.EntitiesBO#returnEntitiesDetails(com.path.imco.
     * vo.entities.EntitiesSC)
     */
    @Override
    public EntitiesCO returnEntitiesDetails(EntitiesSC entitiesSC) throws BaseException
    {
	return entitiesDAO.returnEntitiesDetails(entitiesSC);
    }
}
