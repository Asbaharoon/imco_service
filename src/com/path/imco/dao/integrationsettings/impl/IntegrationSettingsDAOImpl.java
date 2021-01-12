package com.path.imco.dao.integrationsettings.impl;

import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.dbmaps.vo.SYNC_BRANCHVO;
import com.path.imco.dao.integrationsettings.IntegrationSettingsDAO;
import com.path.imco.vo.integrationsettings.IntegrationSettingsSC;
import com.path.imco.vo.integrationsettings.IntegrationSettingsCO;
import com.path.lib.common.util.DAOHelper;
import com.path.struts2.lib.common.GridParamsSC;

import java.util.List;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * IntegrationSettingsDAOImpl.java used to
 */
public class IntegrationSettingsDAOImpl extends BaseDAO implements IntegrationSettingsDAO
{

    public int returnIntegrationSettingsListCount(IntegrationSettingsSC criteria) throws DAOException
    {		
	return ((Integer) getSqlMap().queryForObject("integrationSettingsMapper.returnIntegrationSettingsListCount", criteria)).intValue();
    }
    /**
     * Method used to return List of IntegrationSettings
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws DAOException
     */
    public List returnIntegrationSettingsList(IntegrationSettingsSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "integrationSettingsMapper.resIntegrationSettingsListMap");
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("integrationSettingsMapper.returnIntegrationSettingsList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("integrationSettingsMapper.returnIntegrationSettingsList", criteria, criteria.getRecToskip(),criteria.getNbRec());
	}
    }
    @Override
    public int returnSyncBranchLookupCount(IntegrationSettingsSC integrationSettingsSC) throws DAOException
    {
	{
	    DAOHelper.fixGridMaps(integrationSettingsSC, getSqlMap(), "integrationSettingsMapper.resSyncBranchLookupMap");
	    return ((Integer) getSqlMap().queryForObject("integrationSettingsMapper.returnSyncBranchLookupCount", integrationSettingsSC)).intValue();
	}
    }
    @Override
    public List<SYNC_BRANCHVO> returnSyncBranchLookup(IntegrationSettingsSC integrationSettingsSC) throws DAOException
    {
	DAOHelper.fixGridMaps(integrationSettingsSC, getSqlMap(), "integrationSettingsMapper.resSyncBranchLookupMap");
	if(integrationSettingsSC.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("integrationSettingsMapper.returnSyncBranchLookup", integrationSettingsSC);
	}
	else
	{
	    return getSqlMap().queryForList("integrationSettingsMapper.returnSyncBranchLookup",integrationSettingsSC, integrationSettingsSC.getRecToskip(),integrationSettingsSC.getNbRec());
	}
    }
    @Override
    public IntegrationSettingsCO returnintegrationDetails(IntegrationSettingsSC integrationSettingsSC) throws DAOException
    {
	return ((IntegrationSettingsCO) getSqlMap().queryForObject("integrationSettingsMapper.returnintegrationDetails",integrationSettingsSC));	
    }

}

