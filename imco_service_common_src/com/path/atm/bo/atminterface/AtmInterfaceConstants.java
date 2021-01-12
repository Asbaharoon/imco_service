package com.path.atm.bo.atminterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AtmInterfaceConstants
{
	public static final String STATUS_status_columns= "0_C";
	public static final String STATUS_DELETED = "D";
	public static final String STATUS_MODIFIED = "0_M";
	public static final String STATUS_APPROVED = "P";
	public static final String STATUS_APPROVE_REJECTED = "R";
	public static final String STATUS_SUSPENDED = "S";
	public static final String STATUS_REACTIVATED = "0_R";
	public static final String IV_CRUD_R="R";
	public static final String ATM_INTERFACE_ROOT="ATM00";
	public static final String INTERFACE_OPT="ATMINT";
	
	//Drop Downs
	public static final BigDecimal INTERFACE_TYPE = new BigDecimal(1441);
    public static final BigDecimal BITMAP_TYPE = new BigDecimal(1442);
    public static final BigDecimal LENGTH_TYPE = new BigDecimal(1443);
    public static final BigDecimal ISO_FIELD_TYPE = new BigDecimal(1468);
    public static final BigDecimal MESSAGE_TYPE = new BigDecimal(1561);
	
	//Constants used for AXS
	public static final String AXS_STATUS_P="P";
	public static final String AXS_TO_BE_DELETED_N="N";
	
	public static final List<String> atmInterfaceStatusLst = new ArrayList<String>();
   	static
   	{
   		atmInterfaceStatusLst.add("CREATED_BY," + STATUS_status_columns + ",CREATED_DATE");
   		atmInterfaceStatusLst.add("MODIFIED_BY," + STATUS_MODIFIED + ",MODIFIED_DATE");
   		atmInterfaceStatusLst.add("DELETED_BY," + STATUS_DELETED + ",DELETED_DATE");
   		atmInterfaceStatusLst.add("APPROVED_BY," + STATUS_APPROVED + ",APPROVED_DATE");
   		atmInterfaceStatusLst.add("SUSPENDED_BY," + STATUS_SUSPENDED + ",SUSPENDED_DATE");
   		atmInterfaceStatusLst.add("REACTIVATED_BY," + STATUS_REACTIVATED + ",REACTIVATED_DATE");
	}
}
