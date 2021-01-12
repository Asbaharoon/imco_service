package com.path.imco.bo.dynamicfiles.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.cookie.CommonCookieAttributeHandler;

import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.MessageCodes;
import com.path.dbmaps.vo.IMCO_DYN_FILE_MESSAGEVO;
import com.path.dbmaps.vo.IMCO_DYN_FILE_STRUCTUREVO;
import com.path.dbmaps.vo.IMCO_DYN_FILE_TAGVO;
import com.path.imco.bo.common.ImcoCommonConstants;
import com.path.imco.bo.dynamicfiles.DynamicFileStructureBO;
import com.path.imco.dao.dynamicfiles.DynamicFileStructureDAO;
import com.path.imco.vo.dynamicfiles.DynamicFileStructureCO;
import com.path.imco.vo.dynamicfiles.DynamicFileStructureSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.StringUtil;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * DynamicFileStructureBOImpl.java used to
 */
public class DynamicFileStructureBOImpl extends BaseBO implements DynamicFileStructureBO
{
	DynamicFileStructureDAO dynamicFileStructureDAO;
	
	public DynamicFileStructureDAO getDynamicFileStructureDAO()
	{
		return dynamicFileStructureDAO;
	}
	
	public void setDynamicFileStructureDAO(DynamicFileStructureDAO dynamicFileStructureDAO)
	{
		this.dynamicFileStructureDAO = dynamicFileStructureDAO;
	}
	
	public int returnDynamicFileStructureListCount(DynamicFileStructureSC criteria) throws BaseException
	{
		return dynamicFileStructureDAO.returnDynamicFileStructureListCount(criteria);
	}
		
	public List returnDynamicFileStructureList(DynamicFileStructureSC criteria) throws BaseException
	{
		return dynamicFileStructureDAO.returnDynamicFileStructureList(criteria);
	}
			
	@Override
	public int returnDynamicFileStructureMessageListCount(DynamicFileStructureSC criteria) throws BaseException 
	{
		criteria.setStatus(ImcoCommonConstants.STATUS_ACTIVE);
		return dynamicFileStructureDAO.returnDynamicFileStructureMessageListCount(criteria);
	}
	
	@Override
	public List returnDynamicXmlFileStructureMessageList(DynamicFileStructureSC criteria) throws BaseException 
	{
		List msgList = dynamicFileStructureDAO.returnDynamicFileStructureMessageList(criteria);
		List tagList = null;
		DynamicFileStructureCO msgCo = null;
		DynamicFileStructureCO tagCo = null;
		StringBuilder selectedTags = null;
		for(int i = 0; i<msgList.size(); i++)
		{
			selectedTags = new StringBuilder();
			msgCo = (DynamicFileStructureCO) msgList.get(i);
			criteria.setMessageId(msgCo.getDyn_FILE_MESSAGEVO().getMESSAGE_ID());
			tagList = dynamicFileStructureDAO.returnDynamicFileStructureTagsList(criteria);
			for(int j = 0; j< tagList.size(); j++)
			{
				tagCo = (DynamicFileStructureCO) tagList.get(j);
				selectedTags.append(tagCo.getDyn_FILE_TAGVO().getTAG_NAME()).append(",.,");
				selectedTags.append((tagCo.getDyn_FILE_TAGVO().getPARENT_PATH()==null || "null".equals(tagCo.getDyn_FILE_TAGVO().getPARENT_PATH())?"":tagCo.getDyn_FILE_TAGVO().getPARENT_PATH())).append(",.,");
				selectedTags.append(tagCo.getDyn_FILE_TAGVO().getIS_MULTIPLE_YN()).append(",.,");
				selectedTags.append(tagCo.getDyn_FILE_TAGVO().getTAG_COLOR()).append(",.,");
				selectedTags.append(tagCo.getDyn_FILE_TAGVO().getJOB_TYPE()).append(",.,");
				selectedTags.append(tagCo.getDyn_FILE_TAGVO().getDATA_TYPE()).append(",.,");
				selectedTags.append(tagCo.getDyn_FILE_TAGVO().getEXPRESSION()).append(",.,");
				selectedTags.append(tagCo.getDyn_FILE_TAGVO().getDESCRIPTION()==null?"":tagCo.getDyn_FILE_TAGVO().getDESCRIPTION()).append(",.,");
				selectedTags.append(tagCo.getDyn_FILE_TAGVO().getTAGS_ID()).append(",.,");
				selectedTags.append(tagCo.getDyn_FILE_TAGVO().getIS_ATTR_YN()).append(",.,");
				selectedTags.append("A").append(",.,");
				selectedTags.append(".&.");
			}
			((DynamicFileStructureCO) msgList.get(i)).setTAG_LIST(selectedTags.toString());
			((DynamicFileStructureCO) msgList.get(i)).setMSG_UPDATE_MODE("Y");
		}
		return msgList;
	}
	
	@Override
	public List returnDynamicTextFileStructureMessageList(DynamicFileStructureSC criteria) throws BaseException 
	{
		IMCO_DYN_FILE_TAGVO imco_DYN_FILE_TAGVO = null;
		criteria.setStatus(ImcoCommonConstants.STATUS_ACTIVE);
		List<DynamicFileStructureCO> dynamicFileStructureMessageCOs = dynamicFileStructureDAO.returnDynamicFileStructureMessageList(criteria);
		List<DynamicFileStructureCO> dynamicFileStructureCOs = null;
		for(int i=0; i<dynamicFileStructureMessageCOs.size(); i++)
		{
			IMCO_DYN_FILE_STRUCTUREVO imco_DYN_FILE_STRUCTUREVO = dynamicFileStructureMessageCOs.get(i).getDyn_FILE_STRUCTUREVO();
			IMCO_DYN_FILE_MESSAGEVO imco_DYN_FILE_MESSAGEVO = dynamicFileStructureMessageCOs.get(i).getDyn_FILE_MESSAGEVO();
			criteria.setMessageId(imco_DYN_FILE_MESSAGEVO.getMESSAGE_ID());
			dynamicFileStructureCOs = dynamicFileStructureDAO.returnDynamicFileStructureTagsList(criteria);
			
			String tags = "";
			for(int j=0; j<dynamicFileStructureCOs.size(); j++)
			{
				imco_DYN_FILE_TAGVO = dynamicFileStructureCOs.get(j).getDyn_FILE_TAGVO();
				if(imco_DYN_FILE_STRUCTUREVO.getEXTRACTION_CRITERIA().equals("PR"))
				{
					tags += imco_DYN_FILE_TAGVO.getTAGS_ID()+":"+imco_DYN_FILE_TAGVO.getTAG_NAME()+":"+imco_DYN_FILE_TAGVO.getSTART_POS()+":"+imco_DYN_FILE_TAGVO.getINDEX_NO()+":"
							+imco_DYN_FILE_TAGVO.getTAG_LENGTH()+":"+imco_DYN_FILE_TAGVO.getTAG_COLOR()+":"+imco_DYN_FILE_TAGVO.getTAG_VALUE()+": "+" : "+",";
				}
				else
				{
					tags += imco_DYN_FILE_TAGVO.getTAGS_ID()+":"+imco_DYN_FILE_TAGVO.getTAG_NAME()+":"+imco_DYN_FILE_TAGVO.getINDEX_NO()+":"+imco_DYN_FILE_TAGVO.getINDEX_NO()+":"
						 +  imco_DYN_FILE_TAGVO.getTAG_LENGTH()+":"+imco_DYN_FILE_TAGVO.getTAG_COLOR()+":"+imco_DYN_FILE_TAGVO.getTAG_VALUE()+":"+" : "+",";
				}
			}
			DynamicFileStructureCO  dynamicFileStructureCO  =  dynamicFileStructureMessageCOs.get(i);
			dynamicFileStructureCO.setTags(tags);
			dynamicFileStructureMessageCOs.set(i, dynamicFileStructureCO);
		}
		return dynamicFileStructureMessageCOs;
	}
	
	@Override
	public DynamicFileStructureCO saveDynamicTextFileStructure(DynamicFileStructureCO dynamicFileStructureCO , HashMap<String,Object> GridsDataMap) throws BaseException 
	{
		IMCO_DYN_FILE_STRUCTUREVO imco_DYN_FILE_STRUCTUREVO  = dynamicFileStructureCO.getDyn_FILE_STRUCTUREVO();
		
		//save Dynamic File Structure
		if(ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(imco_DYN_FILE_STRUCTUREVO.getFILE_STRUCTURE_ID()))
    	{
			imco_DYN_FILE_STRUCTUREVO.setFILE_SAMPLE(dynamicFileStructureCO.getFullText());
			imco_DYN_FILE_STRUCTUREVO.setSTATUS(ImcoCommonConstants.STATUS_ACTIVE);
			imco_DYN_FILE_STRUCTUREVO.setCREATED_BY(dynamicFileStructureCO.getUserId());
			imco_DYN_FILE_STRUCTUREVO.setCREATED_DATE(commonLibBO.addSystemTimeToDate(dynamicFileStructureCO.getRunningDate()));
			dynamicFileStructureDAO.insertFileStructure(imco_DYN_FILE_STRUCTUREVO);
    	}
		//Update Dynamic File Structure
		else
		{
			imco_DYN_FILE_STRUCTUREVO.setFILE_SAMPLE(dynamicFileStructureCO.getFullText());
			imco_DYN_FILE_STRUCTUREVO.setMODIFIED_BY(dynamicFileStructureCO.getUserId());
			imco_DYN_FILE_STRUCTUREVO.setMODIFIED_DATE(commonLibBO.addSystemTimeToDate(dynamicFileStructureCO.getRunningDate()));
    		Integer row = genericDAO.update(imco_DYN_FILE_STRUCTUREVO);
    		
    		if (row == null || row < 1)
    		{
    			throw new BOException(MessageCodes.RECORD_CHANGED);
    		}
		}
		//Save/Update/Dete Messages and Tags
		saveDynamicFileTextMessageAndTagGrids(GridsDataMap , imco_DYN_FILE_STRUCTUREVO.getFILE_STRUCTURE_ID(), dynamicFileStructureCO);
		
		dynamicFileStructureCO.setDyn_FILE_STRUCTUREVO(imco_DYN_FILE_STRUCTUREVO);
		return dynamicFileStructureCO;
	}
	
	@Override
	public DynamicFileStructureCO saveDynamicXmlFileStructure(DynamicFileStructureCO fileStructureCO, List lstAllRec, List lstDelete)
			throws BaseException {
		IMCO_DYN_FILE_MESSAGEVO file_MESSAGEVO = null;
		IMCO_DYN_FILE_TAGVO file_TAGSVO = null;
		
		DynamicFileStructureCO co = null;
		int row = 0;
		String tagRows[] = null;
		String[] cols = null;
		fileStructureCO.getDyn_FILE_STRUCTUREVO().setCREATED_BY(fileStructureCO.getUserId());
		fileStructureCO.getDyn_FILE_STRUCTUREVO().setCREATED_DATE(commonLibBO.addSystemTimeToDate(fileStructureCO.getRunningDate()));
		fileStructureCO.getDyn_FILE_STRUCTUREVO().setSTATUS(ImcoCommonConstants.STATUS_ACTIVE);
		
		if(fileStructureCO.getDyn_FILE_STRUCTUREVO().getFILE_STRUCTURE_ID() == null 
				|| ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(fileStructureCO.getDyn_FILE_STRUCTUREVO().getFILE_STRUCTURE_ID()))
		{//Add new File Structure 
			dynamicFileStructureDAO.insertFileStructure(fileStructureCO.getDyn_FILE_STRUCTUREVO());
						
			//Add messages
			for(int i=0; i< lstAllRec.size(); i++)
			{
				co = (DynamicFileStructureCO) lstAllRec.get(i);
				if(co != null)
				{
					file_MESSAGEVO = new IMCO_DYN_FILE_MESSAGEVO();
					file_MESSAGEVO.setFILE_STRUCTURE_ID(fileStructureCO.getDyn_FILE_STRUCTUREVO().getFILE_STRUCTURE_ID());
					file_MESSAGEVO.setIDENTIFIER(co.getTAG_NAME());
					file_MESSAGEVO.setDESCRIPTION(co.getDyn_FILE_MESSAGEVO().getDESCRIPTION());
					file_MESSAGEVO.setMESSAGE_SAMPLE(co.getMESSAGE_SAMPLE());
					file_MESSAGEVO.setCREATED_BY(fileStructureCO.getUserId());
					file_MESSAGEVO.setCREATED_DATE(commonLibBO.addSystemTimeToDate(fileStructureCO.getRunningDate()));
					file_MESSAGEVO.setSTATUS(ImcoCommonConstants.STATUS_ACTIVE);
					
					dynamicFileStructureDAO.insertFileMessage(file_MESSAGEVO);
					
					tagRows = co.getTAG_LIST().split(".&.");
					for(int a=0; a<tagRows.length; a++)
					{
						cols = tagRows[a].split(",.,");
						
						file_TAGSVO = new IMCO_DYN_FILE_TAGVO();

						file_TAGSVO.setMESSAGE_ID(file_MESSAGEVO.getMESSAGE_ID());
						file_TAGSVO.setTAG_NAME(cols[0]);
						file_TAGSVO.setPARENT_PATH(cols[1]);
						file_TAGSVO.setIS_MULTIPLE_YN(cols[2]);
						file_TAGSVO.setTAG_COLOR(cols[3]);
						file_TAGSVO.setJOB_TYPE(""/*cols[4]*/);
						file_TAGSVO.setDATA_TYPE(cols[5]);
						file_TAGSVO.setEXPRESSION(cols.length>6?cols[6]:"");
						file_TAGSVO.setDESCRIPTION(cols.length>7?cols[7]:"");
						file_TAGSVO.setIS_ATTR_YN(cols[9]);
						file_TAGSVO.setCREATED_BY(fileStructureCO.getUserId());
						file_TAGSVO.setCREATED_DATE(commonLibBO.addSystemTimeToDate(fileStructureCO.getRunningDate()));
						
						genericDAO.insert(file_TAGSVO);
					}//end tags loop
				}//end if
			}//end msgs loop
		}//end if
		else
		{// Update records
			fileStructureCO.getDyn_FILE_STRUCTUREVO().setMODIFIED_BY(fileStructureCO.getUserId());
			fileStructureCO.getDyn_FILE_STRUCTUREVO().setMODIFIED_DATE(commonLibBO.addSystemTimeToDate(fileStructureCO.getRunningDate()));
			genericDAO.update(fileStructureCO.getDyn_FILE_STRUCTUREVO());
			//Updated messages
			for(int i=0; i< lstAllRec.size(); i++)
			{
				co = (DynamicFileStructureCO) lstAllRec.get(i);
				if(co != null)
				{
					file_MESSAGEVO = co.getDyn_FILE_MESSAGEVO();
					file_MESSAGEVO.setFILE_STRUCTURE_ID(fileStructureCO.getDyn_FILE_STRUCTUREVO().getFILE_STRUCTURE_ID());
					file_MESSAGEVO.setIDENTIFIER(co.getTAG_NAME());
					file_MESSAGEVO.setDESCRIPTION(co.getDyn_FILE_MESSAGEVO().getDESCRIPTION());
					file_MESSAGEVO.setMESSAGE_SAMPLE(co.getMESSAGE_SAMPLE());
					file_MESSAGEVO.setMODIFIED_BY(fileStructureCO.getUserId());
					file_MESSAGEVO.setMODIFIED_DATE(commonLibBO.addSystemTimeToDate(fileStructureCO.getRunningDate()));
					file_MESSAGEVO.setSTATUS(ImcoCommonConstants.STATUS_ACTIVE);
					
					row = genericDAO.update(file_MESSAGEVO);
					if(row < 1)
					{
						file_MESSAGEVO.setMODIFIED_BY(null);
						file_MESSAGEVO.setMODIFIED_DATE(null);
						
						file_MESSAGEVO.setCREATED_BY(fileStructureCO.getUserId());
						file_MESSAGEVO.setCREATED_DATE(commonLibBO.addSystemTimeToDate(fileStructureCO.getRunningDate()));
						dynamicFileStructureDAO.insertFileMessage(file_MESSAGEVO);
					}
					
					tagRows = co.getTAG_LIST().split(".&.");
					for(int a=0; a<tagRows.length; a++)
					{
						cols = tagRows[a].split(",.,");
						
						file_TAGSVO = co.getDyn_FILE_TAGVO();

						file_TAGSVO.setMESSAGE_ID(file_MESSAGEVO.getMESSAGE_ID());
						file_TAGSVO.setTAG_NAME(cols[0]);
						file_TAGSVO.setPARENT_PATH(cols[1]);
						file_TAGSVO.setIS_MULTIPLE_YN(cols[2]);
						file_TAGSVO.setTAG_COLOR(cols[3]);
						file_TAGSVO.setJOB_TYPE(""/*cols[4]*/);
						file_TAGSVO.setDATA_TYPE(cols[5]);
						file_TAGSVO.setEXPRESSION(cols.length>6?cols[6]:"");
						file_TAGSVO.setDESCRIPTION(cols.length>7?cols[7]:"");
						file_TAGSVO.setTAGS_ID(cols.length>8 && StringUtil.isNotEmpty(cols[8])?(new BigDecimal(cols[8])):null);
						file_TAGSVO.setIS_ATTR_YN(cols[9]);
						
							
						if(file_TAGSVO.getTAGS_ID() != null && file_TAGSVO.getTAGS_ID() != ConstantsCommon.EMPTY_BIGDECIMAL_VALUE)
						{
							file_TAGSVO.setMODIFIED_BY(fileStructureCO.getUserId());
							file_TAGSVO.setMODIFIED_DATE(commonLibBO.addSystemTimeToDate(fileStructureCO.getRunningDate()));
							row = genericDAO.update(file_TAGSVO);
						}
						else	
						{
							file_TAGSVO.setMESSAGE_ID(file_MESSAGEVO.getMESSAGE_ID());
							file_TAGSVO.setCREATED_BY(fileStructureCO.getUserId());
							file_TAGSVO.setCREATED_DATE(commonLibBO.addSystemTimeToDate(fileStructureCO.getRunningDate()));
							genericDAO.insert(file_TAGSVO);
						}
					}//end tags loop
				}//end if
			}//end msgs loop
			for(int i=0; i< lstDelete.size();i++)
			{
				co = (DynamicFileStructureCO) lstDelete.get(i);
				if(co != null && ImcoCommonConstants.STATUS_DELETED.equals(co.getDyn_FILE_MESSAGEVO().getSTATUS()))
				{
					co.getDyn_FILE_MESSAGEVO().setDELETED_BY(fileStructureCO.getUserId());
					co.getDyn_FILE_MESSAGEVO().setDELETED_DATE(commonLibBO.addSystemTimeToDate(fileStructureCO.getRunningDate()));
					genericDAO.update(co.getDyn_FILE_MESSAGEVO());
				}
			}
		}//end else
		return fileStructureCO;
	}
	
	@Override
	public void deleteDynamicFileStructure(DynamicFileStructureCO dynamicFileStructureCO) throws BaseException {
		
		IMCO_DYN_FILE_STRUCTUREVO dynamic_FILE_STRUCTUREVO = dynamicFileStructureCO.getDyn_FILE_STRUCTUREVO();
		dynamic_FILE_STRUCTUREVO.setSTATUS(ImcoCommonConstants.STATUS_DELETED);
		dynamic_FILE_STRUCTUREVO.setDELETED_BY(dynamicFileStructureCO.getUserId());
		dynamic_FILE_STRUCTUREVO.setDELETED_DATE(commonLibBO.addSystemTimeToDate(dynamicFileStructureCO.getRunningDate()));
		Integer update = genericDAO.update(dynamic_FILE_STRUCTUREVO);
		if(update == null || update < 1)
		{
		    throw new BOException(MessageCodes.RECORD_CHANGED);
		}
	}
	
	/**
	 * Save Message and Tags
	 * @param paramMap
	 * @param dynamicStructureId
	 * @param dynamicFileStructureCO
	 * @throws BaseException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void saveDynamicFileTextMessageAndTagGrids(HashMap<String,Object> paramMap, BigDecimal dynamicStructureId, DynamicFileStructureCO dynamicFileStructureCO)
			 throws BaseException {
		List messageListAdd    =  (ArrayList<DynamicFileStructureCO>) paramMap.get("messageAdd");
		List messageListUpdate =  (ArrayList<DynamicFileStructureCO>) paramMap.get("messageModify"); 
		List messageListDelete =  (ArrayList<DynamicFileStructureCO>) paramMap.get("messageDelete");
		
		DynamicFileStructureCO dynamicFileStructure  = null;
		IMCO_DYN_FILE_MESSAGEVO imco_DYN_FILE_MESSAGEVO = null;
		IMCO_DYN_FILE_TAGVO imco_DYN_FILE_TAGVO = null;
		String tagData[] = null;
		String tagsData[] = null;
		
		IMCO_DYN_FILE_STRUCTUREVO imco_DYN_FILE_STRUCTUREVO = dynamicFileStructureCO.getDyn_FILE_STRUCTUREVO();
		DynamicFileStructureSC criteria = new DynamicFileStructureSC();
		
		//delete Dynamic File Structure Message/Tags from Grid
    	if(messageListDelete != null && messageListDelete.size() > 0)
		{
    		for(int i=0; i<messageListDelete.size(); i++)
        	{
    			dynamicFileStructure = (DynamicFileStructureCO) messageListDelete.get(i);
    			imco_DYN_FILE_MESSAGEVO = dynamicFileStructure.getDyn_FILE_MESSAGEVO();
        		genericDAO.delete(imco_DYN_FILE_MESSAGEVO);
        		criteria.setMessageId(imco_DYN_FILE_MESSAGEVO.getMESSAGE_ID());
        		criteria.setStatus(ImcoCommonConstants.STATUS_ACTIVE);
        		//return all tags with message id
        		List<DynamicFileStructureCO> dynamicFileStructureCOs =  dynamicFileStructureDAO.returnDynamicFileStructureTagsList(criteria);
        		for(int j=0; j<dynamicFileStructureCOs.size(); j++)
				{
        			imco_DYN_FILE_TAGVO = dynamicFileStructureCOs.get(j).getDyn_FILE_TAGVO();
        			//delete tags
        			genericDAO.delete(imco_DYN_FILE_TAGVO);
				}
        	}
		}
    	
    	//add Messages And Tags
    	if(messageListAdd != null && messageListAdd.size() > 0)
		{
    		for(int i=0; i<messageListAdd.size(); i++)
        	{
    			dynamicFileStructure = (DynamicFileStructureCO) messageListAdd.get(i);
    			imco_DYN_FILE_MESSAGEVO = dynamicFileStructure.getDyn_FILE_MESSAGEVO();
    			imco_DYN_FILE_MESSAGEVO.setFILE_STRUCTURE_ID(dynamicStructureId);
    			imco_DYN_FILE_MESSAGEVO.setCREATED_BY(dynamicFileStructureCO.getUserId());
    			imco_DYN_FILE_MESSAGEVO.setCREATED_DATE(commonLibBO.addSystemTimeToDate(dynamicFileStructureCO.getRunningDate()));
    			imco_DYN_FILE_MESSAGEVO.setSTATUS(ImcoCommonConstants.STATUS_ACTIVE);
    			
    			//save Dynamic Message
    			dynamicFileStructureDAO.insertFileMessage(imco_DYN_FILE_MESSAGEVO);
    			
    			if(StringUtil.isNotEmpty(dynamicFileStructure.getTags()))
    			{
    				tagsData = dynamicFileStructure.getTags().split(",");
    				for(int j=0; j<tagsData.length; j++)
    				{
    					tagData = tagsData[j].split(":");
    					imco_DYN_FILE_TAGVO = new IMCO_DYN_FILE_TAGVO();
    					imco_DYN_FILE_TAGVO.setMESSAGE_ID(imco_DYN_FILE_MESSAGEVO.getMESSAGE_ID());
    					imco_DYN_FILE_TAGVO.setTAG_NAME(tagData[1]);
    					if(imco_DYN_FILE_STRUCTUREVO.getEXTRACTION_CRITERIA().equals("PR"))
    					{
    						imco_DYN_FILE_TAGVO.setSTART_POS(BigDecimal.valueOf(Long.valueOf(tagData[2])));
    					}
    					else if(imco_DYN_FILE_STRUCTUREVO.getEXTRACTION_CRITERIA().equals("DE"))
    					{
    						imco_DYN_FILE_TAGVO.setINDEX_NO(BigDecimal.valueOf(Long.valueOf(tagData[2])));
    					}
    					imco_DYN_FILE_TAGVO.setTAG_LENGTH(BigDecimal.valueOf(Long.valueOf(tagData[4])));
    					imco_DYN_FILE_TAGVO.setTAG_COLOR(tagData[5]);
    					imco_DYN_FILE_TAGVO.setTAG_VALUE(tagData[6]);
    					imco_DYN_FILE_TAGVO.setCREATED_BY(dynamicFileStructureCO.getUserId());
    					imco_DYN_FILE_TAGVO.setCREATED_DATE(commonLibBO.addSystemTimeToDate(dynamicFileStructureCO.getRunningDate()));
    					//save Dynamic Tags
    					genericDAO.insert(imco_DYN_FILE_TAGVO);
    				}
    			}
        	}
		}
    	//Update Messages and Tags
    	if(messageListUpdate != null && messageListUpdate.size() > 0)
		{
    		for(int i=0; i<messageListUpdate.size(); i++)
        	{
    			dynamicFileStructure = (DynamicFileStructureCO) messageListUpdate.get(i);
    			imco_DYN_FILE_MESSAGEVO = dynamicFileStructure.getDyn_FILE_MESSAGEVO();
    			imco_DYN_FILE_MESSAGEVO.setFILE_STRUCTURE_ID(dynamicStructureId);
    			imco_DYN_FILE_MESSAGEVO.setMODIFIED_BY(dynamicFileStructureCO.getUserId());
    			imco_DYN_FILE_MESSAGEVO.setMODIFIED_DATE(commonLibBO.addSystemTimeToDate(dynamicFileStructureCO.getRunningDate()));
    			
    			//save Message
    			genericDAO.update(imco_DYN_FILE_MESSAGEVO);
    			
    			tagsData = dynamicFileStructure.getTags().split(",");
    	    	criteria.setMessageId(imco_DYN_FILE_MESSAGEVO.getMESSAGE_ID());
    	    	
    	    	//return all tags with Message Id
    	    	List<DynamicFileStructureCO> dynamicFileStructureCOs =   dynamicFileStructureDAO.returnDynamicFileStructureTagsList(criteria);
    	    	
    	    	if(dynamicFileStructureCOs != null && dynamicFileStructureCOs.size() > 0)
    	    	{
    	    		//delete all Tags
    	    		for(int k=0; k<dynamicFileStructureCOs.size(); k++)
        			{
    	    			imco_DYN_FILE_TAGVO = dynamicFileStructureCOs.get(k).getDyn_FILE_TAGVO();
    	    			genericDAO.delete(imco_DYN_FILE_TAGVO);
        			}
    	    	}
    	    	
    	    	for(int j=0; j<tagsData.length; j++)
    	    	{
    	    		tagData = tagsData[j].split(":");
    	    		imco_DYN_FILE_TAGVO = new IMCO_DYN_FILE_TAGVO();
    	    		imco_DYN_FILE_TAGVO.setMESSAGE_ID(imco_DYN_FILE_MESSAGEVO.getMESSAGE_ID());
    	    		imco_DYN_FILE_TAGVO.setTAG_NAME(tagData[1]);
    	    		if(imco_DYN_FILE_STRUCTUREVO.getEXTRACTION_CRITERIA().equals("PR"))
    	    		{
    	    			imco_DYN_FILE_TAGVO.setSTART_POS(BigDecimal.valueOf(Long.valueOf(tagData[2])));
    	    		}
    	    		else if(imco_DYN_FILE_STRUCTUREVO.getEXTRACTION_CRITERIA().equals("DE"))
    	    		{
    	    			imco_DYN_FILE_TAGVO.setINDEX_NO(BigDecimal.valueOf(Long.valueOf(tagData[2])));
    	    		}
    	    		imco_DYN_FILE_TAGVO.setTAG_LENGTH(BigDecimal.valueOf(Long.valueOf(tagData[4])));
    	    		imco_DYN_FILE_TAGVO.setTAG_COLOR(tagData[5]);
    	    		imco_DYN_FILE_TAGVO.setTAG_VALUE(tagData[6]);
    	    		
    	    		imco_DYN_FILE_TAGVO.setCREATED_BY(dynamicFileStructureCO.getUserId());
    	    		imco_DYN_FILE_TAGVO.setCREATED_DATE(commonLibBO.addSystemTimeToDate(dynamicFileStructureCO.getRunningDate()));
    	    		imco_DYN_FILE_TAGVO.setMODIFIED_BY(dynamicFileStructureCO.getUserId());
    	    		imco_DYN_FILE_TAGVO.setMODIFIED_DATE(commonLibBO.addSystemTimeToDate(dynamicFileStructureCO.getRunningDate()));
    	    		//Save Tags
    	    		genericDAO.insert(imco_DYN_FILE_TAGVO);
    	    	}
        	}
		}
	}
	
	@Override
	public DynamicFileStructureCO returnDynamicFileStructureDetails(DynamicFileStructureSC dynamicFileStructureSC) throws BaseException 
	{
		return dynamicFileStructureDAO.returnDynamicFileStructureDetails(dynamicFileStructureSC);
	}

	@Override
	public int checkFileRefUnique(DynamicFileStructureSC dynamicFileStructureSC) throws BaseException
	{
		return dynamicFileStructureDAO.checkFileRefUnique(dynamicFileStructureSC);
	}

}
