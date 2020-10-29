package com.lzsoft.service.safe.feedback;

import java.util.Date;

public interface IFeedbackService extends IBaseInterface {
/**
 * 
 * @param importdate 导出上报文件时的系统日期
 * @param brca      分行代码（反馈读取时应该忽略）
 * @param appType   reportsenttype中type. ACC/BOP/JSH
 * @param local   是否读取本地（不需要从MTS传回的反馈）
 * @return
 * @throws Exception
 */
	String readFeedback(Date importdate, String brca,String appType,boolean local) throws Exception;
/**
 * 审核通过后，查询是否有已通知过的错误信息，如果有就删除
 * @param bopobj
 */
      void deleteFeedbackError(Object bopobj);

}
