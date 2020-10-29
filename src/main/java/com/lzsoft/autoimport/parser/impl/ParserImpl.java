package com.lzsoft.autoimport.parser.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.DateUtils;
import org.jetel.data.DataField;
import org.jetel.data.DataRecord;
import org.jetel.data.parser.DelimitedDataParser;
import org.jetel.graph.runtime.EngineInitializer;
import org.jetel.metadata.DataRecordMetadata;
import org.jetel.metadata.DataRecordMetadataXMLReaderWriter;
import org.springframework.stereotype.Service;

import com.lzsoft.autoimport.commons.Constants;
import com.lzsoft.autoimport.parser.IParser;
import com.lzsoft.util.LogUtil;
import com.lzsoft.util.Rootpath;

@Service("parserimpl")
public class ParserImpl implements IParser {

	private static final Logger log = Logger.getLogger(ParserImpl.class);

	private static Configuration mappingFilePath = null;

	/**
	 * 过滤没有内容的文件
	 * 
	 * @param files
	 */
	private boolean isEmptyFile(File file) {
		LineIterator li = null;
		String line = null;
		try {
			li = FileUtils.lineIterator(file, "UTF8");
			if (null != li && li.hasNext()) {
				line = li.nextLine();
				if (StringUtils.isEmpty(line)) {
					line = null;
					return true;
				}
				line = null;
			} else {
				return true;
			}
			file = null;
		} catch (IOException e) {
			log.error("ParserImpl.isEmptyFile(File file) \n" + e.getMessage(),
					e);
		} finally {
			li.close();
			li = null;
		}
		return false;
	}

	private String getMappingfilePath(String filename) {
		if (null == mappingFilePath) {
			try {
				mappingFilePath = new PropertiesConfiguration(
						Constants.AUDITIMPORT_PATH);
			} catch (ConfigurationException e) {
				log.error(
						"getMappingfilePath(String classname) \n"
								+ e.getMessage(), e);
			}
		}
		return mappingFilePath.getString(filename.substring(0,
				filename.lastIndexOf(".")).toUpperCase());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> parser(String filename, Class  clazz, Date importdate)
			throws Exception {
		File sourcefile=new File(filename);
		if (isEmptyFile(sourcefile)) {
			log.info("文件:" + sourcefile.getName() + "没有数据");
			return Collections.emptyList();
		}
		log.info("文件路径:" + sourcefile.getAbsolutePath());

		String mappingfile = getMappingfilePath(sourcefile.getName());
		if (null == mappingfile) {
			log.error("没有与" + clazz + "对应的配置文件");
			return Collections.emptyList();
		}
		EngineInitializer.initEngine("", "", "");
		DataRecordMetadataXMLReaderWriter reader = new DataRecordMetadataXMLReaderWriter();
		DataRecordMetadata metadata = null;
		String logPath = "";
		FileInputStream in = null;

		try {

			PropertiesConfiguration p = new PropertiesConfiguration(
					Constants.DATA_FILES_PATH);
			logPath = p.getString("logpath");
			metadata = reader.read(new FileInputStream(Rootpath.getRootPath()
					+ mappingfile));
			DelimitedDataParser parser = new DelimitedDataParser("UTF8");
			parser.init(metadata);
			in = new FileInputStream(sourcefile);
			parser.setDataSource(in);

			DataRecord record = null;
			List<T> targetList = new ArrayList<T>();
			parser.skip(1);
			T obj = null;

			while (null != (record = parser.getNext())) {
//System.out.println(record);
				obj = (T) getTargetObject(record, clazz);
				BeanUtils.copyProperty(obj, "source", StringUtils
						.upperCase(StringUtils.replaceOnce(
								sourcefile.getName(), ".", "")));
//System.out.println(obj.toString());
				PropertyUtils.setProperty(obj, "importdate", importdate);
				targetList.add(obj);
			}
			in.close();
			return targetList;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ParserImpl.parser(File sourcefile) \n" + e.getMessage(),
					e);
			LogUtil.generateLog(logPath,
					"ParserImpl.parser(File sourcefile) \n" + e.getMessage());
			// Collections.emptyList();
			throw e;
		} finally {
			in.close();
		}
	}
	
	public <T> List<T> parser(File sourcefile, Class clazz) throws Exception {
		if (isEmptyFile(sourcefile)) {
			log.info("文件:" + sourcefile.getName() + "没有数据");
			return Collections.emptyList();
		}
		log.info("文件路径:" + sourcefile.getAbsolutePath());

		String mappingfile = getMappingfilePath(sourcefile.getName());
		if (null == mappingfile) {
			log.error("没有与" + clazz + "对应的配置文件");
			return Collections.emptyList();
		}

		// mappingfile="D:\\Workspaces\\CUB\\AutoImport\\src\\config\\importdata\\etl\\apostpdadd.fmt";
		// mappingfile="D:\\Workspaces\\Cathay\\Task\\src\\config\\importdata\\fmt\\apostpdadd.fmt";
		EngineInitializer.initEngine("", "", "");
		DataRecordMetadataXMLReaderWriter reader = new DataRecordMetadataXMLReaderWriter();
		DataRecordMetadata metadata = null;
		String logPath = "";
		FileInputStream in = null;

		try {

			PropertiesConfiguration p = new PropertiesConfiguration(
					Constants.DATA_FILES_PATH);
			logPath = p.getString("logpath");
			metadata = reader.read(new FileInputStream(Rootpath.getRootPath()
					+ mappingfile));
			DelimitedDataParser parser = new DelimitedDataParser("UTF8");
			parser.init(metadata);
			in = new FileInputStream(sourcefile);
			parser.setDataSource(in);

			DataRecord record = null;
			List<T> targetList = new ArrayList<T>();
			parser.skip(1);
			T obj = null;

			while (null != (record = parser.getNext())) {
//System.out.println(record);
				obj = (T) getTargetObject(record, clazz);
				BeanUtils.copyProperty(obj, "source", StringUtils
						.upperCase(StringUtils.replaceOnce(
								sourcefile.getName(), ".", "")));
//System.out.println(obj.toString());
				targetList.add(obj);
			}
			in.close();
			return targetList;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ParserImpl.parser(File sourcefile) \n" + e.getMessage(),
					e);
			LogUtil.generateLog(logPath,
					"ParserImpl.parser(File sourcefile) \n" + e.getMessage());
			// Collections.emptyList();
			throw e;
		} finally {
			in.close();
		}
	}

	private Object getTargetObject(DataRecord record, Class clazz)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, InvocationTargetException,
			NoSuchMethodException, Exception {
		String fieldtype = null;
		Object obj = Class.forName(clazz.getName()).newInstance();
		Object objvalue = null;
		for (Iterator<DataField> iterator = record.iterator(); iterator
				.hasNext();) {
			DataField dataField = iterator.next();

			String fieldname = dataField.getMetadata().getName();
//System.out.println(fieldname+":"+PropertyUtils.getPropertyType(obj, fieldname));
			fieldtype = PropertyUtils.getPropertyType(obj, fieldname).getName();

			objvalue = null;
			if (StringUtils.equals(Constants.JAVA_DATE_TYPE, fieldtype)) {
				String value = "";
				if (null != dataField.getValue()) {
					value = dataField.getValue().toString();
					if (value.indexOf("\"") != -1) {
						value = value.substring(value.indexOf("\"")
								+ Constants.NUM_ONE, value.lastIndexOf("\""));
					}
				}
				Date datevalue = convertDate(value, fieldname);
				objvalue = datevalue;
			} else if (StringUtils
					.equals(Constants.JAVA_DOUBLE_TYPE, fieldtype)) {
				Double value = convertDouble(dataField.getValue(), fieldname);
				objvalue = value;
			} else if (StringUtils.equals(Constants.JAVA_BIGDECIMAL_TYPE,
					fieldtype)) {
				BigDecimal value = convertBigDecimal(dataField.getValue(),
						fieldname);
				objvalue = value;
			} else if (null != dataField.getValue()) {
				String value = "";
				if (null != dataField.getValue()) {
					value = dataField.getValue().toString();
					if (value.indexOf("\"") != -1) {
						value = value.substring(value.indexOf("\"")
								+ Constants.NUM_ONE, value.lastIndexOf("\""));
					}
				}
				value = StringUtils.replace(StringUtils.trim(value), "|", " ");
				objvalue = value;
			}
			BeanUtils.copyProperty(obj, fieldname, objvalue);
		}
		return obj;
	}

	private Double convertDouble(Object value, String field) {
		try {
			PropertiesConfiguration p = new PropertiesConfiguration(
					Constants.DATA_FILES_PATH);
			String logPath = p.getString("logpath");
			if (value == null) {
				return new Double(Constants.NUM_ZERO);
			} else if (NumberUtils.isNumber(value.toString())) {
				return Double.valueOf(value.toString());
			} else {
				log.info("字段:" + field + " = " + String.valueOf(value) + "不是数字");
				LogUtil.generateLog(logPath, "字段:" + field + " = " + String.valueOf(value) + "不是数字");
				return new Double(Constants.NUM_ZERO);
			}
		} catch (Exception e) {
			log.error(
					"ParserImpl.changDouble(Object value) \n" + e.getMessage(),
					e);
		}
		return null;
	}

	private BigDecimal convertBigDecimal(Object value, String field) {
		
		try {
			PropertiesConfiguration p = new PropertiesConfiguration(
					Constants.DATA_FILES_PATH);
			String logPath = p.getString("logpath");
			if (value == null) {
				return new BigDecimal(Constants.NUM_ZERO);
			} else if (NumberUtils.isNumber(value.toString())) {
				return new BigDecimal(value.toString());
			} else {
				log.info("字段:" + field + " = " + String.valueOf(value) + "不是数字");
				LogUtil.generateLog(logPath, "字段:" + field + " = " + String.valueOf(value) + "不是数字");
				return new BigDecimal(Constants.NUM_ZERO);
			}
		} catch (Exception e) {
			log.error(
					"ParserImpl.changBigDecimal(Object value) \n"
							+ e.getMessage(), e);
		}
		return null;
	}

	private Date convertDate(Object value, String field) {
		try {
			if (value == null) {
				return null;
			} else if("".equals(value.toString().trim())){
				return null;
			}else if (StringUtils.INDEX_NOT_FOUND != StringUtils.indexOf(value.toString(), "    -")
					|| StringUtils.INDEX_NOT_FOUND != StringUtils.indexOf(
							value.toString(), "    ")
					|| StringUtils.equals("99999999", value.toString())) {
				return DateUtils.strToDate(Constants.DATE_DEFAULT,
						Constants.DATEFORMAT);
			} else if (value.toString().indexOf("?\"") != -1) {
				String date = value.toString().substring(
						value.toString().indexOf("\"") + 1,
						value.toString().lastIndexOf("\""));
				date = DateUtils.format(date.toString(), "-",
						Constants.DATEFORMAT);

				return org.apache.commons.lang3.time.DateUtils.parseDate(date, Constants.DATEFORMATS);
			} else if (null != value && value.toString().length() >= 8) {
				String date = value.toString();
				return org.apache.commons.lang3.time.DateUtils.parseDate(date, Constants.DATEFORMATS);
			}
		} catch (Exception e) {
			log.error("ParserImpl.changDate(Object value) \n" + e.getMessage(),
					e);
			log.info("字段:" + field + " = " + String.valueOf(value) + "转换出错");
		}
		return null;
	}


}