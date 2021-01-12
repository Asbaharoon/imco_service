package com.path.imco.dao.common;

import java.util.ArrayList;

import com.path.dbmaps.vo.IMCO_PWS_TIMESTAMP_LOGVO;
import com.path.imco.vo.common.CheckAccessSC;
import com.path.imco.vo.common.ImcoPwsRequestLogVO;
import com.path.lib.common.exception.DAOException;

public interface ImcoPwsCommonServiceDAO {

	void logRequest(ImcoPwsRequestLogVO imcoPwsRequestLogVO)throws DAOException;
	void logResponse(ImcoPwsRequestLogVO imcoPwsRequestLogVO)throws DAOException;
	 void logResponseXml(ImcoPwsRequestLogVO imcoPwsRequestLogVO) throws DAOException;
    int returnHasAcess(CheckAccessSC sc)throws DAOException;
    ArrayList<String>returnMaskingParams(CheckAccessSC sc)throws DAOException;
    void logTimeConsumption(IMCO_PWS_TIMESTAMP_LOGVO vo) throws DAOException;
}
