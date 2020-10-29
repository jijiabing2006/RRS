package com.finereport.main;

import com.finereport.report.report.Report;
import com.fr.base.FRContext;
import com.fr.base.io.IOFile;
import com.fr.general.ComparatorUtils;
import com.fr.general.GeneralUtils;
import com.fr.io.attr.ReportExportAttr;
import com.fr.plugin.ExtraClassManager;
import com.fr.privilege.PrivilegeManager;
import com.fr.privilege.finegrain.WorkSheetPrivilegeControl;
import com.fr.report.core.PackedReport;
import com.fr.report.fun.ReportFitProcessor;
import com.fr.report.report.ECReport;
import com.fr.report.worksheet.AnalysisRWorkSheet;
import com.fr.report.worksheet.PageRWorkSheet;
import com.fr.report.worksheet.WorkSheet;
import com.fr.script.Calculator;
import com.fr.stable.ArrayUtils;
import com.fr.stable.fun.ReportFitAttrProvider;
import com.fr.stable.xml.XMLPrintWriter;
import com.fr.stable.xml.XMLReadable;
import com.fr.stable.xml.XMLVersion;
import com.fr.stable.xml.XMLableReader;
import com.fr.web.attr.ReportWebAttr;
import com.fr.xml.ReportXMLUtils;
import com.fr.xml.SynchronizedBoxRelation;
import com.fr.xml.SynchronizedStyleList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFineBook extends IOFile
  implements FineBook
{
  private List nameReportList = new ArrayList(1);
  protected ReportWebAttr reportWebAttr = null;
  protected ReportExportAttr reportExportAttr = null;
  protected ReportFitAttrProvider fitAttr = null;

  protected void addReport(Report paramReport)
  {
    addReport(null, paramReport);
  }

  public void addReport(String paramString, Report paramReport)
  {
    addReport(getReportCount(), paramString, paramReport);
  }

  protected abstract void setWorkBook(Report paramReport, FineBook paramFineBook);

  protected void addReport(int paramInt, String paramString, Report paramReport)
  {
    if (paramString == null)
      paramString = __autoReportName();
    this.nameReportList.add(paramInt, new NameReport(paramString, paramReport));
    setWorkBook(paramReport, this);
  }

  public boolean isElementCaseBook()
  {
    for (int i = 0; i < getReportCount(); i++)
      if (!isElementCaseBook(i))
        return false;
    return true;
  }

  public boolean isElementCaseBook(int paramInt)
  {
    return (getReportCount() > paramInt) && (getReport(paramInt).isElementCaseReport());
  }

  public ECReport getElementCaseReport(int paramInt)
  {
    if (!isElementCaseBook(paramInt))
      throw new RuntimeException("No ECReport exist, please check the function 'isElementCaseBook'!");
    return (ECReport)getReport(paramInt);
  }

  private NameReport __getNameReportByIndex(int paramInt)
  {
    return (NameReport)this.nameReportList.get(paramInt);
  }

  public String getReportName(int paramInt)
  {
    return __getNameReportByIndex(paramInt).getName();
  }

  public void setReportName(int paramInt, String paramString)
  {
    __getNameReportByIndex(paramInt).name = paramString;
  }

  public Report getReport(int paramInt)
  {
    return __getNameReportByIndex(paramInt).report;
  }

  protected void setReport(int paramInt, Report paramReport)
  {
    setWorkBook(paramReport, this);
    __getNameReportByIndex(paramInt).report = paramReport;
  }

  protected void setReport(int paramInt, String paramString, Report paramReport)
  {
    setWorkBook(paramReport, this);
    __getNameReportByIndex(paramInt).name = paramString;
    __getNameReportByIndex(paramInt).report = paramReport;
  }

  protected int getReportIndex(Report paramReport)
  {
    int i = 0;
    int j = this.nameReportList.size();
    while (i < j)
    {
      if (((NameReport)this.nameReportList.get(i)).report == paramReport)
        return i;
      i++;
    }
    return -1;
  }

  public void removeReport(String paramString)
  {
    int i = 0;
    int j = this.nameReportList.size();
    while (i < j)
    {
      NameReport localNameReport = __getNameReportByIndex(i);
      if (ComparatorUtils.equals(paramString, localNameReport.name))
      {
        setWorkBook(localNameReport.report, null);
        this.nameReportList.remove(i);
        return;
      }
      i++;
    }
  }

  public void removeReports()
  {
    int i = 0;
    int j = this.nameReportList.size();
    while (i < j)
    {
      setWorkBook(__getNameReportByIndex(i).report, null);
      i++;
    }
    this.nameReportList.clear();
  }

  public void removeReport(int paramInt)
  {
    NameReport localNameReport = (NameReport)this.nameReportList.remove(paramInt);
    setWorkBook(localNameReport.report, null);
  }

  public int getReportCount()
  {
    return this.nameReportList.size();
  }

  public ReportWebAttr getReportWebAttr()
  {
    return this.reportWebAttr;
  }

  public ReportFitAttrProvider getFitAttr()
  {
    return this.fitAttr;
  }

  public void setFitAttr(ReportFitAttrProvider paramReportFitAttrProvider)
  {
    this.fitAttr = paramReportFitAttrProvider;
  }

  public void setReportWebAttr(ReportWebAttr paramReportWebAttr)
  {
    this.reportWebAttr = paramReportWebAttr;
  }

  public void setReportExportAttr(ReportExportAttr paramReportExportAttr)
  {
    this.reportExportAttr = paramReportExportAttr;
  }

  public ReportExportAttr getReportExportAttr()
  {
    return this.reportExportAttr;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    AbstractFineBook localAbstractFineBook = (AbstractFineBook)super.clone();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    int j = this.nameReportList.size();
    while (i < j)
    {
      NameReport localNameReport = (NameReport)((NameReport)this.nameReportList.get(i)).clone();
      setWorkBook(localNameReport.report, localAbstractFineBook);
      localArrayList.add(localNameReport);
      i++;
    }
    localAbstractFineBook.nameReportList = localArrayList;
    if (getReportWebAttr() != null)
      localAbstractFineBook.setReportWebAttr((ReportWebAttr)getReportWebAttr().clone());
    if (getReportExportAttr() != null)
      localAbstractFineBook.setReportExportAttr((ReportExportAttr)getReportExportAttr().clone());
    return localAbstractFineBook;
  }

  public void writeXML(XMLPrintWriter paramXMLPrintWriter)
  {
    writeXML(paramXMLPrintWriter, OReportWriter.SC);
  }

  protected void writeXML(XMLPrintWriter paramXMLPrintWriter, ReportWriter paramReportWriter)
  {
    super.writeXML(paramXMLPrintWriter);
    ReportWebAttr localReportWebAttr = getReportWebAttr();
    if (localReportWebAttr != null)
      localReportWebAttr.writeXML(paramXMLPrintWriter);
    ReportExportAttr localReportExportAttr = getReportExportAttr();
    if (localReportExportAttr != null)
      localReportExportAttr.writeXML(paramXMLPrintWriter);
    ReportFitAttrProvider localReportFitAttrProvider = getFitAttr();
    if (localReportFitAttrProvider != null)
      localReportFitAttrProvider.writeXML(paramXMLPrintWriter);
    int i = getReportCount();
    for (int j = 0; j < i; j++)
    {
      Report localReport = getReport(j);
      if (localReport != null)
        paramReportWriter.writeReport(paramXMLPrintWriter, localReport, getReportName(j));
    }
  }

  public void readXML(XMLableReader paramXMLableReader)
  {
    super.readXML(paramXMLableReader);
    if (paramXMLableReader.isChildNode())
    {
      String str2 = paramXMLableReader.getTagName();
      String str1;
      if (str2.equals("Version"))
      {
        if ((str1 = paramXMLableReader.getElementValue()) != null)
          paramXMLableReader.getXMLVersion().setVersion(Double.parseDouble(str1));
      }
      else if (str2.equals("XMLVersion"))
      {
        if ((str1 = paramXMLableReader.getElementValue()) != null)
          paramXMLableReader.setXmlVersionByString(str1);
      }
      else if ("StyleList".equals(str2))
      {
        SynchronizedStyleList.getSynchronizedStyleList().deXmlizeStyleList(paramXMLableReader);
      }
      else if ("Report".equals(str2))
      {
        readReportXML(paramXMLableReader);
      }
      else
      {
        Object localObject;
        if ("ReportWebAttr".equals(str2))
        {
          localObject = getReportWebAttr();
          if (localObject == null)
          {
            localObject = new ReportWebAttr();
            setReportWebAttr((ReportWebAttr)localObject);
          }
          paramXMLableReader.readXMLObject((XMLReadable)localObject);
        }
        else if ("ReportExportAttr".equals(str2))
        {
          localObject = new ReportExportAttr();
          paramXMLableReader.readXMLObject((XMLReadable)localObject);
          setReportExportAttr((ReportExportAttr)localObject);
        }
        else if ("ReportFitAttr".equals(str2))
        {
          readReportFitAttr(paramXMLableReader);
        }
      }
    }
  }

  private void readReportFitAttr(XMLableReader paramXMLableReader)
  {
    ReportFitProcessor localReportFitProcessor = ExtraClassManager.getInstance().getReportFitProcessor();
    if (localReportFitProcessor == null)
      return;
    ReportFitAttrProvider localReportFitAttrProvider = localReportFitProcessor.newInstanceFitAttr();
    paramXMLableReader.readXMLObject(localReportFitAttrProvider);
    setFitAttr(localReportFitAttrProvider);
  }

  private void readReportXML(XMLableReader paramXMLableReader)
  {
    Report localReport = null;
    String str1;
    if ((str1 = paramXMLableReader.getAttrAsString("class", null)) != null)
      localReport = readReportFromClassName(str1);
    String str2 = "";
    if ((str1 = paramXMLableReader.getAttrAsString("title", null)) != null)
      str2 = str1;
    if ((str1 = paramXMLableReader.getAttrAsString("name", null)) != null)
      str2 = str1;
    if ((localReport instanceof PackedReport))
    {
      paramXMLableReader.readXMLObject(localReport);
      addReport(str2, localReport);
    }
    else
    {
      addReport(str2, localReport);
      paramXMLableReader.readXMLObject(localReport);
      if ((localReport instanceof AnalysisRWorkSheet))
        SynchronizedBoxRelation.flushAll((AnalysisRWorkSheet)localReport);
    }
  }
  public static Report readReportFromClassName(String paramString)
  {
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
      try
      {
        Class localClass = GeneralUtils.classForName(paramString);
        localObject = (Report)localClass.newInstance();
      }
      catch (Exception localException)
      {
        FRContext.getLogger().error(localException.getMessage(), localException);
        localObject = new WorkSheet();
      }
    if (localObject == null)
      localObject = new WorkSheet();
    return (Report)localObject;
  }
  public String[] getJSImport()
  {
    ReportWebAttr localReportWebAttr = getReportWebAttr();
    if (localReportWebAttr == null)
      return ArrayUtils.EMPTY_STRING_ARRAY;
    return localReportWebAttr.getJSImport();
  }

  public String[] getCSSImport()
  {
    ReportWebAttr localReportWebAttr = getReportWebAttr();
    if (localReportWebAttr == null)
      return ArrayUtils.EMPTY_STRING_ARRAY;
    return localReportWebAttr.getCSSImport();
  }

  private String __autoReportName()
  {
    Object localObject = "sheet1";
    int i = 0;
    int j = this.nameReportList.size();
    while (i < j)
    {
      String str1 = __getNameReportByIndex(i).name;
      if (str1.toLowerCase().matches("^sheet[1-9]\\d*$"))
      {
        String str2 = "sheet" + (Integer.parseInt(str1.toLowerCase().substring(5)) + 1);
        if ((i == getReportCount() - 1) || (didNotContainsIgnoreCase(str2)))
        {
          localObject = str2;
          break;
        }
      }
      i++;
    }
    return (String) localObject;
  }

  private boolean didNotContainsIgnoreCase(String paramString)
  {
    int i = 0;
    int j = this.nameReportList.size();
    while (i < j)
    {
      String str = __getNameReportByIndex(i).name;
      if (str.equalsIgnoreCase(paramString))
        return false;
      i++;
    }
    return true;
  }

  public void mainContent(XMLPrintWriter paramXMLPrintWriter)
  {
    mainContentWriteXML(paramXMLPrintWriter);
    SynchronizedStyleList.getSynchronizedStyleList().xmlizeStyleList(paramXMLPrintWriter);
  }

  protected abstract void mainContentWriteXML(XMLPrintWriter paramXMLPrintWriter);

  public boolean equals(Object paramObject)
  {
    return (super.equals(paramObject)) && ((paramObject instanceof AbstractFineBook)) && (ComparatorUtils.equals(this.reportWebAttr, ((AbstractFineBook)paramObject).reportWebAttr)) && (ComparatorUtils.equals(this.reportExportAttr, ((AbstractFineBook)paramObject).reportExportAttr)) && (ComparatorUtils.equals(this.nameReportList, ((AbstractFineBook)paramObject).nameReportList));
  }

  private static class NameReport
    implements Cloneable, Serializable
  {
    private static final long serialVersionUID = -4090072956249660275L;
    private String name;
    private Report report;

    private NameReport(String paramString, Report paramReport)
    {
      this.name = paramString;
      this.report = paramReport;
    }

    public String getName()
    {
      if ((this.report instanceof WorkSheet))
      {
        WorkSheetPrivilegeControl localWorkSheetPrivilegeControl = ((WorkSheet)this.report).getWorkSheetPrivilegeControl();
        String[] arrayOfString = PrivilegeManager.getRoleFromCalculator(Calculator.createCalculator());
        if (localWorkSheetPrivilegeControl.checkInvisible(arrayOfString))
          return null;
      }
      return this.name;
    }

    public Object clone()
      throws CloneNotSupportedException
    {
      NameReport localNameReport = (NameReport)super.clone();
      localNameReport.report = ((Report)this.report.clone());
      return localNameReport;
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof NameReport)) && (ComparatorUtils.equals(this.name, ((NameReport)paramObject).name)) && (ComparatorUtils.equals(this.report, ((NameReport)paramObject).report));
    }
  }

  private static class OReportWriter
    implements AbstractFineBook.ReportWriter
  {
    private static OReportWriter SC = new OReportWriter();

    public void writeReport(XMLPrintWriter paramXMLPrintWriter, Report paramReport, String paramString)
    {
      String str = paramReport.getClass().getName();
      paramXMLPrintWriter.startTAG("Report").attr("class", str).attr("name", paramString);
      paramReport.writeXML(paramXMLPrintWriter);
      paramXMLPrintWriter.end();
    }
  }

  protected static abstract interface ReportWriter
  {
    public abstract void writeReport(XMLPrintWriter paramXMLPrintWriter, Report paramReport, String paramString);
  }
}

/* Location:           C:\Users\CN60437\Desktop\fr-report-8.0.jar
 * Qualified Name:     com.fr.main.AbstractFineBook
 * JD-Core Version:    0.6.2
 */