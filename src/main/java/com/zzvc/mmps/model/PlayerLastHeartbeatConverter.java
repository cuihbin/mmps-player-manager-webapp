package com.zzvc.mmps.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.ConversionException;
import org.apache.struts2.util.StrutsTypeConverter;

public class PlayerLastHeartbeatConverter extends StrutsTypeConverter {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (toClass == Date.class) {
			try {
				return format.parseObject(values[0]);
			} catch (ParseException e) {
				throw new ConversionException("Could not convert value \"" + values[0] + "\" to date.", e);
			}
		}
		throw new ConversionException("Could not convert " + toClass.getClass().getName() + " to java.util.Date");
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof Date) {
			return format.format((Date) o);
		}
		return o.toString();
	}
}
