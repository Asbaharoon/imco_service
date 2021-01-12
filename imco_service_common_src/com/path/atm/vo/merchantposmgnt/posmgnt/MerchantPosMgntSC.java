package com.path.atm.vo.merchantposmgnt.posmgnt;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;

/**
 * 
 * Copyright 2015, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: FatimaSalam
 * GridParamsSC: contains the parameters needed for Search, Flipping and Sorting 
 *          
 */
public class MerchantPosMgntSC extends GridParamsSC
{
    private String language;
    private String progRef;
    private String ivCrud;
    private BigDecimal lovTypeId;
    private BigDecimal code;
    private String isFromAlert;
    private String posStatusCode;
    private String country;
    

    public String getLanguage()
    {
	return language;
    }

    public void setLanguage(String language)
    {
	this.language = language;
    }

    public String getProgRef()
    {
	return progRef;
    }

    public void setProgRef(String progRef)
    {
	this.progRef = progRef;
    }

    public String getIvCrud()
    {
	return ivCrud;
    }

    public void setIvCrud(String ivCrud)
    {
	this.ivCrud = ivCrud;
    }

    public BigDecimal getLovTypeId()
    {
	return lovTypeId;
    }

    public void setLovTypeId(BigDecimal lovTypeId)
    {
	this.lovTypeId = lovTypeId;
    }

    public BigDecimal getCode()
    {
	return code;
    }

    public void setCode(BigDecimal code)
    {
	this.code = code;
    }

    public String getIsFromAlert()
    {
        return isFromAlert;
    }

    public void setIsFromAlert(String isFromAlert)
    {
        this.isFromAlert = isFromAlert;
    }

    public String getPosStatusCode()
    {
        return posStatusCode;
    }

    public void setPosStatusCode(String posStatusCode)
    {
        this.posStatusCode = posStatusCode;
    }



    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }
}
