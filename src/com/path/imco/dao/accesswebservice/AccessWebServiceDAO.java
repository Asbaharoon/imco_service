package com.path.imco.dao.accesswebservice;

import java.math.BigDecimal;
import java.util.List;

import com.path.dbmaps.vo.IMCO_PWS_CHANNELVO;
import com.path.imco.vo.accesswebservice.AccessWebServiceCO;
import com.path.imco.vo.accesswebservice.AccessWebServiceSC;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;

/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * ChannelDAO.java used to
 */
public interface AccessWebServiceDAO
{
    /**
     * return Access WebServiceListCount
     * @param criteria
     * @return
     * @throws DAOException
     */
    public int returnAccessWebServiceListCount(AccessWebServiceSC criteria) throws DAOException;
    
    /**
     * return Access WebServiceList
     * @param criteria
     * @return
     * @throws DAOException
     */
    public List<AccessWebServiceCO> returnAccessWebServiceList(AccessWebServiceSC criteria) throws DAOException;
    /**
     * return UserId List Count
     * @param accessWebServiceSC
     * @return
     * @throws DAOException
     */
    public int returnUserIdListCount(AccessWebServiceSC accessWebServiceSC) throws DAOException;
    /**
     * return UserId List
     * @param accessWebServiceSC
     * @return
     * @throws DAOException
     */
    public List<IMCO_PWS_CHANNELVO> returnUserIdList(AccessWebServiceSC accessWebServiceSC) throws DAOException;
    
    /**
     * return Selected UserId
     * @param accessWebServiceSC
     * @return
     * @throws DAOException
     */
    public AccessWebServiceCO returnSelectedUserIdDetails(AccessWebServiceSC accessWebServiceSC) throws DAOException;
    
    /**
     * return Selected UserId Details
     * @param tempID
     * @return
     * @throws DAOException
     */
    public int returnTemplateIdCount(BigDecimal tempID) throws DAOException;
    
    /**
     * retrieve SelecetdQueryId
     * @param sc
     * @return
     * @throws DAOException
     */
    public AccessWebServiceCO retrieveSelectedTemplateId(AccessWebServiceSC sc) throws DAOException;
    
    /**
     * return Services FromDb
     * @param tempId
     * @return
     * @throws DAOException
     */
    public List<AccessWebServiceCO> returnServicesFromDb(AccessWebServiceSC accessWebServiceSC) throws DAOException;
    
    /**
     * delete All Previous Record
     * @param templateId
     * @throws DAOException
     */
    public void deleteAllPreviousRecord(BigDecimal templateId) throws DAOException;
    
    /**
     * delete Record DetParam table
     * @param accessWebServiceSC
     */
    public void deleteRecordDetParamFromDB(AccessWebServiceSC accessWebServiceSC) throws DAOException;
    
    /**
     * delete Record Det table
     * @param accessWebServiceSC
     */
    public void deleteRecordDetFromDB(AccessWebServiceSC accessWebServiceSC) throws DAOException;
    
    /**
     * returned ParamCheckedList
     * @param awsCO
     * @return
     * @throws DAOException
     */
    public List<String> returnedParamCheckedList(AccessWebServiceSC sc) throws DAOException;
    
    /**
     * count TmpltUsrVO table
     * @param tempID
     * @return
     * @throws DAOException
     */
    public List<BigDecimal> countTmpltUsrVO(BigDecimal tempID) throws DAOException;
}