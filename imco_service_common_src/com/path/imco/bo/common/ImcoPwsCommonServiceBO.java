package com.path.imco.bo.common;

import java.util.ArrayList;
import java.util.HashMap;

import com.path.imco.vo.channel.ChannelSC;
import com.path.imco.vo.common.ImcoPwsRequestLogVO;
import com.path.lib.common.exception.BaseException;

public interface ImcoPwsCommonServiceBO {
	
	int checkChannelAccess(HashMap<String, Object> hm) throws BaseException;
	void logRequest(ImcoPwsRequestLogVO imcoPwsRequestLogVO)throws BaseException;
	void logResponse(ImcoPwsRequestLogVO imcoPwsRequestLogVO)throws BaseException;
	void logResponseXml(ImcoPwsRequestLogVO imcoPwsRequestLogVO)throws BaseException;
	int validateUserId(ChannelSC channelSC)throws BaseException;
	ArrayList<String> returnMaskingParams(HashMap<String, Object> hm)throws BaseException; 
	int authenticateUser(HashMap<String, Object> hm)throws BaseException;
	void logTimeConsumption(HashMap<String, Object> hm) throws BaseException ;
}
