/**
 * PWSGenerationConstant.java - Oct 17, 2018  
 *
 * Copyright 2018, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Raed Saad
 *
 *User Story #740995 PWS generation From DB Procedure -screen
 */
package com.path.imco.bo.pwsgeneration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PWSGenerationConstant {
	public static String MAP_TO_API = "API";
	public static String MAP_TO_BPEL = "BPEL";
	public static String MAP_TO_WINDOWS_API="WIDOWSAPI";
	public static String PWS_GENERATION_SAVED_STATUS = "C";
	public static String PWS_GENERATION_APPROVED_STATUS = "P";
	public static String PWS_GENERATION_UPDATE_AFTER_APPROVED_STATUS = "M";
	
	public static final BigDecimal PWSGENERATION_STATUS_LOV = new BigDecimal(1476);
	
    public static final String STATUS_APPROVED = "P";
    public static final String STATUS_CREATED = "C";
    public static final String STATUS_MODIFIED = "M";
    

	public static final List<String> PWSGENERATION_STATUS_LIST = new ArrayList<String>();
	static
	{
		PWSGENERATION_STATUS_LIST.add("CREATED_BY," + STATUS_CREATED + ",DATE_CREATED");
		PWSGENERATION_STATUS_LIST.add("MODIFIED_BY," + STATUS_MODIFIED + ",DATE_MODIFIED");
		PWSGENERATION_STATUS_LIST.add("APPROVED_BY," + STATUS_APPROVED + ",DATE_APPROVED");
	}

	public static final Map<String,Object> PWS_STATUS_DESC = new HashMap<String,Object>();
	static
	{
		PWS_STATUS_DESC.put("C", "Created");
		PWS_STATUS_DESC.put("P", "Approved");
		PWS_STATUS_DESC.put("M", "Updated");
	}
	
	public static final Map<String,Object> types;
	static{
		types = new HashMap<String,Object>();
		types.put("N", "numeric");
		types.put("V","varchar");
		types.put("v", "varchar");
	}
	
    public static final String PWS_WEB_SERVICE_EXPLORER_LIST_ACTION_NAME = "/PWSWebServiceExplorerList_*";
    public static final String PWS_WEB_SERVICE_EXPLORER_SCREEN_NAME_SPACE = "/path/PWSGeneration";
    public static final String PWS_WEB_SERVICE_EXPLORER_GRID_RESULT_MAP = "loadPWSGenerationWebserviceExplorer";
    public static final String PWS_WEB_SERVICE_EXPLORER__SUB_GRID_RESULT_MAP = "loadPWSGenerationWebserviceExplorerSub";
}
