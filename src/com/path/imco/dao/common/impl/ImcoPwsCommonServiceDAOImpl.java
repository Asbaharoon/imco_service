package com.path.imco.dao.common.impl;

import java.util.ArrayList;

import com.path.dbmaps.vo.IMCO_PWS_TIMESTAMP_LOGVO;
import com.path.imco.dao.common.ImcoPwsCommonServiceDAO;
import com.path.imco.vo.accesswebservice.AccessWebServiceCO;
import com.path.imco.vo.common.CheckAccessSC;
import com.path.imco.vo.common.ImcoPwsRequestLogVO;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;

public class ImcoPwsCommonServiceDAOImpl extends BaseDAO implements ImcoPwsCommonServiceDAO {

	@Override
	public void logRequest(ImcoPwsRequestLogVO imcoPwsRequestLogVO) throws DAOException {
		// TODO Auto-generated method stub
		getSqlMap().insert("ImcoPwsCommonServiceMapper.insertLogRequest", imcoPwsRequestLogVO);
	}
	@Override
	public void logResponse(ImcoPwsRequestLogVO imcoPwsRequestLogVO) throws DAOException {
		// TODO Auto-generated method stub
		getSqlMap().insert("ImcoPwsCommonServiceMapper.insertLogResponse", imcoPwsRequestLogVO);
	}
	@Override
	public void logResponseXml(ImcoPwsRequestLogVO imcoPwsRequestLogVO) throws DAOException {
		// TODO Auto-generated method stub
		getSqlMap().update("ImcoPwsCommonServiceMapper.updateLogResponseXml", imcoPwsRequestLogVO);
	}
	@Override
	public int returnHasAcess(CheckAccessSC sc) throws DAOException {
		// TODO Auto-generated method stub
		return ((Integer) getSqlMap().queryForObject("ImcoPwsCommonServiceMapper.returnHasAcess", sc)).intValue();
	}
	@Override
	public ArrayList<String> returnMaskingParams(CheckAccessSC sc) throws DAOException {
		// TODO Auto-generated method stub
		return (ArrayList<String>) getSqlMap().queryForList("ImcoPwsCommonServiceMapper.returnMaskingParams", sc);
	}
	@Override
	public void logTimeConsumption(IMCO_PWS_TIMESTAMP_LOGVO vo) throws DAOException 
	{
		getSqlMap().insert("ImcoPwsCommonServiceMapper.insertLogTimeConsumption", vo);
	}
	
}
