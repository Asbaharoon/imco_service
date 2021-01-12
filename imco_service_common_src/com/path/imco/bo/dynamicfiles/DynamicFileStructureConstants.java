package com.path.imco.bo.dynamicfiles;

import java.math.BigDecimal;

public class DynamicFileStructureConstants
{
	// LOV ID
    public static final BigDecimal LOV_TYPE_EXTRACTION_CRITERIA = new BigDecimal(1408);
    public static final BigDecimal LOV_TYPE_DATA_TYPE = new BigDecimal(1409);
    
    // IV CRUD VALUES
    public static final String IV_CRUD_MAINTENANCE = "R";
    public static final String IV_CRUD_APPROVE = "P";
    public static final String IV_CRUD_UPDATE_AFTER_APPROVE = "UP";
    public static final String IV_CRUD_SUSPENDED = "S";
    public static final String IV_CRUD_REACTIVATE = "RA";
    
    // Status
    public static final String STATUS_CREATED = "0_C";
    public static final String STATUS_MODIFIED = "0_M";
    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_DELETED = "D";
    public static final String STATUS_APPROVED = "P";
    public static final String STATUS_SUSPENDED = "S";
    public static final String STATUS_REACTIVATE = "RA";
    public static final String STATUS_APPROVE_REJECTED = "R";
    public static final String STATUS_UPDATE_AFTER_APPROVE = "UP";
    public static final String STR_ONE = "1";
    public static final String WEB_SERVICE_EXPLORER_LIST_ACTION_NAME = "PWSWebMappingExplorerList_*";
    public static final String WEB_SERVICE_EXPLORER_SCREEN_NAME_SPACE = "/path/fileStructure";
}
