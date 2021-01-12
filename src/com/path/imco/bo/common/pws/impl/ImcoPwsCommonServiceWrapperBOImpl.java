package com.path.imco.bo.common.pws.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.path.bo.common.login.LoginBO;
import com.path.common.pws.ImcoPwsCommonServiceWrapperBO;
import com.path.imco.bo.common.ImcoPwsCommonServiceBO;
import com.path.imco.vo.channel.ChannelSC;
import com.path.imco.vo.common.ImcoPwsRequestLogVO;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.PathPropertyUtil;

public class ImcoPwsCommonServiceWrapperBOImpl extends BaseBO implements ImcoPwsCommonServiceWrapperBO
{
	@Override
	public int authenticateUser(HashMap<String, Object> hm) throws BaseException {

		//check first if user is a valid channel user, if not should be forbidden
		ChannelSC sc = new ChannelSC();
		sc.setChannelUserId((String) hm.get("userID"));
		int userExist = imcoPwsCommonServiceBO.validateUserId(sc);
		if(userExist == 0)
			return 0;
		else
		{
			//user is valid channel user, then authenticate credentials 
			//use loginbo rmi from imco service url since imco could be on different server/db
			//this user should be validated on imco environment
			return loginBO.authenticateUserAndPass((String) hm.get("userID"), (String) hm.get("password"));
		}
//		return 1;
	}
	private LoginBO loginBO;

	public LoginBO getLoginBO() {
		return loginBO;
	}

	public void setLoginBO(LoginBO loginBO) {
		this.loginBO = loginBO;
	}
	private ImcoPwsCommonServiceBO imcoPwsCommonServiceBO;
	
	@Override
	public int checkAccess(HashMap<String, Object> hm) throws BaseException
	{
		int res = 0;
		res = imcoPwsCommonServiceBO.checkChannelAccess(hm);
		return res;
	}

	@Override
	public HashMap<String, Object> logRequest(HashMap<String, Object> hm) throws BaseException
	{
		String[] properties= new String[] {"requestID","userID","hashKey","hostName","channelID",
				"coreRequestTimeStamp","companyCode","branchCode","cifNo",
				"requestKernelDetails", "businessArea", "businessDomain", "serviceDomain", "operationName", "version", "serviceID",
				"dealNo", "transactionNo", "branch", "currency", "accGl", "serialNo", "cifNo", "additionalRef", "ibanAccNo", "opNo", "cardNo"};
		ImcoPwsRequestLogVO imcoPwsRequestLogVO = new ImcoPwsRequestLogVO();
		PathPropertyUtil.copyProperties(hm, imcoPwsRequestLogVO, properties);
		imcoPwsCommonServiceBO.logRequest(imcoPwsRequestLogVO);
		return hm;
	}

	public ImcoPwsCommonServiceBO getImcoPwsCommonServiceBO() {
		return imcoPwsCommonServiceBO;
	}

	public void setImcoPwsCommonServiceBO(ImcoPwsCommonServiceBO imcoPwsCommonServiceBO) {
		this.imcoPwsCommonServiceBO = imcoPwsCommonServiceBO;
	}

	@Override
	public HashMap<String, Object> logResponse(HashMap<String, Object> hm) throws BaseException {
			
		String[] properties= new String[] {"responseID","userID","hashKey","hostName","channelID",
				"coreRequestTimeStamp","coreResponseTimeStamp","companyCode","branchCode","cifNo",
				"requestKernelDetails", "businessArea", "businessDomain", "serviceDomain", "operationName", "version", "serviceID",
				"dealNo", "transactionNo", "branch", "currency", "accGl", "serialNo", "cifNo",
				"additionalRef", "ibanAccNo", "opNo", "cardNo", "statusCode", "statusDesc", "severity", "errorType"};
		
		ImcoPwsRequestLogVO imcoPwsRequestLogVO = new ImcoPwsRequestLogVO();
		PathPropertyUtil.copyProperties(hm, imcoPwsRequestLogVO, properties);
		imcoPwsCommonServiceBO.logResponse(imcoPwsRequestLogVO);
		return null;
	}

	@Override
	public HashMap<String, Object> logResponseXml(HashMap<String, Object> hm) throws BaseException {
		// TODO Auto-generated method stub
		String[] properties= new String[] {"responseID","requestKernelDetails"};
		ImcoPwsRequestLogVO imcoPwsRequestLogVO = new ImcoPwsRequestLogVO();
		PathPropertyUtil.copyProperties(hm, imcoPwsRequestLogVO, properties);
		imcoPwsCommonServiceBO.logResponseXml(imcoPwsRequestLogVO);
		return null;
	}

	@Override
	public ArrayList<String> returnMaskingParams(HashMap<String, Object> hm) throws BaseException {
		// TODO Auto-generated method stub
		return imcoPwsCommonServiceBO.returnMaskingParams(hm);
	}

	@Override
	public void logTimeConsumption(HashMap<String, Object> hm) throws BaseException {
		// TODO Auto-generated method stub
		imcoPwsCommonServiceBO.logTimeConsumption(hm);
	}

	
}
