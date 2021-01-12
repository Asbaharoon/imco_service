package com.path.imco.bo.appsettings.impl;

import java.util.List;

import com.path.imco.bo.appsettings.AppSetBO;
import com.path.imco.dao.appsettings.AppSetDAO;
import com.path.imco.vo.appsettings.AppSetCO;
import com.path.imco.vo.appsettings.AppSetSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * AppSetBOImpl.java used to
 */
public class AppSetBOImpl extends BaseBO implements AppSetBO
{
    AppSetDAO appSetDAO;
    /**
     * Method used to return Count of AppSet
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws BaseException
     */
    @Override
    public int returnAppSetListCount(AppSetSC criteria) throws BaseException
    {
	return appSetDAO.returnAppSetListCount(criteria);
    }
    /**
     * Method used to return List of AppSet
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws BaseException
     */
    @Override
    public List returnAppSetList(AppSetSC criteria) throws BaseException
    {
	return appSetDAO.returnAppSetList(criteria);
    }
    public AppSetDAO getAppSetDAO()
    {
	return appSetDAO;
    }
    public void setAppSetDAO(AppSetDAO appSetDAO)
    {
	this.appSetDAO = appSetDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.bo.appsettings.AppSetBO#returnGroupGridMapList(com.path.
     * imco.vo.appsettings.AppSetSC)
     */
    @Override
    public List returnGroupGridMapList(AppSetSC criteria) throws BaseException
    {
	return appSetDAO.returnGroupGridMapList(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.bo.appsettings.AppSetBO#saveGroupData(com.path.imco.vo.
     * appsettings.AppSetCO)
     */
    @Override
    public AppSetCO saveGroupData(AppSetCO appsetCO) throws BaseException
    {

	for(AppSetCO appsetgroupCO : appsetCO.getAppGroupGridList())
	{

	    // genericDAO.delete(appsetgroupCO.getSyncintlevelVO());
	    Integer update = genericDAO.update(appsetgroupCO.getSyncintlevelVO());
	    // System.out.println("Test :" + update);

	    // genericDAO.delete(newApiCoLocal.getImApiUsersVO());
	    // genericDAO.insert(fixmapLocal.getSyncfixedmappingVO());
	}
	return appsetCO;
    }
}
