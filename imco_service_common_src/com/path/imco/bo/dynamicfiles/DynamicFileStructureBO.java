package com.path.imco.bo.dynamicfiles;

import java.util.HashMap;
import java.util.List;

import com.path.imco.vo.dynamicfiles.DynamicFileStructureCO;
import com.path.imco.vo.dynamicfiles.DynamicFileStructureSC;
import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * DynamicFileStructureBO.java used to
 */
public interface DynamicFileStructureBO 
{
	 /**
	 * Method used to return Count of DynamicFileStructure
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws BaseException
	 */	
	public int returnDynamicFileStructureListCount(DynamicFileStructureSC criteria) throws BaseException;
	
	/**
	 * Method used to return List of DynamicFileStructure
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws BaseException
	 */
	public List returnDynamicFileStructureList(DynamicFileStructureSC criteria) throws BaseException;
	
	/**
	 * method used to Return XML messages list count
	 * @param criteria
	 * @return
	 * @throws BaseException
	 */
	public int returnDynamicFileStructureMessageListCount(DynamicFileStructureSC criteria) throws BaseException;
	
	/**
	 * method used to Return XML messages list
	 * @param criteria
	 * @return
	 * @throws BaseException
	 */
	public List returnDynamicXmlFileStructureMessageList(DynamicFileStructureSC criteria) throws BaseException;
	
	/**
	 * method used to Return TXT messages List
	 * @param criteria
	 * @return
	 * @throws BaseException
	 */
	public List returnDynamicTextFileStructureMessageList(DynamicFileStructureSC criteria) throws BaseException;
	
	
	/**
	 * Method used to save text File Structure Data
	 * @param dynamicFileStructureCO
	 * @param GridsDataMap
	 * @return
	 * @throws BaseException
	 */
	public DynamicFileStructureCO saveDynamicTextFileStructure(DynamicFileStructureCO dynamicFileStructureCO, HashMap<String,Object> GridsDataMap) throws BaseException;

	

	/**
	 * Method used to save File Structure
	 * @param criteria DynamicFileStructureCO Parameter
	 * @return 
	 * @throws BaseException
	 */	
	public DynamicFileStructureCO saveDynamicXmlFileStructure(DynamicFileStructureCO fileStructureCO, List lstAllRec, List lstDelete) throws BaseException;
	
	/**
	 * Method used to Delete File structure
	 * @param criteria DynamicFileStructureCO Parameter
	 * @return 
	 * @throws BaseException
	 */	
	public void deleteDynamicFileStructure(DynamicFileStructureCO dynamicFileStructureCO) throws BaseException;
	
	/**
	 * Method used to return Dynamic Structure File Details
	 * @param criteria
	 * Search Criteria Parameter
	 * @return Object Result
	 * @throws BaseException
	 */
	public DynamicFileStructureCO returnDynamicFileStructureDetails(DynamicFileStructureSC dynamicFileStructureSC) throws BaseException;
	
	/**
	 * Method used to return Count FileStructure by Reference
	 * @param criteria
	 * Search Criteria Parameter
	 * @return int Result
	 * @throws BaseException
	 */
	public int checkFileRefUnique(DynamicFileStructureSC criteria) throws BaseException;
}