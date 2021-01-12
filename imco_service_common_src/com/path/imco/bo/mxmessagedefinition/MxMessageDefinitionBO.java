package com.path.imco.bo.mxmessagedefinition;

import java.util.List;

import com.path.imco.vo.mxmessagedefinition.MxMessageDefinitionCO;
import com.path.imco.vo.mxmessagedefinition.MxMessageDefinitionSC;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * MxMessageDefinitionBO.java used to
 */
public interface MxMessageDefinitionBO 
{
	/**
	 * Method used to return List of Mx Message Definition
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List returnMxMessageDefinitionList(MxMessageDefinitionSC criteria) throws BaseException;
	
	/**
	 * Method used to return Count of Mx Message Definition
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws DAOException
	 */
	public int returnMxMessageDefinitionListCount(MxMessageDefinitionSC criteria) throws BaseException;
		

	/**
	 * Method used to return List of returnMxMessageDefinitionTagsList
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List returnMxMessageDefinitionTagsList(MxMessageDefinitionSC criteria) throws BaseException;
	
	/**
	 * Method used to return an Object of MxMessageDefinitionCO
	 * @param criteria Search Criteria Parameter
	 * @return Object Result
	 * @throws DAOException
	 */
	public MxMessageDefinitionCO returnMxMessageDefinitionDetails(MxMessageDefinitionSC criteria) throws BaseException;
	
	/**
	 * Method used for to save the records
	 * @param co
	 * @return
	 * @throws BaseException
	 */
	public Integer save(MxMessageDefinitionCO co) throws BaseException;

	/**
	 * Method used for to handle all the status like approve
	 * @param co
	 * @return
	 * @throws BaseException
	 */
	public Integer handleStatusProcess(MxMessageDefinitionCO mxMessageDefinitionCO) throws BaseException;

	/**
	 * validate Mx Message Definition by Code
	 * @param mxMessageDefinitionCO
	 * @return
	 * @throws BaseException
	 */
	public MxMessageDefinitionCO validateMxMsgDefinitionCode(MxMessageDefinitionCO mxMessageDefinitionCO) throws BaseException;

}