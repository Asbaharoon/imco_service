package com.path.imco.bo.accesswebservice;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * ReportQueryConstant.java used to define Constant variables
 */
public class AccessWebServiceConstant
{
    public static BigDecimal LOV_LK_TYPE = new BigDecimal(1299);
    public static BigDecimal LOV_TYPE_STATUS = new BigDecimal(1305);
    public static BigDecimal LOV_TYPE_DESC = new BigDecimal(1287);

    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_DELETED = "D";
    public static final String STATUS_MODIFIED = "M";
    public static final String STATUS_APPROVED = "P";
    public static final String STATUS_REACTIVATED = "RA";
    public static final String STATUS_SUSPENDED = "S";
    public static final String IV_CRUD_UPDATE_AFTER_APPROVE = "UP";

    
    public static final List<String> ACCESS_WEB_SEVRVICE_STATUS_LIST = new ArrayList<String>();
    static
    {
    	ACCESS_WEB_SEVRVICE_STATUS_LIST.add("CREATED_BY," + STATUS_ACTIVE + ",CREATED_DATE");
		ACCESS_WEB_SEVRVICE_STATUS_LIST.add("DELETED_BY," + STATUS_DELETED + ",DELETED_DATE");
		ACCESS_WEB_SEVRVICE_STATUS_LIST.add("MODIFIED_BY," + STATUS_MODIFIED + ",MODIFIED_DATE");
		ACCESS_WEB_SEVRVICE_STATUS_LIST.add("APPROVED_BY," + STATUS_APPROVED + ",APPROVED_DATE");
    }
    
    public static final String ACCESS_WEB_SERVICE_EXPLORER_LIST_ACTION_NAME = "AccessWebServiceExplorerList_*";
    public static final String ACCESS_WEB_SERVICE_EXPLORER_SCREEN_NAME_SPACE = "/path/accessWebService/";
}
