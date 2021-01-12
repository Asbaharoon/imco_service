package com.path.imco.dao.fieldmapping.impl;

import java.util.List;

import com.path.imco.dao.fieldmapping.FieldMappingDAO;
import com.path.imco.vo.fieldmapping.FieldMappingSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * FieldMappingDAOImpl.java used to
 */
public class FieldMappingDAOImpl extends BaseDAO implements FieldMappingDAO
{
    /**
     * Method used to return Count of FieldMapping
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws DAOException
     */
    @Override
    public int returnFieldMappingListCount(FieldMappingSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "fieldMappingMapper.resFieldMappingListMap");
	return ((Integer) getSqlMap().queryForObject("fieldMappingMapper.returnFieldMappingListCount", criteria)).intValue();
    }
    /**
     * Method used to return List of FieldMapping
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws DAOException
     */
    @Override
    public List returnFieldMappingList(FieldMappingSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "fieldMappingMapper.resFieldMappingListMap");
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("fieldMappingMapper.returnFieldMappingList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("fieldMappingMapper.returnFieldMappingList", criteria, criteria.getRecToskip(),criteria.getNbRec());
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.dao.fieldmapping.FieldMappingDAO#returnGlobalColMapList(com
     * .path.imco.vo.fieldmapping.FieldMappingSC)
     */
    @Override
    public List returnGlobalColMapList(FieldMappingSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "fieldMappingMapper.resGlobalColMapListMap");

	return getSqlMap().queryForList("fieldMappingMapper.returnGlobalColMapList", criteria);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.dao.fieldmapping.FieldMappingDAO#returnFixColMapList(com.
     * path.imco.vo.fieldmapping.FieldMappingSC)
     */
    @Override
    public List returnFixColMapList(FieldMappingSC criteria) throws DAOException
    {
	// TODO Auto-generated method stub
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "fieldMappingMapper.resFixColMapListMap");

	return getSqlMap().queryForList("fieldMappingMapper.returnFixColMapList", criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.dao.fieldmapping.FieldMappingDAO#deleteGridData(com.path.
     * imco.vo.fieldmapping.FieldMappingSC)
     */
    @Override
    public int deleteGridData(FieldMappingSC criteria) throws DAOException
    {
	return getSqlMap().delete("fieldMappingMapper.deleteGridData", criteria);

    }




}
