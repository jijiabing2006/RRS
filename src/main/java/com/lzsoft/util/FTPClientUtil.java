package com.lzsoft.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.jeecgframework.core.util.DateUtils;
import org.springframework.stereotype.Service;

import com.lzsoft.common.Constants;
import com.lzsoft.entity.common.ReportsenttypeEntity;

@Service
public class FTPClientUtil {

	private FTPClient ftp;

	/**
	 * 上报日志数据库访问对象
	 */
	// private IDaoSupport<Bopreportlog, Long> bopreportlogDAO;

	/**
	 * 连接FTP服务器
	 * 
	 * @param server
	 *            主机地址
	 * @param port
	 *            端口号
	 * @param userName
	 *            名称
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	public FTPClient connectFtpServer(String server, int port, String userName,
			String password) throws Exception {
		ftp = new FTPClient();// 创建ftp客户端对象
		try {
			ftp.connect(server, port);// 连接ftp服务器
		} catch (Exception e) {
			throw new Exception("连接服务器失败,请检查server[" + server + "],port["
					+ port + "]是否正确,以上信息正确的情况下请检查网络连接是否正常或者请求被防火墙拒绝.");
		}
		try {
			ftp.login(userName, password);// 登录
		} catch (Exception e) {
			throw new Exception("登陆服务器失败,请检查username[" + userName
					+ "],password[" + password + "]是否正确.");
		}
		try {
			int reply = ftp.getReplyCode();// 返回值
			if ((!FTPReply.isPositiveCompletion(reply))) {
				ftp.disconnect();
				throw new Exception("登录服务器请求超时,请重试.");
			}
			ftp.setDefaultPort(port);
			ftp.setControlEncoding("GBK");
			ftp.setDataTimeout(120000);
			return ftp;
		} catch (Exception e) {
			ftp.disconnect();
			throw new Exception("登录服务器请求超时,请重试.");
		}
	}

	/**
	 * 关闭连接
	 * 
	 */
	public void disconnectFtpServer() throws Exception {
		try {
			ftp.disconnect();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 上传文件到FTP
	 * 
	 * @param localIn
	 *            本地输入流
	 * @param remoteFilePath
	 *            远程文件路径
	 * @throws Exception
	 */
	public void upload(InputStream localIn, String remoteFilePath)
			throws Exception {

		try {
			boolean result = ftp.storeFile(remoteFilePath, localIn);
			if (!result) {
				throw new Exception("文件上传失败!");
			}
		} catch (Exception e) {
			disconnectFtpServer();
			throw new Exception(e);
		}
	}

	/**
	 * 上传结束以后关闭输入流
	 * 
	 * @param localIn
	 *            本地输入流
	 * @param remoteFilePath
	 *            远程文件路径
	 * @param afterUploadCloseInputStream
	 *            是否上传结束关闭输入流
	 * @throws FtpException
	 */
	public void upload(InputStream localIn, String remoteFilePath,
			boolean afterUploadCloseInputStream) throws Exception {
		try {
			// 上传
			upload(localIn, remoteFilePath);
		} finally {
			if (afterUploadCloseInputStream) {
				if (localIn != null) {
					try {
						localIn.close();
					} catch (Exception e) {
						throw new Exception(e);
					}
				}
			}
		}
	}

	/**
	 * 下载
	 * 
	 * @param fileName
	 *            文件名
	 * @param localPath
	 *            本地路径
	 * @return
	 */
	public boolean download(String fileName, String localPath) {
		boolean success = false;

		try {
			int reply;
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			fileName = StringHelper.GBK2ISO(fileName);
			File localFile = new File(localPath);

			OutputStream out = new FileOutputStream(localFile);
			ftp.enterLocalPassiveMode();
			ftp.retrieveFile(fileName, out);
			out.close();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return success;
	}

	/**
	 * 切换路径
	 * 
	 * @param directory
	 * @return
	 */
	public boolean changeWorkingDirectory(String directory) {
		try {
			return ftp.changeWorkingDirectory(directory);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 切换到上级目录
	 * 
	 * @return
	 */
	public boolean changeToParentDirectory() {
		try {
			return ftp.changeToParentDirectory();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 远程删除文件
	 * 
	 * @param url
	 *            被删除的路径
	 * @throws Exception
	 */
	public void delete_file(String url) throws Exception {
		boolean flag = ftp.deleteFile(url);
		if (flag) {
			// log.info("删除文件[" + url + "]成功!");
		} else {
			// log.info("删除文件[" + url + "]失败");
		}
	}

	/**
	 * 远程创建目录
	 * 
	 * @param url
	 *            需要创建的路径
	 * @throws Exception
	 */
	public boolean create_dir(String url) throws Exception {
		boolean flag = ftp.makeDirectory(url);
		if (flag) {
			// log.info("创建文件夹[" + url + "]成功!");
		} else {
			// log.info("创建文件夹[" + url + "]失败!");
		}
		return flag;
	}

	public FTPFile[] listFiles() throws IOException {
		return ftp.listFiles();
	}

	/**
	 * 远程文件路径编码(上传到ftp上的文件路径)
	 * 
	 * @param remoteFilePath
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected String enCodingRemoteFilePath(String remoteFilePath)
			throws UnsupportedEncodingException {
		return StringHelper.UTF82ISO(remoteFilePath);
	}

	/**
	 * 根据BOP上报文件的列表写Token.lock文件
	 * 
	 * @param tokenfilepath
	 *            Token.lock的完整路径
	 * @param reportfilelist
	 *            上报文件的列表
	 * @throws IOException
	 */
	private void writeTokenFile(String tokenfilepath, String[] reportfilelist,
			String reportdate) throws IOException {
		File file = new File(tokenfilepath);
		FileOutputStream fWriter = new FileOutputStream(file);
		BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(
				fWriter));
		bufWriter.write("ACC上报的文件列表如下:\n");
		for (int i = 0; i < reportfilelist.length; i++) {
			bufWriter.write(reportfilelist[i] + "\n");

		}
		bufWriter.close();
		fWriter.close();
	}
	public String renderReportByAccType(ReportsenttypeEntity reportsenttype,String accType) {
		
		if("ACC".equals(accType)){
			return renderAccReport(reportsenttype);
		}else if("BOP".equals(accType)){
			return renderBopReport(reportsenttype);
		}else if("JSH".equals(accType)){
			return renderJshReport(reportsenttype);
		}
		return "";
	}
	/**
	 * 发送ACC上报
	 * 
	 * @param reportsenttype
	 * @param user
	 * @return
	 */
	private String renderAccReport(ReportsenttypeEntity reportsenttype) {
		return renderReport(reportsenttype, Constants.ACCTOKENFILEPATH);
	}

	/**
	 * 发送BOP上报
	 * 
	 * @param reportsenttype
	 * @param user
	 * @return
	 */
	private String renderBopReport(ReportsenttypeEntity reportsenttype) {
		return renderReport(reportsenttype, Constants.BOPTOKENFILEPATH);
	}

	/**
	 * 发送JSH上报
	 * 
	 * @param reportsenttype
	 * @param user
	 * @return
	 * @throws Exception
	 */
	private String renderJshReport(ReportsenttypeEntity reportsenttype) {
		return renderReport(reportsenttype, Constants.JSHTOKENFILEPATH);
	}

	/**
	 * 报送BOP报表
	 * 
	 * @param reportsenttype
	 *            报送方式
	 * @return
	 */
	private String renderReport(ReportsenttypeEntity reportsenttype,
			String tokenpath) {
		try {
			connectFtpServer(reportsenttype.getServeraddress(),
					reportsenttype.getPort(), reportsenttype.getUsername(),
					reportsenttype.getPassword());
		} catch (Exception e) {
			return e.getMessage();
		}
		String urls = null;
		File[] reportList = null;
		try {
			FTPFile[] files = null;
			changeWorkingDirectory(StringHelper.GBK2ISO(reportsenttype
					.getReadytosenddirpath()));// 设置待发送目录
			files = listFiles();// 取得待发送目录下的文件列表
			for (FTPFile file : files) {// 遍历是否在待发送目录下存在令牌文件,如果存在则不能报送.否则...
				if (StringUtils.equals("Token.lock", file.getName())) {
					disconnectFtpServer();
					return "令牌文件已经存在.暂时不能报送.";
				}
			}

			String tokenfilepath = tokenpath;// 存放Token.lock文件的路径

			String reportfilepath = reportsenttype.getUploaddirpath();// 待上报文件路径
			File report = new File(reportfilepath);// 待上报文件路径
			reportList = report.listFiles();// 待上报文件列表
			File tokenfolder = new File(tokenfilepath);
			FileUtils.forceMkdir(tokenfolder);// 创建存放Token.lock文件的目录
			try {
				FileUtils.cleanDirectory(tokenfolder);// 删除存放Token.lock文件目录下的所有文件.
				writeTokenFile(tokenfilepath + "Token.lock", report.list(), "");// 写Token.lock文件
			} catch (Exception e) {
				disconnectFtpServer();
				return "创建令牌文件失败,请检查网络连接是否正常!";
			}
			InputStream localIn = new FileInputStream(tokenfilepath
					+ "Token.lock");
			for (File f : reportList) {// 遍历待上报文件,找到T文件
				if (StringUtils.indexOf(f.getName(), "T") != StringUtils.INDEX_NOT_FOUND) {
					urls = StringUtils.substring(f.getName(), 0,
							StringUtils.indexOf(f.getName(), "."));
				}
			}
			if (StringUtils.isEmpty(urls)) {
				ftp.disconnect();
				return "报送的压缩包中没有控制文件,请检查后重新报送.";
			}
			upload(localIn, "Token.lock", true);// 报送文件之前先创建令牌文件
			if (!create_dir(urls)) {// 24位接口控制文件名作为当次报送的目录,创建失败则代表服务器上有同名文件夹
				delete_file("Token.lock");// 报送完毕删除令牌文件
				return "待发送目录[" + urls + "]已经存在,该报文已经上报过,无需重复上报.";
			}
			changeWorkingDirectory(urls);
			for (File file : reportList) {// 遍历待上报文件列表,然后上传到服务器
				localIn = new FileInputStream(file);
				upload(localIn, file.getName(), true);
			}
			changeToParentDirectory();
			delete_file("Token.lock");// 报送完毕删除令牌文件

		} catch (Exception e) {
			return "报送文件失败,请检查网络连接是否正常." + e.getMessage();
		} finally {
			try {
				disconnectFtpServer();
			} catch (Exception e) {
				return "报送文件失败,请检查网络连接是否正常." + e.getMessage();
			}
		}
		return "已成功将报表传送至服务器[" + reportsenttype.getServeraddress()
				+ "]的待发送目录下的文件夹[" + urls + "]下";
	}

	/**
	 * 将反馈文件下载到服务器中
	 * @param reportsenttype
	 * @param reportdate
	 * @param feedback
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String readFeedbackFile(ReportsenttypeEntity reportsenttype,
			Date reportdate,
			String feedback, String type) throws Exception {
		connectFtpServer(reportsenttype);
		try {
			changeWorkingDirectory(StringHelper.GBK2ISO(reportsenttype
					.getFeedbackdirpath()));// 设置反馈目录
			FTPFile[] files = listFiles();// 取得反馈目录下的文件列表
			String _reportdate = DateUtils.dateToStr(reportdate, "yyMMdd");
			int controlFileCount = 0;
			for (FTPFile file : files) {// 遍历反馈文件是否有reportdate那天的文件夹
				if (StringUtils.indexOf(file.getName(), _reportdate) != StringUtils.INDEX_NOT_FOUND
						&& file.isDirectory()
						&& StringUtils.indexOf(file.getName(), type) != StringUtils.INDEX_NOT_FOUND) {
					String feedbackpath = feedback + file.getName()
							+ File.separator;// 存放反馈文件的路径
					FileUtils.forceMkdir(new File(feedbackpath));
					FileUtils.cleanDirectory(new File(feedbackpath));// 删除存放反馈文件目录下的所有文件.
								changeWorkingDirectory(file.getName());
						files = listFiles();
						for (FTPFile f : files) {
							if (f.getName().length() <= 2) {
								continue;
							}
							if (!download(f.getName(),
									feedbackpath + f.getName())) {
							}
						}

						//创建一个备份文件
						changeToParentDirectory();
						ftp.mkd("../backup/");
						ftp.rename(file.getName(),
								"../backup/" + file.getName());
						controlFileCount++;
					

				}
			}
			if (controlFileCount > 0) {
				return "已传送日期[" + DateUtils.dateToStr(reportdate, "yyyyMMdd")
						+ "]的反馈文件到本地服务器.";
			} else {
				return "MTS服务器下没有日期["
						+ DateUtils.dateToStr(reportdate, "yyyyMMdd")
						+ "]的反馈文件可以传递.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				disconnectFtpServer();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

	}

	public int getFilesInSendFloder(ReportsenttypeEntity reportsenttype)
			throws Exception {
		connectFtpServer(reportsenttype);
		try {
			changeWorkingDirectory(StringHelper.GBK2ISO(reportsenttype
					.getReadytosenddirpath()));// 设置预发送目录
			FTPFile[] files = listFiles();// 取得预发送目录下的文件列表

			if (files.length > 0) {
				return 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				disconnectFtpServer();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return 0;

	}

	private void connectFtpServer(ReportsenttypeEntity reportsenttype)
			throws Exception {
		try {
			connectFtpServer(reportsenttype.getServeraddress(),
					reportsenttype.getPort(), reportsenttype.getUsername(),
					reportsenttype.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String downloadFilesByDate(ReportsenttypeEntity reportsenttype,
			Date maxd, String localpath) throws Exception {
		connectFtpServer(reportsenttype);

		changeWorkingDirectory(StringHelper.GBK2ISO(reportsenttype
				.getLogdirpath()));// 读取Log目录，
		FTPFile[] logfiles = listFiles();// 取得发送目录下的文件列表

		String _reportdate = DateUtils.dateToStr(maxd, "yyMMdd");
		int counts = 0;
		for (FTPFile file : logfiles) {// 遍历LOG文件是否有reportdate那天的文件
			counts++;
			if (StringUtils.indexOf(file.getName(), _reportdate) != StringUtils.INDEX_NOT_FOUND) {
				String locallogpath = localpath + _reportdate + File.separator;// 存放LOG文件的路径
				FileUtils.forceMkdir(new File(locallogpath));
				download(file.getName(), locallogpath + file.getName());
				return locallogpath + file.getName();
			}
		}

		return "";
	}

}