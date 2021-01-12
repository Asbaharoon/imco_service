package com.path.imco.dao.newapi;

import java.math.BigDecimal;
import java.util.List;

import com.path.imco.vo.newapi.NewApiCO;
import com.path.imco.vo.newapi.NewApiSC;
import com.path.lib.common.exception.DAOException;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * NewApiDAO.java used to
 */
public interface NewApiDAO
{
    public int returnNewApiListCount(NewApiSC criteria) throws DAOException;

    public List returnNewApiList(NewApiSC criteria) throws DAOException;

    public NewApiCO returnApiDetails(NewApiSC newapiSC) throws DAOException;

    public List returnUsersGridList(NewApiSC criteria) throws DAOException;

    public Integer returnUsersGridCount(NewApiSC criteria) throws DAOException;

    /**
     * @param imApiUsersVO
     */
    public void deleteGridData(NewApiSC criteria) throws DAOException;

    /**
     * @param criteria
     * @return
     */
    public Integer returnMachGridCount(NewApiSC criteria) throws DAOException;

    /**
     * @param criteria
     * @return
     */
    public List returnMachineGridList(NewApiSC criteria) throws DAOException;

    /**
     * @param criteria
     */
    public void deleteMachineGridData(NewApiSC criteria) throws DAOException;

    /**
     * @param criteria
     */
    public void deleteArgumentsGridData(NewApiSC criteria) throws DAOException;

    /**
     * @param criteria
     * @return
     * @throws DAOException
     */
    public Integer returnArgumentsGridCount(NewApiSC criteria) throws DAOException;

    /**
     * @param criteria
     * @return
     * @throws DAOException
     */
    public List returnArgumentsGridList(NewApiSC criteria) throws DAOException;
    
    /**
     * @param newapiCO
     * @return
     * @throws DAOException
     */
    public List returnNewApiParams(NewApiCO newapiCO) throws DAOException;
    
    /**
     * @param
     * @return
     * @throws DAOException
     */
    public String returnSchemaName()throws DAOException;
    
    /**
     * @param newapiCO
     * @return
     * @throws DAOException
     */
    public BigDecimal getNewDynApiCode(NewApiCO newapiCO) throws DAOException;

}