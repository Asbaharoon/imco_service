package com.path.imco.bo.fieldmapping;

import java.util.List;

import com.path.imco.vo.fieldmapping.FieldMappingCO;
import com.path.imco.vo.fieldmapping.FieldMappingSC;
import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * FieldMappingBO.java used to
 */
public interface FieldMappingBO
{
    public int returnFieldMappingListCount(FieldMappingSC criteria) throws BaseException;

    public List returnFieldMappingList(FieldMappingSC criteria) throws BaseException;

    /**
     * @param criteria
     * @return
     */
    public List returnGlobalColMapList(FieldMappingSC criteria) throws BaseException;

    /**
     * @param criteria
     * @return
     */
    public List returnFixColMapList(FieldMappingSC criteria) throws BaseException;

    /**
     * @param fieldmapCO
     * @return
     */
    public FieldMappingCO fixDataSave(FieldMappingCO fieldmapCO) throws BaseException;

    /**
     * @param fieldmapCO
     * @return
     */
    public FieldMappingCO fieldMapDataSave(FieldMappingCO fieldmapCO) throws BaseException;

}