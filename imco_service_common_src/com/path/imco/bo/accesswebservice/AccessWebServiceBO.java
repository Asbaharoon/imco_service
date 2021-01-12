package com.path.imco.bo.accesswebservice;

import java.math.BigDecimal;
import java.util.List;

import com.path.dbmaps.vo.IMCO_PWS_CHANNELVO;
import com.path.imco.vo.accesswebservice.AccessWebServiceCO;
import com.path.imco.vo.accesswebservice.AccessWebServiceSC;
import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * AccessWebServiceBO.java used to
 */
public interface AccessWebServiceBO 
{
    /**
     * return Access WebServiceListCount
     * @param criteria
     * @return
     * @throws BaseException
     */
    public int returnAccessWebServiceListCount(AccessWebServiceSC criteria) throws BaseException;
    
    /**
     * return Access WebServiceList
     * @param criteria
     * @return
     * @throws BaseException
     */
    public List<AccessWebServiceCO> returnAccessWebServiceList(AccessWebServiceSC criteria) throws BaseException;
    
    /**
     * on Change Template ID
     * @param accesswebserviceCO
     * @return
     * @throws BaseException
     */
    public AccessWebServiceCO onChangeTemplateID(AccessWebServiceCO accesswebserviceCO) throws BaseException;
    /**
     * return UserId List Count
     * @param accessWebServiceSC
     * @return
     * @throws BaseException
     */
    public int returnUserIdListCount(AccessWebServiceSC accessWebServiceSC) throws BaseException;
    /**
     * return UserId List
     * @param accessWebServiceSC
     * @return
     * @throws BaseException
     */
    public List<IMCO_PWS_CHANNELVO> returnUserIdList(AccessWebServiceSC accessWebServiceSC) throws BaseException;
    
    /**
     * return Selected UserId
     * @param accessWebServiceSC
     * @return
     * @throws BaseException
     */
    public AccessWebServiceCO returnSelectedUserIdDetails(AccessWebServiceSC accessWebServiceSC) throws BaseException;
    
    /**
     * save New
     * @param accesswebserviceCO
     * @param lstAllRecCO
     * @throws BaseException
     */
    public void saveNew(AccessWebServiceCO accesswebserviceCO,List<AccessWebServiceCO> lstAllRecCO) throws BaseException;
    
    /**
     * aws Approve
     * @param accesswebserviceCO
     * @throws BaseException
     */
    public void awsApprove(AccessWebServiceCO accesswebserviceCO) throws BaseException;
    
    /**
     * aws Delete
     * @param accesswebserviceCO
     * @throws BaseException
     */
    public void awsDelete(AccessWebServiceCO accesswebserviceCO) throws BaseException;
    
    /**
     * retrieve Selected TemplateId
     * @param accessWebServiceSC
     * @return
     * @throws BaseException
     */
    public AccessWebServiceCO retrieveSelectedTemplateId(AccessWebServiceSC accessWebServiceSC) throws BaseException;
    
    /**
     * return Services FromDb
     * @param tempId
     * @return
     * @throws BaseException
     */
    public List<AccessWebServiceCO> returnServicesFromDb(AccessWebServiceSC accessWebServiceSC) throws BaseException;
    
    
    public void deleteRecordFromDB(AccessWebServiceSC accessWebServiceSC) throws BaseException;
}