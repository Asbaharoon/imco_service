package com.path.imco.bo.fieldmapping.impl;

import java.util.List;

import com.path.dbmaps.vo.SYNC_FIXED_MAPPINGVO;
import com.path.imco.bo.fieldmapping.FieldMappingBO;
import com.path.imco.dao.fieldmapping.FieldMappingDAO;
import com.path.imco.vo.fieldmapping.FieldMappingCO;
import com.path.imco.vo.fieldmapping.FieldMappingSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * FieldMappingBOImpl.java used to
 */
public class FieldMappingBOImpl extends BaseBO implements FieldMappingBO
{
    FieldMappingDAO fieldMappingDAO;
    /**
     * Method used to return Count of FieldMapping
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws BaseException
     */
    @Override
    public int returnFieldMappingListCount(FieldMappingSC criteria) throws BaseException
    {
	return fieldMappingDAO.returnFieldMappingListCount(criteria);
    }
    /**
     * Method used to return List of FieldMapping
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws BaseException
     */
    @Override
    public List returnFieldMappingList(FieldMappingSC criteria) throws BaseException
    {
	return fieldMappingDAO.returnFieldMappingList(criteria);
    }
    public FieldMappingDAO getFieldMappingDAO()
    {
	return fieldMappingDAO;
    }
    public void setFieldMappingDAO(FieldMappingDAO fieldMappingDAO)
    {
	this.fieldMappingDAO = fieldMappingDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.bo.fieldmapping.FieldMappingBO#returnGlobalColMapList(com.
     * path.imco.vo.fieldmapping.FieldMappingSC)
     */
    @Override
    public List returnGlobalColMapList(FieldMappingSC criteria) throws BaseException
    {
	return fieldMappingDAO.returnGlobalColMapList(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.bo.fieldmapping.FieldMappingBO#returnFixColMapList(com.path
     * .imco.vo.fieldmapping.FieldMappingSC)
     */
    @Override
    public List returnFixColMapList(FieldMappingSC criteria) throws BaseException
    {
	// TODO Auto-generated method stub
	return fieldMappingDAO.returnFixColMapList(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.bo.fieldmapping.FieldMappingBO#fixDataSave(com.path.imco.vo
     * .fieldmapping.FieldMappingCO)
     */
    @Override
    public FieldMappingCO fixDataSave(FieldMappingCO fieldmapCO) throws BaseException
    {
	FieldMappingSC criteria = new FieldMappingSC();
	criteria.setBrcode(fieldmapCO.getBrcode());
	criteria.setColNBR(fieldmapCO.getColNBR());
	criteria.setEntityCode(fieldmapCO.getEntityCode());
	int i = fieldMappingDAO.deleteGridData(criteria);

	for(FieldMappingCO fixmapLocal : fieldmapCO.getFixcolGridList())
	{

	    SYNC_FIXED_MAPPINGVO syncfixMap = new SYNC_FIXED_MAPPINGVO();
	    syncfixMap.setBR_CODE(fieldmapCO.getBrcode());
	    syncfixMap.setCOL_NBR(fieldmapCO.getColNBR());
	    syncfixMap.setENTITY_CODE(fieldmapCO.getEntityCode());

	    syncfixMap.setLINE_NBR(fixmapLocal.getSyncfixedmappingVO().getLINE_NBR());
	    syncfixMap.setIMAL_VALUE(fixmapLocal.getSyncfixedmappingVO().getIMAL_VALUE());
	    syncfixMap.setEXT_SYS_VALUE(fixmapLocal.getSyncfixedmappingVO().getEXT_SYS_VALUE());

	    genericDAO.insert(syncfixMap);

	    // genericDAO.delete(newApiCoLocal.getImApiUsersVO());
	    // genericDAO.insert(fixmapLocal.getSyncfixedmappingVO());
	}
	return fieldmapCO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.bo.fieldmapping.FieldMappingBO#fieldMapDataSave(com.path.
     * imco.vo.fieldmapping.FieldMappingCO)
     */
    @Override
    public FieldMappingCO fieldMapDataSave(FieldMappingCO fieldmapCO) throws BaseException
    {

	for(FieldMappingCO fieldmapLocal : fieldmapCO.getFieldGridList())
	{

	    // SYNC_FIXED_MAPPINGVO syncfixMap = new SYNC_FIXED_MAPPINGVO();
	    // syncfixMap.setBR_CODE(fieldmapCO.getBrcode());
	    // syncfixMap.setCOL_NBR(fieldmapCO.getColNBR());
	    // syncfixMap.setENTITY_CODE(fieldmapCO.getEntityCode());
	    //
	    // syncfixMap.setLINE_NBR(fixmapLocal.getSyncfixedmappingVO().getLINE_NBR());
	    // syncfixMap.setIMAL_VALUE(fixmapLocal.getSyncfixedmappingVO().getIMAL_VALUE());
	    // syncfixMap.setEXT_SYS_VALUE(fixmapLocal.getSyncfixedmappingVO().getEXT_SYS_VALUE());
	    genericDAO.update(fieldmapLocal.getSyncolmappingVO());
	    // genericDAO.insert(fieldmapLocal.getSyncolmappingVO());

	    // genericDAO.delete(newApiCoLocal.getImApiUsersVO());
	    // genericDAO.insert(fixmapLocal.getSyncfixedmappingVO());
	}
	return fieldmapCO;
    }
}
