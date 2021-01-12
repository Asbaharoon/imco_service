package com.path.imco.bo.channel.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;

import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.MessageCodes;
import com.path.bo.common.login.LoginBO;
import com.path.dbmaps.vo.AXSVO;
import com.path.dbmaps.vo.GTW_CHANNELVO;
import com.path.dbmaps.vo.GTW_CHANNEL_DETVO;
import com.path.dbmaps.vo.GTW_PWS_TMPLT_MASTERVO;
import com.path.dbmaps.vo.GTW_PWS_TMPLT_USRVO;
import com.path.dbmaps.vo.OPTVO;
import com.path.dbmaps.vo.OPT_EXTENDEDVO;
import com.path.dbmaps.vo.SYS_PARAM_SCREEN_DISPLAYVO;
import com.path.dbmaps.vo.USRVO;
import com.path.imco.bo.channel.ChannelBO;
import com.path.imco.bo.channel.ChannelConstant;
import com.path.imco.dao.channel.ChannelDAO;
import com.path.imco.vo.channel.ChannelCO;
import com.path.imco.vo.channel.ChannelSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.FileUtil;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.common.util.SecurityUtils;
import com.path.lib.common.util.StringUtil;
import com.path.vo.admin.user.UsrCO;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * ChannelBOImpl.java used to
 */
public class ChannelBOImpl extends BaseBO implements ChannelBO
{
    private ChannelDAO channelDAO;
    private LoginBO loginBO;
    
    private static final String PASS_ENC_KEY = "P@th_pwdLDAPauth";

    /**
     * Method used to return Count of Channel
     * 
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws BaseException
     */
    @Override
    public int returnChannelListCount(ChannelSC criteria) throws BaseException
    {
    	return channelDAO.returnChannelListCount(criteria);
    }

    /**
     * Method used to return List of Channel
     * 
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws BaseException
     */
    @Override
    public List returnChannelList(ChannelSC criteria) throws BaseException
    {
    	return channelDAO.returnChannelList(criteria);
    }

    public ChannelDAO getChannelDAO()
    {
	return channelDAO;
    }

    public void setChannelDAO(ChannelDAO channelDAO)
    {
	this.channelDAO = channelDAO;
    }

    @Override
    public ChannelCO saveNew(ChannelCO channelCO,ArrayList<GTW_PWS_TMPLT_MASTERVO> lstMultiselect) throws BaseException
    {
	    GTW_CHANNELVO imApiChannelsVO = channelCO.getImApiChannelsVO();
		//set the user id uppercase
		imApiChannelsVO.setUSER_ID(imApiChannelsVO.getUSER_ID().toUpperCase());
		
		imApiChannelsVO.setSTATUS(ConstantsCommon.ACTIVE_RECORD);
		
		if(NumberUtil.isEmptyDecimal(imApiChannelsVO.getCHANNEL_ID()))
		{
		    throw new BOException(MessageCodes.PLEASE_SPECIFY_CHANNEL_ID);
		}
	
		GTW_PWS_TMPLT_USRVO imcoPwsTmpltUserVO;
		
		//Encrypt Password by Pass Encryption Key and set in password Field
		if(StringUtil.isNotEmpty(imApiChannelsVO.getHTTP_PASSWORD()))
    	{
			 imApiChannelsVO.setHTTP_PASSWORD(SecurityUtils.encryptAES(PASS_ENC_KEY, imApiChannelsVO.getHTTP_PASSWORD()));
    	}
	    
		if (ConstantsCommon.YES.equals(channelCO.getUpdateMode())) 
		{
			imApiChannelsVO.setMODIFIED_BY(channelCO.getUserID());
			imApiChannelsVO.setMODIFIED_DATE(channelCO.getRunningDate());
			imApiChannelsVO.setSERVER_MODIFIED_DATE(commonLibBO.returnSystemDateWithTime());

			Integer row = genericDAO.update(imApiChannelsVO);
			if (row == null || row < 1) {
				throw new BOException(MessageCodes.RECORD_CHANGED);
			}
			ChannelCO auditObj = (ChannelCO) channelCO.getAuditObj();
			auditBO.callAudit(auditObj.getImApiChannelsVO(), imApiChannelsVO, channelCO.getAuditRefCO());
			// delete all machine id related to this channel id they will be inserted again
			channelDAO.deleteAllTemplateId(channelCO);
		} else 
		{
			imApiChannelsVO.setCREATED_BY(channelCO.getUserID());
			imApiChannelsVO.setCREATED_DATE(channelCO.getRunningDate());
			imApiChannelsVO.setSERVER_CREATED_DATE(commonLibBO.returnSystemDateWithTime());

			genericDAO.insert(imApiChannelsVO);

			channelCO.setImApiChannelsVO(imApiChannelsVO);
			// insert opts
			// insertChannelOpts(channelCO);
			auditBO.callAudit(null, imApiChannelsVO, channelCO.getAuditRefCO());

		}
		for(int j = 0; j < lstMultiselect.size(); j++)
		{	
		    imcoPwsTmpltUserVO = new GTW_PWS_TMPLT_USRVO();
		    imcoPwsTmpltUserVO.setCHANNEL_ID(imApiChannelsVO.getCHANNEL_ID());
		    imcoPwsTmpltUserVO.setUSER_ID(imApiChannelsVO.getUSER_ID());
		    imcoPwsTmpltUserVO.setTEMPLATE_ID(lstMultiselect.get(j).getTEMPLATE_ID());
		    genericDAO.insert(imcoPwsTmpltUserVO);
		}
		
		
		auditBO.insertAudit(channelCO.getAuditRefCO());
		
		
		return channelCO;
    }
    
    

	/**
	 * insert Channel OPTs
	 * @param channelCO
	 * @throws BaseException
	 */
    private void insertChannelOpts(ChannelCO channelCO) throws BaseException
    {
    	
    	OPTVO parentOptVo = new OPTVO(); 
    	/**
		 * Used as criteria to deletion operation
		 */
    	parentOptVo.setPARENT_REF(ChannelConstant.CHANNEL_PARENT_REF);
    	
    	/**
		 * Set the parent ref here again to reset the old value
		 */
    	parentOptVo.setPROG_REF(ChannelConstant.CHANNEL_PROG_REF+channelCO.getImApiChannelsVO().getCHANNEL_ID());
		
		// get order
		/**
		 * Create Batch Menu Reference
		 */
    	parentOptVo.setAPP_NAME(channelCO.getAppName());
    	parentOptVo.setPROG_DESC(channelCO.getImApiChannelsVO().getDESCRIPTION());
    	parentOptVo.setPROG_DESC_FR(channelCO.getImApiChannelsVO().getDESCRIPTION());
    	parentOptVo.setPROG_DESC_ARAB(channelCO.getImApiChannelsVO().getDESCRIPTION());
    	parentOptVo.setCATEG_ID(new BigDecimal(6));
    	parentOptVo.setMENU_TITLE_ENG(channelCO.getImApiChannelsVO().getDESCRIPTION());
    	parentOptVo.setMENU_TITLE_FR(channelCO.getImApiChannelsVO().getDESCRIPTION());
    	parentOptVo.setMENU_TITLE_ARAB(channelCO.getImApiChannelsVO().getDESCRIPTION());
		
    	parentOptVo.setPROG_ORDER(BigDecimal.ONE);
    	parentOptVo.setAUDIT_RETRIEVE(BigDecimal.ONE.toString());
    	parentOptVo.setDISP_ORDER(channelDAO.returnLatestDisplayOrder(parentOptVo));
    	parentOptVo.setPROG_TYPE(ConstantsCommon.PROG_TYPE_PROGRAM);
		
		// insert Parent OPT
		genericDAO.insert(parentOptVo);
		
		
		AXSVO parentAxs =  new AXSVO();
		parentAxs.setUSER_ID(channelCO.getUserID());
		parentAxs.setCOMP_CODE(channelCO.getCompCode());
		parentAxs.setBRANCH_CODE(channelCO.getBranchCode());
		parentAxs.setAPP_NAME(channelCO.getAppName());
		// logged in user @check with paty 
		parentAxs.setCREATED_BY(channelCO.getUserID());
		
		/**
		 * Note: Granting Privilege will be occurs only in Approval.
		 */
		parentAxs.setSTATUS(ChannelConstant.STATUS_APPROVED);
		parentAxs.setDIRECT_ACCESS(channelCO.getUserID());
		parentAxs.setDATE_CREATED(channelCO.getImApiChannelsVO().getCREATED_DATE());
		parentAxs.setPROG_REF(parentOptVo.getPROG_REF());
		genericDAO.insert(parentAxs);
		
		
		
		
		
		OPTVO childOptVo = new OPTVO(); 
    	/**
		 * set parent prog ref
		 */
		childOptVo.setPARENT_REF(parentOptVo.getPROG_REF());
    	
    	/**
		 * Set prog ref
		 */
		childOptVo.setPROG_REF(ChannelConstant.CHANNEL_CTRL_PROG_REF+""+channelCO.getImApiChannelsVO().getCHANNEL_ID());
		
		// get order
		/**
		 * Create Batch Menu Reference
		 */
		childOptVo.setAPP_NAME(channelCO.getAppName());
		childOptVo.setPROG_DESC(ChannelConstant.CHANNEL_CTRL_TITLE);
		childOptVo.setPROG_DESC_FR(ChannelConstant.CHANNEL_CTRL_TITLE);
		childOptVo.setPROG_DESC_ARAB(ChannelConstant.CHANNEL_CTRL_TITLE);
		
		childOptVo.setMENU_TITLE_ENG(ChannelConstant.CHANNEL_CTRL_TITLE);
		childOptVo.setMENU_TITLE_FR(ChannelConstant.CHANNEL_CTRL_TITLE);
    	childOptVo.setMENU_TITLE_ARAB(ChannelConstant.CHANNEL_CTRL_TITLE);
		
    	childOptVo.setPROG_ORDER(BigDecimal.ONE);
    	childOptVo.setAUDIT_RETRIEVE(BigDecimal.ONE.toString());
    	childOptVo.setDISP_ORDER(channelDAO.returnLatestDisplayOrder(childOptVo));
    	childOptVo.setPROG_TYPE(ConstantsCommon.PROG_TYPE_PROGRAM);
		
		// insert child OPT
		genericDAO.insert(childOptVo);
		
		
		// Create the PROG REF URL by inserting a row in OPT_EXTENED
		OPT_EXTENDEDVO extendedvo = new OPT_EXTENDEDVO();
		extendedvo.setAPP_NAME(channelCO.getAppName());
		extendedvo.setPROG_REF(childOptVo.getPROG_REF());
		
		extendedvo.setIV_CRUD(ChannelConstant.MAINTENANCE_IVCRUD);
			
		//set channel control url
		extendedvo.setOPT_URL(ChannelConstant.CHANNEL_CTRL_URL);
		//insert OPT_EXTENDED
		genericDAO.insert(extendedvo);
			
		
		
		AXSVO childAxs =  new AXSVO();
		childAxs.setUSER_ID(channelCO.getUserID());
		childAxs.setCOMP_CODE(channelCO.getCompCode());
		childAxs.setBRANCH_CODE(channelCO.getBranchCode());
		childAxs.setAPP_NAME(channelCO.getAppName());
		childAxs.setCREATED_BY(channelCO.getUserID());
		
		/**
		 * Note: Granting Privilege will be occurs only in Approval.
		 */
		childAxs.setSTATUS(ChannelConstant.STATUS_APPROVED);
		childAxs.setDIRECT_ACCESS(channelCO.getUserID());
		childAxs.setDATE_CREATED(channelCO.getImApiChannelsVO().getCREATED_DATE());
		childAxs.setPROG_REF(childOptVo.getPROG_REF());
		genericDAO.insert(childAxs);
		
		childOptVo = new OPTVO(); 
    	/**
		 * set parent prog ref
		 */
		childOptVo.setPARENT_REF(parentOptVo.getPROG_REF());
    	
    	/**
		 * Set prog ref
		 */
		childOptVo.setPROG_REF(ChannelConstant.CHANNEL_MONITOR_PROG_REF+""+channelCO.getImApiChannelsVO().getCHANNEL_ID());
		
		// get order
		/**
		 * Create Batch Menu Reference
		 */
		childOptVo.setAPP_NAME(channelCO.getAppName());
		childOptVo.setPROG_DESC(ChannelConstant.CHANNEL_MONITOR_TITLE);
		childOptVo.setPROG_DESC_FR(ChannelConstant.CHANNEL_MONITOR_TITLE);
		childOptVo.setPROG_DESC_ARAB(ChannelConstant.CHANNEL_MONITOR_TITLE);
		
		childOptVo.setMENU_TITLE_ENG(ChannelConstant.CHANNEL_MONITOR_TITLE);
		childOptVo.setMENU_TITLE_FR(ChannelConstant.CHANNEL_MONITOR_TITLE);
    	childOptVo.setMENU_TITLE_ARAB(ChannelConstant.CHANNEL_MONITOR_TITLE);
		
    	childOptVo.setPROG_ORDER(BigDecimal.ONE);
    	childOptVo.setAUDIT_RETRIEVE(BigDecimal.ONE.toString());
    	childOptVo.setDISP_ORDER(channelDAO.returnLatestDisplayOrder(childOptVo));
    	childOptVo.setPROG_TYPE(ConstantsCommon.PROG_TYPE_PROGRAM);
		
		// insert child OPT
		genericDAO.insert(childOptVo);
		
		// Create the PROG REF URL by inserting a row in OPT_EXTENED
		extendedvo = new OPT_EXTENDEDVO();
		extendedvo.setAPP_NAME(channelCO.getAppName());
		extendedvo.setPROG_REF(childOptVo.getPROG_REF());
		
		extendedvo.setIV_CRUD(ChannelConstant.MAINTENANCE_IVCRUD);
			
		//set channel Monitor URL
		extendedvo.setOPT_URL(ChannelConstant.CHANNEL_MONITOR_URL);
		//insert OPT_EXTENDED
		genericDAO.insert(extendedvo);
			
		childAxs =  new AXSVO();
		childAxs.setUSER_ID(channelCO.getUserID());
		childAxs.setCOMP_CODE(channelCO.getCompCode());
		childAxs.setBRANCH_CODE(channelCO.getBranchCode());
		childAxs.setAPP_NAME(channelCO.getAppName());
		childAxs.setCREATED_BY(channelCO.getUserID());
		
		/**
		 * Note: Granting Privilege will be occurs only in Approval.
		 */
		childAxs.setSTATUS(ChannelConstant.STATUS_APPROVED);
		childAxs.setDIRECT_ACCESS(channelCO.getUserID());
		childAxs.setDATE_CREATED(channelCO.getImApiChannelsVO().getCREATED_DATE());
		childAxs.setPROG_REF(childOptVo.getPROG_REF());
		genericDAO.insert(childAxs);
		
    }

    @Override
    public ChannelCO returnChannelDetails(ChannelSC sc) throws BaseException
    {
    	ChannelCO channelCO =  channelDAO.returnChannelDetails(sc);
    	//decrypt password
    	if(StringUtil.isNotEmpty(channelCO.getImApiChannelsVO().getHTTP_PASSWORD()))
    	{
    		channelCO.getImApiChannelsVO().setHTTP_PASSWORD(SecurityUtils.decryptAES(PASS_ENC_KEY, channelCO.getImApiChannelsVO().getHTTP_PASSWORD()));
    	}
	    return channelCO;
    }

    @Override
    public void deleteChannel(ChannelCO channelCO) throws BaseException
    {
		Date systemDate = commonLibBO.returnSystemDateWithTime();
		channelCO.getImApiChannelsVO().setDELETED_BY(channelCO.getUserID());
		channelCO.getImApiChannelsVO().setDELETED_DATE(channelCO.getRunningDate());
		channelCO.getImApiChannelsVO().setSTATUS(ChannelConstant.STATUS_DELETED);
		channelCO.getImApiChannelsVO().setSERVER_DELETED_DATE(systemDate);
		
		GTW_CHANNELVO oldAuditVO = (GTW_CHANNELVO) genericDAO.selectByPK(channelCO.getImApiChannelsVO());
		Integer update = genericDAO.update(channelCO.getImApiChannelsVO());
		if(update == null || update < 1)
		{
		    throw new BOException(MessageCodes.RECORD_CHANGED);
		}
		
		GTW_CHANNELVO newAuditVO = new GTW_CHANNELVO();
		PathPropertyUtil.copyProperties(oldAuditVO, newAuditVO);
	
		newAuditVO.setDELETED_BY(channelCO.getUserID());
		newAuditVO.setDELETED_DATE(channelCO.getRunningDate());
		newAuditVO.setSERVER_DELETED_DATE(systemDate);
		newAuditVO.setSTATUS(ChannelConstant.STATUS_DELETED);
		
		auditBO.callAudit(oldAuditVO, newAuditVO, channelCO.getAuditRefCO());
		auditBO.insertAudit(channelCO.getAuditRefCO());

    }

    @Override
    public ChannelCO update(ChannelCO channelCO) throws BaseException
    {
		GTW_CHANNELVO imApiChannelsVO = channelCO.getImApiChannelsVO();
	
		genericDAO.update(imApiChannelsVO);
		// genericDAO.delete(imApiChannelsVO);
		return channelCO;
	    }
	
	    @Override
	    public ChannelCO onChangeChannelID(ChannelCO channelCO) throws BaseException
	    {
		GTW_CHANNELVO imApiChannelsVO = channelCO.getImApiChannelsVO();
		GTW_CHANNELVO channelVO = (GTW_CHANNELVO) genericDAO.selectByPK(imApiChannelsVO);
		if(channelVO != null)
		{
		    throw new BOException(MessageCodes.CHANNEL_ID_ALREADY_DEFINED);
		}
		return channelCO;
    }
    
    @Override
    public ChannelCO onChangeUserID(ChannelCO channelCO) throws BaseException
    {
		USRVO userVO = new USRVO();
		userVO.setUSER_ID(channelCO.getImApiChannelsVO().getUSER_ID().toUpperCase());
		USRVO userVO1 = (USRVO) genericDAO.selectByPK(userVO);
		if(userVO1 == null)
		{
		    throw new BOException(MessageCodes.INCORRECT_USER_ID);
		}
		return channelCO;
    }

    @Override
    public ChannelCO applySysParamSettings(ChannelCO channelCO)
    {
		HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> elementMap = channelCO.getElementMap();
	
		SYS_PARAM_SCREEN_DISPLAYVO sysParam = new SYS_PARAM_SCREEN_DISPLAYVO();
		sysParam.setIS_MANDATORY(BigDecimal.ONE);
		elementMap.put("channel_id", sysParam);
	
		sysParam = new SYS_PARAM_SCREEN_DISPLAYVO();
		sysParam.setIS_VISIBLE(BigDecimal.ZERO);
		elementMap.put("channelMaint_del", sysParam);
	
		return channelCO;
    }

    @Override
    public Integer chanelCheckUsrPwd(ChannelCO channelCO) throws BaseException
    {
	try
	{
	    channelCO.setAllowUserAccess(0);
	    String passFinal = loginBO.returnEncryptedPassword(
		    channelCO.getImApiChannelsVO().getUSER_ID().toUpperCase(), channelCO.getChannelUserPassword());

	    UsrCO usrCO = loginBO.returnAdDetails(channelCO.getImApiChannelsVO().getUSER_ID().toUpperCase());

	    if(passFinal.equals(usrCO.getPASS_FINAL()))
	    {
		channelCO.setAllowUserAccess(1);
	    }
	}
	catch(Exception e)
	{
	    throw new BOException(MessageCodes.ERROR_WHILE_CHECKING_USER_PASS_VALIDATION);
	}
	return channelCO.getAllowUserAccess();
    }
    
    public String generateKey(ChannelCO channelCO) throws BaseException
    {
		String thePass = null, encChar = null;
		try
		{
		    thePass = new String(channelCO.getImApiChannelsVO().getCHANNEL_ID()
			    + channelCO.getImApiChannelsVO().getUSER_ID().toUpperCase() /*+ channelCO.getChannelUserPassword()*/)
			    + channelCO.getImApiChannelsDetVO().getHOST_NAME();
		    encChar = "SHA-512";
		    byte[] convertBytes = SecurityUtils.returnMd5Digest(thePass.getBytes(FileUtil.DEFAULT_FILE_ENCODING),
			    encChar);
		    // the blow is only to get the String Form The Hashed ByteArray
		    /*
		     * "%02X" means Flag '0' - The result will be zero-padded Width 2
		     * Conversion 'X' - The result is formatted as a hexadecimal
		     * integer, uppercase
		     */
		    String theFormat = "%02X";// upper case Formatting of The hashed
					      // ByteArray (convertBytes);
		    if("MD5".equals(encChar))
		    {
			theFormat = "%02x"; // for MD5 it is lower Case Hashing
		    }
		    Formatter formatter = new Formatter();
		    for(byte b : convertBytes)
		    {
			formatter.format(theFormat, b);
		    }
		    String result = formatter.toString().substring(20, 52);
		    formatter.close();
		    return result;
		}
		catch(Exception e)
		{
		    log.error(e, "Error in returnPwdEnc for encChar " + encChar);
		    throw new BadCredentialsException("Contact Administrator", e);
		}
    }
    
    @Override
    public String returnHashKey(ChannelCO channelCO) throws BaseException
    {
	String thePass = null, encChar = null;
	try
	{
	    ChannelSC channelSC = new ChannelSC();
	    channelSC.setChannelId(channelCO.getImApiChannelsVO().getCHANNEL_ID());
	    channelSC.setChannelHostName(channelCO.getImApiChannelsDetVO().getHOST_NAME());
	    channelSC.setChannelUserId(channelCO.getImApiChannelsVO().getUSER_ID().toUpperCase());
	    thePass = channelDAO.returnHashKey(channelSC);
	    return thePass;
	}
	catch(Exception e)
	{
	    throw new BadCredentialsException("Contact Administrator", e);
	}
    }
    
    @Override
    public int returnMachineIdListCount(ChannelSC criteria) throws BaseException
    {
	return channelDAO.returnMachineIdListCount(criteria);
    }

    @Override
    public List<ChannelCO> returnMachineIdList(ChannelSC criteria) throws BaseException
    {
	return channelDAO.returnMachineIdList(criteria);
    }
    
    @Override
    public void saveChannelMachineIds(ChannelCO channelCO, ArrayList lstMod) throws BaseException
    {
		ChannelCO chanCO;
		GTW_CHANNEL_DETVO lVO;
		
		//delete all machine id related to this channel id
		channelDAO.deleteAllMachineId(channelCO);
		
		//insert into DGTL_GTW_PWS_CHANNEL_DET
		Iterator<ChannelCO> itMod = lstMod.iterator();
		while(itMod.hasNext())
		{
		    chanCO = itMod.next();
		    lVO = chanCO.getImApiChannelsDetVO();
		    lVO.setCHANNEL_ID(channelCO.getImApiChannelsVO().getCHANNEL_ID());
		    genericDAO.insert(lVO);
		}
    }
    
    @Override
    public void approveChannelId(ChannelCO channelCO) throws BaseException
    {
		Date systemDate = commonLibBO.returnSystemDateWithTime();
		channelCO.getImApiChannelsVO().setAPPROVED_BY(channelCO.getUserID());
		channelCO.getImApiChannelsVO().setAPPROVED_DATE(channelCO.getRunningDate());
		channelCO.getImApiChannelsVO().setSTATUS(ChannelConstant.STATUS_APPROVED);
		channelCO.getImApiChannelsVO().setSERVER_APPROVED_DATE(systemDate);
		
		GTW_CHANNELVO oldAuditVO = (GTW_CHANNELVO) genericDAO.selectByPK(channelCO.getImApiChannelsVO());
		Integer update = genericDAO.update(channelCO.getImApiChannelsVO());
		if(update == null || update < 1)
		{
		    throw new BOException(MessageCodes.RECORD_CHANGED);
		}
		
		GTW_CHANNELVO newAuditVO = new GTW_CHANNELVO();
		PathPropertyUtil.copyProperties(oldAuditVO, newAuditVO);
	
		newAuditVO.setAPPROVED_BY(channelCO.getUserID());
		newAuditVO.setAPPROVED_DATE(channelCO.getRunningDate());
		newAuditVO.setSERVER_APPROVED_DATE(systemDate);
		newAuditVO.setSTATUS(ChannelConstant.STATUS_APPROVED);
		
		auditBO.callAudit(oldAuditVO, newAuditVO, channelCO.getAuditRefCO());
		auditBO.insertAudit(channelCO.getAuditRefCO());
    }
    
    @Override
    public void suspendChannelId(ChannelCO channelCO) throws BaseException
    {
    	Date systemDate = commonLibBO.returnSystemDateWithTime();
		channelCO.getImApiChannelsVO().setSUSPENDED_BY(channelCO.getUserID());
		channelCO.getImApiChannelsVO().setSUSPENDED_DATE(channelCO.getRunningDate());
		channelCO.getImApiChannelsVO().setSTATUS(ChannelConstant.STATUS_SUSPENDED);
		channelCO.getImApiChannelsVO().setSERVER_SUSPENDED_DATE(systemDate);
		
		GTW_CHANNELVO oldAuditVO = (GTW_CHANNELVO) genericDAO.selectByPK(channelCO.getImApiChannelsVO());
		Integer update = genericDAO.update(channelCO.getImApiChannelsVO());
		if(update == null || update < 1)
		{
		    throw new BOException(MessageCodes.RECORD_CHANGED);
		}
		
		GTW_CHANNELVO newAuditVO = new GTW_CHANNELVO();
		PathPropertyUtil.copyProperties(oldAuditVO, newAuditVO);
	
		newAuditVO.setAPPROVED_BY(channelCO.getUserID());
		newAuditVO.setAPPROVED_DATE(channelCO.getRunningDate());
		newAuditVO.setSERVER_APPROVED_DATE(systemDate);
		newAuditVO.setSTATUS(ChannelConstant.STATUS_SUSPENDED);
		
		auditBO.callAudit(oldAuditVO, newAuditVO, channelCO.getAuditRefCO());
		auditBO.insertAudit(channelCO.getAuditRefCO());
    }
    
    @Override
    public void reactivateChannelId(ChannelCO channelCO) throws BaseException
    {
		Date systemDate = commonLibBO.returnSystemDateWithTime();
		channelCO.getImApiChannelsVO().setREACTIVATED_BY(channelCO.getUserID());
		channelCO.getImApiChannelsVO().setREACTIVATED_DATE(channelCO.getRunningDate());
		channelCO.getImApiChannelsVO().setSTATUS(ChannelConstant.STATUS_APPROVED);
		channelCO.getImApiChannelsVO().setSERVER_REACTIVATED_DATE(systemDate);
		
		GTW_CHANNELVO oldAuditVO = (GTW_CHANNELVO) genericDAO.selectByPK(channelCO.getImApiChannelsVO());
		Integer update = genericDAO.update(channelCO.getImApiChannelsVO());
		if(update == null || update < 1)
		{
		    throw new BOException(MessageCodes.RECORD_CHANGED);
		}
		
		GTW_CHANNELVO newAuditVO = new GTW_CHANNELVO();
		PathPropertyUtil.copyProperties(oldAuditVO, newAuditVO);
		
		newAuditVO.setAPPROVED_BY(channelCO.getUserID());
		newAuditVO.setAPPROVED_DATE(channelCO.getRunningDate());
		newAuditVO.setSERVER_APPROVED_DATE(systemDate);
		newAuditVO.setSTATUS(ChannelConstant.STATUS_APPROVED);
		
		auditBO.callAudit(oldAuditVO, newAuditVO, channelCO.getAuditRefCO());
		auditBO.insertAudit(channelCO.getAuditRefCO());
    }
    
    @Override
    public int returnTempIdListCount(ChannelSC sc) throws BaseException
    {
    	return channelDAO.returnTempIdListCount(sc);
    }
    
    
    @Override
    public List<GTW_PWS_TMPLT_USRVO> loadAssignedTemplateIdListGrid(ChannelSC sc) throws BaseException
    {
    	return channelDAO.loadAssignedTemplateIdListGrid(sc);
    }
    
    @Override
    public List<GTW_PWS_TMPLT_MASTERVO> returnTempIdList(ChannelSC sc) throws BaseException
    {
    	return channelDAO.returnTempIdList(sc);
    }
    
    public LoginBO getLoginBO()
    {
    	return loginBO;
    }

    public void setLoginBO(LoginBO loginBO)
    {
    	this.loginBO = loginBO;
    }

	@Override
	public int validateUserId(ChannelSC channelSC) throws BaseException {
		// TODO Auto-generated method stub
		return channelDAO.validateUserId(channelSC);
	}
}
