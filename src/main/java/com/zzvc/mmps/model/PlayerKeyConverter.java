package com.zzvc.mmps.model;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class PlayerKeyConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof PlayerKey) {
			return convertPlayerKey((PlayerKey) o);
		}
		return null;
	}
	
	private String convertPlayerKey(PlayerKey playerKey) {
		StringBuffer sb = new StringBuffer();
		sb.append("<li><span class=\"folder\">");
		sb.append(playerKey.getKeyName());
		sb.append("</span><ul>");
		for (PlayerKeyValue playerKeyValue : playerKey.getPlayerKeyValues()) {
			sb.append("<li><span class=\"file\">");
			sb.append(playerKeyValue.getValue());
			sb.append("</span></li>");
		}
		for (PlayerKey childPlayerKey : playerKey.getPlayerKeies()) {
			sb.append(convertPlayerKey(childPlayerKey));
		}
		sb.append("</ul></li>");
		return sb.toString();
	}

}
