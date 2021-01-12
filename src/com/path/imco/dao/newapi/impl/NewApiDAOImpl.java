package com.path.imco.dao.newapi.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.path.bo.common.ConstantsCommon;
import com.path.imco.dao.newapi.NewApiDAO;
import com.path.imco.vo.newapi.NewApiCO;
import com.path.imco.vo.newapi.NewApiSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * NewApiDAOImpl.java used to
 */
public class NewApiDAOImpl extends BaseDAO implements NewApiDAO
{
    /**
     * Method used to return Count of NewApi
     *
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws DAOException
     */
    @Override
    public int returnNewApiListCount(NewApiSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "newApiMapper.resNewApiListMap");
	return ((Integer) getSqlMap().queryForObject("newApiMapper.returnNewApiListCount", criteria)).intValue();
    }

    /**
     * Method used to return List of NewApi
     *
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws DAOException
     */
    @Override
    public List returnNewApiList(NewApiSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "newApiMapper.resNewApiListMap");
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("newApiMapper.returnNewApiList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("newApiMapper.returnNewApiList", criteria, criteria.getRecToskip(),
		    criteria.getNbRec());
	}
    }

    @Override
    public NewApiCO returnApiDetails(NewApiSC newapiSC) throws DAOException
    {
	return (NewApiCO) getSqlMap().queryForObject("newApiMapper.returnApiDetails", newapiSC);
    }

    @Override
    public List returnUsersGridList(NewApiSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "newApiMapper.resUsersGridMap");
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("newApiMapper.returnUsersGridList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("newApiMapper.returnUsersGridList", criteria, criteria.getRecToskip(),
		    criteria.getNbRec());
	}
    }

    @Override
    public Integer returnUsersGridCount(NewApiSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "newApiMapper.resUsersGridMap");
	return ((Integer) getSqlMap().queryForObject("newApiMapper.returnUsersGridCount", criteria)).intValue();
    }

    @Override
    public void deleteGridData(NewApiSC criteria) throws DAOException
    {
	getSqlMap().delete("newApiMapper.deleteGridData", criteria);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.newapi.NewApiDAO#returnMachGridCount(com.path.imco.vo.
     * newapi.NewApiSC)
     */
    @Override
    public Integer returnMachGridCount(NewApiSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "newApiMapper.resMachineGridMap");
	return ((Integer) getSqlMap().queryForObject("newApiMapper.returnMachineGridCount", criteria)).intValue();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.newapi.NewApiDAO#returnMachineGridList(com.path.imco.vo
     * .newapi.NewApiSC)
     */
    @Override
    public List returnMachineGridList(NewApiSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "newApiMapper.resMachineGridMap");
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("newApiMapper.returnMachineGridList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("newApiMapper.returnMachineGridList", criteria, criteria.getRecToskip(),
		    criteria.getNbRec());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.newapi.NewApiDAO#deleteMachineGridData(com.path.imco.vo
     * .newapi.NewApiSC)
     */
    @Override
    public void deleteMachineGridData(NewApiSC criteria) throws DAOException
    {
	getSqlMap().delete("newApiMapper.deleteMachineGridData", criteria);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.newapi.NewApiDAO#deleteArgumentsGridData(com.path.imco.
     * vo.newapi.NewApiSC)
     */

    @Override
    public Integer returnArgumentsGridCount(NewApiSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "newApiMapper.resArgumentsGridMap");
	return ((Integer) getSqlMap().queryForObject("newApiMapper.returnArgumentsGridCount", criteria)).intValue();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.newapi.NewApiDAO#returnMachineGridList(com.path.imco.vo
     * .newapi.NewApiSC)
     */
    @Override
    public List returnArgumentsGridList(NewApiSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "newApiMapper.resArgumentsGridMap");
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("newApiMapper.returnArgumentsGridList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("newApiMapper.returnArgumentsGridList", criteria, criteria.getRecToskip(),
		    criteria.getNbRec());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.newapi.NewApiDAO#deleteMachineGridData(com.path.imco.vo
     * .newapi.NewApiSC)
     */

    @Override
    public void deleteArgumentsGridData(NewApiSC criteria) throws DAOException
    {
	getSqlMap().delete("newApiMapper.deleteArgumentsGridData", criteria);
    }
    
    @Override
    public List<NewApiCO> returnNewApiParams(NewApiCO newapiCO)throws DAOException
	{
		return (List<NewApiCO>)getSqlMap().queryForList("newApiMapper.returnNewApiParams", newapiCO);
	}
    
    @Override
    public String returnSchemaName() throws DAOException 
	{
		Connection c = getSqlMap().returnConnection();
		String schemaName = null;
		try {
			if(ConstantsCommon.CURR_DBMS_ORACLE == 1)
			{
				schemaName = c.getSchema();
			}else {
				schemaName = c.getCatalog();
			} 
		} catch (Exception e) {
			log.error("Error while getting schema name");
		}	
		finally
        {
            try {
            	if(schemaName==null && ConstantsCommon.CURR_DBMS_ORACLE == 1) {
            		schemaName = c.getMetaData().getUserName();
            	}
				c.close();
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
        }
		return schemaName;
	}
    
    @Override
	public BigDecimal getNewDynApiCode(NewApiCO newapiCO) throws DAOException {
		BigDecimal maxApiCode = ((BigDecimal) getSqlMap().queryForObject("newApiMapper.getNewDynApiCode", newapiCO));
		return maxApiCode;
	}
}
