package com.finereport.dao;

import java.sql.Connection;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.SpringUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.fr.base.TemplateUtils;
import com.fr.data.impl.AbstractDatabaseConnection;
import com.fr.data.pool.DBCPConnectionPoolAttr;
import com.fr.data.pool.MemoryConnection;
import com.fr.general.ComparatorUtils;
import com.fr.general.FRLogger;
import com.fr.stable.CodeUtils;
import com.fr.stable.xml.XMLPrintWriter;
import com.fr.stable.xml.XMLableReader;

public class FRJDBCConnection extends AbstractDatabaseConnection {

	private String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	private String url = "";
	private String user = "";
	private String password = "";
	private DBCPConnectionPoolAttr dbcpAttr = null;
	private boolean encryptPassword = true;

	public FRJDBCConnection() {
		this("", "", "", "");
	}

	public FRJDBCConnection(String paramString1, String paramString2,
			String paramString3, String paramString4) {
		setDriver(paramString1);
		setURL(paramString2);
		setUser(paramString3);
		setPassword(paramString4);
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String paramString) {
		this.driver = paramString;
	}

	public String getURL() {
		return this.url;
	}

	public void setURL(String paramString) {
		this.url = paramString;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String paramString) {
		this.user = paramString;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String paramString) {
		this.password = paramString;
	}

	public boolean isEncryptPassword() {
		return this.encryptPassword;
	}

	public void setEncryptPassword(boolean paramBoolean) {
		this.encryptPassword = paramBoolean;
	}

	public DBCPConnectionPoolAttr getDbcpAttr() {
		return this.dbcpAttr;
	}

	public void setDbcpAttr(DBCPConnectionPoolAttr paramDBCPConnectionPoolAttr) {
		this.dbcpAttr = paramDBCPConnectionPoolAttr;
	}

	public void testConnection() throws Exception {
		createConnection().close();
	}

	public Connection createConnection() throws Exception {
		return MemoryConnection.create(renderAttribute(getDriver()),
				renderAttribute(getURL()), renderAttribute(getUser()),
				renderAttribute(getPassword()), this.dbcpAttr);
	}

	private static String renderAttribute(String paramString) {
		if (paramString != null)
			try {
				return TemplateUtils.render(paramString);
			} catch (Exception localException) {
				FRLogger.getLogger().error(localException.getMessage(),
						localException);
			}
		return paramString;
	}

	public boolean equals(Object paramObject) {
		if (!(paramObject instanceof FRJDBCConnection))
			return false;
		FRJDBCConnection localFRJDBCConnection = (FRJDBCConnection) paramObject;
		return (super.equals(localFRJDBCConnection))
				&& (ComparatorUtils.equals(this.driver,
						localFRJDBCConnection.driver))
				&& (ComparatorUtils.equals(this.url, localFRJDBCConnection.url))
				&& (ComparatorUtils.equals(this.user,
						localFRJDBCConnection.user))
				&& (ComparatorUtils.equals(this.password,
						localFRJDBCConnection.password))
				&& (ComparatorUtils.equals(
						Boolean.valueOf(this.encryptPassword),
						Boolean.valueOf(localFRJDBCConnection.encryptPassword)))
				&& (ComparatorUtils.equals(this.dbcpAttr,
						localFRJDBCConnection.dbcpAttr));
	}

	public int hashCode() {
		int i = 0;
		i = 37 * i + getDriver().hashCode();
		i = 37 * i + getURL().hashCode();
		i = 37 * i + getUser().hashCode();
		i = 37 * i + getPassword().hashCode();
		i = 37 * i + (isEncryptPassword() ? 1 : 0);
		return i;
	}

	public String toString() {
		return "[JDBCDatabase][Driver:" + getDriver() + "][URL:" + getURL()
				+ "]" + "[User:" + getUser() + "][Password:***]";
	}

	public void readXML(XMLableReader paramXMLableReader) {

		DruidDataSource obj = (DruidDataSource) SpringUtils
				.getBean("dataSource_jeecg");
		super.readXML(paramXMLableReader);
		if (StringUtils.contains(paramXMLableReader.getTagName(),
				"JDBCDatabaseAttr")) {

			String str;
			if ((str = obj.getDriverClassName()) != null)
				setDriver(str);
			if ((str = obj.getUrl()) != null)
				setURL(str);
			if ((str = obj.getUsername()) != null)
				setUser(str);
			if ((str = obj.getPassword()) != null)
				setPassword(str);
			// setEncryptPassword(paramXMLableReader.getAttrAsBoolean(
			// "encryptPassword", true));
			DBCPConnectionPoolAttr localDBCPConnectionPoolAttr = new DBCPConnectionPoolAttr();
			setDbcpAttr(localDBCPConnectionPoolAttr);
			paramXMLableReader.readXMLObject(localDBCPConnectionPoolAttr);
		}

	}

	public void writeXML(XMLPrintWriter paramXMLPrintWriter) {
		super.writeXML(paramXMLPrintWriter);
		paramXMLPrintWriter.startTAG("JDBCDatabaseAttr").attr("url", getURL())
				.attr("driver", getDriver()).attr("user", getUser());
		if (isEncryptPassword())
			paramXMLPrintWriter.attr("password",
					CodeUtils.passwordEncode(getPassword()));
		else
			paramXMLPrintWriter.attr("password", getPassword());
		paramXMLPrintWriter.attr("encryptPassword", isEncryptPassword());
		if (getDbcpAttr() != null)
			getDbcpAttr().writeXML(paramXMLPrintWriter);
		paramXMLPrintWriter.end();
	}

}
