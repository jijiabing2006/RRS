package com.lzsoft.controller.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lzsoft.common.Constants;
import com.lzsoft.entity.summary.OutlineImport;
import com.lzsoft.entity.summary.OutlineSAFE;
import com.lzsoft.service.common.SummaryServiceI;

/**
 * @ClassName: accController
 * @Description: TODO
 * @author
 */
@Scope("prototype")
@Controller
@RequestMapping("/stateController")
public class StateController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger
			.getLogger(StateController.class);

	@Autowired
	private SummaryServiceI summaryService;
	@Autowired
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 跳转操作
	 */
	@RequestMapping(params = "execute")
	public ModelAndView execute(HttpServletRequest request) {
		String turn = oConvertUtils.getString(request.getParameter("turn"));

		if ("accSummary".equals(turn)) {
			String[] allTypeList = Constants.ACC_BASE_TYPE;
			Map<String, OutlineSAFE> outlineAcc = (Map<String, OutlineSAFE>) summaryService
					.getSummary("ACC", allTypeList, null);
			request.setAttribute("accSummary", outlineAcc);
		} else if ("bopSummary".equals(turn)) {
			String[] allTypeList = ArrayUtils.addAll(ArrayUtils.addAll(
					Constants.BOP_BASE_TYPE, Constants.BOP_DECLARE_TYPE),
					Constants.BOP_CONTROL_TYPE);
			Map<String, OutlineSAFE> outlineBop = (Map<String, OutlineSAFE>) summaryService
					.getSummary("BOP", allTypeList, null);
			request.setAttribute("bopSummary", outlineBop);
		} else if ("jshSummary".equals(turn)) {
			String[] allTypeList = ArrayUtils.addAll(Constants.JSH_BASE_TYPE,
					Constants.JSH_CONTROL_TYPE);
			Map<String, OutlineSAFE> outlineJsh = (Map<String, OutlineSAFE>) summaryService
					.getSummary("JSH", allTypeList, null);
			request.setAttribute("jshSummary", outlineJsh);
		} else if ("dataSummary".equals(turn)) {
			try {
				OutlineImport outlineImport = (OutlineImport) summaryService
						.getImportSummary(OutlineImport.class, null);
				request.setAttribute("dataSummary", outlineImport);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
		}

		return new ModelAndView("safe/state/" + turn + "");
	}
}
