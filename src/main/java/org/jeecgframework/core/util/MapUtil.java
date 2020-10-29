package org.jeecgframework.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapUtil {
	
	public static List<String> getKeyList(Map map) {
		if (null == map || map.isEmpty())
			return null;
		Set keys = map.keySet();
		List<String> strs = new ArrayList<String>();
		for (Iterator iter = keys.iterator(); iter.hasNext();) {
			strs.add(iter.next().toString());
		}
		return strs;
	}
}
