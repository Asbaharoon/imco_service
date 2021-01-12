/**
 * PWSGenerationBOImpl.java - Oct 24, 2018  
 *
 * Copyright 2018, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Raed Saad
 *
 */
package com.path.imco.bo.pwsgeneration.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.Configuration;

import com.path.bo.common.CommonLibBO;
import com.path.codegen.common.lib.api.mapper.GenMapperProcedure;
import com.path.codegen.webservicegen.contract.Config;
import com.path.codegen.webservicegen.contract.ConfigRow;
import com.path.codegen.webservicegen.driver.GenLauncher;
import com.path.codegen.webservicegen.excel.ExcelFile;
import com.path.codegen.webservicegen.excel.ExcelSheet;
import com.path.codegen.webservicegen.excel.ExcelSheetRow;
import com.path.codegen.webservicegen.pojo.DataHolder;
import com.path.codegen.webservicegen.util.ConfigProperties;
import com.path.dbmaps.vo.DGTL_GTW_WS_ADAPTERVO;
import com.path.imco.bo.pwsgeneration.PWSGenerationBO;
import com.path.imco.bo.pwsgeneration.PWSGenerationConstant;
import com.path.imco.dao.pwsgeneration.PWSGenerationDAO;
import com.path.imco.vo.pwsgeneration.PWSGenerationCO;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.FileUtil;

public class PWSGenerationBOImpl extends BaseBO implements PWSGenerationBO{
	private PWSGenerationDAO pwsGenerationDAO;

	/**
	 * function to check if a procedure exists in a database
	 * @param obj
	 * @return
	 * @throws BaseException
	 */
	public void validateProcedure(Object obj) throws BaseException
	{
		if(this.checkIfProcedureExists(obj)==0)
		{
			//throw new BOException("Procedure does not exist");
		}
	}
	
	@Override
	public List<PWSGenerationCO> returnPWSGenerationAdaptersList(Object obj) throws BaseException {
		return pwsGenerationDAO.returnPWSGenerationAdaptersList(obj);
	}

	@Override
	public Integer returnPWSGenerationAdapterListCount(Object obj) throws BaseException {
		return pwsGenerationDAO.returnPWSGenerationAdapterListCount(obj);
	}
	
	@Override
	public void saveAdapterData(Object obj) throws BaseException {
		PWSGenerationCO pwsGenerationCO = (PWSGenerationCO)obj;
		pwsGenerationCO.getDgtlAdapterVO().setCREATED_DATE(commonLibBO.addSystemTimeToDate(new Date()));
		pwsGenerationCO.getDgtlAdapterVO().setSTATUS(PWSGenerationConstant.PWS_GENERATION_SAVED_STATUS);
		pwsGenerationDAO.saveAdapterData(obj);
	}
	
	@Override
	public Object returnSavedRecordsQuery(Object obj) throws BaseException {
		return pwsGenerationDAO.returnSavedRecordsQuery(obj);
	}

//	@Override
//	public List<PWS_OPERATION_FIELDSVO> returnOperationFields(Object obj) throws BaseException {
//		return pwsGenerationDAO.returnOperationFields(obj);
//	}
	
	@Override
	public void insertOperationData(Object obj) throws BaseException {
		pwsGenerationDAO.insertOperationData(obj);
	}
	
	@Override
	public Integer returnProcedureArgumentsCount(Object obj) throws BaseException {
		return pwsGenerationDAO.returnProcedureArgumentsCount(obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PWSGenerationCO> loadProcedureArguments(Object obj) throws BaseException {
		return ((List<PWSGenerationCO>)pwsGenerationDAO.loadProcedureArguments(obj));
	}
	
	@Override
	public Integer checkIfProcedureExists(Object obj) throws BaseException {
		return pwsGenerationDAO.checkIfProcedureExists(obj);
	}
	
	public PWSGenerationDAO getPwsGenerationDAO() {
		return pwsGenerationDAO;
	}

	public void setPwsGenerationDAO(PWSGenerationDAO pwsGenerationDAO) {
		this.pwsGenerationDAO = pwsGenerationDAO;
	}

	@Override
	public List<PWSGenerationCO> returnSavedProcedureArguments(Object obj) throws BaseException {
		return pwsGenerationDAO.returnSavedProcedureArguments(obj);
	}

	@Override
	public PWSGenerationCO loadPWSRecord(Object obj) throws BaseException {
		List<DGTL_GTW_WS_ADAPTERVO> lstDgtlAdapterVO = (List<DGTL_GTW_WS_ADAPTERVO>)this.returnSavedRecordsQuery(obj);
		PWSGenerationCO pwsGenerationCO = (PWSGenerationCO)obj;
		if(lstDgtlAdapterVO.size()>0)
		{
			pwsGenerationCO.setLstDgtlAdapterVO(lstDgtlAdapterVO);
			pwsGenerationCO.setDgtlAdapterVO(lstDgtlAdapterVO.get(0));
			pwsGenerationCO.setServiceName(lstDgtlAdapterVO.get(0).getSERVICE_NAME());
			pwsGenerationCO.setProcedureName(lstDgtlAdapterVO.get(0).getAPI_NAME());
			pwsGenerationCO.setStatusDesc(PWSGenerationConstant.PWS_STATUS_DESC.get(lstDgtlAdapterVO.get(0).getSTATUS())+"");
		}
		return pwsGenerationCO;	
	}

	@Override
	public void saveRecord(Object obj) throws BaseException {
		this.insertOperationData(obj);		
	}

	@Override
	public void approveRecord(Object obj) throws BaseException {
		PWSGenerationCO pwsGenerationCO = (PWSGenerationCO)obj;
		pwsGenerationCO.getDgtlAdapterVO().setSTATUS(PWSGenerationConstant.PWS_GENERATION_APPROVED_STATUS);
		if(pwsGenerationCO.getDgtlAdapterVO().getADAPTER_TYPE().equalsIgnoreCase("BPEL"))
		{
			pwsGenerationCO.getDgtlAdapterVO().setAPI_NAME(null);
		}
		genericDAO.update(((PWSGenerationCO)obj).getDgtlAdapterVO());
	}

	@Override
	public void updateAfterApproveRecord(Object obj) throws BaseException {
		PWSGenerationCO pwsGenerationCO = (PWSGenerationCO)obj;
		pwsGenerationCO.getDgtlAdapterVO().setSTATUS(PWSGenerationConstant.PWS_GENERATION_UPDATE_AFTER_APPROVED_STATUS);
		if(pwsGenerationCO.getDgtlAdapterVO().getADAPTER_TYPE().equalsIgnoreCase("BPEL"))
		{
			pwsGenerationCO.getDgtlAdapterVO().setAPI_NAME(null);
		}
		genericDAO.update(((PWSGenerationCO)obj).getDgtlAdapterVO());
	}

	@Override
	public void deploy(Object obj) throws BaseException 
	{
		PWSGenerationCO pwsGenerationCO = ((PWSGenerationCO)obj);
		if(null != obj && pwsGenerationCO.getDgtlAdapterVO().getADAPTER_TYPE().equals("Api"))
		{
			this.deployApi(obj);
		}
		else if(pwsGenerationCO.getDgtlAdapterVO().getADAPTER_TYPE().equals("BPEL"))
		{
			this.deployBpelAdapter(obj);
		}
	}
	
	/**
	 * @description function to deploy PWS BPEL Adapter
	 * @param obj
	 * @throws BaseException
	 */
	private void deployBpelAdapter(Object obj) throws BaseException
	{
		try{
			PWSGenerationCO pwsGenerationCO = (PWSGenerationCO)obj;
			DGTL_GTW_WS_ADAPTERVO dgtlAdapterVO = pwsGenerationCO.getDgtlAdapterVO();
			dgtlAdapterVO = (DGTL_GTW_WS_ADAPTERVO) genericDAO.selectByPK(dgtlAdapterVO);
			dgtlAdapterVO.setSTATUS("D");
			genericDAO.update(dgtlAdapterVO);
			String bpelLocation = FileUtil.getFileURLByName("repository")+File.separator+"bpel"+File.separator+dgtlAdapterVO.getAPI_NAME();
			ConfigProperties configProperties = this.returnConfigProperties();
			configProperties.setProjectType("1");
			configProperties.setOrderIndicator("2");
			configProperties.setInputFileType("4");
			configProperties.setBpelZipLocation(bpelLocation);
			ConfigProperties.setInstance(configProperties);
			GenLauncher genLauncher = new GenLauncher();
			genLauncher.triggerGenerator();
			System.out.println("BPEL Deployment finished successfully");
		}
		catch(Exception e)
		{
			ConfigProperties.setInstance(null);
			System.gc();
			e.printStackTrace();
			throw new BOException(e.getMessage());
		}
	}
	
	/**
	 * @description function to deploy PWS API Adapter
	 * @param obj
	 * @throws BaseException
	 */
	public void deployApi(Object obj) throws BaseException
	{
		try{
			PWSGenerationCO pwsGenerationCO = (PWSGenerationCO)obj;
			DGTL_GTW_WS_ADAPTERVO dgtlAdapterVO = pwsGenerationCO.getDgtlAdapterVO();
			dgtlAdapterVO = (DGTL_GTW_WS_ADAPTERVO) genericDAO.selectByPK(dgtlAdapterVO);
			dgtlAdapterVO.setSTATUS("D");
			genericDAO.update(dgtlAdapterVO);
			pwsGenerationCO.setAdapterIdSequence(dgtlAdapterVO.getADAPTER_ID());
			List<PWSGenerationCO> lstPWSCO =  this.returnSavedProcedureArguments(pwsGenerationCO);
			ConfigProperties configProperties = this.returnConfigProperties();
			configProperties.setProjectType("2");
			configProperties.setOrderIndicator("2");
			configProperties.setInputFileType("5");
			Config config = new Config();
			List<ConfigRow> configRows = new ArrayList<ConfigRow>();
			ConfigRow configRow = new ConfigRow();
			dgtlAdapterVO.getOPERATION_NAME();
			configRow.setServiceName(dgtlAdapterVO.getOPERATION_NAME());
			configRow.setAppName("PWS");
			configRow.setBusinessArea("PWS");
			configRow.setBusinessDomain("PWS");
			configRow.setServiceDomain(dgtlAdapterVO.getSERVICE_NAME());
			configRow.setVersion("1.0");
			configRow.setServiceId("0");
			configRow.setOperationName(dgtlAdapterVO.getOPERATION_NAME());
			configRow.setModifierClass(dgtlAdapterVO.getOPERATION_NAME());
			GenMapperProcedure genMapper = new GenMapperProcedure();
			genMapper.setProcedureName(dgtlAdapterVO.getAPI_NAME());
			genMapper.setType("S");//must be filled from screen
			configRow.setGenMapper(genMapper);
			configRows.add(configRow);
			config.setConfigRows(configRows);
			ExcelSheet excelSheet = new ExcelSheet();
			excelSheet.setSheetName(dgtlAdapterVO.getOPERATION_NAME());
			List<ExcelSheetRow> excelSheetRows = new ArrayList<ExcelSheetRow>();
			ExcelSheetRow excelSheetRow = new ExcelSheetRow();
			String type = null;
			for(PWSGenerationCO pwsGenerationCO1 : lstPWSCO)
			{
				excelSheetRow = new ExcelSheetRow();
				excelSheetRow.setParamName(pwsGenerationCO1.getDgtlAdapterParamVO().getPARAMETER_NAME());
				excelSheetRow.setDescription(pwsGenerationCO1.getDgtlAdapterParamVO().getDESCRIPTION());
				type = pwsGenerationCO1.getDgtlAdapterParamVO().getPARAM_TYPE().toLowerCase();
				type = type.replace("numeric", "number");
				excelSheetRow.setType(type);
//				if(null == excelSheetRow.getType() || "null".equalsIgnoreCase(excelSheetRow.getType()))
//				{
//					excelSheetRow.setType("varchar");
//				}
				excelSheetRow.setMandatory(pwsGenerationCO1.getDgtlAdapterParamVO().getIS_MANDATORY_YN());
				excelSheetRow.setInOut(pwsGenerationCO1.getDgtlAdapterParamVO().getIN_OUT());
				excelSheetRow.setDefaultValue(pwsGenerationCO1.getDgtlAdapterParamVO().getDEFAULT_VALUE());
				excelSheetRow.setMappingField(pwsGenerationCO1.getDgtlAdapterParamVO().getMAPPED_PARAM_NAME());
				excelSheetRow.setNillable(pwsGenerationCO1.getDgtlAdapterParamVO().getIS_NILLABLE_YN());
				excelSheetRow.setParent(excelSheet);
				excelSheetRows.add(excelSheetRow);
			}
			excelSheet.setSheetRows(excelSheetRows);
			List<ExcelSheet> excelSheets = new ArrayList<ExcelSheet>();
			excelSheets.add(excelSheet);
			ExcelFile excelFile = new ExcelFile();
			excelFile.setExcelSheets(excelSheets);
			DataHolder dataHolder = DataHolder.newInstance();
			dataHolder.setConfigs(config);
			dataHolder.setExcelFile(excelFile);
			ConfigProperties.setInstance(configProperties);
			GenLauncher genLauncher = new GenLauncher();
			genLauncher.setDataHolder(dataHolder);
			genLauncher.triggerGenerator();
			System.out.println("Api deployment finished successfully");
		}
		catch(Exception e)
		{
			ConfigProperties.setInstance(null);
			System.gc();
			e.getStackTrace();
			throw new BOException(e.getMessage());
		}	
	}
	
	/**
	 * @description function to upload bpel file to repository
	 * @param pwsGenerationCO
	 * @throws BOException
	 */
	public void uploadBPEL(PWSGenerationCO pwsGenerationCO) throws BOException
	{
		String wsdl = null;
		try{
			String bpelZipName = pwsGenerationCO.getBpelFileName();
			if(pwsGenerationCO.getMethodType().equals("save"))
			{
				saveAdapterData(pwsGenerationCO);
			}
			File bpelFile = pwsGenerationCO.getBpelFile();
			String repository = FileUtil.getFileURLByName("repository")+File.separator+"bpel";
			File saveFile = new File(repository+File.separator + bpelZipName);
			Path path = Paths.get(repository + File.separator);
			if (!Files.exists(path)) 
			{
				Files.createDirectories(path);
			}
			OutputStream outputStream = new FileOutputStream(saveFile);
			byte[] content = FileUtils.readFileToByteArray(bpelFile);
			outputStream.write(content);
			outputStream.close();
//			if(pwsGenerationCO.getMethodType().equals("import"))
//			{
//				String bpelLocation = FileUtil.getFileURLByName("repository")+File.separator+"bpel"+File.separator+bpelZipName;
//				ConfigProperties configProperties = this.returnConfigProperties();
//				configProperties.setProjectType("1");
//				configProperties.setOrderIndicator("2");
//				configProperties.setInputFileType("4");
//				configProperties.setBpelZipLocation(bpelLocation);
//				ConfigProperties.setInstance(configProperties);
//				GenLauncher genLauncher = new GenLauncher();
//				wsdl = genLauncher.triggerWsdlGenerator();
//				String tempWsdlRep = FileUtil.getFileURLByName("repository")+File.separator+"bpel"+File.separator+"temp_wsdl";
//				String x[] = bpelZipName.split("\\.");				
//				File file = new File(tempWsdlRep);
//		        Path path1 = Paths.get(tempWsdlRep);
//		        //if directory exists?
//		        if (!Files.exists(path)) 
//		        {
//		            try {
//		                Files.createDirectories(path1);
//		            } 
//		            catch (IOException e) 
//		            {
//		                //fail to create directory
//		            	throw new Exception(e);
//		            }
//		        }
//		        File f1 = new File(tempWsdlRep+x[0]+".wsdl");
//				FileOutputStream fileOutputStream = new FileOutputStream(f1);
//				byte[] contentInBytes = wsdl.getBytes();
//				fileOutputStream.write(contentInBytes);
//				fileOutputStream.flush();
//				fileOutputStream.close();	
//			}
		}
		catch(Exception e)
		{
			throw new BOException(e.getMessage());
		}
	}
	
	@Override
	public void generateWsdlAdapter(Object obj) throws BOException 
	{
		try{
			PWSGenerationCO pwsGenerationCO = (PWSGenerationCO)obj;
			DGTL_GTW_WS_ADAPTERVO dgtlAdapterVO = pwsGenerationCO.getDgtlAdapterVO();
			//dgtlAdapterVO = (DGTL_GTW_WS_ADAPTERVO) genericDAO.selectByPK(dgtlAdapterVO);
//			dgtlAdapterVO.setSTATUS("C");
//			genericDAO.update(dgtlAdapterVO);
			String wsdlLocation = FileUtil.getFileURLByName("repository")+File.separator+"wsdl"+File.separator;
			ConfigProperties configProperties = this.returnConfigProperties();
			configProperties.setProjectType("1");
			configProperties.setOrderIndicator("2");
			configProperties.setInputFileType("3");
			configProperties.setWsdlUrl(pwsGenerationCO.getDgtlAdapterVO().getAPI_NAME());
			ConfigProperties.setInstance(configProperties);
			GenLauncher genLauncher = new GenLauncher();
			genLauncher.triggerGenerator();
			//System.out.println("BPEL Deployment finished successfully");
		}
		catch(Exception e)
		{
			ConfigProperties.setInstance(null);
			System.gc();
			e.printStackTrace();
			throw new BOException(e.getMessage());
		}
	}
	
	@Override
	public PWSGenerationCO loadIRPConnection(Object obj) throws BaseException {
		return pwsGenerationDAO.loadIRPConnection(obj);
	}

	@Override
	public List<PWSGenerationCO> loadIRPConnectionList(Object obj) throws BaseException {
		return pwsGenerationDAO.loadIRPConnectionList(obj);
	}

	@Override
	public Integer returnIRPConnectionCount(Object obj) throws BaseException {
		return pwsGenerationDAO.returnIRPConnectionCount(obj);
	}
	
//	@Override
//    public List<PWS_OPERATIONVO> returnProcedureParams(Object obj) throws BaseException
//    {
//    	return pwsGenerationDAO.returnProcedureParams(obj);
//    }
    
    @Override
    public Integer returnProcedureParamsCount(Object obj) throws BaseException
    {
    	return pwsGenerationDAO.returnProcedureParamsCount(obj);
    }
	
//	/**
//	 * @description function to return a config properties object
//	 * @return
//	 */
	public ConfigProperties returnConfigProperties() throws IOException
	{
		String workingDirectory = System.getProperty("user.dir");
		String repository = FileUtil.getFileURLByName("repository");
		Map<String,String> applicationsHashMap = new HashMap<String,String>();
				//WebServiceUtil().getApplicationNameAndServerPATHMap(WebServiceExplorerConstant.PROPERTY_FILE_NAME);
		String loc = Configuration.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String []locs = null;
		if(loc.contains("/"))
		{
			locs = loc.split("/");
		}
		else if(loc.contains("\\"))
		{
			locs = loc.split("\\");
		}
		loc = loc.replace(locs[locs.length-1],"");
		ConfigProperties.isMiddleWare = true;
		ConfigProperties configProp = ConfigProperties.newInstance();
		configProp.setAppName("PWS");
		configProp.setProjectLocation(repository);
		configProp.repositoryLocation = repository;
		configProp.libLocation = loc;
		configProp.setGenerateCommonHeader(true);
		configProp.setOverrideExportLocation("0");
		configProp.deployLocation = applicationsHashMap.get("DesignedFlows");
		configProp.generateCommonHeader = true;
		configProp.setCommonHeadersExcelName("ContractCommonHeaders_All.xlsx");
		return configProp;
	}
	
	@Override
	public String applyScreenVisiblity(PWSGenerationCO pwsGenerationCO) throws BOException {

		return null;
	}

	public CommonLibBO getCommonLibBO() {
		return commonLibBO;
	}

	public void setCommonLibBO(CommonLibBO commonLibBO) {
		this.commonLibBO = commonLibBO;
	}
}
