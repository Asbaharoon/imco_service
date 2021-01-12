package com.path.imco.dao.entities.impl;

import java.util.List;

import com.path.imco.dao.entities.EntitiesDAO;
import com.path.imco.vo.entities.EntitiesCO;
import com.path.imco.vo.entities.EntitiesSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;
/**
 *
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 *
 * EntitiesDAOImpl.java used to
 */
public class EntitiesDAOImpl extends BaseDAO implements EntitiesDAO
{
    /**
     * Method used to return Count of Entities
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws DAOException
     */
    @Override
    public int returnEntitiesListCount(EntitiesSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "entitiesMapper.resEntitiesListMap");
	return ((Integer) getSqlMap().queryForObject("entitiesMapper.returnEntitiesListCount", criteria)).intValue();
    }
    /**
     * Method used to return List of Entities
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws DAOException
     */
    @Override
    public List returnEntitiesList(EntitiesSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "entitiesMapper.resEntitiesListMap");
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("entitiesMapper.returnEntitiesList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("entitiesMapper.returnEntitiesList", criteria, criteria.getRecToskip(),criteria.getNbRec());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.entities.EntitiesDAO#returnEntitiesDetails(com.path.
     * imco.vo.entities.EntitiesSC)
     */
    @Override
    public EntitiesCO returnEntitiesDetails(EntitiesSC entitiesSC) throws DAOException
    {
	return (EntitiesCO) getSqlMap().queryForObject("entitiesMapper.returnEntitiesDetails", entitiesSC);

    }
}
