package com.path.imco.bo.sytmset;

import java.io.IOException;
import java.util.List;

import com.path.imco.vo.sytmset.SytmsetCO;
import com.path.imco.vo.sytmset.SytmsetSC;
import com.path.lib.common.exception.BaseException;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * SytmsetBO.java used to
 */
public interface SytmsetBO
{
    public int returnSytmsetListCount(SytmsetSC criteria) throws BaseException;

    public List returnSytmsetList(SytmsetSC criteria) throws BaseException;

    public SytmsetCO returnSytmsetDetails(SytmsetCO sytmsetCO) throws BaseException;

    public SytmsetCO deleteSytmset(SytmsetCO sytmsetCO) throws BaseException;

    public SytmsetCO saveNew(SytmsetCO sytmsetCO) throws BaseException;

    public SytmsetCO pingNetwork(SytmsetCO sytmsetCO) throws BaseException, IOException;

    public SytmsetCO pingDatabase(SytmsetCO sytmsetCO) throws BaseException;

    public SytmsetCO pingSendCartridge(SytmsetCO sytmsetCO) throws BaseException;

    public SytmsetCO pingReciveCartridge(SytmsetCO sytmsetCO) throws BaseException;

}