package com.lzsoft.autoimport.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.autoimport.commons.Constants;
import com.lzsoft.entity.safe.ReportMonitorEntity;
import com.lzsoft.service.safe.ReportMonitorServiceI;

@Service("reportMonitorMain")
@Transactional
public class ReportMonitorMainService extends CommonServiceImpl {
	
	@Autowired
	private  ReportMonitorServiceI reportMonitorService;

	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(ReportMonitorMainService.class);
	
	private List<ReportMonitorEntity> reportMonitors = null;
	private static PropertiesConfiguration pc = null;
	private static String filePath = null;	
	private static String filename = null;
	private static String destFilename = null;
	private static String history = null;
	private static String bakpath = null;
	private static File f = null;
	private static SmbFile  smbFile = null;
	
	/**
	 * 查询t_report_monitor表所有是否上报标志为否的交易
	 */
	public void reportMonitorMain() throws Exception{
		int ret=0;
		int icount=0;
		
		System.out.println("............reportMonitorMain start........");
		try {
			CriteriaQuery cq = new CriteriaQuery(ReportMonitorEntity.class);
			cq.eq("isDone", "N");
			cq.add();
			reportMonitors = getListByCriteriaQuery(cq, false);
			
			/*get system date*/
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String dateNowStr = sdf.format(d);  
			String dateStr=DateUtils.date_sdf.format(d);
			Date dateNow=DateUtils.date_sdf.parse(dateStr);
			
			if (null != reportMonitors && !reportMonitors.isEmpty()) {
				/*获取文件存放地址*/	
				pc = new PropertiesConfiguration(Constants.DATA_FILES_PATH);
				filePath = pc.getString("reportwarnfilepath");
				bakpath = pc.getString("reportwarnbakfilepath");
				if(filePath == null) {
					System.out.println("get filepath fail");
					return ;
				}
				if(bakpath == null) {
					System.out.println("get bakpath fail");
					return ;
				}

				filename = filePath + "REPORT_WARN.txt";
				f=new File(filename);
				//创建新文件
				if(f.exists()){
					f.delete();
					f=new  File(filename);
				}
				
				for (ReportMonitorEntity reportMonitor : reportMonitors) {
			 		//赋值报表类型
					//CriteriaQuery cq2 = new CriteriaQuery(ReportDicEntity.class);
			 		//cq2.eq("reportName", reportMonitor.getReportName());
			 		//cq2.add();
			 		//ReportDicEntity o = getUniqueObjectByCriteriaQuery(cq2);
			 		//o.setUpdateDate(dateNow);
			 		//commonDao.saveOrUpdate(o);
			 		String reportType=reportMonitor.getReportType();
			 		//if (null != o) {
			 		//	reportType=o.getReportType();
			 		//}
					Date  warnTime=reportMonitor.getWarnTime();
					//如果为日报并且不是周末，则到期提醒；如果不为日报，如果为周末则提前到周五提醒
					//根据提醒日获取本周五时间
					Date  parseWarnTime=DateUtils.getWorkDate(warnTime);
					System.out.println(reportMonitor.getReportName()+"parseWarnTime:"+parseWarnTime+"dateNow:"+dateNow+"reportType:"+reportType+"result:"+(dateNow.equals(parseWarnTime)&&(!"day".equals(reportType)||("day".equals(reportType)&&parseWarnTime.equals(warnTime)))));
					
					if(dateNow.equals(parseWarnTime)){
						/*产生warning文档*/
						ret=proWarnFile(reportMonitor,icount);
						if(ret <0) {
							return ;
						}
						icount++;
					}
					//提醒完成后，自动延期
					if(dateNow.equals(warnTime)||dateNow.after(warnTime)) {
						//reportMonitor.setLastWarnTime(warnTime);
						String  nextWarnTime=getNextWarnTime(reportType,warnTime,dateNow);
						reportMonitor.setLastWarnTime(warnTime);
						reportMonitor.setWarnTime(DateUtils.strToDate(nextWarnTime,"yyyy-MM-dd"));
						commonDao.executeHql("update ReportMonitorEntity t  set t.lastWarnTime='"+warnTime+"',t.warnTime='"+nextWarnTime+"' where  t.id='"+reportMonitor.getId()+"'");
						commonDao.getSession().saveOrUpdate(reportMonitor);
				 		reportMonitorService.saveOrUpdate((ReportMonitorEntity)reportMonitor);
					}
				}
				

				String monitorD = dateNowStr.substring(0, 4) + dateNowStr.substring(5, 7) + dateNowStr.substring(8, 10);
				history = bakpath + monitorD + "\\";
				destFilename = history + "REPORT_WARN.txt." + dateNowStr.substring(11, 13) + dateNowStr.substring(14, 16);
				File file= new File(filename);
				File destFile = new File(destFilename);
				if(file.exists()) {
					FileUtils.copyFile(file, destFile);
					//FileUtils.cleanDirectory();
				}
				
				f=null;
				
				//throw new  RuntimeException("设置异常,才能正常提交业务");

				
				/*上传文件*/
//				if (icount > 0) {					
//					String remotePath = pc.getString("wxdestpath");
//					init(remotePath);
//					ret = uploadFile(f, remotePath);
//					if (ret == 0) {
//						System.out.println("数据文件:" + f.getName()+"，上传成功！");
//					} else {
//						System.out.println("数据文件:" + f.getName()+"，上传失败！");
//					}
//				}
			}
			System.out.println("............reportMonitorMain end........");
		}catch(RuntimeException  e){
			
		}catch (Exception e) {
			e.printStackTrace();
			FileUtils.cleanDirectory(new File(filePath));
		}
	}


	private int proWarnFile(ReportMonitorEntity reportMonitor, int icount) throws ConfigurationException {
		/*写文件*/
		try {
			//如果文件存在，则追加内容；如果文件不存在，则创建文件
			if(!f.getParentFile().exists()){
				f.getParentFile().mkdirs();
			}
			
			OutputStream out = new FileOutputStream(f,true);
			OutputStreamWriter os=new OutputStreamWriter(out,"utf-8");
            BufferedWriter rd = new BufferedWriter(os);
            /*获取微信渠道号*/
            CriteriaQuery cq = new CriteriaQuery(TSTypegroup.class);
            cq.eq("typegroupcode", "WXQD");
			cq.add();
			TSTypegroup tsTypegroup = getUniqueObjectByCriteriaQuery(cq);
			//获取所有渠道号
			List<TSType>  tsTypes=tsTypegroup.getTSTypes();
			//查询与纪录相匹配的渠道号
			if(tsTypes!=null){
				for(TSType ts:tsTypes){
					//根据机构号查找相应的渠道号码
					String  brca=reportMonitor.getBrca();
					if(ts.getTypename().contains(brca)){
						if(icount == 0) {
							rd.write("所属渠道,成员ID列表,部门ID列表,标签ID列表,数据类型,企业应用ID,消息内容,是否保密\n");
						}
						String text=ts.getTypecode()+",@all,@all,@all,text,," 
								+ "监管机构申报告警[";
						if(reportMonitor.getUserId()!=null&&reportMonitor.getUserName()!=null){
							String[]  userId=reportMonitor.getUserId().split("、");
							String[] userName= reportMonitor.getUserName().split("、"); 
							for(int  i=0;i<userId.length;i++){
								text+=userId[i];
								if(userName.length>i){
									text+="("+userName[i]+")";
								}
								if(i!=userId.length-1){
									text+="、";
								}
							}
						}

						text+="负责的报表:" + reportMonitor.getReportName() + "还未完成，请尽快处理( ▼-▼ )！]"
								+ ",0\n"; 
						rd.write(text);
					}
				}
			}
			
			rd.flush();
			rd.close();
			os.close();
			out.close();
			rd=null;
			os=null;
			out=null;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}	
		return 0;
	}
	
	/**
	 * 上传文件到远程目录
	 * 
	 * @param smbFile
	 *            本地SmbFile
	 * @param localDirectory
	 *            本地存储目录,本地目录不存在时会自动创建,本地目录存在时可自行选择是否清空该目录下的文件,默认为不清空
	 * @return boolean 是否拷贝成功
	 */
	private static int uploadFile(File file, String remoteDirectory) {
		int flag = -1; 
        BufferedInputStream bf = null; 
        SmbFileOutputStream  smbOut = null;
        try{
            smbOut = new SmbFileOutputStream(remoteDirectory + file.getName(), false); 
            bf = new BufferedInputStream(new FileInputStream(file)); 
            byte[] bt = new byte[1024]; 
            int n = bf.read(bt); 
            while (n != -1){ 
                smbOut.write(bt, 0, n); 
                smbOut.flush(); 
                n = bf.read(bt); 
            } 
            flag = 0; 
            System.out.println("file transmission complete..."); 
        }catch(SmbException e) { 
            e.printStackTrace(); 
        }catch(MalformedURLException e) { 
            e.printStackTrace(); 
        }catch(UnknownHostException e) { 
            e.printStackTrace(); 
        }catch(IOException e) { 
            e.printStackTrace(); 
        }finally{
            try { 
                if(null != smbOut) 
                    smbOut.close(); 
                if(null != bf) 
                    bf.close(); 
            }catch(Exception e2) { 
                e2.printStackTrace(); 
            } 
        } 
        return flag;
	}
	
	public void init(String url) {
        try {
           System.out.println("connecting...url：" + url); 
           smbFile = new SmbFile(url); 
           smbFile.connect(); 
           System.out.println("connect successfu...url：" + url); 
        }catch (MalformedURLException e) { 
           e.printStackTrace(); 
        }catch (IOException e) { 
           e.printStackTrace(); 
        } 
    }
	
	
	public  String  getNextWarnTime(String reportType,Date date,Date dateNow){
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(date);
		while(true){
	 		if("month".equals(reportType)){
	 			gc.add(2,1);
	 	    }else if("season".equals(reportType)) {
	 			gc.add(2,3);
		    }else if("year".equals(reportType)){		
	 			gc.add(1,1);
			}else if("day".equals(reportType)){
				gc.add(5,1);
			}else if("ten".equals(reportType)){
				gc.add(5,10);
			}else if("halfy".equals(reportType)){
	 			gc.add(2,6);
			}else if("week".equals(reportType)){
				gc.add(4,1);
			}
	 		if(gc.getTime().equals(dateNow)||gc.getTime().after(dateNow)){
	 			break;
	 		}
 		}
		return  DateUtils.dateToStr(gc.getTime(),"yyyy-MM-dd");
	}
	
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ReportMonitorEntity t){
	 	return true;
 	}
	
}