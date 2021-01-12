package com.path.imco.dao.entities;

import java.util.List;

import com.path.imco.vo.entities.EntitiesCO;
import com.path.imco.vo.entities.EntitiesSC;
import com.path.lib.common.exception.DAOException;
/**
 *
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 *
 * EntitiesDAO.java used to
 */
public interface EntitiesDAO
{
    public int returnEntitiesListCount(EntitiesSC criteria) throws DAOException;

    public List returnEntitiesList(EntitiesSC criteria) throws DAOException;

    public EntitiesCO returnEntitiesDetails(EntitiesSC entitiesSC) throws DAOException;

}