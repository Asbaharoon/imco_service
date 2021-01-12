package com.path.imco.dao.fieldmapping;

import java.util.List;

import com.path.imco.vo.fieldmapping.FieldMappingSC;
import com.path.lib.common.exception.DAOException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * FieldMappingDAO.java used to
 */
public interface FieldMappingDAO
{
    public int returnFieldMappingListCount(FieldMappingSC criteria) throws DAOException;

    public List returnFieldMappingList(FieldMappingSC criteria) throws DAOException;

    /**
     * @param criteria
     * @return
     */
    public List returnGlobalColMapList(FieldMappingSC criteria) throws DAOException;

    /**
     * @param criteria
     * @return
     */
    public List returnFixColMapList(FieldMappingSC criteria) throws DAOException;

    /**
     * @param criteria
     */
    public int deleteGridData(FieldMappingSC criteria) throws DAOException;
}