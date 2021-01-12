package com.path.imco.bo.common;

import java.util.HashMap;
import java.util.Map;

import com.path.lib.common.exception.BaseException;

public interface ImcoPwsMappingBO {

	/**
	 * @param parameters
	 * @throws BaseException
	 */
	public void savePWSMapping(Map<String,Object> parameters) throws BaseException;
	
	/**
	 * @param mapId
	 * @param mappingIdsMap
	 * @return
	 * @throws BaseException
	 */
	public HashMap<String,Object> callPWS(String mapId, HashMap<String,Object> mappingIdsMap)  throws BaseException;
}
