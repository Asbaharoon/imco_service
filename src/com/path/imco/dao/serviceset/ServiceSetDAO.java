package com.path.imco.dao.serviceset;

import java.util.List;

import com.path.imco.vo.integrationsettings.IntegrationSettingsCO;
import com.path.imco.vo.integrationsettings.IntegrationSettingsSC;
import com.path.imco.vo.serviceset.ServiceSetCO;
import com.path.imco.vo.serviceset.ServiceSetSC;
import com.path.lib.common.exception.DAOException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * ServiceSetDAO.java used to
 */
public interface ServiceSetDAO 
{

    public List returnServiceSettingsList(ServiceSetSC serviceSetSC) throws DAOException;	

    
   
}