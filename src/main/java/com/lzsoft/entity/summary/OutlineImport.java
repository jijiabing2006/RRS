package com.lzsoft.entity.summary;

import javax.persistence.Entity;

import org.jeecgframework.core.common.entity.IdEntity;

@Entity
@SuppressWarnings("serial")
public class OutlineImport extends IdEntity implements java.io.Serializable {
	
	private String importstate;
	private String accextractstate;
	private String bopextractstate;
	private String jshextractstate;
	private String importfolderdate;
	private String importmessage;
	private String hasexceptiondeal;
	private String lastt24importdate;
	private String lastsummitimportdate;
	private String t24summitdatediff;
	
	public String getAccextractstate() {
		return accextractstate;
	}
	public void setAccextractstate(String accextractstate) {
		this.accextractstate = accextractstate;
	}
	public String getBopextractstate() {
		return bopextractstate;
	}
	public void setBopextractstate(String bopextractstate) {
		this.bopextractstate = bopextractstate;
	}
	public String getJshextractstate() {
		return jshextractstate;
	}
	public void setJshextractstate(String jshextractstate) {
		this.jshextractstate = jshextractstate;
	}
	
	public String getImportfolderdate() {
		return importfolderdate;
	}
	public void setImportfolderdate(String importfolderdate) {
		this.importfolderdate = importfolderdate;
	}
	public String getImportmessage() {
		return importmessage;
	}
	public void setImportmessage(String importmessage) {
		this.importmessage = importmessage;
	}
	public String getImportstate() {
		return importstate;
	}
	public void setImportstate(String importstate) {
		this.importstate = importstate;
	}
	public String getHasexceptiondeal() {
		return hasexceptiondeal;
	}
	public void setHasexceptiondeal(String hasexceptiondeal) {
		this.hasexceptiondeal = hasexceptiondeal;
	}
	public String getLastt24importdate() {
		return lastt24importdate;
	}
	public void setLastt24importdate(String lastt24importdate) {
		this.lastt24importdate = lastt24importdate;
	}
	public String getLastsummitimportdate() {
		return lastsummitimportdate;
	}
	public void setLastsummitimportdate(String lastsummitimportdate) {
		this.lastsummitimportdate = lastsummitimportdate;
	}
	public String getT24summitdatediff() {
		return t24summitdatediff;
	}
	public void setT24summitdatediff(String t24summitdatediff) {
		this.t24summitdatediff = t24summitdatediff;
	}
}
