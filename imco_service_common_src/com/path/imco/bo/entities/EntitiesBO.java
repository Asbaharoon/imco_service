package com.path.imco.bo.entities;

import java.util.List;

import com.path.imco.vo.entities.EntitiesCO;
import com.path.imco.vo.entities.EntitiesSC;
import com.path.lib.common.exception.BaseException;
/**
 *
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 *
 * EntitiesBO.java used to
 */
public interface EntitiesBO
{
    public int returnEntitiesListCount(EntitiesSC criteria) throws BaseException;

    public List returnEntitiesList(EntitiesSC criteria) throws BaseException;

    public EntitiesCO returnEntitiesDetails(EntitiesSC entitiesSC) throws BaseException;

    public EntitiesCO saveNew(EntitiesCO entitiesCO) throws BaseException;


}
