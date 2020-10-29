package com.finereport.main;

import com.fr.page.ReportSettingsProvider;
import com.fr.report.report.Report;
import com.fr.report.stable.ReportSettings;
import java.util.ArrayList;
import java.util.List;

public class SynchronizedReportSettings
{
  private static ThreadLocal threadReportSettingsTL = new ThreadLocal();
  private TemplateWorkBook wbTpl;
  private ReportSettingsProvider reportSettings;

  private SynchronizedReportSettings(TemplateWorkBook paramTemplateWorkBook, ReportSettings paramReportSettings)
  {
    this.wbTpl = paramTemplateWorkBook;
    this.reportSettings = paramReportSettings;
  }

  public TemplateWorkBook getWbTpl()
  {
    return this.wbTpl;
  }

  public void setWbTpl(TemplateWorkBook paramTemplateWorkBook)
  {
    this.wbTpl = paramTemplateWorkBook;
  }

  public ReportSettingsProvider getReportSettings()
  {
    return this.reportSettings;
  }

  public void setReportSettings(ReportSettingsProvider paramReportSettingsProvider)
  {
    this.reportSettings = paramReportSettingsProvider;
  }

  public static void putSynchronizedReportSettings(TemplateWorkBook paramTemplateWorkBook, ReportSettings paramReportSettings)
  {
    if ((paramTemplateWorkBook == null) || (paramReportSettings == null))
      return;
    Object localObject = (List)threadReportSettingsTL.get();
    if (localObject != null)
    {
      ((List)localObject).add(new SynchronizedReportSettings(paramTemplateWorkBook, paramReportSettings));
    }
    else
    {
      localObject = new ArrayList(6);
      ((List)localObject).add(new SynchronizedReportSettings(paramTemplateWorkBook, paramReportSettings));
      threadReportSettingsTL.set(localObject);
    }
  }

  public static void flushSynchronizedReportSettings()
  {
    List localList = (List)threadReportSettingsTL.get();
    if (localList != null)
      for (int i = 0; i < localList.size(); i++)
      {
        SynchronizedReportSettings localSynchronizedReportSettings = (SynchronizedReportSettings)localList.get(i);
        if (localSynchronizedReportSettings == null)
          continue;
        for (int j = 0; j < localSynchronizedReportSettings.getWbTpl().getReportCount(); j++)
          localSynchronizedReportSettings.getWbTpl().getReport(j).setReportSettings(localSynchronizedReportSettings.getReportSettings());
      }
    threadReportSettingsTL.set(null);
  }
}

/* Location:           C:\FineReport_8.0\WebReport\WEB-INF\lib\fr-report-8.0.jar
 * Qualified Name:     com.fr.main.SynchronizedReportSettings
 * JD-Core Version:    0.6.0
 */