package com.path.atm.bo.merchantposmgnt.merchantmgnt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MerchantMgntConstant {
	
	public static final BigDecimal MERCHANTMGNT_STATUS_SYS_PARAM_LOV_TYPE  = new BigDecimal(395);
	public static final BigDecimal MERCHANTMGNT_CONTACT_SYS_PARAM_LOV_TYPE = new BigDecimal(396);
	public static final BigDecimal MERCHANTMGNT_MERCHANT_TYPE_SYS_PARAM_LOV_TYPE = new BigDecimal(594);
	public static final String SHOW_IN_POS ="1";
	
	public static final String MERCHANTMGNT_SENT_FLAG = "0";
	public static final String CRUD_MAINTENANCE = "R";
	
	//statuses
	public static final String STATUS_ACTIVE       = "A";
	public static final String STATUS_APPROVED     = "P";
	public static final String STATUS_DELETED      = "D";
	public static final String STATUS_TOCANCEL     = "K";
	public static final String STATUS_CANCELLED    = "N";
	public static final String STATUS_CREATED      = "0_C";
	public static final String STATUS_MODIFIED     = "0_M";
	    
	// references
	public static final String REFERENCE_CREATE = "C";
	public static final String REFERENCE_UPDATE = "U";
	// OPT
	public static final String PARENT_OPT = "MER00";
	
	//crud
	public static final String CRUD_TO_CANCEL = "K";
	public static final String CRUD_CANCEL    = "N";
	
        // Print Advice
        public static final String CANCEL_PRINT = "CAN";
        public static final String APPROVE_PRINT = "APP";

    // A static hashMap for MerchantMgnt Status 
    public static final List<String> MERCHANTMGNT_STATUS_LST = new ArrayList<String>();
    static
    {
	MERCHANTMGNT_STATUS_LST.add("CREATED_BY," + STATUS_CREATED + ",DATE_CREATED");
	MERCHANTMGNT_STATUS_LST.add("MODIFIED_BY," + STATUS_MODIFIED + ",DATE_MODIFIED");
	MERCHANTMGNT_STATUS_LST.add("APPROVED_BY," + STATUS_APPROVED + ",DATE_APPROVED");
	MERCHANTMGNT_STATUS_LST.add("DELETED_BY," + STATUS_DELETED + ",DATE_DELETED");
	MERCHANTMGNT_STATUS_LST.add("CANCELED_BY," + STATUS_CANCELLED + ",DATE_CANCELED");
	MERCHANTMGNT_STATUS_LST.add("TOBE_CANCELED_BY," + STATUS_TOCANCEL + ",DATE_TOBE_CANCELED");
    }
}

