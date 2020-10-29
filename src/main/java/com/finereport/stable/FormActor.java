package com.finereport.stable;

import com.fr.general.DeclareRecordType;
import com.fr.general.Inter;
import com.fr.general.LogConfig;
import com.fr.json.JSONException;
import com.fr.json.JSONObject;
import com.fr.main.impl.WorkBook;
import com.fr.main.workbook.FormRWorkBook;
import com.fr.main.workbook.ResultWorkBook;
import com.fr.report.cell.cellattr.core.attribute.CellElementAttribute;
import com.fr.report.cell.cellattr.core.attribute.FormCellElementAttribute;
import com.fr.report.cell.cellattr.core.attribute.OptionalAttribute;
import com.fr.report.core.A.H;
import com.fr.report.core.A.i;
import com.fr.report.core.A.q;
import com.fr.report.core.sheet.FormWorkBookExecutor;
import com.fr.report.core.sheet.WorkBookExecutor;
import com.fr.report.web.ToolBarManager;
import com.fr.report.worksheet.AbstractResECWorkSheet;
import com.fr.report.worksheet.FormRWorkSheet;
import com.fr.stable.web.Repository;
import com.fr.web.core.ReportSessionIDInfor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FormActor extends AbstractActor {
	public String description() {
		return Inter.getLocText("M-Form_Preview");
	}

	public q createBoxFactory() {
		return new i();
	}

	public AbstractResECWorkSheet createResultECWorkSheet(H paramH) {
		return new FormRWorkSheet();
	}

	public void release(H paramH) {
		paramH.B(false);
	}

	public OptionalAttribute cloneOptionalAttribute(
			OptionalAttribute paramOptionalAttribute) {
		return paramOptionalAttribute.lightClone4Form();
	}

	public CellElementAttribute createHyperCellAttr() {
		return FormCellElementAttribute.NAMEHYPERLINKGROUP;
	}

	public CellElementAttribute createCellGUIAttr() {
		return FormCellElementAttribute.CELLGUIATTR;
	}

	public CellElementAttribute createWidgetAttr() {
		return FormCellElementAttribute.WIDGET;
	}

	public ResultWorkBook createResultBook(Map paramMap) {
		return new FormRWorkBook(paramMap);
	}

	public WorkBookExecutor createWorkBookExecutor(WorkBook paramWorkBook,
			Map paramMap) {
		return new FormWorkBookExecutor(paramWorkBook, paramMap);
	}

	public DeclareRecordType getRecordType() {
		return DeclareRecordType.EXECUTE_TYPE_FORM;
	}

	public boolean shouldRecord() {
		return LogConfig.getLogConfig().isRecordExe4form();
	}

	public ToolBarManager[] toolbarManagers(Repository paramRepository) {
		return new ToolBarManager[0];
	}

	public String panelType() {
		return "form";
	}

	public String mainJavaScriptPath() {
		return "";
	}

	public JSONObject createReportWebAttr4Mobile(
			HttpServletRequest paramHttpServletRequest,
			ReportSessionIDInfor paramReportSessionIDInfor)
			throws JSONException {
		return null;
	}
}

/*
 * Location: C:\FineReport_8.0\WebReport\WEB-INF\lib\fr-report-8.0.jar Qualified
 * Name: com.fr.stable.FormActor JD-Core Version: 0.6.0
 */