package com.lzsoft.autoimport.service.base.etl;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.DateUtils;

import com.lzsoft.autoimport.commons.Constants;
import com.lzsoft.autoimport.service.base.etl.parse.Field;
import com.lzsoft.autoimport.service.base.etl.parse.Record;
import com.lzsoft.util.BeanUtils;
import com.lzsoft.util.LogUtil;
import com.lzsoft.util.Property;

public abstract class Etl {
	private static Configuration pc = null;

	public List etl(String classname, String resourceFilePath, Date importdate)
			throws Exception {
		List<Object> resultList = new ArrayList<Object>();
		File resourceFile = new File(resourceFilePath);
		try {
			if (null == pc) {
				pc = new PropertiesConfiguration(
						"config/importdata/etlmapping.properties");
			}

			Record record = new Record(pc.getString(getMappingName(
					resourceFile.getName(), importdate)));
			Object object = null;
			if (null == record.getType() || record.getType().trim().equals(""))
				throw new Exception("elt配置文件的type属性没有配置.");
			List<String> list = FileUtils.readLines(resourceFile, "UTF-8");
			for (String line : list) {
				// line = new String(line.getBytes(), "8859_1");
				object = Class.forName(classname)
						.getConstructor(new Class[] {})
						.newInstance(new Object[] {});
				if (null == object) {
					throw new Exception(classname + "不存在");
				}
				setValue(line, object, record);
				resultList.add(object);
			}
			PropertiesConfiguration p = new PropertiesConfiguration(
					Constants.DATA_FILES_PATH);
			String logPath = p.getString("logpath");
			LogUtil.generateLog(logPath, "文件夹路径为:" + DateUtils.getCurrDate24()
					+ "|info|" + resourceFilePath + " ok");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultList;
	}

	private String getMappingName(String sourceFilePath, Date trandate) {
		//String date = DateUtils.dateToStr(trandate, "yyyyMMdd");
		String mappingName = "";
		try {
			mappingName = sourceFilePath.substring(0,
					sourceFilePath.lastIndexOf("."));
		} catch (Exception e) {
			mappingName = sourceFilePath;
		}
		return mappingName;
	}

	private void setValue(String line, Object object, Record record)
			throws Exception {
		Property property = null;
		Integer start = null;
		Integer end = null;
		String value = null;
		Object pojoValue = null;
		DecimalFormat df = null;
		String[] values = null;
		Field field = null;
		if (record.getType().trim().toLowerCase().equals("fixed")) {
			for (int i = 0; i < record.getFieldList().size(); i++) {
				field = record.getFieldList().get(i);
				if (null == field.getName()
						|| "".equals(field.getName().trim()))
					throw new Exception("需要配置Field节点中的name属性");
				if (null == field.getStart()
						|| "".equals(field.getStart().trim())
						|| null == field.getEnd()
						|| "".equals(field.getEnd().trim())) {
					throw new Exception("fixed模式需要配置Field节点中的start和end属性");
				}
				if (null == field.getType()
						|| "".equals(field.getType().trim()))
					throw new Exception("需要配置Field节点中的type属性");
				property = BeanUtils.getProperty(object, field.getName());
				// if (null == property)
				// throw new Exception(object.getClass().toString() + "中没有属性["
				// + field.getName() + "]");
				if (null != property) {
					if (!property.getType().equals(field.getType()))
						throw new Exception(object.getClass().toString()
								+ "中属性[" + field.getName()
								+ "]的类型和elt配置文件中配置的类型不一致");
					try {
						start = Integer.parseInt(field.getStart());
					} catch (Exception e) {
						throw new Exception("请正确配置etl属性[" + field.getName()
								+ "]的start");
					}
					try {
						end = Integer.parseInt(field.getEnd());
					} catch (Exception e) {
						throw new Exception("请正确配置etl属性[" + field.getName()
								+ "]的end");
					}
					try {
						value = line.substring(start, end);
					} catch (Exception e) {
						throw new Exception("属性[" + field.getName() + "]的开始位置["
								+ start + "]和结束位置[" + end + "]超出了数据的长度["
								+ line.length() + "]");
					}
					setValue(object, field, value, pojoValue, df);
				}
			}
		}
		if (record.getType().trim().toLowerCase().equals("delimited")) {
			if (null == record.getFieldDelimiter()
					|| "".equals(record.getFieldDelimiter().trim()))
				throw new Exception("delimited模式需要配置Record节点的fieldDelimiter属性");
			// values = line.split(record.getFieldDelimiter());
			values = StringUtils.splitPreserveAllTokens(line,
					record.getFieldDelimiter());
			for (int i = 0; i < record.getFieldList().size(); i++) {
				field = record.getFieldList().get(i);
				try {
					// System.out.println(field.getName()+line);
					value = values[i];
				} catch (Exception e) {
					throw new Exception(object.getClass().toString()
							+ "的elt配置的字段数量大于被分隔符分割后的数据数量.");
				}
				setValue(object, field, value.replace("\"", ""), pojoValue, df);
			}
		}
	}

	private void setValue(Object object, Field field, String value,
			Object pojoValue, DecimalFormat df) throws Exception {

		pojoValue = null;
		try {
			if ("String".equalsIgnoreCase(field.getType())) {
				pojoValue = value;
			} else if ("Date".equalsIgnoreCase(field.getType())) {
				if (null == field.getFormat()
						|| "".equals(field.getFormat().trim()))
					throw new Exception(object.getClass().toString() + "的属性["
							+ field.getName() + "]需要配置format");
				try {
					if (null==value||"".equalsIgnoreCase(value.trim())||value.startsWith("999")||value.length()!=8) {
						pojoValue = DateUtils.strToDate(Constants.DATE_DEFAULT,
								"yyyy-MM-dd");
//						pojoValue=null;
					} else {
						pojoValue = DateUtils
								.strToDate(value, field.getFormat());
					}
				} catch (Exception e) {
					throw new Exception("Date类型转换失败");
				}
			} else if ("Long".equalsIgnoreCase(field.getType())) {
				try {
					pojoValue = Long.parseLong(value);
				} catch (Exception e) {
					throw new Exception("Long类型转换失败");
				}
			} else if ("Double".equalsIgnoreCase(field.getType())) {
				try {
					if ("".equalsIgnoreCase(value.trim())) {
						pojoValue = 0.00;
					} else {
						pojoValue = Double.parseDouble(value);
						if (null != field.getFormat()
								&& !"".equals(field.getFormat())) {
							try {
								df = new DecimalFormat(field.getFormat());
								pojoValue = df.format(value);
							} catch (Exception e) {
								throw new Exception("Double类型格式化失败");
							}
						}
					}

				} catch (Exception e) {
					throw new Exception("Double类型转换失败");
				}
			} else if ("BigDecimal".equalsIgnoreCase(field.getType())) {
				try {
//					System.out.println("vale="+value);
					if(null==value||"".equalsIgnoreCase(value.trim())){
						pojoValue = new BigDecimal(0);
					}else{
					pojoValue = new BigDecimal(value);
					}
//					System.out.println("pojoValue="+pojoValue);
				} catch (Exception e) {
					throw new Exception("BigDecimal类型转换失败");
				}
			}
			if (null != pojoValue)
				BeanUtils.copyProperty(object, field.getName(), pojoValue);
		} catch (Exception e) {
			throw new Exception(object.getClass().toString() + "的属性["
					+ field.getName() + "]赋值错误.value=" + value);
		}
	}
}
