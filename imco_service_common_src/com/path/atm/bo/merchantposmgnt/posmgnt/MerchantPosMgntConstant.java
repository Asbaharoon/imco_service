package com.path.atm.bo.merchantposmgnt.posmgnt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MerchantPosMgntConstant
{
    public static final String POS_SCREEN_NAME = "POS";
    /* LOV ID */
    public static final BigDecimal POS_TYPE_LOV_TYPE = new BigDecimal(578);
    public static final BigDecimal POS_STATUS_LOV = new BigDecimal(581);

    /* IV CRUD */
    public static final String IV_CRUD_R = "R";
    public static final String IV_CRUD_A = "A";
    public static final String IV_CRUD_P = "P";
    public static final String IV_CRUD_UP = "UP";

    public static final String MERCHANT_REF = "POS00";
    public static final String MERCHANTPOSMGNT_SENT_FLAG = "0";

    public static final BigDecimal MERCHANTMGNT_CONTACT_SYS_PARAM_LOV_TYPE = new BigDecimal(396);
    // Print Advice
    public static final String CANCEL_PRINT = "CAN";
    public static final String APPROVE_PRINT = "APP";
    // statuses
    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_APPROVED = "P";
    public static final String STATUS_DELETED = "D";
    public static final String STATUS_TOCANCEL = "K";
    public static final String STATUS_CANCELLED = "N";
    public static final String STATUS_CREATED = "0_C";
    public static final String STATUS_MODIFIED = "0_M";
    
    // A static hashMap for MerchantPosMgnt Status 
    public static final List<String> MERCHANTMGNT_POS_STATUS_LST = new ArrayList<String>();
    static
    {
	MERCHANTMGNT_POS_STATUS_LST.add("CREATED_BY," + STATUS_CREATED + ",DATE_CREATED");
	MERCHANTMGNT_POS_STATUS_LST.add("MODIFIED_BY," + STATUS_MODIFIED + ",DATE_MODIFIED");
	MERCHANTMGNT_POS_STATUS_LST.add("APPROVED_BY," + STATUS_APPROVED + ",DATE_APPROVED");
	MERCHANTMGNT_POS_STATUS_LST.add("DELETED_BY," + STATUS_DELETED + ",DATE_DELETED");
	MERCHANTMGNT_POS_STATUS_LST.add("CANCELED_BY," + STATUS_CANCELLED + ",DATE_CANCELED");
	MERCHANTMGNT_POS_STATUS_LST.add("TOBE_CANCELED_BY," + STATUS_TOCANCEL + ",DATE_TOBE_CANCELED");
    }
}
