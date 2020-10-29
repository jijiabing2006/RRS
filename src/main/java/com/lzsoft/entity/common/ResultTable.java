package com.lzsoft.entity.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;
import org.springframework.stereotype.Repository;

@Entity
@Table(name="resultTable")
@Repository("resultTable")
public class ResultTable extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TABLENAME", nullable = true)
	private String tablename;
	@Column(name = "COLUMNINDEX", nullable = true)
	private Integer columnindex;
	@Column(name = "ROWINDEX", nullable = true)
	private Integer rowindex;
	@Column(name = "TABLEAMOUNT", nullable = true)
	private Double tableamount;
	@Column(name = "CCY", nullable = true)
	private String ccy;
	@Column(name = "BKID", nullable = true)
	private String bkid;
	@Column(name = "IMPORTDATE", updatable = false)
	private Date importdate;

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public Integer getColumnindex() {
		return columnindex;
	}

	public void setColumnindex(Integer columnindex) {
		this.columnindex = columnindex;
	}

	public Integer getRowindex() {
		return rowindex;
	}

	public void setRowindex(Integer rowindex) {
		this.rowindex = rowindex;
	}

	public Double getTableamount() {
		return tableamount;
	}

	public void setTableamount(Double tableamount) {
		this.tableamount = tableamount;
	}

	public Date getImportdate() {
		return importdate;
	}

	public void setImportdate(Date importdate) {
		this.importdate = importdate;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getBkid() {
		return bkid;
	}

	public void setBkid(String bkid) {
		this.bkid = bkid;
	}

}
