package com.path.imco.bo.accesswebservice.impl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.MessageCodes;
import com.path.bo.common.audit.AuditConstant;
import com.path.dbmaps.vo.IMCO_PWS_CHANNELVO;
import com.path.dbmaps.vo.IMCO_PWS_TMPLT_DETVO;
import com.path.dbmaps.vo.IMCO_PWS_TMPLT_DET_PARAMVO;
import com.path.dbmaps.vo.IMCO_PWS_TMPLT_MASTERVO;
import com.path.imco.bo.accesswebservice.AccessWebServiceBO;
import com.path.imco.bo.accesswebservice.AccessWebServiceConstant;
import com.path.imco.dao.accesswebservice.AccessWebServiceDAO;
import com.path.imco.vo.accesswebservice.AccessWebServiceCO;
import com.path.imco.vo.accesswebservice.AccessWebServiceSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.vo.common.audit.AuditRefCO;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * ChannelBOImpl.java used to
 */
public class AccessWebServiceBOImpl extends BaseBO implements AccessWebServiceBO
{
    AccessWebServiceDAO accesswebserviceDAO;
    
    @Override
    public int returnAccessWebServiceListCount(AccessWebServiceSC criteria) throws BaseException
    {
	return accesswebserviceDAO.returnAccessWebServiceListCount(criteria);
    }
    
    @Override
    public List<AccessWebServiceCO> returnAccessWebServiceList(AccessWebServiceSC criteria) throws BaseException
    {
	return accesswebserviceDAO.returnAccessWebServiceList(criteria);
    }
    
    @Override
    public AccessWebServiceCO onChangeTemplateID(AccessWebServiceCO accesswebserviceCO) throws BaseException
    {
	IMCO_PWS_TMPLT_MASTERVO imcoPwsTmpltMaster = accesswebserviceCO.getImcoPwsTmpltMasterVO();
	IMCO_PWS_TMPLT_MASTERVO tmpltMasterVO = (IMCO_PWS_TMPLT_MASTERVO) genericDAO.selectByPK(imcoPwsTmpltMaster);
	if(tmpltMasterVO != null)
	{
	    throw new BOException("Templatel ID is already defined");
	}
	return accesswebserviceCO;
    }
    
    @Override
    public int returnUserIdListCount(AccessWebServiceSC accessWebServiceSC) throws BaseException
    {
	return accesswebserviceDAO.returnUserIdListCount(accessWebServiceSC);
    }
    
    @Override
    public List<IMCO_PWS_CHANNELVO> returnUserIdList(AccessWebServiceSC accessWebServiceSC) throws BaseException
    {
	return accesswebserviceDAO.returnUserIdList(accessWebServiceSC);
    }
    
    @Override
    public AccessWebServiceCO returnSelectedUserIdDetails(AccessWebServiceSC accessWebServiceSC) throws BaseException
    {
	return accesswebserviceDAO.returnSelectedUserIdDetails(accessWebServiceSC);
    }
    
    @Override
    public void saveNew(AccessWebServiceCO accesswebserviceCO,List<AccessWebServiceCO> lstAllRecCO) throws BaseException
    {
	AccessWebServiceCO accessCO;
	IMCO_PWS_TMPLT_DETVO lVO;
	IMCO_PWS_TMPLT_DET_PARAMVO lVODet;
	JSONObject jsonFilter; 
	AccessWebServiceSC accessWebServiceSC;
	AuditRefCO refCO = accesswebserviceCO.getAuditRefCO();
	accesswebserviceCO.getImcoPwsTmpltMasterVO().setSTATUS(ConstantsCommon.ACTIVE_RECORD);
	//delete all previous record related to a specific template id
//	accesswebserviceDAO.deleteAllPreviousRecord(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
	
	// add new
	if(accesswebserviceDAO.returnTemplateIdCount(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID()) == 0)
	{
	    accesswebserviceCO.getImcoPwsTmpltMasterVO().setCREATED_BY(accesswebserviceCO.getUserID());
	    accesswebserviceCO.getImcoPwsTmpltMasterVO().setCREATED_DATE(accesswebserviceCO.getRunningDate());
	    accesswebserviceCO.getImcoPwsTmpltMasterVO().setSERVER_CREATED_DATE(commonLibBO.returnSystemDateWithTime());
	    
	    //insert into ReportQuery
	    genericDAO.insert(accesswebserviceCO.getImcoPwsTmpltMasterVO());
	    
	    // apply audit
	    refCO.setOperationType(AuditConstant.CREATED);
	    auditBO.callAudit(null, accesswebserviceCO.getImcoPwsTmpltMasterVO(), refCO);
	}
	// update
	else
	{
	    accesswebserviceCO.getImcoPwsTmpltMasterVO().setMODIFIED_BY(accesswebserviceCO.getUserID());
	    accesswebserviceCO.getImcoPwsTmpltMasterVO().setMODIFIED_DATE(accesswebserviceCO.getRunningDate());
	    accesswebserviceCO.getImcoPwsTmpltMasterVO().setSERVER_MODIFIED_DATE(commonLibBO.returnSystemDateWithTime());
	    Integer row = genericDAO.update(accesswebserviceCO.getImcoPwsTmpltMasterVO());
	    if(row == null || row < 1)
	    {
		throw new BOException(MessageCodes.RECORD_CHANGED);
	    }
	}
	
	// insert into IMCO_PWS_TMPLT_DETVO
	// update records grid
	Iterator<AccessWebServiceCO> itMod = lstAllRecCO.iterator();
	while(itMod.hasNext())
	{
	    accessCO = itMod.next();
	    lVO = accessCO.getImcoPwsTmpltDetVO();
	    if("1".equals(lVO.getEXCLUDE_ACCESS_YN()))
	    {
		lVO.setTEMPLATE_ID(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
		if("A".equals(accessCO.getImcoPwsTmpltDetVO().getTYPE())&& "1".equals(accessCO.getLevel())||
			"false".equals(accessCO.getIsLeaf()) && "S".equals(accessCO.getImcoPwsTmpltDetVO().getTYPE())
			    && !accessCO.getParent().contains("-"))
		{
		    lVO.setAPP_NAME(accessCO.getFeName());
		    lVO.setWS_NAME("All");
		    lVO.setOPERATION("All");
		    genericDAO.delete(lVO);
		    
		    accessWebServiceSC = new AccessWebServiceSC();
		    accessWebServiceSC.setTempID(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
		    accessWebServiceSC.setAppName(accessCO.getFeName());
		    //delete from det param table
		    accesswebserviceDAO.deleteRecordDetParamFromDB(accessWebServiceSC);
		    //delete from det table
		    accesswebserviceDAO.deleteRecordDetFromDB(accessWebServiceSC);
		}
		else
		{
		    if("2".equals(accessCO.getLevel()))
		    {
			if("false".equals(accessCO.getIsLeaf())
				    && "S".equals(accessCO.getImcoPwsTmpltDetVO().getTYPE())
				    && accessCO.getParent().contains("-"))
			{
			    lVO.setAPP_NAME(accessCO.getParent().split("-")[0]);
			    lVO.setWS_NAME(accessCO.getFeName());
			    lVO.setOPERATION("All");
			    genericDAO.delete(lVO);
			}
			else
			{
			    lVO.setAPP_NAME(accessCO.getParent().split("-")[0]);
			    lVO.setWS_NAME(accessCO.getFeName());
			    lVO.setOPERATION("All");
			    
			    accessWebServiceSC = new AccessWebServiceSC();
			    accessWebServiceSC.setTempID(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
			    accessWebServiceSC.setAppName(accessCO.getParent().split("-")[0]);
			    accessWebServiceSC.setServiceName(accessCO.getFeName());
			    // delete from det param table
			    accesswebserviceDAO.deleteRecordDetParamFromDB(accessWebServiceSC);
			    // delete from det table
			    accesswebserviceDAO.deleteRecordDetFromDB(accessWebServiceSC);
			}
		    }
		    //leaf level
		    else
		    {
			lVO.setAPP_NAME(accessCO.getParent().split("-")[1]);
			lVO.setWS_NAME(accessCO.getParent().split("-")[0]);
			lVO.setOPERATION(accessCO.getFeName());
			lVO.setTYPE("S");
			genericDAO.delete(lVO);

			lVODet = new IMCO_PWS_TMPLT_DET_PARAMVO();
			lVODet.setTEMPLATE_ID(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
			lVODet.setAPP_NAME(accessCO.getParent().split("-")[1]);
			lVODet.setWS_NAME(accessCO.getParent().split("-")[0]);
			lVODet.setOPERATION(accessCO.getFeName());

			// delete from det param table
			accessWebServiceSC = new AccessWebServiceSC();
			accessWebServiceSC.setTempID(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
			accessWebServiceSC.setAppName(accessCO.getParent().split("-")[1]);
			accessWebServiceSC.setServiceName(accessCO.getParent().split("-")[0]);
			accessWebServiceSC.setOperationName(accessCO.getFeName());
			// delete from det param table
			accesswebserviceDAO.deleteRecordDetParamFromDB(accessWebServiceSC);
			if(!"".equals(accessCO.getCUSTOM_DETAILS()))
			{
			    jsonFilter = (JSONObject) JSONSerializer.toJSON(accessCO.getCUSTOM_DETAILS());
			    Iterator<String> keys = jsonFilter.keys();
			    while(keys.hasNext())
			    {
				String key = (String) keys.next(); // First key
								   // in
								   // your json
								   // object
				lVODet.setPARAM_ID(key);
				genericDAO.insert(lVODet);
			    }
			}
		    }
		}
		genericDAO.insert(lVO);
	    }
	    //delete web service
	    else
	    {
		lVO.setTEMPLATE_ID(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
		if("A".equals(accessCO.getImcoPwsTmpltDetVO().getTYPE())&& "1".equals(accessCO.getLevel())||
			"false".equals(accessCO.getIsLeaf()) && "S".equals(accessCO.getImcoPwsTmpltDetVO().getTYPE())
			    && !accessCO.getParent().contains("-"))
		{
		    lVO.setAPP_NAME(accessCO.getFeName());
		    lVO.setWS_NAME("All");
		    lVO.setOPERATION("All");
		    //delete from det param table
		    accessWebServiceSC = new AccessWebServiceSC();
		    accessWebServiceSC.setTempID(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
		    accessWebServiceSC.setAppName(accessCO.getFeName());
		    //delete from det param table
		    accesswebserviceDAO.deleteRecordDetParamFromDB(accessWebServiceSC);
		    //delete from det table
		    accesswebserviceDAO.deleteRecordDetFromDB(accessWebServiceSC);
		}
		else
		{
		    if("false".equals(accessCO.getIsLeaf())
			    && "S".equals(accessCO.getImcoPwsTmpltDetVO().getTYPE())
			    && accessCO.getParent().contains("-")||
			    "2".equals(accessCO.getLevel()))
		    {
			lVO.setAPP_NAME(accessCO.getParent().split("-")[0]);
			lVO.setWS_NAME(accessCO.getFeName());
			lVO.setOPERATION("All");
			genericDAO.delete(lVO);
		    }
		    else
		    {
			lVO.setAPP_NAME(accessCO.getParent().split("-")[1]);
			lVO.setWS_NAME(accessCO.getParent().split("-")[0]);
			lVO.setOPERATION(accessCO.getFeName());
			lVO.setTYPE("S");
			
			lVODet = new IMCO_PWS_TMPLT_DET_PARAMVO();
			lVODet.setTEMPLATE_ID(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
			lVODet.setAPP_NAME(accessCO.getParent().split("-")[1]);
			lVODet.setWS_NAME(accessCO.getParent().split("-")[0]);
			lVODet.setOPERATION(accessCO.getFeName());
			// delete from det param table
			accessWebServiceSC = new AccessWebServiceSC();
			accessWebServiceSC.setTempID(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
			accessWebServiceSC.setAppName(accessCO.getParent().split("-")[1]);
			accessWebServiceSC.setServiceName(accessCO.getParent().split("-")[0]);
			accessWebServiceSC.setOperationName(accessCO.getFeName());
			
			// delete from det param table
			accesswebserviceDAO.deleteRecordDetParamFromDB(accessWebServiceSC);
			if(!"".equals(accessCO.getCUSTOM_DETAILS()))
			{
			    jsonFilter = (JSONObject) JSONSerializer.toJSON(accessCO.getCUSTOM_DETAILS());
			    Iterator<String> keys = jsonFilter.keys();
			    while(keys.hasNext())
			    {
				String key = (String) keys.next(); // First key
								   // in
								   // your json
								   // object
				lVODet.setPARAM_ID(key);
				genericDAO.insert(lVODet);
			    }
			}
			
			genericDAO.delete(lVO);

			
		    }
		}
	    }
	}
    }
    
    @Override
    public void awsApprove(AccessWebServiceCO accesswebserviceCO) throws BaseException
    {
	Date systemDate = commonLibBO.returnSystemDateWithTime();
	accesswebserviceCO.getImcoPwsTmpltMasterVO().setAPPROVED_BY(accesswebserviceCO.getUserID());
	accesswebserviceCO.getImcoPwsTmpltMasterVO().setAPPROVED_DATE(accesswebserviceCO.getRunningDate());
	accesswebserviceCO.getImcoPwsTmpltMasterVO().setSTATUS(AccessWebServiceConstant.STATUS_APPROVED);
	accesswebserviceCO.getImcoPwsTmpltMasterVO().setSERVER_APPROVED_DATE(systemDate);
	
	IMCO_PWS_TMPLT_MASTERVO oldAuditVO = (IMCO_PWS_TMPLT_MASTERVO) genericDAO.selectByPK(accesswebserviceCO.getImcoPwsTmpltMasterVO());
	Integer update = genericDAO.update(accesswebserviceCO.getImcoPwsTmpltMasterVO());
	if(update == null || update < 1)
	{
	    throw new BOException(MessageCodes.RECORD_CHANGED);
	}
	
	IMCO_PWS_TMPLT_MASTERVO newAuditVO = new IMCO_PWS_TMPLT_MASTERVO();
	PathPropertyUtil.copyProperties(oldAuditVO, newAuditVO);

	newAuditVO.setAPPROVED_BY(accesswebserviceCO.getUserID());
	newAuditVO.setAPPROVED_DATE(accesswebserviceCO.getRunningDate());
	newAuditVO.setSERVER_APPROVED_DATE(systemDate);
	newAuditVO.setSTATUS(AccessWebServiceConstant.STATUS_APPROVED);
	
	auditBO.callAudit(oldAuditVO, newAuditVO, accesswebserviceCO.getAuditRefCO());
	auditBO.insertAudit(accesswebserviceCO.getAuditRefCO());
    }
    
    @Override
    public void awsDelete(AccessWebServiceCO accesswebserviceCO) throws BaseException
    {
	String channelsLinked=" ";
	List<BigDecimal> tmpltChannelList = accesswebserviceDAO.countTmpltUsrVO(accesswebserviceCO.getImcoPwsTmpltMasterVO().getTEMPLATE_ID());
	for(int i=0;i<tmpltChannelList.size();i++)
	{
	    if(i == tmpltChannelList.size()-1)
	    {
		channelsLinked+=tmpltChannelList.get(i);
	    }
	    else
	    {
		channelsLinked+=String.valueOf(tmpltChannelList.get(i)).concat(",");
	    }
	}
	if(tmpltChannelList.size() > 0)
	{
	    throw new BOException(MessageCodes.SELECTED_TEMPLATE_CANNOT_BE_DELETED,new String[] {channelsLinked} );
	}
	
	Date systemDate = commonLibBO.returnSystemDateWithTime();
	accesswebserviceCO.getImcoPwsTmpltMasterVO().setDELETED_BY(accesswebserviceCO.getUserID());
	accesswebserviceCO.getImcoPwsTmpltMasterVO().setDELETED_DATE(accesswebserviceCO.getRunningDate());
	accesswebserviceCO.getImcoPwsTmpltMasterVO().setSTATUS(AccessWebServiceConstant.STATUS_DELETED);
	accesswebserviceCO.getImcoPwsTmpltMasterVO().setSERVER_DELETED_DATE(systemDate);
	
	IMCO_PWS_TMPLT_MASTERVO oldAuditVO = (IMCO_PWS_TMPLT_MASTERVO) genericDAO.selectByPK(accesswebserviceCO.getImcoPwsTmpltMasterVO());
	Integer update = genericDAO.update(accesswebserviceCO.getImcoPwsTmpltMasterVO());
	if(update == null || update < 1)
	{
	    throw new BOException(MessageCodes.RECORD_CHANGED);
	}
	
	IMCO_PWS_TMPLT_MASTERVO newAuditVO = new IMCO_PWS_TMPLT_MASTERVO();
	PathPropertyUtil.copyProperties(oldAuditVO, newAuditVO);

	newAuditVO.setAPPROVED_BY(accesswebserviceCO.getUserID());
	newAuditVO.setAPPROVED_DATE(accesswebserviceCO.getRunningDate());
	newAuditVO.setSERVER_APPROVED_DATE(systemDate);
	newAuditVO.setSTATUS(AccessWebServiceConstant.STATUS_DELETED);
	
	auditBO.callAudit(oldAuditVO, newAuditVO, accesswebserviceCO.getAuditRefCO());
	auditBO.insertAudit(accesswebserviceCO.getAuditRefCO());
    }
    
    @Override
    public AccessWebServiceCO retrieveSelectedTemplateId(AccessWebServiceSC accessWebServiceSC) throws BaseException
    {
	return accesswebserviceDAO.retrieveSelectedTemplateId(accessWebServiceSC);
    }
    
    @Override
    public List<AccessWebServiceCO> returnServicesFromDb(AccessWebServiceSC accessWebServiceSC) throws BaseException
    {
	AccessWebServiceCO awsCO;
	HashMap<String,String> paramMap;
	JSONObject jsonFilter;
	List<String> returnedParamCheckedList;
	AccessWebServiceSC sc;
	List<AccessWebServiceCO> servicesFromDbListCO = accesswebserviceDAO.returnServicesFromDb(accessWebServiceSC);
	for(int i = 0;i<servicesFromDbListCO.size();i++)
	{
	    awsCO = servicesFromDbListCO.get(i);
	    if(!("All".equals(awsCO.getImcoPwsTmpltDetVO().getWS_NAME())
		    || "All".equals(awsCO.getImcoPwsTmpltDetVO().getOPERATION())))
	    {
		paramMap = new HashMap<String,String>();
		sc = new AccessWebServiceSC();
		sc.setTempID(accessWebServiceSC.getTempID());
		sc.setAccessCO(awsCO);
		returnedParamCheckedList = accesswebserviceDAO.returnedParamCheckedList(sc);
		for(int j = 0; j < returnedParamCheckedList.size(); j++)
		{
		    paramMap.put(returnedParamCheckedList.get(j), returnedParamCheckedList.get(j) + "_include");
		}

//		try
//		{
//		    awsCO.setCUSTOM_DETAILS(JSONUtil.serialize(paramMap, null, null, false, true));
//		}
//		catch(JSONException e)
//		{
//		    // TODO Auto-generated catch block
//		    e.printStackTrace();
//		}
	    }
	}
	return servicesFromDbListCO;
    }
    
    @Override
    public void deleteRecordFromDB(AccessWebServiceSC accessWebServiceSC) throws BaseException
    {
	//delete from det param table
	accesswebserviceDAO.deleteRecordDetParamFromDB(accessWebServiceSC);
	//delete from det table
	accesswebserviceDAO.deleteRecordDetFromDB(accessWebServiceSC);
    }

    public AccessWebServiceDAO getAccesswebserviceDAO()
    {
        return accesswebserviceDAO;
    }

    public void setAccesswebserviceDAO(AccessWebServiceDAO accesswebserviceDAO)
    {
        this.accesswebserviceDAO = accesswebserviceDAO;
    }
}
