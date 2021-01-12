package com.path.atm.dao.atminterface;

import java.util.List;

import com.path.atm.vo.atminterface.AtmInterfaceCO;
import com.path.atm.vo.atminterface.AtmInterfaceSC;
import com.path.lib.common.exception.DAOException;

/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * IsoMessageDAO.java used to
 */
public interface AtmInterfaceDAO
{
	/**
	 * Method used to return List of IsoFields
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List retIsoFieldsList(AtmInterfaceSC criteria) throws DAOException;
	
	/**
	 * Method used to return Count of IsoFields
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws DAOException
	 */	
	public int retIsoFieldsListCount(AtmInterfaceSC criteria) throws DAOException;
	
	/**
	 * Method used to return List of Interfaces
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List retInterfaceList(AtmInterfaceSC criteria) throws DAOException;
	
	/**
	 * Method used to return Count of Interfaces
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws DAOException
	 */	
	public int retInterfaceListCount(AtmInterfaceSC criteria) throws DAOException;
	
	/**
	 * Method used to return New Interface ID
	 * @param criteria Search Criteria Parameter
	 * @throws DAOException
	 */
	public int retNewInterfaceId(AtmInterfaceSC criteria) throws DAOException;
	
	/**
	 * Method used to return New Message CODE
	 * @param criteria Search Criteria Parameter
	 * @throws DAOException
	 */
	public int retNewMessageCode(AtmInterfaceSC criteria) throws DAOException;
	
	/**
	 * Method used to return List of Messages
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List returnMessageList(AtmInterfaceSC criteria) throws DAOException;

	/**
	 * Method used to return Count of Messages
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws DAOException
	 */	
	public int returnMessageListCount (AtmInterfaceSC criteria) throws DAOException;
	
	/**
	 * Method used to return List of Fields
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List returnFieldsList(AtmInterfaceSC criteria) throws DAOException;

	/**
	 * Method used to return Count of Fields
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws DAOException
	 */	
	public int returnFieldsListCount (AtmInterfaceSC criteria) throws DAOException;
	
	/**
	 * Method to Load Interface Details by ID
	 * @param AtmInterfaceSC 
	 * @return AtmInterfaceCO 
	 * @throws DAOException
	 */
	public AtmInterfaceCO returnInterfaceById(AtmInterfaceSC criteria) throws DAOException;
	
	 /**
     * return ProgOrder
     * @return
     * @throws DAOException
     */
	public int retProgOrder() throws DAOException;
	
	/**
	 * Method used to return List of Sub Fields
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List retIsoSubFieldsList(AtmInterfaceSC criteria) throws DAOException;
	
	 /**
	 * Method used to return Count of Sub Fields
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws DAOException
	 */	
	public int retIsoSubFieldsListCount(AtmInterfaceSC criteria) throws DAOException;
	
	/**
	 * Method used to return List of Fields By Bitmap
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws DAOException
	 */
	public List retFieldListByBitmap(AtmInterfaceSC criteria) throws DAOException;
}
