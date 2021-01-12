package com.path.imco.vo.fieldmapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.path.dbmaps.vo.SYNC_COL_MAPPINGVO;
import com.path.dbmaps.vo.SYNC_FIXED_MAPPINGVO;
import com.path.lib.vo.BaseVO;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * FieldMappingCO.java used to
 */
public class FieldMappingCO extends BaseVO
{
    SYNC_COL_MAPPINGVO syncolmappingVO = new SYNC_COL_MAPPINGVO();
    SYNC_FIXED_MAPPINGVO syncfixedmappingVO = new SYNC_FIXED_MAPPINGVO();

    BigDecimal forcenull;
    String fieldmapGridListString;
    String defaultGridListString;
    String globalGridListString;
    String checkactions;
    BigDecimal rowId;
    String fixGridListString;
    private BigDecimal brcode;
    private BigDecimal entityCode;
    private BigDecimal colNBR;
    private List<FieldMappingCO> fieldGridList = new ArrayList<>();
    private List<FieldMappingCO> fixcolGridList = new ArrayList<>();
    /**
     * @return the syncolmappingVO
     */
    public SYNC_COL_MAPPINGVO getSyncolmappingVO()
    {
	return syncolmappingVO;
    }

    /**
     * @param syncolmappingVO the syncolmappingVO to set
     */
    public void setSyncolmappingVO(SYNC_COL_MAPPINGVO syncolmappingVO)
    {
	this.syncolmappingVO = syncolmappingVO;
    }

    /**
     * @return the forcenull
     */
    public BigDecimal getForcenull()
    {
	return forcenull;
    }

    /**
     * @param forcenull the forcenull to set
     */
    public void setForcenull(BigDecimal forcenull)
    {
	this.forcenull = forcenull;
    }

    /**
     * @return the fieldmapGridListString
     */
    public String getFieldmapGridListString()
    {
	return fieldmapGridListString;
    }

    /**
     * @param fieldmapGridListString the fieldmapGridListString to set
     */
    public void setFieldmapGridListString(String fieldmapGridListString)
    {
	this.fieldmapGridListString = fieldmapGridListString;
    }

    /**
     * @return the fieldGridList
     */
    public List<FieldMappingCO> getFieldGridList()
    {
	return fieldGridList;
    }

    /**
     * @param fieldGridList the fieldGridList to set
     */
    public void setFieldGridList(List<FieldMappingCO> fieldGridList)
    {
	this.fieldGridList = fieldGridList;
    }

    /**
     * @return the syncfixedmappingVO
     */
    public SYNC_FIXED_MAPPINGVO getSyncfixedmappingVO()
    {
	return syncfixedmappingVO;
    }

    /**
     * @param syncfixedmappingVO the syncfixedmappingVO to set
     */
    public void setSyncfixedmappingVO(SYNC_FIXED_MAPPINGVO syncfixedmappingVO)
    {
	this.syncfixedmappingVO = syncfixedmappingVO;
    }

    /**
     * @return the checkactions
     */
    public String getCheckactions()
    {
	return checkactions;
    }

    /**
     * @param checkactions the checkactions to set
     */
    public void setCheckactions(String checkactions)
    {
	this.checkactions = checkactions;
    }

    /**
     * @return the fixGridListString
     */
    public String getFixGridListString()
    {
	return fixGridListString;
    }

    /**
     * @param fixGridListString the fixGridListString to set
     */
    public void setFixGridListString(String fixGridListString)
    {
	this.fixGridListString = fixGridListString;
    }

    /**
     * @return the fixcolGridList
     */
    public List<FieldMappingCO> getFixcolGridList()
    {
	return fixcolGridList;
    }

    /**
     * @param fixcolGridList the fixcolGridList to set
     */
    public void setFixcolGridList(List<FieldMappingCO> fixcolGridList)
    {
	this.fixcolGridList = fixcolGridList;
    }

    /**
     * @return the brcode
     */
    public BigDecimal getBrcode()
    {
	return brcode;
    }

    /**
     * @param brcode the brcode to set
     */
    public void setBrcode(BigDecimal brcode)
    {
	this.brcode = brcode;
    }

    /**
     * @return the entityCode
     */
    public BigDecimal getEntityCode()
    {
	return entityCode;
    }

    /**
     * @param entityCode the entityCode to set
     */
    public void setEntityCode(BigDecimal entityCode)
    {
	this.entityCode = entityCode;
    }

    /**
     * @return the colNBR
     */
    public BigDecimal getColNBR()
    {
	return colNBR;
    }

    /**
     * @param colNBR the colNBR to set
     */
    public void setColNBR(BigDecimal colNBR)
    {
	this.colNBR = colNBR;
    }

    /**
     * @return the defaultGridListString
     */
    public String getDefaultGridListString()
    {
	return defaultGridListString;
    }

    /**
     * @param defaultGridListString the defaultGridListString to set
     */
    public void setDefaultGridListString(String defaultGridListString)
    {
	this.defaultGridListString = defaultGridListString;
    }

    /**
     * @return the globalGridListString
     */
    public String getGlobalGridListString()
    {
	return globalGridListString;
    }

    /**
     * @param globalGridListString the globalGridListString to set
     */
    public void setGlobalGridListString(String globalGridListString)
    {
	this.globalGridListString = globalGridListString;
    }

    /**
     * @return the rowId
     */
    public BigDecimal getRowId()
    {
	return rowId;
    }

    /**
     * @param rowId the rowId to set
     */
    public void setRowId(BigDecimal rowId)
    {
	this.rowId = rowId;
    }

    /**
     * @return the checkaction
     */

}
