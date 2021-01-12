package com.path.imco.dao.appsettings.impl;

import java.util.List;

import com.path.imco.dao.appsettings.AppSetDAO;
import com.path.imco.vo.appsettings.AppSetSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * AppSetDAOImpl.java used to
 */
public class AppSetDAOImpl extends BaseDAO implements AppSetDAO
{
    /**
     * Method used to return Count of AppSet
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws DAOException
     */
    @Override
    public int returnAppSetListCount(AppSetSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "appSetMapper.resAppSetListMap");
	return ((Integer) getSqlMap().queryForObject("appSetMapper.returnAppSetListCount", criteria)).intValue();
    }
    /**
     * Method used to return List of AppSet
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws DAOException
     */
    @Override
    public List returnAppSetList(AppSetSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "appSetMapper.resAppSetListMap");
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("appSetMapper.returnAppSetList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("appSetMapper.returnAppSetList", criteria, criteria.getRecToskip(),criteria.getNbRec());
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.dao.appsettings.AppSetDAO#returnGroupGridMapList(com.path.
     * imco.vo.appsettings.AppSetSC)
     */
    @Override
    public List returnGroupGridMapList(AppSetSC criteria) throws DAOException
    {

	return getSqlMap().queryForList("appSetMapper.returnGroupSetList", criteria);

    }
}
