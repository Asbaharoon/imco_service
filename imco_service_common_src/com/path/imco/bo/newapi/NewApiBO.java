package com.path.imco.bo.newapi;

import java.util.List;

import com.path.imco.vo.newapi.NewApiCO;
import com.path.imco.vo.newapi.NewApiSC;
import com.path.lib.common.exception.BaseException;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * NewApiBO.java used to
 */
public interface NewApiBO
{
    public int returnNewApiListCount(NewApiSC criteria) throws BaseException;

    public List returnNewApiList(NewApiSC criteria) throws BaseException;

    // public NewApiCO initApiDetails(NewApiCO newapiCO)throws BaseException;
    public NewApiCO saveNew(NewApiCO newapiCO) throws BaseException;

    public NewApiCO deleteApi(NewApiCO newapiCO) throws BaseException;

    public NewApiCO returnApiDetails(NewApiCO newapiCO) throws BaseException;

    public NewApiCO onChangeApiID(NewApiCO newapiCO) throws BaseException;

    public Integer returnUsersGridCount(NewApiSC criteria) throws BaseException;

    public List returnUsersGridList(NewApiSC criteria) throws BaseException;

    /**
     * @param criteria
     * @return
     * @throws BaseException
     */
    public List returnMachineGridList(NewApiSC criteria) throws BaseException;

    /**
     * @param criteria
     * @return
     * @throws BaseException
     */
    public Integer returnMachGridCount(NewApiSC criteria) throws BaseException;

    /**
     * @param criteria
     * @return
     * @throws BaseException
     */
    public Integer returnArgumentsGridCount(NewApiSC criteria) throws BaseException;

    /**
     * @param criteria
     * @return
     * @throws BaseException
     */
    public List returnArgumentsGridList(NewApiSC criteria) throws BaseException;
    
    /**
     * @param newapiCO
     * @return
     * @throws BaseException
     */
    public List returnNewApiParams(NewApiCO newapiCO)throws BaseException;

}