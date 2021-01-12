package com.path.imco.bo.serviceset;

import java.util.List;

import com.path.imco.vo.integrationsettings.IntegrationSettingsCO;
import com.path.imco.vo.integrationsettings.IntegrationSettingsSC;
import com.path.imco.vo.serviceset.ServiceSetCO;
import com.path.imco.vo.serviceset.ServiceSetSC;
import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * ServiceSetBO.java used to
 */
public interface ServiceSetBO 
{

    public List returnServiceSettingsList(ServiceSetSC serviceSetSC) throws BaseException;

    public ServiceSetCO saveNew(ServiceSetCO serviceSetCO) throws BaseException;
}