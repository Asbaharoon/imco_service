package com.path.imco.bo.setoff;

import java.util.List;

import com.path.imco.vo.setoff.SetoffCO;
import com.path.imco.vo.setoff.SetoffSC;
import com.path.lib.common.exception.BaseException;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * SetoffBO.java used to
 */
public interface SetoffBO
{

    public List returnSetOffList(SetoffSC criteria) throws BaseException;

    public int returnSetOffListCount(SetoffSC criteria) throws BaseException;

    /**
     * @param setoffCO
     * @return
     */

    public SetoffCO setoff(SetoffCO setoffCO) throws BaseException;

    public SetoffCO refresh(SetoffCO setoffCO) throws BaseException;


}