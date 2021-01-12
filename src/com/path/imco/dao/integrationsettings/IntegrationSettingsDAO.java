package com.path.imco.dao.integrationsettings;

import com.path.lib.common.exception.DAOException;
import com.path.imco.vo.integrationsettings.IntegrationSettingsSC;
import com.path.imco.vo.integrationsettings.IntegrationSettingsCO;
import java.util.List;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * IntegrationSettingsDAO.java used to
 */
public interface IntegrationSettingsDAO 
{
	public int returnIntegrationSettingsListCount(IntegrationSettingsSC criteria) throws DAOException;
	public List returnIntegrationSettingsList(IntegrationSettingsSC criteria) throws DAOException;
	public int returnSyncBranchLookupCount(IntegrationSettingsSC integrationSettingsSC) throws DAOException;
	public List returnSyncBranchLookup(IntegrationSettingsSC integrationSettingsSC) throws DAOException;
	public IntegrationSettingsCO returnintegrationDetails(IntegrationSettingsSC integrationSettingsSC) throws DAOException;			
}
