package com.path.imco.bo.integrationsettings.impl;

import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.dbmaps.vo.SYS_PARAM_SCREEN_DISPLAYVO;
import com.path.imco.bo.integrationsettings.IntegrationSettingsBO;
import com.path.imco.dao.integrationsettings.IntegrationSettingsDAO;
//import com.path.imco.dao.integrationsettings.impl.maps.IntegrationEventMgmtDAO;
import com.path.imco.vo.integrationsettings.IntegrationSettingsSC;
import com.path.imco.vo.integrationsettings.IntegrationSettingsCO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * IntegrationSettingsBOImpl.java used to
 */
public class IntegrationSettingsBOImpl extends BaseBO implements IntegrationSettingsBO
{
    IntegrationSettingsDAO integrationSettingsDAO;

    /**
     * Method used to return Count of IntegrationSettings
     * 
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws BaseException
     */
    public int returnIntegrationSettingsListCount(IntegrationSettingsSC criteria) throws BaseException
    {
	return integrationSettingsDAO.returnIntegrationSettingsListCount(criteria);
    }

    /**
     * Method used to return List of IntegrationSettings
     * 
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws BaseException
     */
    public List returnIntegrationSettingsList(IntegrationSettingsSC criteria) throws BaseException
    {
	return integrationSettingsDAO.returnIntegrationSettingsList(criteria);
    }

    public IntegrationSettingsDAO getIntegrationSettingsDAO()
    {
	return integrationSettingsDAO;
    }

    public void setIntegrationSettingsDAO(IntegrationSettingsDAO integrationSettingsDAO)
    {
	this.integrationSettingsDAO = integrationSettingsDAO;
    }

    @Override
    public int returnSyncBranchLookupCount(IntegrationSettingsSC integrationSettingsSC) throws BaseException
    {
	return integrationSettingsDAO.returnSyncBranchLookupCount(integrationSettingsSC);

    }

    @Override
    public List returnSyncBranchLookup(IntegrationSettingsSC integrationSettingsSC) throws BaseException
    {
	return integrationSettingsDAO.returnSyncBranchLookup(integrationSettingsSC);

    }

    @Override
    public IntegrationSettingsCO saveNew(IntegrationSettingsCO integrationSettingsCO) throws BaseException
    {
	// integrationSettingsCO.getSync_entity_parametersblobVO().setBR_CODE(BigDecimal.ZERO);
	// integrationSettingsCO.getSync_entity_parametersblobVO().setENTITY_CODE(BigDecimal.valueOf(89));
	/*
	 * integrationSettingsCO.getSync_entity_parametersblobVO().
	 * setENTITY_CODE(integrationSettingsCO.getSync_entity_parametersVO().
	 * getENTITY_CODE());
	 * integrationSettingsCO.getSync_entity_parametersblobVO().setBR_CODE(
	 * integrationSettingsCO.getSync_entity_parametersVO().getBR_CODE());
	 */
	// genericDAO.update(integrationSettingsCO.getSync_entity_parametersVO());
	int i = genericDAO.update(integrationSettingsCO.getSync_entity_parametersblobVO());
	integrationSettingsCO.getSync_entityVO()
		.setENTITY_CODE(integrationSettingsCO.getSync_entity_parametersblobVO().getENTITY_CODE());
	i = genericDAO.update(integrationSettingsCO.getSync_entityVO());
	return integrationSettingsCO;
    }

    @Override
    public IntegrationSettingsCO returnintegrationDetails(IntegrationSettingsSC integrationSettingsSC)
	    throws BaseException
    {

	return integrationSettingsDAO.returnintegrationDetails(integrationSettingsSC);
    }

}
