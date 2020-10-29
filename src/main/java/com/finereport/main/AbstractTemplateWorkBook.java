package com.finereport.main;

import com.finereport.main.workbook.ResultWorkBook;
import com.finereport.report.report.Report;
import com.finereport.report.report.TemplateReport;
import com.finereport.stable.fun.Actor;
import com.fr.base.parameter.ParameterUI;
import com.fr.general.ComparatorUtils;
import com.fr.main.parameter.ReportParameterAttr;
import com.fr.report.stable.ReportSettings;
import com.fr.report.worksheet.WorkSheet;
import com.fr.stable.xml.XMLPrintWriter;
import com.fr.stable.xml.XMLReadable;
import com.fr.stable.xml.XMLableReader;
import com.fr.web.attr.ReportWebAttr;

import java.util.Map;

public abstract class AbstractTemplateWorkBook
  extends AbstractFineBook
  implements TemplateWorkBook
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
protected ReportParameterAttr reportParameterAttr = null;
  
  public void addReport(TemplateReport paramTemplateReport)
  {
    super.addReport(null, paramTemplateReport);
  }
  
  public void addReport(String paramString, TemplateReport paramTemplateReport)
  {
    super.addReport(getReportCount(), paramString, paramTemplateReport);
  }
  
  public void setReport(int paramInt, TemplateReport paramTemplateReport)
  {
    super.setReport(paramInt, paramTemplateReport);
  }
  
  public void setReport(int paramInt, String paramString, TemplateReport paramTemplateReport)
  {
    super.setReport(paramInt, paramString, paramTemplateReport);
  }
  
  public TemplateReport getTemplateReport(int paramInt)
  {
    return (TemplateReport)super.getReport(paramInt);
  }
  
  public WorkSheet getTemplateElementCaseReport(int paramInt)
  {
    return (WorkSheet)super.getElementCaseReport(paramInt);
  }
  
  public int getReportIndex(TemplateReport paramTemplateReport)
  {
    return super.getReportIndex(paramTemplateReport);
  }
  
  protected void setWorkBook(Report paramReport, FineBook paramFineBook)
  {
    ((TemplateReport)paramReport).setTemplateWorkBook((TemplateWorkBook)paramFineBook);
  }
  
  public ReportParameterAttr getReportParameterAttr()
  {
    if (this.reportParameterAttr != null)
    {
      ParameterUI localParameterUI = this.reportParameterAttr.getParameterUI();
      if (localParameterUI != null) {
        localParameterUI.setTableDataSource(this);
      }
    }
    return this.reportParameterAttr;
  }
  
  public void setReportParameterAttr(ReportParameterAttr paramReportParameterAttr)
  {
    this.reportParameterAttr = paramReportParameterAttr;
  }
  
  public void setReportWebAttr(ReportWebAttr paramReportWebAttr)
  {
    this.reportWebAttr = paramReportWebAttr;
  }
  
  public ResultWorkBook execute(Map paramMap, Actor paramActor, int paramInt)
  {
    return execute(paramMap, paramActor);
  }
  
  public Object clone()
    throws CloneNotSupportedException
  {
    AbstractTemplateWorkBook localAbstractTemplateWorkBook = (AbstractTemplateWorkBook)super.clone();
    if (getReportParameterAttr() != null) {
      localAbstractTemplateWorkBook.setReportParameterAttr((ReportParameterAttr)getReportParameterAttr().clone());
    }
    if (getReportWebAttr() != null)
    {
      ReportWebAttr localReportWebAttr = (ReportWebAttr)getReportWebAttr().clone();
      localAbstractTemplateWorkBook.setReportWebAttr(localReportWebAttr);
    }
    return localAbstractTemplateWorkBook;
  }
  
  public void writeXML(XMLPrintWriter paramXMLPrintWriter)
  {
    super.writeXML(paramXMLPrintWriter);
    ReportParameterAttr localReportParameterAttr = getReportParameterAttr();
    if (localReportParameterAttr != null)
    {
      paramXMLPrintWriter.startTAG("ReportParameterAttr");
      localReportParameterAttr.writeXML(paramXMLPrintWriter);
      paramXMLPrintWriter.end();
    }
  }
  
  public void readXML(XMLableReader paramXMLableReader)
  {
    super.readXML(paramXMLableReader);
    readExtra(paramXMLableReader);
    if (paramXMLableReader.isChildNode())
    {
      String str = paramXMLableReader.getTagName();
      Object localObject;
      if ("ReportParameterAttr".equals(str))
      {
        localObject = getReportParameterAttr();
        if (localObject == null)
        {
          localObject = new ReportParameterAttr();
          setReportParameterAttr((ReportParameterAttr)localObject);
        }
        paramXMLableReader.readXMLObject((XMLReadable)localObject);
      }
      else if (("ReportSettings".equals(str)) || ("ReportSetting".equals(str)))
      {
        localObject = new ReportSettings();
        paramXMLableReader.readXMLObject((XMLReadable)localObject);
        SynchronizedReportSettings.putSynchronizedReportSettings(this, (ReportSettings)localObject);
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return (super.equals(paramObject)) && ((paramObject instanceof AbstractTemplateWorkBook)) && (ComparatorUtils.equals(this.reportParameterAttr, ((AbstractTemplateWorkBook)paramObject).reportParameterAttr));
  }
}
