package com.path.imco.dao.serviceset.impl;

import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;

import java.util.List;

import com.path.imco.dao.serviceset.ServiceSetDAO;
import com.path.imco.vo.integrationsettings.IntegrationSettingsCO;
import com.path.imco.vo.serviceset.ServiceSetCO;
import com.path.imco.vo.serviceset.ServiceSetSC;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * ServiceSetDAOImpl.java used to
 */
public class ServiceSetDAOImpl extends BaseDAO implements ServiceSetDAO
{

    @Override
    public List returnServiceSettingsList(ServiceSetSC serviceSetSC) throws DAOException
    {
	DAOHelper.fixGridMaps(serviceSetSC, getSqlMap(), "serviceSetMapper.resServiceSettingsMap");
//	if(serviceSetSC.getNbRec() == -1)
//	{
	    return getSqlMap().queryForList("serviceSetMapper.returnServiceSettingsList", serviceSetSC);
//	}
//	else
//	{
//	    return getSqlMap().queryForList("serviceSetMapper.returnServiceSettingsList", serviceSetSC, serviceSetSC.getRecToskip(),serviceSetSC.getNbRec());
//	}
    }
}
