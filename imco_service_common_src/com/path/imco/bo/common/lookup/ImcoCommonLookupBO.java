package com.path.imco.bo.common.lookup;

import java.util.List;

import com.path.lib.common.exception.BaseException;

/**
 * Copyright 2016, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * @author: Shahnawaz
 *
 */
public interface ImcoCommonLookupBO
{
	 /**
     * Method to Get list of formula for auto complete
     * 
     * @param sc
     * @return
     * @throws BaseException
     */
    public List<Object> retLookupList(Object obj) throws BaseException; 
}
