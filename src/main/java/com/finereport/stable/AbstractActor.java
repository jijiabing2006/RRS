package com.finereport.stable;

import com.fr.base.Formula;
import com.fr.base.chart.BaseChartPainter;
import com.fr.base.present.Present;
import com.fr.general.FUNC;
import com.fr.general.VT4FR;
import com.fr.json.JSONArray;
import com.fr.json.JSONException;
import com.fr.json.JSONObject;
import com.fr.log.LogUtils;
import com.fr.main.TemplateWorkBook;
import com.fr.main.impl.WorkBook;
import com.fr.main.workbook.ResultWorkBook;
import com.fr.page.PageSetChainProvider;
import com.fr.report.cell.CellElement;
import com.fr.report.core.A.A;
import com.fr.report.core.A.H;
import com.fr.report.core.ReportUtils;
import com.fr.report.core.block.PolyWorkSheetExecutor;
import com.fr.report.core.sheet.SheetSequenceExecutor;
import com.fr.report.poly.PageChartBlock;
import com.fr.report.poly.PageECBlock;
import com.fr.report.poly.PolyWorkSheet;
import com.fr.report.poly.ResultChartBlock;
import com.fr.report.poly.ResultECBlock;
import com.fr.report.stable.fun.Actor;
import com.fr.report.web.ToolBarManager;
import com.fr.report.worksheet.AbstractResECWorkSheet;
import com.fr.script.Calculator;
import com.fr.stable.script.ExTool;
import com.fr.stable.web.Repository;
import com.fr.web.RTypeService;
import com.fr.web.RepositoryHelper;
import com.fr.web.cache.ReportCache;
import com.fr.web.cache.ReportEntry;
import com.fr.web.core.ReportSession;
import com.fr.web.core.ReportSessionIDInfor;
import com.fr.web.core.ReportWebUtils;
import com.fr.web.utils.WebUtils;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractActor
  implements Actor
{
  private static final long DEFAULT_CACHE_TIME = 0L;

  public boolean canCalculateOnDemand()
  {
    return false;
  }

  public boolean willPreCalculate()
  {
    return false;
  }
 
  public ResultECBlock createResultECBlock()
  {
    return new PageECBlock();
  }

  public void cacheCellElement(AbstractResECWorkSheet paramAbstractResECWorkSheet, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    paramAbstractResECWorkSheet.cacheCellElement(paramInt1, paramInt2, paramBoolean);
  }

  public void present(CellElement paramCellElement, Present paramPresent, Object paramObject)
  {
    paramCellElement.setValue(paramObject);
  }

  public boolean considerBuildRelation()
  {
    return false;
  }

  public void release(H paramH)
  {
  }

  public void buildRelation(ExTool paramExTool, Calculator paramCalculator, Formula paramFormula1, Formula paramFormula2, A paramA)
  {
  }

  public boolean isDynamicJavaScript()
  {
    return true;
  }

  public boolean canBeUseForSchedule()
  {
    return false;
  }

  public boolean hasWidget()
  {
    return false;
  }

  public FUNC getBookFUNC()
  {
    return VT4FR.WORK_BOOK;
  }

  public ResultWorkBook executeWorkBook(WorkBook paramWorkBook, Map paramMap)
  {
    return new SheetSequenceExecutor(paramWorkBook, paramMap).execute(this);
  }

  public ResultWorkBook executeWorkBook(WorkBook paramWorkBook, Map paramMap, int paramInt)
  {
    return executeWorkBook(paramWorkBook, paramMap);
  }

  public PolyWorkSheetExecutor createPolySequenceExecutor(PolyWorkSheet paramPolyWorkSheet, Map paramMap, Actor paramActor)
  {
    return null;
  }

  public long updateCacheTime(TemplateWorkBook paramTemplateWorkBook)
  {
    return 0L;
  }

  public ReportCache createReportCache(TemplateWorkBook paramTemplateWorkBook, ReportEntry paramReportEntry, Map paramMap, boolean paramBoolean)
  {
    return null;
  }

  public ResultWorkBook getResultBookFromCacheIfNeed(TemplateWorkBook paramTemplateWorkBook, String paramString, ReportCache paramReportCache, Map paramMap, int paramInt)
  {
    return LogUtils.executeAndLogRecord(paramTemplateWorkBook, paramString, paramMap, this, paramInt);
  }

  public boolean shouldNotBeScale()
  {
    return false;
  }

  public boolean isPageByPage(HttpServletRequest paramHttpServletRequest)
  {
    return true;
  }

  public PageSetChainProvider getPageSet(ResultWorkBook paramResultWorkBook)
  {
    return paramResultWorkBook == null ? null : paramResultWorkBook.generateReportPageSet(ReportUtils.getPaperSettingListFromWorkBook(paramResultWorkBook));
  }

  protected ToolBarManager[] getDefineToolBars(Repository paramRepository)
  {
    return ReportUtils.getToolBarFromWorkBook(((ReportSessionIDInfor)RepositoryHelper.getSessionIDInfor(paramRepository)).getContextBook(), 0);
  }

  public JSONObject panelConfig(Repository paramRepository)
    throws JSONException
  {
    return new JSONObject();
  }

  public int getReportCountInRepo(Repository paramRepository)
  {
    return ((ReportSession)RepositoryHelper.getSessionIDInfor(paramRepository)).getReportCount();
  }

  public int calculateCurrentSheetIndex(Repository paramRepository)
  {
    return -1;
  }

  public ResultChartBlock getChartBlock4Ploy(BaseChartPainter paramBaseChartPainter)
  {
    return new PageChartBlock(paramBaseChartPainter);
  }

  public boolean supportPolyExecute()
  {
    return false;
  }

  public Map<String, Object> createContext4Tpl(HttpServletRequest paramHttpServletRequest, ReportSessionIDInfor paramReportSessionIDInfor)
  {
    return ReportWebUtils.context4PageTpl(paramHttpServletRequest, paramReportSessionIDInfor);
  }

  public void flushHtml(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Map<String, Object> paramMap, ReportSessionIDInfor paramReportSessionIDInfor)
    throws IOException
  {
    paramMap.put("DOCTYPE", "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
    WebUtils.writeOutTemplate("/com/fr/web/core/page.html", paramHttpServletResponse, paramMap);
  }

  public JSONObject createReportWebAttr4Mobile(HttpServletRequest paramHttpServletRequest, ReportSessionIDInfor paramReportSessionIDInfor)
    throws JSONException
  {
    return null;
  }

  public JSONArray processMultipleSheet(Repository paramRepository)
  {
    JSONArray localJSONArray = new JSONArray();
    int i = getReportCountInRepo(paramRepository);
    for (int j = 0; j < i; j++)
    {
      ReportSession localReportSession = (ReportSession)RepositoryHelper.getSessionIDInfor(paramRepository);
      String str = localReportSession.getReportName(j);
      RTypeService.executeSheetName(paramRepository, str, localJSONArray, j);
    }
    return localJSONArray;
  }
}

/* Location:           C:\FineReport_8.0\WebReport\WEB-INF\lib\fr-report-8.0.jar
 * Qualified Name:     com.fr.stable.AbstractActor
 * JD-Core Version:    0.6.0
 */