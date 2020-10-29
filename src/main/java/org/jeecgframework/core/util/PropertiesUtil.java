package org.jeecgframework.core.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.lzsoft.common.Constants;

/**
 * 
 * @author  张代浩
 *
 */
public class PropertiesUtil {
	private String properiesName = "";

	public PropertiesUtil() {

	}
	public PropertiesUtil(String fileName) {
		this.properiesName = fileName;
	}
	public String readProperty(String key) {
		String value = "";
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					properiesName);
			Properties p = new Properties();
			p.load(is);
			value = p.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}

	public Properties getProperties() {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					properiesName);
			p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}

	public void writeProperty(String key, String value) {
		InputStream is = null;
		OutputStream os = null;
		Properties p = new Properties();
		try {
			is = new FileInputStream(properiesName);
			p.load(is);
			os = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(properiesName).getFile());

			p.setProperty(key, value);
			p.store(os, key);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
				if (null != os)
					os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public static Map<String, String> getMapByPorpFile(String filePath){
		
		PropertiesUtil p = new PropertiesUtil(filePath);
		Map<String, String> map = new HashMap<String, String>();
		Properties ps=p.getProperties();
		Set<Object> ks=ps.keySet();
		for (Object key : ks) {
			map.put((String) key,ps.getProperty((String) key));
		}
		return map;
	}
	public static void main(String[] args) {
//		PropertiesUtil p = new PropertiesUtil("sysConfig.properties");
//		p.writeProperty("namess", "wang");
//		org.jeecgframework.core.util.LogUtil.info(p.readProperty("namess"));
		
		PropertiesUtil p = new PropertiesUtil(Constants.ACCOUNTTYPEPATH);
		Map<String, String> map = new HashMap<String, String>();
		Properties ps=p.getProperties();
		Set<Object> ks=ps.keySet();
		for (Object key : ks) {
			map.put((String) key,ps.getProperty((String) key));
		}
		System.out.println(map.size());
		List<String> sl=MapUtil.getKeyList(map);
		System.out.println(sl);
	}

}
