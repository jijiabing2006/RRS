package com.finereport.main.impl;

import com.finereport.main.AbstractTemplateWorkBook;
import com.finereport.main.workbook.ResultWorkBook;
import com.finereport.report.report.Report;
import com.finereport.report.report.TemplateReport;
import com.finereport.stable.fun.Actor;
import com.fr.base.ConfigManager;
import com.fr.base.Env;
import com.fr.base.FRContext;
import com.fr.base.Formula;
import com.fr.base.Parameter;
import com.fr.base.ParameterHelper;
import com.fr.base.ParameterMapNameSpace;
import com.fr.base.TableData;
import com.fr.base.Utils;
import com.fr.base.chart.BaseChart;
import com.fr.base.chart.BaseChartCollection;
import com.fr.base.chart.chartdata.BaseTableDefinition;
import com.fr.base.io.XMLReadHelper;
import com.fr.base.parameter.ParameterUI;
import com.fr.base.present.DictPresent;
import com.fr.base.present.Present;
import com.fr.data.SimpleDSColumn;
import com.fr.data.TableDataSource;
import com.fr.data.condition.CommonCondition;
import com.fr.data.condition.JoinCondition;
import com.fr.data.condition.ListCondition;
import com.fr.data.core.Compare;
import com.fr.data.impl.NameTableData;
import com.fr.data.impl.TableDataDictionary;
import com.fr.form.ui.ComboBox;
import com.fr.form.ui.ToolBar;
import com.fr.form.ui.Widget;
import com.fr.general.ComparatorUtils;
import com.fr.general.FRLogger;
import com.fr.general.FUNC;
import com.fr.general.GeneralUtils;
import com.fr.general.Inter;
import com.fr.general.data.Condition;
import com.fr.main.FineBook;
import com.fr.main.SynchronizedReportSettings;
import com.fr.main.parameter.ReportParameterAttr;
import com.fr.page.ReportSettingsProvider;
import com.fr.privilege.PrivilegeEditedRoleProvider;
import com.fr.privilege.finegrain.CellPrivilegeControl;
import com.fr.privilege.finegrain.ColumnRowPrivilegeControl;
import com.fr.privilege.finegrain.FloatPrivilegeControl;
import com.fr.privilege.finegrain.WidgetPrivilegeControl;
import com.fr.privilege.finegrain.WorkSheetPrivilegeControl;
import com.fr.report.cell.AbstractWidgetCellElement;
import com.fr.report.cell.CellElement;
import com.fr.report.cell.FloatElement;
import com.fr.report.cell.TemplateCellElement;
import com.fr.report.cell.cellattr.core.group.DSColumn;
import com.fr.report.core.ReportUtils;
import com.fr.report.core.sheet.WorkBookExecutor;
import com.fr.report.core.utils.ScriptUtils;
import com.fr.report.elementcase.ElementCase;
import com.fr.report.stable.ReportSettings;
import com.fr.report.web.ToolBarManager;
import com.fr.report.worksheet.AnalysisRWorkSheet;
import com.fr.report.worksheet.PageRWorkSheet;
import com.fr.report.worksheet.WorkSheet;
import com.fr.script.Calculator;
import com.fr.stable.ColumnRow;
import com.fr.stable.ListMap;
import com.fr.stable.ParameterProvider;
import com.fr.stable.script.NameSpace;
import com.fr.stable.xml.XMLPrintWriter;
import com.fr.stable.xml.XMLReadable;
import com.fr.stable.xml.XMLVersion;
import com.fr.stable.xml.XMLableReader;
import com.fr.xml.ReportXMLUtils;
import com.fr.xml.SynchronizedFrozenColumnRow;

import java.awt.Dimension;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkBook extends AbstractTemplateWorkBook implements
		PrivilegeEditedRoleProvider {
	public static final String XML_TAG = "WorkBook";
	public static final String STRING_TO_FORMULA = "string2Formula";

	public WorkBook() {
	}

	public WorkBook(TemplateReport paramTemplateReport) {
		addReport(paramTemplateReport);
	}

	public void addReport(int paramInt, TemplateReport paramTemplateReport) {
		super.addReport(paramInt, null, paramTemplateReport);
	}

	public void addReport(int paramInt, String paramString,
			TemplateReport paramTemplateReport) {
		super.addReport(paramInt, paramString, paramTemplateReport);
	}

	public ResultWorkBook execute(Map paramMap, Actor paramActor) {
		return execute(paramMap, paramActor, -1);
	}

	public ResultWorkBook execute(Map paramMap, Actor paramActor, int paramInt) {
		apply4Parameters(paramMap);
		if ((!paramActor.getBookFUNC().support()) && (getReportCount() > 1))
			return paramActor.createResultBook(paramMap);
		if ((getReportCount() > 1)
				&& (!ReportUtils.hasLayerReport4Template((FineBook) this)))
			return paramActor.executeWorkBook(this, paramMap, paramInt);
		return (ResultWorkBook) paramActor.createWorkBookExecutor(this,
				paramMap).execute();
	}

	public Parameter[] getParameters() {
		HashSet localHashSet = new HashSet();
		Calculator localCalculator = Calculator.createCalculator();
		localCalculator.setAttribute(TableDataSource.class, this);
		ParameterHelper.addGlobal_ParameterToSet(localHashSet);
		ReportParameterAttr localReportParameterAttr = getReportParameterAttr();
		if ((localReportParameterAttr != null)
				&& (localReportParameterAttr.getParameters() != null))
			localHashSet.addAll(Arrays.asList(localReportParameterAttr
					.getParameters()));
		try {
			int i = 0;
			int j = this.tableDataList.size();
			while (i < j) {
				TableData localTableData = (TableData) this.tableDataList
						.get(i);
				ParameterProvider[] arrayOfParameterProvider = FRContext
						.getCurrentEnv().getTableDataParameters(localTableData);
				if ((localTableData != null)
						&& (arrayOfParameterProvider != null))
					localHashSet
							.addAll(Arrays.asList(arrayOfParameterProvider));
				i++;
			}
		} catch (Exception localException) {
			FRLogger.getLogger().error(localException.getMessage());
		}
		return (Parameter[]) (Parameter[]) localHashSet
				.toArray(new Parameter[localHashSet.size()]);
	}

	public void apply4Parameters(Map paramMap) {
		ReportParameterAttr localReportParameterAttr = getReportParameterAttr();
		Calculator localCalculator = Calculator.createCalculator();
		Parameter[] localObject3;
		Iterator localObject4;
		if (localReportParameterAttr != null) {
			Object localObject1 = new ListMap();
			Object localObject2 = new ListMap();
			localObject3 = localReportParameterAttr.getParameters();
			for (int i = 0; i < localObject3.length; i++)
				if ((paramMap.get(localObject3[i].getName()) == null)
						|| (shouldExecuteParameter(localObject3[i])))
					((ListMap) localObject1).put(localObject3[i].getName(),
							localObject3[i].getValue());
				else
					((ListMap) localObject2).put(localObject3[i].getName(),
							paramMap.get(localObject3[i].getName()));
			ParameterMapNameSpace localParameterMapNameSpace1 = ParameterMapNameSpace
					.create((Map) localObject2);
			localCalculator.pushNameSpace(localParameterMapNameSpace1);
			localObject4 = ((ListMap) localObject1).keySet().iterator();
			while (((Iterator) localObject4).hasNext()) {
				ParameterMapNameSpace localParameterMapNameSpace2 = ParameterMapNameSpace
						.create(paramMap);
				localCalculator.pushNameSpace(localParameterMapNameSpace2);
				String str = (String) ((Iterator) localObject4).next();
				Object localObject5 = ((ListMap) localObject1).get(str);
				Object localObject6 = (localObject5 instanceof Formula) ? ScriptUtils
						.executeNormalFormula(localCalculator,
								(Formula) localObject5) : localObject5;
				paramMap.put(str, localObject6);
			}
			localCalculator.removeNameSpace(localParameterMapNameSpace1);
		}
		Object localObject1 = ConfigManager.getInstance();
		Parameter[] localObject2 = ((ConfigManager) localObject1)
				.getGlobal_Parameters();
		Object localObject5;
		Object localObject6;
		if (localObject2 != null) {
			localObject5 = ParameterMapNameSpace.create(paramMap);
			localCalculator.pushNameSpace((NameSpace) localObject5);
			for (int j = 0; j < localObject2.length; j++) {
				if (paramMap.get(localObject2[j].getName()) != null)
					continue;
				localObject6 = localObject2[j].getValue();
				if ((localObject6 instanceof Formula))
					localObject6 = ScriptUtils.executeNormalFormula(
							localCalculator, (Formula) localObject6);
				paramMap.put(localObject2[j].getName(), localObject6);
			}
			localCalculator.removeNameSpace((NameSpace) localObject5);
		}
	}

	private boolean shouldExecuteParameter(Parameter paramParameter) {
		Object localObject = paramParameter.getValue();
		if (!(localObject instanceof Formula))
			return false;
		String str = Utils.objectToString(localObject);
		return (localObject != null) && (str.startsWith("="))
				&& (str.indexOf('$') != -1);
	}

	public String[] getAllEditedRoleSet() {
		HashSet localHashSet = new HashSet();
		dealWithToolBar(ReportUtils.getToolBarFromWorkBook((FineBook) this, 0),
				localHashSet);
		dealWithToolBar(ReportUtils.getToolBarFromWorkBook((FineBook) this, 1),
				localHashSet);
		dealWithToolBar(ReportUtils.getToolBarFromWorkBook((FineBook) this, 3),
				localHashSet);
		iteratorParameterUI(localHashSet);
		iteratorReport(localHashSet);
		return (String[]) new ArrayList(localHashSet)
				.toArray(new String[localHashSet.size()]);
	}

	private void iteratorParameterUI(HashSet<String> paramHashSet) {
		ReportParameterAttr localReportParameterAttr = getReportParameterAttr();
		if (localReportParameterAttr != null) {
			ParameterUI localParameterUI = localReportParameterAttr
					.getParameterUI();
			if (localParameterUI != null) {
				Widget[] arrayOfWidget = localParameterUI.getAllWidgets();
				if (arrayOfWidget != null)
					for (int i = 0; i < arrayOfWidget.length; i++)
						dealWithWidget(arrayOfWidget[i], paramHashSet);
			}
		}
	}

	private void iteratorReport(HashSet<String> paramHashSet) {
		for (int i = 0; i < getReportCount(); i++) {
			Report localReport = getReport(i);
			if (localReport == null)
				continue;
			if ((localReport instanceof TemplateReport)) {
				Object localObject = ((TemplateReport) localReport)
						.getWorkSheetPrivilegeControl();
				paramHashSet.addAll(Arrays
						.asList(((WorkSheetPrivilegeControl) localObject)
								.getAllEditedRoles()));
			}
			Object localObject = localReport.iteratorOfElementCase();
			while (((Iterator) localObject).hasNext()) {
				ElementCase localElementCase = (ElementCase) ((Iterator) localObject)
						.next();
				iteratorElementCase(paramHashSet, localElementCase);
				Iterator localIterator = localElementCase.cellIterator();
				while (localIterator.hasNext()) {
					CellElement localCellElement = (CellElement) localIterator
							.next();
					CellPrivilegeControl localCellPrivilegeControl = localCellElement
							.getCellPrivilegeControl();
					paramHashSet.addAll(Arrays.asList(localCellPrivilegeControl
							.getAllEditedRoles()));
					if ((localCellElement instanceof AbstractWidgetCellElement)) {
						Widget localWidget = ((AbstractWidgetCellElement) localCellElement)
								.getWidget();
						dealWithWidget(localWidget, paramHashSet);
					}
				}
			}
		}
	}

	private void iteratorElementCase(HashSet<String> paramHashSet,
			ElementCase paramElementCase) {
		int i = paramElementCase.getRowCount();
		int j = paramElementCase.getColumnCount();
		ColumnRowPrivilegeControl localColumnRowPrivilegeControl;
		for (int k = 0; k < i; k++) {
			localColumnRowPrivilegeControl = paramElementCase
					.getRowPrivilegeControl(k);
			paramHashSet.addAll(Arrays.asList(localColumnRowPrivilegeControl
					.getAllEditedRoles()));
		}
		for (int l = 0; l < j; l++) {
			localColumnRowPrivilegeControl = paramElementCase
					.getColumnPrivilegeControl(l);
			paramHashSet.addAll(Arrays.asList(localColumnRowPrivilegeControl
					.getAllEditedRoles()));
		}
		Iterator localIterator = paramElementCase.floatIterator();
		while (localIterator.hasNext()) {
			FloatElement localFloatElement = (FloatElement) localIterator
					.next();
			FloatPrivilegeControl localFloatPrivilegeControl = localFloatElement
					.getFloatPrivilegeControl();
			paramHashSet.addAll(Arrays.asList(localFloatPrivilegeControl
					.getAllEditedRoles()));
		}
	}

	private void dealWithToolBar(ToolBarManager[] paramArrayOfToolBarManager,
			HashSet<String> paramHashSet) {
		if (paramArrayOfToolBarManager == null)
			return;
		for (int i = 0; i < paramArrayOfToolBarManager.length; i++) {
			ToolBar localToolBar = paramArrayOfToolBarManager[i].getToolBar();
			for (int j = 0; j < localToolBar.getWidgetSize(); j++) {
				Widget localWidget = localToolBar.getWidget(j);
				dealWithWidget(localWidget, paramHashSet);
			}
		}
	}

	private void dealWithWidget(Widget paramWidget, HashSet<String> paramHashSet) {
		if (paramWidget == null)
			return;
		WidgetPrivilegeControl localWidgetPrivilegeControl = paramWidget
				.getWidgetPrivilegeControl();
		paramHashSet.addAll(Arrays.asList(localWidgetPrivilegeControl
				.getAllEditedRoles()));
	}

	public boolean renameTableData(String paramString1, String paramString2) {
		if (super.renameTableData(paramString1, paramString2)) {
			if (getTableData(paramString1) == null)
				for (int i = 0; i < getReportCount(); i++) {
					Report localReport = getReport(i);
					if (localReport == null)
						continue;
					Iterator localIterator = localReport
							.iteratorOfElementCase();
					while (localIterator.hasNext()) {
						ElementCase localElementCase = (ElementCase) localIterator
								.next();
						iteratorFloatCell(localElementCase, paramString2,
								paramString1);
						iteratorNormalCell(localElementCase, paramString2,
								paramString1);
					}
				}
			return true;
		}
		return false;
	}

	private void iteratorFloatCell(ElementCase paramElementCase,
			String paramString1, String paramString2) {
		Iterator localIterator = paramElementCase.floatIterator();
		while (localIterator.hasNext()) {
			FloatElement localFloatElement = (FloatElement) localIterator
					.next();
			Object localObject = localFloatElement.getValue();
			if ((localObject instanceof BaseChartCollection))
				setChartTableData(localObject, paramString1, paramString2);
		}
	}

	private void iteratorNormalCell(ElementCase paramElementCase,
			String paramString1, String paramString2) {
		Iterator localIterator = paramElementCase.cellIterator();
		while (localIterator.hasNext()) {
			CellElement localCellElement = (CellElement) localIterator.next();
			setPresentAndDictionary(localCellElement, paramString1,
					paramString2);
			Object localObject = localCellElement.getValue();
			if ((localObject instanceof DSColumn)) {
				DSColumn localDSColumn = (DSColumn) localObject;
				if (ComparatorUtils.equals(localDSColumn.getDSName(),
						paramString2))
					localDSColumn.setDSName(paramString1);
				Condition localCondition1 = ((DSColumn) localObject)
						.getCondition();
				if (localCondition1 != null) {
					if ((localCondition1 instanceof CommonCondition))
						dealWithTableDataNameChange(
								(CommonCondition) localCondition1,
								paramString2, paramString1);
					if ((localCondition1 instanceof ListCondition))
						for (int i = 0; i < ((ListCondition) localCondition1)
								.getJoinConditionCount(); i++) {
							JoinCondition localJoinCondition = ((ListCondition) localCondition1)
									.getJoinCondition(i);
							Condition localCondition2 = localJoinCondition
									.getCondition();
							if ((localCondition2 == null)
									|| (!(localCondition2 instanceof CommonCondition)))
								continue;
							dealWithTableDataNameChange(
									(CommonCondition) localCondition2,
									paramString2, paramString1);
						}
				}
			} else if ((localObject instanceof BaseChartCollection)) {
				setChartTableData(localObject, paramString1, paramString2);
			}
		}
	}

	private void dealWithTableDataNameChange(
			CommonCondition paramCommonCondition, String paramString1,
			String paramString2) {
		Compare localCompare = paramCommonCondition.getCompare();
		Object localObject = localCompare.getValue();
		if (((localObject instanceof SimpleDSColumn))
				&& (ComparatorUtils.equals(
						((SimpleDSColumn) localObject).getDsName(),
						paramString1)))
			((SimpleDSColumn) localObject).setDsName(paramString2);
	}

	private void setChartTableData(Object paramObject, String paramString1,
			String paramString2) {
		BaseChartCollection localBaseChartCollection = (BaseChartCollection) paramObject;
		int i = 0;
		int j = localBaseChartCollection.getChartCount();
		while (i < j) {
			BaseChart localBaseChart = localBaseChartCollection
					.getChartWithIndex(i);
			if ((localBaseChart.getFilterDefinition() instanceof BaseTableDefinition)) {
				BaseTableDefinition localBaseTableDefinition = (BaseTableDefinition) localBaseChart
						.getFilterDefinition();
				NameTableData localNameTableData = (NameTableData) localBaseTableDefinition
						.getTableData();
				if (ComparatorUtils.equals(localNameTableData.getName(),
						paramString2))
					localBaseTableDefinition.setTableData(new NameTableData(
							paramString1));
			}
			i++;
		}
	}

	private void setPresentAndDictionary(CellElement paramCellElement,
			String paramString1, String paramString2) {
		TemplateCellElement localTemplateCellElement = (TemplateCellElement) paramCellElement;
		Present localPresent = localTemplateCellElement.getPresent();
		if (!(localPresent instanceof DictPresent))
			return;
		DictPresent localDictPresent = (DictPresent) localPresent;
		if (!(localDictPresent.getDictionary() instanceof TableDataDictionary))
			return;
		TableDataDictionary localTableDataDictionary1 = (TableDataDictionary) localDictPresent
				.getDictionary();
		if (!(localTableDataDictionary1.getTableData() instanceof NameTableData))
			return;
		NameTableData localNameTableData1 = (NameTableData) localTableDataDictionary1
				.getTableData();
		if (ComparatorUtils.equals(localNameTableData1.getName(), paramString2))
			localTableDataDictionary1.setTableData(new NameTableData(
					paramString1));
		if ((localTemplateCellElement.getWidget() instanceof ComboBox)) {
			ComboBox localComboBox = (ComboBox) (ComboBox) localTemplateCellElement
					.getWidget();
			if ((localComboBox != null)
					&& ((localComboBox.getDictionary() instanceof TableDataDictionary))) {
				TableDataDictionary localTableDataDictionary2 = (TableDataDictionary) localComboBox
						.getDictionary();
				NameTableData localNameTableData2 = (NameTableData) localTableDataDictionary2
						.getTableData();
				if (ComparatorUtils.equals(localNameTableData1.getName(),
						paramString2))
					localTableDataDictionary1.setTableData(new NameTableData(
							paramString1));
			}
		}
	}

	public String openTag() {
		return "WorkBook";
	}

	public void mainContentWriteXML(XMLPrintWriter paramXMLPrintWriter) {
		writeXML(paramXMLPrintWriter);
	}

	public void readStream(InputStream paramInputStream) throws Exception {
		readStream(paramInputStream, true);
	}

	public void readStream(InputStream paramInputStream, boolean paramBoolean)
			throws Exception {
		XMLableReader localXMLableReader = XMLReadHelper.createXMLableReader(
				paramInputStream, "UTF-8");
		this.xmlVersion = localXMLableReader.getXMLVersion();
		if (localXMLableReader == null)
			throw new RuntimeException(
					Inter.getLocText("FR-Engine-NS_exception_readError"));
		if (paramBoolean)
			localXMLableReader.setContextAttribute("string2Formula",
					Boolean.valueOf(paramBoolean));
		String str1 = localXMLableReader.getTagName();
		if ("WorkBook".equals(str1)) {
			localXMLableReader.readXMLObject(this);
			compatibleParameterUI(localXMLableReader);
		} else if ("Report".equals(str1)) {
			Object localObject = null;
			String str2;
			if ((str2 = localXMLableReader.getAttrAsString("class", null)) != null) {
				Report localReport = readReportFromClassName(str2);
				if ((localReport instanceof TemplateReport))
					localObject = (TemplateReport) localReport;
				else
					throw new Exception("Error Report Class ("
							+ localReport.getClass().getName()
							+ ") in Template File.");
			}
			if (localObject == null)
				localObject = new WorkSheet();
			addReport("Compatible0651WorkSheet", (TemplateReport) localObject);
			localXMLableReader.readXMLObject((XMLReadable) localObject);
			setReportName(0, "sheet1");
		} else if ("FREncrypted".equals(str1)) {
			FRContext.getLogger().error(
					Inter.getLocText("FR-Engine-ECP_decode_failed"));
		}
		compatibleWith6_5();
		SynchronizedFrozenColumnRow.getThreadColumnRowList().set(null);
		localXMLableReader.close();
		paramInputStream.close();
	}

	private void compatibleParameterUI(XMLableReader paramXMLableReader) {
		if ((this.reportParameterAttr != null)
				&& (this.reportParameterAttr.getParameterUI() != null)) {
			this.reportParameterAttr.getParameterUI().convert();
			if ((!paramXMLableReader.getXMLVersion()
					.isAfterREPORT_REFECT_FOR7_1_XML_VERSION())
					&& (paramXMLableReader.getXMLVersion()
							.isAfterREPORT_REFECT_FOR65_4_XML_VERSION()))
				this.reportParameterAttr.getParameterUI()
						.setParameterValueFor_7_0_Version(getParameters());
			if ((!paramXMLableReader.getXMLVersion()
					.isAfterREPORT_REFECT_FOR7_0_3_XML_VERSION())
					&& (paramXMLableReader.getXMLVersion()
							.isAfterREPORT_REFECT_FOR7_0_XML_VERSION())
					&& (this.reportParameterAttr.getParameterUI()
							.isUseDefaultSize()))
				this.reportParameterAttr.getParameterUI().setDesignSize(
						new Dimension(800, 200));
		}
	}

	private void compatibleWith6_5() {
		SynchronizedReportSettings.flushSynchronizedReportSettings();
		ColumnRow localColumnRow = (ColumnRow) SynchronizedFrozenColumnRow
				.getThreadColumnRowList().get();
		if (localColumnRow != null)
			for (int i = 0; i < getReportCount(); i++) {
				if (getReport(i) == null)
					continue;
				Object localObject = getReport(i).getReportSettings();
				if (localObject == null) {
					localObject = new ReportSettings();
					getReport(i).setReportSettings(
							(ReportSettingsProvider) localObject);
				}
				((ReportSettingsProvider) localObject)
						.setWriteFrozenColumnRow(localColumnRow);
			}
	}

	public boolean equals(Object paramObject) {
		return (super.equals(paramObject))
				&& ((paramObject instanceof WorkBook));
	}

	public static Report readReportFromClassName(String paramString) {
		Object localObject;
		if (paramString.endsWith(".WorkSheet"))
			localObject = new WorkSheet();
		else if (paramString.endsWith(".CommonERReport"))
			localObject = new PageRWorkSheet();
		else if (paramString.endsWith(".AnalysisRWorkSheet"))
			localObject = new AnalysisRWorkSheet();
		else if (paramString.endsWith(".PageRWorkSheet"))
			localObject = new PageRWorkSheet();
		else
			try {
				Class localClass = GeneralUtils.classForName(paramString);
				localObject = (Report) localClass.newInstance();
			} catch (Exception localException) {
				FRContext.getLogger().error(localException.getMessage(),
						localException);
				localObject = new WorkSheet();
			}
		if (localObject == null)
			localObject = new WorkSheet();
		return (Report) localObject;
	}
}

/*
 * Location: C:\FineReport_8.0\WebReport\WEB-INF\lib\fr-report-8.0.jar Qualified
 * Name: com.fr.main.impl.WorkBook JD-Core Version: 0.6.0
 */