package com.path.imco.dao.accesswebservice.impl;

import java.math.BigDecimal;
import java.util.List;

import com.path.dbmaps.vo.IMCO_PWS_CHANNELVO;
import com.path.imco.dao.accesswebservice.AccessWebServiceDAO;
import com.path.imco.vo.accesswebservice.AccessWebServiceCO;
import com.path.imco.vo.accesswebservice.AccessWebServiceSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;

/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * ChannelDAOImpl.java used to
 */
public class AccessWebServiceDAOImpl extends BaseDAO implements AccessWebServiceDAO
{
    
    @Override
    public int returnAccessWebServiceListCount(AccessWebServiceSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "accesswebserviceMapper.returnAccessWebServiceListMap");
	int nb = 0;
	nb = ((Integer) getSqlMap().queryForObject("accesswebserviceMapper.returnAccessWebServiceListCount", criteria))
		.intValue();
	return nb;
    }

    @Override
    public List<AccessWebServiceCO> returnAccessWebServiceList(AccessWebServiceSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "accesswebserviceMapper.returnAccessWebServiceListMap");
	if(criteria.getSortOrder() == null)
	{
	    criteria.setSortOrder(" ORDER BY TEMPLATE_ID");
	}
	if(criteria.getNbRec() == -1)
	{
	    return (List<AccessWebServiceCO>) getSqlMap().queryForList("accesswebserviceMapper.returnAccessWebServiceList", criteria);
	}
	else
	{
	    return (List<AccessWebServiceCO>) getSqlMap().queryForList("accesswebserviceMapper.returnAccessWebServiceList", criteria,
		    criteria.getRecToskip(), criteria.getNbRec());
	}
    }
    
    @Override
    public int returnUserIdListCount(AccessWebServiceSC accessWebServiceSC) throws DAOException
    {
	DAOHelper.fixGridMaps(accessWebServiceSC, getSqlMap(), "accesswebserviceMapper.resUserIdLkupMap");
	int nb = 0;
	nb = ((Integer) getSqlMap().queryForObject("accesswebserviceMapper.returnUserIdListCount", accessWebServiceSC)).intValue();
	return nb;
    }
    
    @Override
    public List<IMCO_PWS_CHANNELVO> returnUserIdList(AccessWebServiceSC accessWebServiceSC) throws DAOException
    {
	if(accessWebServiceSC.getSortOrder() == null)
	{
	    accessWebServiceSC.setSortOrder(" ORDER BY CHANNEL_ID");
	}
	DAOHelper.fixGridMaps(accessWebServiceSC, getSqlMap(), "accesswebserviceMapper.resUserIdLkupMap");
	return getSqlMap().queryForList("accesswebserviceMapper.returnUserIdList", accessWebServiceSC,
		accessWebServiceSC.getRecToskip(), accessWebServiceSC.getNbRec());
    }
    
    @Override
    public AccessWebServiceCO returnSelectedUserIdDetails(AccessWebServiceSC accessWebServiceSC) throws DAOException
    {
	return ((AccessWebServiceCO) getSqlMap().queryForObject("accesswebserviceMapper.returnSelectedUserIdDetails", accessWebServiceSC));
    }
    
    @Override
    public int returnTemplateIdCount(BigDecimal tempID) throws DAOException
    {
	int nb = 0;
	nb = ((Integer) getSqlMap().queryForObject("accesswebserviceMapper.returnTemplateIdCount", tempID)).intValue();
	return nb;
    }
    
    @Override
    public AccessWebServiceCO retrieveSelectedTemplateId(AccessWebServiceSC sc) throws DAOException
    {
	return ((AccessWebServiceCO) getSqlMap().queryForObject("accesswebserviceMapper.retrieveSelectedTemplateId", sc));
    }
    
    @Override
    public List<AccessWebServiceCO> returnServicesFromDb(AccessWebServiceSC accessWebServiceSC) throws DAOException
    {
	DAOHelper.fixGridMaps(accessWebServiceSC, getSqlMap(), "accesswebserviceMapper.returnServicesFromDbMap");
	return ((List<AccessWebServiceCO>) getSqlMap().queryForList("accesswebserviceMapper.returnServicesFromDb", accessWebServiceSC));
    }
    
    @Override
    public void deleteAllPreviousRecord(BigDecimal templateId) throws DAOException
    {
	getSqlMap().delete("accesswebserviceMapper.deleteAllPreviousRecord", templateId);
    }
    
    @Override
    public void deleteRecordDetParamFromDB(AccessWebServiceSC accessWebServiceSC) throws DAOException
    {
	getSqlMap().delete("accesswebserviceMapper.deleteRecordDetParamFromDB", accessWebServiceSC);
    }
    
    @Override
    public void deleteRecordDetFromDB(AccessWebServiceSC accessWebServiceSC) throws DAOException
    {
	getSqlMap().delete("accesswebserviceMapper.deleteRecordDetFromDB", accessWebServiceSC);
    }
    
    @Override
    public List<String> returnedParamCheckedList(AccessWebServiceSC sc) throws DAOException
    {
	return ((List<String>) getSqlMap().queryForList("accesswebserviceMapper.returnedParamCheckedList", sc));
    }
    
    @Override
    public List<BigDecimal> countTmpltUsrVO(BigDecimal tempID) throws DAOException
    {
	return (List<BigDecimal>) getSqlMap().queryForList("accesswebserviceMapper.countTmpltUsrVO", tempID);
    }
}
