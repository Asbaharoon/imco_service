package com.path.atm.netty.isoutils;

import com.path.lib.common.util.StringUtil;

public class BaseConvertor {
	
	public static String hexaToBinary(String hexaDecimal) 
	{
		StringBuilder binary = new StringBuilder();
		if(!StringUtil.isEmptyString(hexaDecimal)) 
		{
			String bin = "";
			char hexa[] = hexaDecimal.toCharArray();
			for (int i = 0; i < hexa.length; i++) 
			{
				int j = Integer.parseInt(hexa[i] + "", 16);
				bin = Integer.toBinaryString(j);
				while (bin.length() != 4)
				{
					bin = "0" + bin;
				}
				binary.append(bin);
			}
		}
		return binary.toString();
	}
}
