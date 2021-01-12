package com.path.imco.dao.common.lookup;

import java.util.List;

import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;

/**
 * Copyright 2016, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * @author: Shahnawaz
 *
 */
public interface ImcoCommonLookupDAO
{
	/**
     * Method to Get list of formula for auto complete
     * 
     * @param sc
     * @return
     * @throws BaseException
     */
    public List<Object> retLookupList(Object obj) throws DAOException;
}
