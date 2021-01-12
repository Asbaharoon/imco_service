package com.path.imco.vo.dynamicfiles;

import java.math.BigDecimal;
import java.util.Date;

import com.path.dbmaps.vo.IMCO_DYN_FILE_MESSAGEVO;
import com.path.dbmaps.vo.IMCO_DYN_FILE_STRUCTUREVO;
import com.path.dbmaps.vo.IMCO_DYN_FILE_TAGVO;
import com.path.lib.vo.BaseVO;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * DynamicFileEditorCO.java used to
 */
public class DynamicFileStructureCO extends BaseVO
{
	IMCO_DYN_FILE_STRUCTUREVO dyn_FILE_STRUCTUREVO = new IMCO_DYN_FILE_STRUCTUREVO();
	IMCO_DYN_FILE_MESSAGEVO dyn_FILE_MESSAGEVO = new IMCO_DYN_FILE_MESSAGEVO();
	IMCO_DYN_FILE_TAGVO dyn_FILE_TAGVO = new IMCO_DYN_FILE_TAGVO();
	
	private String tags;
	private String tagName;
	private String tagType;
	private BigDecimal startPos;
	private BigDecimal tagLength;
	private String tagColor;
	private String tagLine;
	private String fullText;
	private String fileType;
	private String mode;
	
	/** Xml Variable **/
	private String xmlMessagesGridData;
	private String xmlMsgGridChangedData;
	private String TAG_NAME;
	private String MSG_DESC;
	private String MESSAGE_SAMPLE;
	private String TAG_LIST;
	private String MSG_UPDATE_MODE;
	private String tagValue;
	private String STATUS;
	
	/** common variables **/
	private Date runningDate;
	private String userId;
	private BigDecimal compCode;

	public String getTagValue()
	{
		return tagValue;
	}

	public void setTagValue(String tagValue)
	{
		this.tagValue = tagValue;
	}

	public String getXmlMsgGridChangedData() 
	{
		return xmlMsgGridChangedData;
	}

	public void setXmlMsgGridChangedData(String xmlMsgGridChangedData) 
	{
		this.xmlMsgGridChangedData = xmlMsgGridChangedData;
	}

	public String getMSG_UPDATE_MODE() 
	{
		return MSG_UPDATE_MODE;
	}

	public void setMSG_UPDATE_MODE(String mSG_UPDATE_MODE) 
	{
		MSG_UPDATE_MODE = mSG_UPDATE_MODE;
	}

	public IMCO_DYN_FILE_STRUCTUREVO getDyn_FILE_STRUCTUREVO() 
	{
		return dyn_FILE_STRUCTUREVO;
	}

	public void setDyn_FILE_STRUCTUREVO(IMCO_DYN_FILE_STRUCTUREVO dyn_FILE_STRUCTUREVO)
	{
		this.dyn_FILE_STRUCTUREVO = dyn_FILE_STRUCTUREVO;
	}

	public IMCO_DYN_FILE_MESSAGEVO getDyn_FILE_MESSAGEVO() 
	{
		return dyn_FILE_MESSAGEVO;
	}

	public void setDyn_FILE_MESSAGEVO(IMCO_DYN_FILE_MESSAGEVO dyn_FILE_MESSAGEVO)
	{
		this.dyn_FILE_MESSAGEVO = dyn_FILE_MESSAGEVO;
	}

	public IMCO_DYN_FILE_TAGVO getDyn_FILE_TAGVO() 
	{
		return dyn_FILE_TAGVO;
	}

	public void setDyn_FILE_TAGVO(IMCO_DYN_FILE_TAGVO dyn_FILE_TAGVO)
	{
		this.dyn_FILE_TAGVO = dyn_FILE_TAGVO;
	}

	public String getTags() 
	{
		return tags;
	}

	public void setTags(String tags) 
	{
		this.tags = tags;
	}

	public String getTagName() 
	{
		return tagName;
	}

	public void setTagName(String tagName) 
	{
		this.tagName = tagName;
	}

	public String getTagType() 
	{
		return tagType;
	}

	public void setTagType(String tagType)
	{
		this.tagType = tagType;
	}

	public BigDecimal getStartPos()
	{
		return startPos;
	}

	public void setStartPos(BigDecimal startPos)
	{
		this.startPos = startPos;
	}

	public BigDecimal getTagLength()
	{
		return tagLength;
	}

	public void setTagLength(BigDecimal tagLength)
	{
		this.tagLength = tagLength;
	}

	public String getTagColor() 
	{
		return tagColor;
	}

	public void setTagColor(String tagColor) 
	{
		this.tagColor = tagColor;
	}

	public String getTagLine() 
	{
		return tagLine;
	}

	public void setTagLine(String tagLine)
	{
		this.tagLine = tagLine;
	}

	public String getFullText()
	{
		return fullText;
	}

	public void setFullText(String fullText) 
	{
		this.fullText = fullText;
	}

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType) 
	{
		this.fileType = fileType;
	}

	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode) 
	{
		this.mode = mode;
	}

	public String getXmlMessagesGridData() 
	{
		return xmlMessagesGridData;
	}

	public void setXmlMessagesGridData(String xmlMessagesGridData) 
	{
		this.xmlMessagesGridData = xmlMessagesGridData;
	}

	public String getTAG_NAME()
	{
		return TAG_NAME;
	}

	public void setTAG_NAME(String tAG_NAME)
	{
		TAG_NAME = tAG_NAME;
	}

	public String getMSG_DESC() 
	{
		return MSG_DESC;
	}

	public void setMSG_DESC(String mSG_DESC) 
	{
		MSG_DESC = mSG_DESC;
	}

	public String getMESSAGE_SAMPLE() 
	{
		return MESSAGE_SAMPLE;
	}

	public void setMESSAGE_SAMPLE(String mESSAGE_SAMPLE) 
	{
		MESSAGE_SAMPLE = mESSAGE_SAMPLE;
	}

	public String getTAG_LIST() 
	{
		return TAG_LIST;
	}

	public void setTAG_LIST(String tAG_LIST)
	{
		TAG_LIST = tAG_LIST;
	}

	public Date getRunningDate() 
	{
		return runningDate;
	}

	public void setRunningDate(Date runningDate) 
	{
		this.runningDate = runningDate;
	}
	
	public String getUserId()
    {
		return userId;
    }

    public void setUserId(String userId)
    {
    	this.userId = userId;
    }

	public BigDecimal getCompCode() 
	{
		return compCode;
	}

	public void setCompCode(BigDecimal compCode) 
	{
		this.compCode = compCode;
	}

	public String getSTATUS()
	{
		return STATUS;
	}

	public void setSTATUS(String sTATUS)
	{
		STATUS = sTATUS;
	}
}
