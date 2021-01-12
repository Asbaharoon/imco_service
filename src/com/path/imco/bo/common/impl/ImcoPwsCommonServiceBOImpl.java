package com.path.imco.bo.common.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import com.path.bo.common.login.LoginBO;
import com.path.dbmaps.vo.IMCO_PWS_CHANNELVO;
import com.path.dbmaps.vo.IMCO_PWS_CHANNEL_DETVO;
import com.path.dbmaps.vo.IMCO_PWS_TIMESTAMP_LOGVO;
import com.path.imco.bo.channel.ChannelBO;
import com.path.imco.bo.common.ImcoPwsCommonServiceBO;
import com.path.imco.dao.common.ImcoPwsCommonServiceDAO;
import com.path.imco.vo.channel.ChannelCO;
import com.path.imco.vo.channel.ChannelSC;
import com.path.imco.vo.common.CheckAccessSC;
import com.path.imco.vo.common.ImcoPwsRequestLogVO;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.common.util.SecurityUtils;
import com.path.lib.remote.RmiServiceCaller;

public class ImcoPwsCommonServiceBOImpl extends BaseBO implements ImcoPwsCommonServiceBO{

	@Override
	public int validateUserId(ChannelSC channelSC) throws BaseException {
		
		return channelBO.validateUserId(channelSC);
	}

	public ChannelBO getChannelBO() {
		return channelBO;
	}

	public void setChannelBO(ChannelBO channelBO) {
		this.channelBO = channelBO;
	}

	ImcoPwsCommonServiceDAO imcoPwsCommonServiceDAO;
	ChannelBO channelBO;  
	@Override
	public int checkChannelAccess(HashMap<String, Object> hm) throws BaseException 
	{
		/*this commented part will be moved to the core applications*/
		/*int authUser = 0;
		try {
			//first authenticate user sent in parameters, in case not authenticated no need to check for access
			authUser = authenticateUser(hm);
		} catch (Exception e) {
			return 0;
		}
		if(authUser == 0)
			return 0;*/
		
		int hasAccess = 0;
		//validate user/channel/hash
		// TODO Auto-generated method stub
		ChannelCO channelCO = new ChannelCO();
		//channelCO.setImApiChannelsVO(new IMCO_PWS_CHANNELVO());
		//channelCO.setImApiChannelsDetVO(new IMCO_PWS_CHANNEL_DETVO());
		channelCO.getImApiChannelsVO().setCHANNEL_ID(new BigDecimal((String)hm.get("channelID")));
		channelCO.getImApiChannelsDetVO().setHOST_NAME((String)hm.get("hostName"));
		channelCO.getImApiChannelsVO().setUSER_ID((String)hm.get("headerUserID"));
		String hash = channelBO.returnHashKey(channelCO);
		//CHECK channel access
		if(hash == null || !hash.equals((String)hm.get("hashKey")) )
		{
			return 0;
		}
		String[] properties= new String[] {"wsAppName", "wsName", "wsOperationName"};
		CheckAccessSC sc = new CheckAccessSC();
		sc.setChannelID(new BigDecimal((String)hm.get("channelID")));
		sc.setUserID((String)hm.get("headerUserID"));
		PathPropertyUtil.copyProperties(hm, sc, properties);
		//check webservice/operation access
		hasAccess = imcoPwsCommonServiceDAO.returnHasAcess(sc);
		return hasAccess;
	}

	@Override
	public void logRequest(ImcoPwsRequestLogVO imcoPwsRequestLogVO) throws BaseException {
		// TODO Auto-generated method stub
		imcoPwsCommonServiceDAO.logRequest(imcoPwsRequestLogVO);
	}
	@Override
	public void logResponse(ImcoPwsRequestLogVO imcoPwsRequestLogVO) throws BaseException {
		// TODO Auto-generated method stub
		imcoPwsCommonServiceDAO.logResponse(imcoPwsRequestLogVO);
	}

	public ImcoPwsCommonServiceDAO getImcoPwsCommonServiceDAO() {
		return imcoPwsCommonServiceDAO;
	}

	public void setImcoPwsCommonServiceDAO(ImcoPwsCommonServiceDAO imcoPwsCommonServiceDAO) {
		this.imcoPwsCommonServiceDAO = imcoPwsCommonServiceDAO;
	}

	@Override
	public void logResponseXml(ImcoPwsRequestLogVO imcoPwsRequestLogVO) throws BaseException {
		// TODO Auto-generated method stub
		imcoPwsCommonServiceDAO.logResponseXml(imcoPwsRequestLogVO);
	}

	@Override
	public ArrayList<String> returnMaskingParams(HashMap<String, Object> hm) throws BaseException {
		// TODO Auto-generated method stub
		String[] properties= new String[] {"wsAppName", "wsName", "wsOperationName"};
		CheckAccessSC sc = new CheckAccessSC();
		sc.setChannelID(new BigDecimal((String)hm.get("channelID")));
		sc.setUserID((String)hm.get("headerUserID"));
		PathPropertyUtil.copyProperties(hm, sc, properties);
		return imcoPwsCommonServiceDAO.returnMaskingParams(sc);
	}

	/*private LoginBO loginBO;

	public LoginBO getLoginBO() {
		return loginBO;
	}

	public void setLoginBO(LoginBO loginBO) {
		this.loginBO = loginBO;
	}*/
	
	@Override
	public int authenticateUser(HashMap<String, Object> hm) throws BaseException
	{
		try
		{
			//getting the loginbo from core url since imco could be on different server, and this user should be teller/core imal user
			//this user should be validated on core environment
			String coreRmiUrl = PathPropertyUtil.returnPathPropertyFromFile("PathDBLRemoting.properties", "integration.RET.serviceURL");
			coreRmiUrl = (coreRmiUrl == null) ? "" : coreRmiUrl;
			LoginBO loginBO = (LoginBO ) RmiServiceCaller.returnRmiInterface(coreRmiUrl, LoginBO.class,"loginBOService");
			String password = SecurityUtils.decodeB64((String) hm.get("password"));
			return loginBO.authenticateUserAndPass((String) hm.get("userID"), password);
		} catch (Exception e) {
			log.error(e, "Error getting remoting for LoginBO");
			return 0;
		}
	}

	@Override
	public void logTimeConsumption(HashMap<String, Object> hm) throws BaseException 
	{
		String[] properties= new String[] {"REQUEST_ID","STEP_NAME","STEP_STAGE", "STEP_TIMESTAMP", "BUSINESS_AREA","SERVICE_NAME","OPERATION_NAME"};
		IMCO_PWS_TIMESTAMP_LOGVO vo = new IMCO_PWS_TIMESTAMP_LOGVO();
		PathPropertyUtil.copyProperties(hm, vo, properties);
		// TODO Auto-generated method stub
		imcoPwsCommonServiceDAO.logTimeConsumption(vo);
	}

}
