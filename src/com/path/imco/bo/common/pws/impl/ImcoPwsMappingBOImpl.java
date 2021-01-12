package com.path.imco.bo.common.pws.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import com.path.bo.common.customization.button.ButtonCustomizationRmiCallerBO;
import com.path.dbmaps.vo.DGTL_GTW_MAPPINGVO;
import com.path.dbmaps.vo.DGTL_GTW_MAPPING_DETAILSVO;
import com.path.imco.bo.common.ImcoPwsMappingBO;
import com.path.imco.dao.common.ImcoPwsMappingDAO;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;
import com.path.lib.remote.RmiServiceCaller;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class ImcoPwsMappingBOImpl extends BaseBO implements ImcoPwsMappingBO {

	private ImcoPwsMappingDAO imcoPwsMappingDAO;

	protected final static Log log = Log.getInstance();

	public void savePWSMapping(Map<String, Object> parameters) throws BaseException {

		// only for testing must be removed later
		//testSavePWSMapping(parameters);

		// parameters hashmap must contain the needed information from the string: it needs 4 parameters in order to save the mapping correctly
		// the parameters are available under PWS Explorer screen
		// wsName : webservice name
		// operName : operation name
		// map ID : this is a unique id that needs to be decided where it is generated ( 1 possibility is like lov id, second possibility is from db)
		// jsonStr : the json string of the the grid that contain the mapping in screen PWS Explorer
		
		// Master table needed information
		String wsName = (String) parameters.get("wsName");
		String operName = (String) parameters.get("operName");
		String mapId = (String) parameters.get("mapId");

		// Detail table needed information
		String jsonStr = (String) parameters.get("jsonStr");
		
		// this method transform the json string to a hashmap
		// it is a recursive method in order to cover all sub levels
		Map<String, String> resultMap = returnMapFromStr(jsonStr);

		
		DGTL_GTW_MAPPINGVO DGTL_GTW_MAPPINGVO = new DGTL_GTW_MAPPINGVO();
		DGTL_GTW_MAPPINGVO.setMAPPING_ID(mapId);
		DGTL_GTW_MAPPINGVO.setOPER_NAME(operName);
		DGTL_GTW_MAPPINGVO.setWS_NAME(wsName);

		if (genericDAO.selectByPK(DGTL_GTW_MAPPINGVO) != null) {
			genericDAO.update(DGTL_GTW_MAPPINGVO);
		} else {
			genericDAO.insert(DGTL_GTW_MAPPINGVO);
		}

		DGTL_GTW_MAPPING_DETAILSVO DGTL_GTW_MAPPING_DETAILSVO = new DGTL_GTW_MAPPING_DETAILSVO();
		DGTL_GTW_MAPPING_DETAILSVO.setMAPPING_ID(mapId);

		Iterator<Entry<String, String>> it = resultMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			DGTL_GTW_MAPPING_DETAILSVO.setDESTINATION_KEY(pair.getValue());
			DGTL_GTW_MAPPING_DETAILSVO.setSOURCE_KEY(pair.getKey());

			if (genericDAO.selectByPK(DGTL_GTW_MAPPING_DETAILSVO) != null) {
				genericDAO.update(DGTL_GTW_MAPPING_DETAILSVO);
			} else {
				genericDAO.insert(DGTL_GTW_MAPPING_DETAILSVO);
			}

			it.remove();
		}
	}

	private Map<String, String> returnMapFromStr(String jsonStr) {

		Map<String, String> inputHM = new LinkedHashMap<String, String>();

		// the json string is coming as a list of json objects,
		// for that the first level is always a list of json objects
		if (StringUtil.isNotEmpty(jsonStr)) {
			List<JSONObject> jsonLst = (List<JSONObject>) JSONSerializer.toJSON(jsonStr);
			for (JSONObject jsonObj : jsonLst) {
				// second level is based on the type of each object
				returnMapFromObj(jsonObj, "", "", inputHM);
			}
		}
		return inputHM;
	}

	private void returnMapFromObj(JSONObject jsonObj, String inputName, String parentType,
			Map<String, String> inputHM) {
		String fieldType;
		
		// these are the generic types: those are leafs and there is no sub level under them
		Set<String> genericTypes = new HashSet<String>();
		genericTypes.add("string");
		genericTypes.add("date");
		genericTypes.add("dateTime");
		genericTypes.add("decimal");

		// get field name, value and field type from object
		String fieldName = jsonObj.get("fieldName").toString();
		String fieldValue = jsonObj.get("value").toString();
		fieldType = jsonObj.get("fieldType").toString();

		// parent type : 2 : List
		// parent type : 1 : Object
		
		// if parent type equal 2, do not append inputname to field name, in order to cover the json format. the format is List > name of Object > then list of objects
		if (!("".equals(parentType) || "2".equals(parentType))) {
			fieldName = inputName + "." + fieldName;
		}
		System.out.println("FieldName: " + fieldName);

		// based on field type iterate through objects
		if ("List<".equals(fieldType.substring(0, 5))) {
			System.out.println("List: " + fieldType);
			parentType = "2";
			List<JSONObject> jsonLst = (List<JSONObject>) jsonObj.get("lstInReqRes");
			for (JSONObject jsonObjTemp : jsonLst) {
				returnMapFromObj(jsonObjTemp, fieldName, parentType, inputHM);
			}
		} else if (genericTypes.contains(fieldType)) {
			System.out.println("generic type: " + fieldType);
			System.out.println("value: " + fieldValue);
			inputHM.put(fieldName, fieldValue);
		} else {
			System.out.println("object: " + fieldType);
			parentType = "1";
			List<JSONObject> jsonLst = (List<JSONObject>) jsonObj.get("reqResCO");
			for (JSONObject jsonObjTemp : jsonLst) {
				returnMapFromObj(jsonObjTemp, fieldName, parentType, inputHM);
			}
		}
	}

	/*public void testSavePWSMapping(Map<String, Object> parameters) {

		String jsonStr = "[{\"ID\":\"requesterContext\",\"fieldName\":\"requesterContext\",\"fieldType\":\"requesterContextVO\",\"restriction\":\"\",\"nillable\":\"false\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":[\"{\\\"ID\\\":\\\"channelID\\\",\\\"fieldName\\\":\\\"channelID\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"hashKey\\\",\\\"fieldName\\\":\\\"hashKey\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"hostName\\\",\\\"fieldName\\\":\\\"hostName\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"langId\\\",\\\"fieldName\\\":\\\"langId\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"password\\\",\\\"fieldName\\\":\\\"password\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"requesterTimeStamp\\\",\\\"fieldName\\\":\\\"requesterTimeStamp\\\",\\\"fieldType\\\":\\\"dateTime\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"2018-11-15T12:08:36\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"userID\\\",\\\"fieldName\\\":\\\"userID\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\"],\"lstInReqRes\":\"\"},{\"ID\":\"accountCRExceptionalRateDCList_WSDLGUIMT\",\"fieldName\":\"accountCRExceptionalRateDCList\",\"fieldType\":\"List<accountCRExceptionalRateDC>\",\"restriction\":\"\",\"nillable\":\"true\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":[{\"ID\":\"accountCRExceptionalRateDC_1\",\"fieldName\":\"accountCRExceptionalRateDCList\",\"fieldType\":\"accountCRExceptionalRateDC\",\"restriction\":\"undefined\",\"nillable\":\"undefined\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":[{\"ID\":\"accountCRExceptionalRateDC_1_additionalSpecialRate_WSDLGUIMT\",\"fieldName\":\"additionalSpecialRate\",\"fieldType\":\"decimal\",\"restriction\":\"\",\"nillable\":\"true\",\"value\":\"testList.testArg\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":\"\"}],\"lstInReqRes\":\"\"}]},{\"ID\":\"accountDetails_WSDLGUIMT\",\"fieldName\":\"accountDetails\",\"fieldType\":\"accountMainDetailDC\",\"restriction\":\"\",\"nillable\":\"false\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":[{\"ID\":\"accountDetails_WSDLGUIMT_accArBriefDesc_WSDLGUIMT\",\"fieldName\":\"accArBriefDesc\",\"fieldType\":\"string\",\"restriction\":\"\",\"nillable\":\"false\",\"value\":\"testObj.testArg1\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":\"\"},{\"ID\":\"accountDetails_WSDLGUIMT_addressesList_WSDLGUIMT\",\"fieldName\":\"addressesList\",\"fieldType\":\"List<accountAddressDC>\",\"restriction\":\"\",\"nillable\":\"true\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":[{\"ID\":\"_1\",\"fieldName\":\"addressesList\",\"fieldType\":\"accountAddressDC\",\"restriction\":\"undefined\",\"nillable\":\"undefined\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":[{\"ID\":\"_1_addressAr1_WSDLGUIMT\",\"fieldName\":\"addressAr1\",\"fieldType\":\"string\",\"restriction\":\"\",\"nillable\":\"false\",\"value\":\"testObj.testList.arg2\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":\"\"}],\"lstInReqRes\":\"\"}]},{\"ID\":\"accountDetails_WSDLGUIMT_autoSweep_WSDLGUIMT\",\"fieldName\":\"autoSweep\",\"fieldType\":\"string\",\"restriction\":\"\",\"nillable\":\"false\",\"value\":\"testObj.Arg3\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":\"\"}],\"lstInReqRes\":\"\"}]";
		parameters.put("jsonStr", jsonStr);
		parameters.put("wsName", "imcoPwsCommonServiceWrapperBO");
		parameters.put("operName", "returnMaskingParams");
		parameters.put("mapId", "testMapId");

	}*/

	public static void main(String[] args) {
		String jsonStr = "[{\"ID\":\"requesterContext\",\"fieldName\":\"requesterContext\",\"fieldType\":\"requesterContextVO\",\"restriction\":\"\",\"nillable\":\"false\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":[\"{\\\"ID\\\":\\\"channelID\\\",\\\"fieldName\\\":\\\"channelID\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"hashKey\\\",\\\"fieldName\\\":\\\"hashKey\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"hostName\\\",\\\"fieldName\\\":\\\"hostName\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"langId\\\",\\\"fieldName\\\":\\\"langId\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"password\\\",\\\"fieldName\\\":\\\"password\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"requesterTimeStamp\\\",\\\"fieldName\\\":\\\"requesterTimeStamp\\\",\\\"fieldType\\\":\\\"dateTime\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"2018-11-15T12:08:36\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\",\"{\\\"ID\\\":\\\"userID\\\",\\\"fieldName\\\":\\\"userID\\\",\\\"fieldType\\\":\\\"string\\\",\\\"restriction\\\":\\\"\\\",\\\"nillable\\\":\\\"false\\\",\\\"value\\\":\\\"\\\",\\\"list\\\":\\\"\\\",\\\"map\\\":\\\"\\\",\\\"reqResCO\\\":\\\"\\\",\\\"lstInReqRes\\\":\\\"\\\"}\"],\"lstInReqRes\":\"\"},{\"ID\":\"accountCRExceptionalRateDCList_WSDLGUIMT\",\"fieldName\":\"accountCRExceptionalRateDCList\",\"fieldType\":\"List<accountCRExceptionalRateDC>\",\"restriction\":\"\",\"nillable\":\"true\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":[{\"ID\":\"accountCRExceptionalRateDC_1\",\"fieldName\":\"accountCRExceptionalRateDCList\",\"fieldType\":\"accountCRExceptionalRateDC\",\"restriction\":\"undefined\",\"nillable\":\"undefined\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":[{\"ID\":\"accountCRExceptionalRateDC_1_additionalSpecialRate_WSDLGUIMT\",\"fieldName\":\"additionalSpecialRate\",\"fieldType\":\"decimal\",\"restriction\":\"\",\"nillable\":\"true\",\"value\":\"testList.testArg\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":\"\"}],\"lstInReqRes\":\"\"}]},{\"ID\":\"accountDetails_WSDLGUIMT\",\"fieldName\":\"accountDetails\",\"fieldType\":\"accountMainDetailDC\",\"restriction\":\"\",\"nillable\":\"false\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":[{\"ID\":\"accountDetails_WSDLGUIMT_accArBriefDesc_WSDLGUIMT\",\"fieldName\":\"accArBriefDesc\",\"fieldType\":\"string\",\"restriction\":\"\",\"nillable\":\"false\",\"value\":\"testObj.testArg1\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":\"\"},{\"ID\":\"accountDetails_WSDLGUIMT_addressesList_WSDLGUIMT\",\"fieldName\":\"addressesList\",\"fieldType\":\"List<accountAddressDC>\",\"restriction\":\"\",\"nillable\":\"true\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":[{\"ID\":\"_1\",\"fieldName\":\"addressesList\",\"fieldType\":\"accountAddressDC\",\"restriction\":\"undefined\",\"nillable\":\"undefined\",\"value\":\"\",\"list\":\"\",\"map\":\"\",\"reqResCO\":[{\"ID\":\"_1_addressAr1_WSDLGUIMT\",\"fieldName\":\"addressAr1\",\"fieldType\":\"string\",\"restriction\":\"\",\"nillable\":\"false\",\"value\":\"testObj.testList.arg2\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":\"\"}],\"lstInReqRes\":\"\"}]},{\"ID\":\"accountDetails_WSDLGUIMT_autoSweep_WSDLGUIMT\",\"fieldName\":\"autoSweep\",\"fieldType\":\"string\",\"restriction\":\"\",\"nillable\":\"false\",\"value\":\"testObj.Arg3\",\"list\":\"\",\"map\":\"\",\"reqResCO\":\"\",\"lstInReqRes\":\"\"}],\"lstInReqRes\":\"\"}]";

		ImcoPwsMappingBOImpl d = new ImcoPwsMappingBOImpl();
		// d.returnMapFromStr(jsonStr);

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		try {
			d.callPWS("testMapId",parameters);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		//d.testCallPWS(parameters);
		

	}

	public HashMap<String, Object> callPWS(String mapId, HashMap<String, Object> mappingIdsMap) throws BaseException {

		// only for testing must be removed later
		//testCallPWS(mappingIdsMap);
		
		// get the mapping details from DB
		String sourceKey, destinationkey;
		HashMap<String, Object> destinationMap = new HashMap<String, Object>(mappingIdsMap);
		DGTL_GTW_MAPPING_DETAILSVO DGTL_GTW_MAPPING_DETAILSVO = new DGTL_GTW_MAPPING_DETAILSVO();
		DGTL_GTW_MAPPINGVO DGTL_GTW_MAPPINGVO = new DGTL_GTW_MAPPINGVO();
		HashMap<String, Object> result = new HashMap<String,Object>();
		DGTL_GTW_MAPPINGVO.setMAPPING_ID(mapId);
		DGTL_GTW_MAPPINGVO = (DGTL_GTW_MAPPINGVO) genericDAO.selectByPK(DGTL_GTW_MAPPINGVO);

		// Master table needed information
		String wsName = DGTL_GTW_MAPPINGVO.getWS_NAME();
		String operName = DGTL_GTW_MAPPINGVO.getOPER_NAME();

		List<DGTL_GTW_MAPPING_DETAILSVO> mappingDetailsList = imcoPwsMappingDAO.returnPWSMappingParams(mapId);

		for (int i = 0; i < mappingDetailsList.size(); i++) {
			DGTL_GTW_MAPPING_DETAILSVO = mappingDetailsList.get(i);
			sourceKey = DGTL_GTW_MAPPING_DETAILSVO.getSOURCE_KEY();
			destinationkey = DGTL_GTW_MAPPING_DETAILSVO.getDESTINATION_KEY();
			
			
			// if destination key is different than null , it can be constant value or a mapped object
			// the checking is done in returnOutputMap method
			if(destinationkey != null) {
				destinationMap = returnOutputMap(sourceKey, destinationkey, mappingIdsMap,destinationMap);
			}
		}
		String serviceUrl = null;

		String appName = "IMCO"; // this needs to be modifed to be dynamic from request
		try {
			 serviceUrl = PathPropertyUtil.returnPathPropertyFromFile("PathWebServicesList.properties",
					"app."+appName+".serviceURL");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//wsName : needs to be linked to a hashmap containing bo reference
		//operName : needs to be linked to a hashmap cotaining bo method
		// call the ws through rmi
		ButtonCustomizationRmiCallerBO buttonCustomizationRmiCallerBO;
		try {
			buttonCustomizationRmiCallerBO = (ButtonCustomizationRmiCallerBO) RmiServiceCaller
					.returnRmiInterface(serviceUrl.concat("buttonCustomizationRmiCallerBOService"), ButtonCustomizationRmiCallerBO.class);
//			result = buttonCustomizationRmiCallerBO.digitalExecuteBoMethod(wsName, operName, destinationMap);
		} catch (Exception e) {
			log.error(e, e.getMessage());
		}
		return result;
	}

	/*public void testCallPWS(HashMap<String, Object> parameters) {

		CreateGeneralAccountReq createGeneralAccountreq = new CreateGeneralAccountReq();
		List<AccountExceptionalRate> accountCRExceptionalRateDCList = new ArrayList<AccountExceptionalRate>();
		AccountExceptionalRate accountExceptionalRate = new AccountExceptionalRate();
		AccountDetails accountDetails = new AccountDetails();
		Address address = new Address();
		List<Address> addressesList = new ArrayList<>();
		String accArBriefDesc = "valueAccArBriefDesc";
		String autoSweep = "valueAutoSweep";

		address.setAddressAr1("valueAddress1");
		address.setArea(BigDecimal.valueOf(999));
		addressesList.add(address);

		accountDetails.setAccArBriefDesc(accArBriefDesc);
		accountDetails.setAddressesList(addressesList);
		accountDetails.setAutoSweep(autoSweep);

		accountExceptionalRate.setExpiryDate(new Date());
		accountExceptionalRate.setFromBalance(BigDecimal.valueOf(5000));
		accountExceptionalRate.setAdditionalSpecialRate(BigDecimal.valueOf(1234));
		accountCRExceptionalRateDCList.add(accountExceptionalRate);

		createGeneralAccountreq.setAccountCRExceptionalRateDCList(accountCRExceptionalRateDCList);
		createGeneralAccountreq.setAccountDetails(accountDetails);

		parameters.putAll(PathPropertyUtil.convertToMap(createGeneralAccountreq));
	}*/

	private HashMap<String, Object> returnOutputMap(String inputKeysStr, String outputKeysStr, HashMap<String, Object> inputKeyMap
			,HashMap<String, Object> outputKeyMap)
	{

		// the name contains dot "." in order to know the objects level
		String[] inputKeysArr = inputKeysStr.split("\\.");
		String[] outputKeysArr = outputKeysStr.split("\\.");

		// if output size different than input size , it is not object mapping and it is a static value
		if(outputKeysArr.length != inputKeysArr.length)
		{
			return outputKeyMap;
		}
		
		Queue<String> inputQueue = new LinkedList<>(Arrays.asList(inputKeysArr));
		Queue<String> outputQueue = new LinkedList<>(Arrays.asList(outputKeysArr));

		String inputKey, outputKey;
		Object childObj = outputKeyMap;
		
		// for the first level of the hashmap, replace the old key name with new key name
		inputKey = inputQueue.poll();
		outputKey = outputQueue.poll();
		
		childObj = inputKeyMap.get(inputKey);
		
		outputKeyMap.remove(inputKey);
		outputKeyMap.put(outputKey, childObj);

		// child obj is second level of hashmap key
		copyFromSourceObj(inputQueue,outputQueue,childObj);
		
		return outputKeyMap;
	}
	private void copyFromSourceObj(Queue<String> inputQueue, Queue<String> outputQueue, Object childObj) {
		String inputKey,outputKey;
		Object parentObj;
		
		// recursive method in order to replace the keys with respective keys
		while ((inputKey = inputQueue.poll()) != null && (outputKey = outputQueue.poll()) != null && childObj != null) {
			parentObj = childObj;
			
			if (childObj instanceof Map) { // its an object
				childObj = ((Map<String, Object>) childObj).get(inputKey);
				((Map<String, Object>) parentObj).remove(inputKey);
				((Map<String, Object>) parentObj).put(outputKey,childObj);

			} else if (childObj instanceof ArrayList<?>) { // its a list
				ArrayList inputList = (ArrayList) childObj;

				for (Object tempObj : inputList) { 
					childObj = ((Map<String, Object>) tempObj).get(inputKey);
					((Map<String, Object>) tempObj).remove(inputKey);
					((Map<String, Object>) tempObj).put(outputKey,childObj);
				}
			}
			
		}
	}

	public ImcoPwsMappingDAO getImcoPwsMappingDAO() {
		return imcoPwsMappingDAO;
	}

	public void setImcoPwsMappingDAO(ImcoPwsMappingDAO imcoPwsMappingDAO) {
		this.imcoPwsMappingDAO = imcoPwsMappingDAO;
	}

}
