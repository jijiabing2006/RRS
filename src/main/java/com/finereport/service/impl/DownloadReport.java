package com.finereport.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadReport extends HttpServlet {

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String rpdate = request.getParameter("rpdate");
			String statfreq = request.getParameter("statfreq");
			String reportname = request.getParameter("reportname");
			String downFilename = "";
			if(!reportname.equals("")) {
				downFilename = rpdate + "\\" + reportname +  ".xls";
			} else {
				downFilename = rpdate + "\\" + statfreq + ".zip";
			}
			response.setContentType("text/plain");
			response.setHeader("Location", downFilename);
			response.setHeader("Content-Disposition", "attachment; filename="
					+ downFilename);
			OutputStream outputStream = response.getOutputStream();
			InputStream inputStream = new FileInputStream("C:\\filename\\"+ downFilename);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("没有找到您要的文件");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("系统错误，请及时与管理员联系");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
