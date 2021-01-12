package com.path.imco.dao.dynamicfiles;

import java.util.List;

import com.path.dbmaps.vo.IMCO_DYN_FILE_MESSAGEVO;
import com.path.dbmaps.vo.IMCO_DYN_FILE_STRUCTUREVO;
import com.path.imco.vo.dynamicfiles.DynamicFileStructureCO;
import com.path.imco.vo.dynamicfiles.DynamicFileStructureSC;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * DynamicFileStructureDAO.java used to
 */
public interface DynamicFileStructureDAO 
{
	/**
	 * Method used to return List of DynamicFileStructure
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List returnDynamicFileStructureList(DynamicFileStructureSC criteria) throws DAOException;
	
	/**
	 * Method used to return Count of DynamicFileStructure
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws DAOException
	 */
	public int returnDynamicFileStructureListCount(DynamicFileStructureSC criteria) throws DAOException;
		
	/**
	 * Method used to return Count of returnDynamicFileStructureMessageListCount
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws DAOException
	 */
	public int returnDynamicFileStructureMessageListCount(DynamicFileStructureSC criteria) throws DAOException;
	
	/**
	 * Method used to return List of returnDynamicFileStructureMessageList
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List returnDynamicFileStructureMessageList(DynamicFileStructureSC criteria) throws DAOException;
	
	/**
	 * Method used to return List of returnDynamicFileStructureTagsList
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List returnDynamicFileStructureTagsList(DynamicFileStructureSC criteria) throws DAOException;
	
	/**
	 * Method used to return an Object of GroupDefinition
	 * @param criteria Search Criteria Parameter
	 * @return Object Result
	 * @throws DAOException
	 */
	public DynamicFileStructureCO returnDynamicFileStructureDetails(DynamicFileStructureSC dynamicFileStructureSC) throws DAOException;

	/**
	 * Insert File Structure with SelectKey
	 * @param file_STRUCTUREVO
	 * @return
	 * @throws DAOException
	 */
	public void insertFileStructure(IMCO_DYN_FILE_STRUCTUREVO file_STRUCTUREVO) throws DAOException;
	
	/**
	 * Insert Message with SelectKey
	 * @param MessageVO
	 * @return
	 * @throws DAOException
	 */
	public void insertFileMessage(IMCO_DYN_FILE_MESSAGEVO file_MESSAGEVO) throws DAOException;
	
	/**
	 * Method used to return Count FileStructure by Reference
	 * @param criteria
	 * Search Criteria Parameter
	 * @return int Result
	 * @throws DAOException
	 */
	public int checkFileRefUnique(DynamicFileStructureSC criteria) throws DAOException;
}