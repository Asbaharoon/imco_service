package com.path.atm.bo.atminterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.path.atm.vo.atminterface.AtmInterfaceCO;
import com.path.atm.vo.atminterface.AtmInterfaceSC;
import com.path.lib.common.exception.BaseException;

/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * IsoMessageBO.java used to
 */
public interface AtmInterfaceBO
{
	/**
	 * Method used to return List of IsoFields
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws BaseException
	 */
	public List retIsoFieldsList(AtmInterfaceSC criteria) throws BaseException;
	
	 /**
	 * Method used to return Count of IsoFields
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws BaseException
	 */	
	public int retIsoFieldsListCount(AtmInterfaceSC criteria) throws BaseException;
	
	/**
	 * Method used to return List of Interfaces
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws BaseException
	 */
	public List retInterfaceList(AtmInterfaceSC criteria) throws BaseException;
	
	/**
	 * Method used to return Count of Interfaces
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws BaseException
	 */	
	public int retInterfaceListCount(AtmInterfaceSC criteria) throws BaseException;
	
	 /**
	 * Method used Save Interface with Fields and Messages
	 * @param AtmInterfaceCO , GridsDataMap
	 * @return int Result number of Records
	 * @throws BaseException
	 */
	public AtmInterfaceCO saveUpdateATMInterface(AtmInterfaceCO atmInterfaceCO , HashMap<String,Object> messagesGridsDataMap,
			Map<Integer, List> subGirdsDataMap) throws BaseException;
	
	/**
	 * Method used to return List of Messages
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws BaseException
	 */
	public List returnMessageList(AtmInterfaceSC criteria) throws BaseException;

	/**
	 * Method used to return Count of Messages
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws BaseException
	 */	
	public int returnMessageListCount (AtmInterfaceSC criteria) throws BaseException;
	
	/**
	 * Method used to return List of Fields
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws BaseException
	 */
	public List returnFieldsList(AtmInterfaceSC criteria) throws BaseException;

	/**
	 * Method used to return Count of Fields
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws BaseException
	 */	
	public int returnFieldsListCount (AtmInterfaceSC criteria) throws BaseException;
	
	/**
	 * Method to Load Interface Details by ID
	 * @param AtmInterfaceCO 
	 * @return AtmInterfaceCO 
	 * @throws BaseException
	 */
	public AtmInterfaceCO returnInterfaceById(AtmInterfaceSC criteria) throws BaseException;
	
	/**
	 * Method used to return List of Sub Fields
	 * @param criteria Search Criteria Parameter
	 * @return List Result
	 * @throws BaseException
	 */
	public List retIsoSubFieldsList(AtmInterfaceSC criteria) throws BaseException;
	
	 /**
	 * Method used to return Count of Sub Fields
	 * @param criteria Search Criteria Parameter
	 * @return int Result number of Records
	 * @throws BaseException
	 */	
	public int retIsoSubFieldsListCount(AtmInterfaceSC criteria) throws BaseException;
	
	 /**
	 * Method used to Delete ATM Interface
	 * @param criteria Search Criteria Parameter
	 * @return AtmInterfaceCO
	 * @throws BaseException
	 */	
	public AtmInterfaceCO deleteInterface(AtmInterfaceCO atmInterfaceCO)  throws BaseException;
	
	/**
	 * Method used to Approve, Reject, Suspend OR Reactivate the ATM Interface 
	 * @param criteria Search Criteria Parameter
	 * @return AtmInterfaceCO
	 * @throws BaseException
	 */
	public AtmInterfaceCO handleStatusProcess(AtmInterfaceCO atmInterfaceCO)  throws BaseException;
	
	/**
	 * Method used to Test ISO Message
	 * @param criteria Search Criteria Parameter
	 * @return List Result 
	 * @throws BaseException
	 */	
	public List testISOMessage(AtmInterfaceSC criteria) throws BaseException;
}
