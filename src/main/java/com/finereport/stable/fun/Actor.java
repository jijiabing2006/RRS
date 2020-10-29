package com.finereport.stable.fun;

import com.finereport.main.TemplateWorkBook;
import com.finereport.main.impl.WorkBook;
import com.finereport.main.workbook.ResultWorkBook;
import com.fr.base.Formula;
import com.fr.base.chart.BaseChartPainter;
import com.fr.base.present.Present;
import com.fr.general.DeclareRecordType;
import com.fr.general.FUNC;
import com.fr.json.JSONArray;
import com.fr.json.JSONException;
import com.fr.json.JSONObject;
import com.fr.page.PageSetChainProvider;
import com.fr.report.cell.CellElement;
import com.fr.report.cell.cellattr.core.attribute.CellElementAttribute;
import com.fr.report.cell.cellattr.core.attribute.OptionalAttribute;
import com.fr.report.core.A.A;
import com.fr.report.core.A.H;
import com.fr.report.core.A.q;
import com.fr.report.core.block.PolyWorkSheetExecutor;
import com.fr.report.core.sheet.WorkBookExecutor;
import com.fr.report.poly.PolyWorkSheet;
import com.fr.report.poly.ResultChartBlock;
import com.fr.report.poly.ResultECBlock;
import com.fr.report.web.ToolBarManager;
import com.fr.report.worksheet.AbstractResECWorkSheet;
import com.fr.script.Calculator;
import com.fr.stable.script.ExTool;
import com.fr.stable.web.Repository;
import com.fr.web.cache.ReportCache;
import com.fr.web.cache.ReportEntry;
import com.fr.web.core.ReportSessionIDInfor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface Actor
{
  public static final String XML_TAG = "WebActor";

  public abstract String description();

  public abstract boolean canCalculateOnDemand();

  public abstract boolean willPreCalculate();

  public abstract q createBoxFactory();

  public abstract ResultECBlock createResultECBlock();

  public abstract AbstractResECWorkSheet createResultECWorkSheet(H paramH);

  public abstract void cacheCellElement(AbstractResECWorkSheet paramAbstractResECWorkSheet, int paramInt1, int paramInt2, boolean paramBoolean);

  public abstract boolean considerBuildRelation();

  public abstract void release(H paramH);

  public abstract OptionalAttribute cloneOptionalAttribute(OptionalAttribute paramOptionalAttribute);

  public abstract CellElementAttribute createHyperCellAttr();

  public abstract CellElementAttribute createCellGUIAttr();

  public abstract CellElementAttribute createWidgetAttr();

  public abstract void buildRelation(ExTool paramExTool, Calculator paramCalculator, Formula paramFormula1, Formula paramFormula2, A paramA);

  public abstract boolean isDynamicJavaScript();

  public abstract boolean canBeUseForSchedule();

  public abstract void present(CellElement paramCellElement, Present paramPresent, Object paramObject);

  public abstract boolean hasWidget();

  public abstract PolyWorkSheetExecutor createPolySequenceExecutor(PolyWorkSheet paramPolyWorkSheet, Map paramMap, Actor paramActor);

  public abstract FUNC getBookFUNC();

  public abstract ResultWorkBook createResultBook(Map paramMap);

  public abstract ResultWorkBook executeWorkBook(WorkBook paramWorkBook, Map paramMap);

  public abstract ResultWorkBook executeWorkBook(WorkBook paramWorkBook, Map paramMap, int paramInt);

  public abstract WorkBookExecutor createWorkBookExecutor(WorkBook paramWorkBook, Map paramMap);

  public abstract DeclareRecordType getRecordType();

  public abstract boolean shouldRecord();

  public abstract long updateCacheTime(TemplateWorkBook paramTemplateWorkBook);

  public abstract ReportCache createReportCache(TemplateWorkBook paramTemplateWorkBook, ReportEntry paramReportEntry, Map paramMap, boolean paramBoolean);

  public abstract ResultWorkBook getResultBookFromCacheIfNeed(TemplateWorkBook paramTemplateWorkBook, String paramString, ReportCache paramReportCache, Map paramMap, int paramInt);

  public abstract boolean shouldNotBeScale();

  public abstract boolean isPageByPage(HttpServletRequest paramHttpServletRequest);

  public abstract PageSetChainProvider getPageSet(ResultWorkBook paramResultWorkBook);

  public abstract ToolBarManager[] toolbarManagers(Repository paramRepository);

  public abstract JSONObject panelConfig(Repository paramRepository)
    throws JSONException;

  public abstract String panelType();

  public abstract String mainJavaScriptPath();

  public abstract int getReportCountInRepo(Repository paramRepository);

  public abstract int calculateCurrentSheetIndex(Repository paramRepository);

  public abstract ResultChartBlock getChartBlock4Ploy(BaseChartPainter paramBaseChartPainter);

  public abstract boolean supportPolyExecute();

  public abstract Map<String, Object> createContext4Tpl(HttpServletRequest paramHttpServletRequest, ReportSessionIDInfor paramReportSessionIDInfor);

  public abstract void flushHtml(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Map<String, Object> paramMap, ReportSessionIDInfor paramReportSessionIDInfor)
    throws IOException;

  public abstract JSONObject createReportWebAttr4Mobile(HttpServletRequest paramHttpServletRequest, ReportSessionIDInfor paramReportSessionIDInfor)
    throws JSONException;

  public abstract JSONArray processMultipleSheet(Repository paramRepository);
}

/* Location:           C:\FineReport_8.0\WebReport\WEB-INF\lib\fr-report-8.0.jar
 * Qualified Name:     com.fr.report.stable.fun.Actor
 * JD-Core Version:    0.6.0
 */