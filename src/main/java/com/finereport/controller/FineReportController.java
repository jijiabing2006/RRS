package com.finereport.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StreamUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.finereport.entity.FRReportEntity;
import com.finereport.entity.FRReportTemplateEntity;
import com.lzsoft.common.Constants;
import com.lzsoft.entity.summary.OutlineImport;
import com.lzsoft.entity.summary.OutlineSAFE;

/**
 * @ClassName: frController
 * @Description: TODO
 * @author
 */
@Scope("prototype")
@Controller
@RequestMapping("/frController")
public class FineReportController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(FineReportController.class);

	@Autowired
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@RequestMapping(params = "frtemplateList")
	public void frtemplateList(FRReportTemplateEntity fRReportTemplate,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {

		CriteriaQuery cq = new CriteriaQuery(FRReportTemplateEntity.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				fRReportTemplate, request.getParameterMap());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);

	}

	/**
	 * 附件预览读取
	 * 
	 * @return
	 */
	@RequestMapping(params = "viewFile")
	public void viewFile(HttpServletRequest request,
			HttpServletResponse response) {
		String fileid = oConvertUtils.getString(request.getParameter("fileid"));
		FRReportTemplateEntity fileobj = systemService.getEntity(
				FRReportTemplateEntity.class, fileid);
		UploadFile uploadFile = new UploadFile(request, response);
		byte[] content = fileobj.getCpt();
		String attachmenttitle = fileobj.getCptname();
		uploadFile.setTitleField(attachmenttitle);
		uploadFile.setContent(content);
		uploadFile.setExtend(fileobj.getExtend());
		// uploadFile.setView(true);
		systemService.viewOrDownloadFile(uploadFile);
	}

	/**
	 * 文件添加跳转
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addFiles")
	public ModelAndView addFiles(HttpServletRequest req) {
		return new ModelAndView("finereport/files");
	}

	/**
	 * 删除文档
	 * 
	 * @param frtemplate
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FRReportTemplateEntity frtemplate,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		frtemplate = systemService.getEntity(FRReportTemplateEntity.class,
				frtemplate.getId());
		message = "" + frtemplate.getCptname() + "被删除成功";
		systemService.delete(frtemplate);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 保存文件
	 * 
	 * @param frtemplate
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request,
			HttpServletResponse response, FRReportTemplateEntity frtemplate) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		String cptname = oConvertUtils.getString(request
				.getParameter("documentTitle"));// 文件标题
		String regorg = oConvertUtils.getString(request.getParameter("regorg"));// 所属机构
		TSUser user = ResourceUtil.getSessionUserName();
		UploadFile uploadFile = new UploadFile(request, frtemplate);
		FRReportTemplateEntity ftpl = systemService.findUniqueByProperty(
				FRReportTemplateEntity.class, "cptname", cptname);
		try {
			String fileName = "";
			uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
			Map<String, MultipartFile> fileMap = uploadFile
					.getMultipartRequest().getFileMap();
			if (fileMap.size() > 1) {
				j.setMsg("一次只能上传一个模板文件");
				return j;
			} else {
				for (Map.Entry<String, MultipartFile> entity : fileMap
						.entrySet()) {
					MultipartFile mf = entity.getValue();// 获取上传文件对象
					fileName = mf.getOriginalFilename();// 获取文件名
					String pername = StringUtils.substringBeforeLast(fileName,
							".");
					String extend = StringUtils.substringAfterLast(fileName,
							".");
					if (!pername.equals(cptname)) {// 文件名与title名不一致时，不可以上传
						j.setMsg("文件名与文件标题不一致");
						return j;
					}
					if (null != ftpl) {
						ftpl.setUpdateDate(new Date());
						ftpl.setUpdateBy(user.getUserName());
						ftpl.setUpdateName(user.getRealName());
					} else {
						ftpl = (FRReportTemplateEntity) Class.forName(
								frtemplate.getClass().getName()).newInstance();
						MyBeanUtils.copyBean2Bean(frtemplate, ftpl);
						ftpl.setCreateDate(new Date());
						ftpl.setCreateBy(user.getUserName());
						ftpl.setCreateName(user.getRealName());
						ftpl.setCptname(cptname);
						ftpl.setExtend(extend);
					}
					ftpl.setCpt(StreamUtils.InputStreamTOByte(mf
							.getInputStream()));
					ftpl.setRegorg(regorg);
					systemService.saveOrUpdate(ftpl);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "模板文件上传/更新失败";
			throw new BusinessException(e.getMessage());
		}
		attributes.put("fileKey", ftpl.getId());
		attributes.put("viewhref", "frController.do?objfileList&fileKey="
				+ ftpl.getId());
		j.setMsg("模板上传成功");
		j.setAttributes(attributes);
		return j;
	}

	/**
	 * FR模板编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FRReportTemplateEntity fRReportTemplate,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fRReportTemplate.getId())) {
			fRReportTemplate = systemService.getEntity(
					FRReportTemplateEntity.class, fRReportTemplate.getId());
			req.setAttribute("fRReportTemplatePage", fRReportTemplate);
		}
		return new ModelAndView("finereport/fileedit");
	}

	/**
	 * 更新FR模板
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FRReportTemplateEntity fRReportTemplate,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "FR模板更新成功";
		FRReportTemplateEntity t = systemService.get(
				FRReportTemplateEntity.class, fRReportTemplate.getId());
		try {
			fRReportTemplate.setCpt(t.getCpt());

			MyBeanUtils.copyBeanNotNull2Bean(fRReportTemplate, t);
			
			t.setUpdateDate(new Date());
			systemService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "FR模板更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}



	/**
	 * 跳转操作
	 */
	@RequestMapping(params = "execute")
	public ModelAndView execute(HttpServletRequest request) {
		String syskind = oConvertUtils.getString(request
				.getParameter("syskind"));
		request.setAttribute("syskind", syskind);
		String statfreq = oConvertUtils.getString(request
				.getParameter("statfreq"));
		request.setAttribute("statfreq", statfreq);
		return new ModelAndView("finereport/reportList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(FRReportEntity fRReport, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FRReportEntity.class, dataGrid);
		String syskind = oConvertUtils.getString(request
				.getParameter("syskind"));
		String statfreq = oConvertUtils.getString(request
				.getParameter("statfreq"));
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				fRReport, request.getParameterMap());
		try {
			if (null != syskind && !"".equals(syskind)) {
				cq.eq("syskind", syskind);
			}
			if (null != statfreq && !"".equals(statfreq)) {
				cq.eq("statfreq", statfreq);
			}

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除文档
	 * 
	 * @param frtemplate
	 * @return
	 */
	@RequestMapping(params = "executeRp")
	@ResponseBody
	public String executeRp(@RequestParam("id") String id,
			HttpServletRequest request) {
		FRReportEntity frtemplate = systemService.getEntity(
				FRReportEntity.class, id);
		String url = "";
		if (null != frtemplate) {
			url = "/ReportServer?reportlet=com.finereport.service.impl.URLParameter&isIframe&op=write&rpname="
					+ frtemplate.getReportname();
		}

		return  url;
	}

}
