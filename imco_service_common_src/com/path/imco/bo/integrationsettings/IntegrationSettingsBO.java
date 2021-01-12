package com.path.imco.bo.integrationsettings;

import com.path.lib.common.exception.BaseException;
import com.path.imco.vo.integrationsettings.IntegrationSettingsSC;
import com.path.imco.vo.integrationsettings.IntegrationSettingsCO;
import java.util.List;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * IntegrationSettingsBO.java used to
 */
public interface IntegrationSettingsBO 
{
	public int returnIntegrationSettingsListCount(IntegrationSettingsSC integrationSettingsSC) throws BaseException;
	public List returnIntegrationSettingsList(IntegrationSettingsSC criteria) throws BaseException;
	public int returnSyncBranchLookupCount(IntegrationSettingsSC integrationSettingsSC) throws BaseException;
	public List returnSyncBranchLookup(IntegrationSettingsSC integrationSettingsSC) throws BaseException;
	public IntegrationSettingsCO saveNew(IntegrationSettingsCO integrationSettingsCO) throws BaseException;
	public IntegrationSettingsCO returnintegrationDetails(IntegrationSettingsSC integrationSettingsSC) throws BaseException;	  	 
	}
