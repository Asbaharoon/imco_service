package com.path.imco.dao.mxmessagedefinition;

import java.math.BigDecimal;
import java.util.List;

import com.path.dbmaps.vo.DGTL_MSG_DEFVO;
import com.path.dbmaps.vo.DGTL_XML_MSG_TAGSVO;
import com.path.imco.vo.mxmessagedefinition.MxMessageDefinitionCO;
import com.path.imco.vo.mxmessagedefinition.MxMessageDefinitionSC;
import com.path.imco.vo.mxmessagedefinition.TagsCo;
import com.path.lib.common.exception.DAOException;
/**
 * 
 * Copyright 2020, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * MxMessageDefinitionDAO.java used to
 */
public interface MxMessageDefinitionDAO 
{
	/**
	 * Method used to return List of Mx Message Definition
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List returnMxMessageDefinitionList(MxMessageDefinitionSC criteria) throws DAOException;
	
	/**
	 * Method used to return Count of Mx Message Definition
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws DAOException
	 */
	public int returnMxMessageDefinitionListCount(MxMessageDefinitionSC criteria) throws DAOException;
		

	/**
	 * Method used to return List of returnMxMessageDefinitionTagsList
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List returnMxMessageDefinitionTagsList(MxMessageDefinitionSC criteria) throws DAOException;
	
	/**
	 * Method used to return an Object of MxMessageDefinitionCO
	 * @param criteria Search Criteria Parameter
	 * @return Object Result
	 * @throws DAOException
	 */
	public MxMessageDefinitionCO returnMxMessageDefinitionDetails(MxMessageDefinitionSC dynamicFileStructureSC) throws DAOException;
	
	/**
	 * Method used to delete the tags by mxMessageDefinition Id
	 * @param mxMessageDefId
	 * @return
	 * @throws DAOException
	 */
	public int deleteMxMessageDefinitionTags(BigDecimal mxMessageDefId) throws DAOException;
	
	/**
	 * validate Mx Message Definition by Code
	 * @param dgtl_MSG_DEFVO
	 * @return
	 * @throws DAOException
	 */
	public int validateMxMsgDefByCode(DGTL_MSG_DEFVO dgtl_MSG_DEFVO) throws DAOException;

	/**
	 * insert bulk tags
	 * @param mxMessageDefinitionCO
	 * @return
	 * @throws DAOException
	 */
	public int insertDGTL_XML_MSG_TAGS(MxMessageDefinitionCO mxMessageDefinitionCO) throws DAOException;

	/**
	 * Update the description for already inserted tags
	 * @param xml_MSG_TAGSVO
	 * @throws DAOException
	 */
	public void updateDGTL_XML_MSG_TAGS(DGTL_XML_MSG_TAGSVO xml_MSG_TAGSVO) throws DAOException;
	

}