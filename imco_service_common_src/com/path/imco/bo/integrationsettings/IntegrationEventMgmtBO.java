package com.path.imco.bo.integrationsettings;

import com.path.imco.vo.integrationsettings.IntegrationSettingsCO;
import com.path.imco.vo.integrationsettings.IntegrationSettingsSC;
import com.path.lib.common.exception.BaseException;
import java.util.List;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * IntegrationEventMgmtBO.java used to
 */
public interface IntegrationEventMgmtBO 
{
	
	public IntegrationSettingsCO onChangeCode(IntegrationSettingsCO integrationSettingsCO) throws BaseException;

	
}