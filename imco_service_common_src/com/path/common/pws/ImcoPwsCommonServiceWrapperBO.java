package com.path.common.pws;

import java.util.ArrayList;
import java.util.HashMap;

import com.path.lib.common.exception.BaseException;

public interface ImcoPwsCommonServiceWrapperBO {

	int authenticateUser(HashMap<String, Object> hm) throws BaseException;
	int checkAccess(HashMap<String, Object> hm)throws BaseException;
	HashMap<String, Object> logRequest(HashMap<String, Object> hm)throws BaseException;
	HashMap<String, Object> logResponse(HashMap<String, Object> hm)throws BaseException;
	HashMap<String, Object> logResponseXml(HashMap<String, Object> hm)throws BaseException;
	ArrayList<String>  returnMaskingParams(HashMap<String, Object> hm)throws BaseException;
	void logTimeConsumption(HashMap<String, Object> hm)throws BaseException;
}
