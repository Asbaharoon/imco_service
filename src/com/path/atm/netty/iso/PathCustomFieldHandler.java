package com.path.atm.netty.iso;

import java.util.ArrayList;
import java.util.List;

import com.solab.iso8583.CustomField;
import com.solab.iso8583.IsoValue;

public class PathCustomFieldHandler implements CustomField<List<IsoValue>>, Cloneable
{
	private ArrayList<IsoValue> customFieldsInfo;
	
	@Override
	public String encodeField(List<IsoValue> customFieldsList) 
	{
		String customFieldsString = null;
		StringBuilder sb = new StringBuilder();
		for (IsoValue isoValue : customFieldsList) 
		{
			 sb.append(isoValue.toString());
		}
		customFieldsString = sb.toString();
		return customFieldsString;
	}
	
	@Override
	public List<IsoValue> decodeField(String customFieldsString) 
	{
		List<IsoValue> customFieldsList = null;
		if(!"".equals(customFieldsString) && this.customFieldsInfo != null && this.customFieldsInfo.size() > 0)
		{
			int lastIndex = 0;
			customFieldsList = new ArrayList();
			IsoValue isoValue = null;
			for (IsoValue info : this.customFieldsInfo) 
			{
				isoValue = new IsoValue(info.getType(), customFieldsString.substring(lastIndex, (lastIndex+info.getLength())).trim(), info.getLength());
				customFieldsList.add(isoValue);
				lastIndex = lastIndex + info.getLength();
			}
		}
		return customFieldsList;
	}
	
	public ArrayList<IsoValue> getCustomFieldsInfo() {
		return customFieldsInfo;
	}

	public void setCustomFieldsInfo(ArrayList<IsoValue> customFieldsInfo) {
		this.customFieldsInfo = customFieldsInfo;
	}
	
	public PathCustomFieldHandler(ArrayList<IsoValue> _customFieldsInfo) 
	{
		this.customFieldsInfo = _customFieldsInfo;
	}
	
	public PathCustomFieldHandler() {
		
	}
}
