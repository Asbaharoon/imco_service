package com.path.atm.vo.merchantposmgnt.posmgnt;

import java.math.BigDecimal;
import java.util.HashMap;

import com.path.dbmaps.vo.CTS_MERCHANT_POSVO;
import com.path.dbmaps.vo.SYS_PARAM_SCREEN_DISPLAYVO;
import com.path.vo.common.AlertsParamCO;
import com.path.vo.common.address.AddressCommonCO;
import com.path.vo.core.common.RetailBaseVO;

/**
 * 
 * Copyright 2015, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: FatimaSalam
 * 
 *          
 */

public class MerchantPosMgntCO extends RetailBaseVO
{
    private String statusDesc;
    private String merchantCodeDesc;
    private String isFromAlert;
    private String userIsBranchManager;
    private AlertsParamCO alertsParamCO = new AlertsParamCO();
    private CTS_MERCHANT_POSVO ctsMerchantPosVO = new CTS_MERCHANT_POSVO();
    private HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> elementHashmap = new HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO>();
    private String countryDesc;
    private String regionDesc;
    private String cityDesc;
    private String sectorDesc;
    private AddressCommonCO addressCO = new AddressCommonCO();
    private BigDecimal cifNo;
    private String cifDesc;
    

    public CTS_MERCHANT_POSVO getCtsMerchantPosVO()
    {
	return ctsMerchantPosVO;
    }

    public void setCtsMerchantPosVO(CTS_MERCHANT_POSVO ctsMerchantPosVO)
    {
	this.ctsMerchantPosVO = ctsMerchantPosVO;
    }

    public String getStatusDesc()
    {
	return statusDesc;
    }

    public void setStatusDesc(String statusDesc)
    {
	this.statusDesc = statusDesc;
    }

    public String getMerchantCodeDesc()
    {
	return merchantCodeDesc;
    }

    public void setMerchantCodeDesc(String merchantCodeDesc)
    {
	this.merchantCodeDesc = merchantCodeDesc;
    }

    public HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> getElementHashmap()
    {
	return elementHashmap;
    }

    public void setElementHashmap(HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> elementHashmap)
    {
	this.elementHashmap = elementHashmap;
    }

    public String getIsFromAlert()
    {
	return isFromAlert;
    }

    public void setIsFromAlert(String isFromAlert)
    {
	this.isFromAlert = isFromAlert;
    }

    public String getUserIsBranchManager()
    {
	return userIsBranchManager;
    }

    public void setUserIsBranchManager(String userIsBranchManager)
    {
	this.userIsBranchManager = userIsBranchManager;
    }

    public AlertsParamCO getAlertsParamCO()
    {
	return alertsParamCO;
    }

    public void setAlertsParamCO(AlertsParamCO alertsParamCO)
    {
	this.alertsParamCO = alertsParamCO;
    }

    public String getCountryDesc()
    {
	return countryDesc;
    }

    public void setCountryDesc(String countryDesc)
    {
	this.countryDesc = countryDesc;
    }

    public String getRegionDesc()
    {
	return regionDesc;
    }

    public void setRegionDesc(String regionDesc)
    {
	this.regionDesc = regionDesc;
    }

    public String getCityDesc()
    {
	return cityDesc;
    }

    public void setCityDesc(String cityDesc)
    {
	this.cityDesc = cityDesc;
    }

    public String getSectorDesc()
    {
	return sectorDesc;
    }

    public void setSectorDesc(String sectorDesc)
    {
	this.sectorDesc = sectorDesc;
    }

    public AddressCommonCO getAddressCO()
    {
	return addressCO;
    }

    public void setAddressCO(AddressCommonCO addressCO)
    {
	this.addressCO = addressCO;
    }

    public BigDecimal getCifNo()
    {
        return cifNo;
    }

    public void setCifNo(BigDecimal cifNo)
    {
        this.cifNo = cifNo;
    }

    public String getCifDesc()
    {
        return cifDesc;
    }

    public void setCifDesc(String cifDesc)
    {
        this.cifDesc = cifDesc;
    }

}
