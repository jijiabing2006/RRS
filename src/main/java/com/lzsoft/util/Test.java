package com.lzsoft.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.lzsoft.entity.safe.AccCAEntity;
import com.lzsoft.entity.safe.AccCBEntity;
import com.lzsoft.entity.safe.BopAEntity;
import com.lzsoft.entity.safe.BopBEntity;
import com.lzsoft.entity.safe.BopCEntity;
import com.lzsoft.entity.safe.BopDEntity;
import com.lzsoft.entity.safe.BopEEntity;
import com.lzsoft.entity.safe.BopFEntity;
import com.lzsoft.entity.safe.BopGEntity;
import com.lzsoft.entity.safe.BopHEntity;
import com.lzsoft.entity.safe.BopKEntity;
import com.lzsoft.entity.safe.BopNEntity;
import com.lzsoft.entity.safe.BopPEntity;
import com.lzsoft.entity.safe.BopQEntity;
import com.lzsoft.entity.safe.BopREntity;
import com.lzsoft.entity.safe.BopSEntity;
import com.lzsoft.entity.safe.JshDEntity;
import com.lzsoft.entity.safe.JshEEntity;
import com.lzsoft.entity.safe.JshFEntity;
import com.lzsoft.entity.safe.JshGEntity;

public class Test {
	
	static Map   beanMap = null;

	public static void main2(String[] args)  throws Exception{
//		List<FactoringEntity> fas =	new ParserImpl().parser("C:\\Users\\‘lenovo\\Desktop\\JB_A8_1.txt", FactoringEntity.class ,DateUtils.str2Date("2017-11-22",DateUtils.date_sdf));  
//	    for(FactoringEntity da:fas){
//	    	System.out.println(da.getMatdate()+"123456");
//	    }	
//	    
//		for(FactoringEntity  fa: fas) {
//			/**************用于计算到期日天数********************/
//			// 到期日
//			if (null != fa.getMatdate()) {
//				// 计算到期天数
//				fa.setMatreportday(DateUtils.dateDiff('d', DateUtils.str2Date(fa.getMatdate(),DateUtils.yyyyMMdd), fa.getImportdate()));
//				// 计算到期月以30天为一月
//				fa.setMatreportmonth((int)DateUtils.getMonthByDay(fa.getImportdate(), DateUtils.str2Date(fa.getMatdate(),DateUtils.yyyyMMdd)));
//			}
//			// 下次付息日NX_PAY
//			if (null != fa.getNxPay()) {
//				// 计算到期天数
//				fa.setNxreportday(DateUtils.dateDiff('d', DateUtils.str2Date(fa.getMatdate(),DateUtils.yyyyMMdd), fa.getImportdate()));
//				// 计算到期月以30天为一月
//				fa.setNxreportmonth((int)DateUtils.getMonthByDay(fa.getImportdate(), DateUtils.str2Date(fa.getMatdate(),DateUtils.yyyyMMdd)));
//			}
//		}
		
		initBeanMap();
		beanMap.remove("ACCCA");
		System.out.println(beanMap.keySet().size());
		initBeanMap();
		System.out.println(beanMap.keySet().size());
	}
	
	
	@SuppressWarnings("rawtypes")
	private static void initBeanMap() {

		if ( null == beanMap || beanMap.keySet().size() !=20) {
			beanMap = Collections.synchronizedMap(new HashMap<String, Class>());
			beanMap.put("ACCCA", AccCAEntity.class);
			beanMap.put("ACCCB", AccCBEntity.class);
			beanMap.put("BOPA", BopAEntity.class);
			beanMap.put("BOPB", BopBEntity.class);
			beanMap.put("BOPC", BopCEntity.class);
			beanMap.put("BOPD", BopDEntity.class);
			beanMap.put("BOPE", BopEEntity.class);
			beanMap.put("BOPF", BopFEntity.class);

			beanMap.put("BOPG", BopGEntity.class);
			beanMap.put("BOPH", BopHEntity.class);
			beanMap.put("BOPK", BopKEntity.class);

			beanMap.put("BOPN", BopNEntity.class);
			beanMap.put("BOPP", BopPEntity.class);
			beanMap.put("BOPQ", BopQEntity.class);
			beanMap.put("BOPR", BopREntity.class);
			beanMap.put("BOPS", BopSEntity.class);
			
			beanMap.put("JSHD", JshDEntity.class);
			beanMap.put("JSHE", JshEEntity.class);
			beanMap.put("JSHF", JshFEntity.class);
			beanMap.put("JSHG", JshGEntity.class);
		}
	}
	
	
	public static void main(String[] args)  throws  Exception{
		    File file = new File("C:\\Users\\‘lenovo\\Desktop\\new.txt");
	        try {
	            BufferedReader  br=new BufferedReader(new FileReader(file));
	            String line="";
	            while((line=br.readLine())!=null){
	            	System.out.print(line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return;
	        }
		
	}
	
	

//day
//ten
//month
//season
//halfy
//year
	
}

