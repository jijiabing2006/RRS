package com.lzsoft.entity.summary;

import javax.persistence.Entity;

import org.jeecgframework.core.common.entity.IdEntity;

@Entity
@SuppressWarnings("serial")
public class OutlineSAFE extends IdEntity implements java.io.Serializable {
	public OutlineSAFE() {
		// TODO Auto-generated constructor stub
	}

	private String importdate;

	public String getImportdate() {
		return importdate;
	}

	public void setImportdate(String importdate) {
		this.importdate = importdate;
	}

	private String counts;
	private String uneditnum;
	private String editnum;
	private String vnum;
	private String unvnum;
	private String waitnum;
	private String vinnum;
	private String failnum;

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getUneditnum() {
		return uneditnum;
	}

	public void setUneditnum(String uneditnum) {
		this.uneditnum = uneditnum;
	}

	public String getEditnum() {
		return editnum;
	}

	public void setEditnum(String editnum) {
		this.editnum = editnum;
	}

	public String getVnum() {
		return vnum;
	}

	public void setVnum(String vnum) {
		this.vnum = vnum;
	}

	public String getUnvnum() {
		return unvnum;
	}

	public void setUnvnum(String unvnum) {
		this.unvnum = unvnum;
	}

	public String getWaitnum() {
		return waitnum;
	}

	public void setWaitnum(String waitnum) {
		this.waitnum = waitnum;
	}

	public String getVinnum() {
		return vinnum;
	}

	public void setVinnum(String vinnum) {
		this.vinnum = vinnum;
	}

	public String getFailnum() {
		return failnum;
	}

	public void setFailnum(String failnum) {
		this.failnum = failnum;
	}
}
