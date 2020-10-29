package com.lzsoft.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityToString {
   
	
	
    /**   
     * 循环向上转型, 获取对象的属性集合
     * @param object : 子类对象   
     * @return 属性集合   
     */          
    public static List<Field> getDeclaredField(Object object){    
    	List<Field> fields = new  ArrayList<Field>() ; 
        Class<?>  clazz= object.getClass() ;              
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {    
            try { 
            	Field[]   currFields=clazz.getDeclaredFields();
            	if(currFields!=null&&currFields.length>0){
            		for(Field  field:currFields){
            			fields.add(field) ;  
            		}
            	}
            } catch (Exception e) {    
                //
            }     
        }          
        return fields;    
    } 
    
	
	/**
	 * 将Object中的属性值输出
	 * @param object  对象
	 * @return object 属性字符串
	 */
	public  static  String  entityToString(Object  en){
		    StringBuffer  sb=new  StringBuffer(en.getClass().getName()+"    ");
	        List<Field> declaredFields = getDeclaredField(en);
	        for(Field  field:declaredFields){
	        	 field.setAccessible(true) ; 
	        	 try{
	        		 sb.append(field.getName()+":"+StringHelper.nulltoString(field.get(en))+" ");
	        	 }catch(IllegalAccessException  e){
	        		 
	        	 }
	        }
	        return   sb.toString();
	} 
	
	public static void main(String[] args) throws  Exception {
	    String[] entityNames={"AccCAEntity","AccCBEntity","BopAEntity","BopBEntity","BopCEntity","JshEEntity",
	    		"JshFEntity",
	    		"JshGEntity",
	    		"ReportDicEntity",
	    		"ReportMonitorEntity",
	    		"BopDEntity",
	    		"BopEEntity",
	    		"BopFEntity",
	    		"BopGEntity",
	    		"BopHEntity",
	    		"BopKEntity",
	    		"BopNEntity",
	    		"BopPEntity",
	    		"BopQEntity",
	    		"BopREntity",
	    		"BopSEntity",
	    		"JshDEntity"};
	    for(String name:entityNames){
	    	Class  cla=Class.forName("com.lzsoft.entity.safe."+name);
	    	System.out.println(EntityToString.entityToString(cla.newInstance()));
	    }
		
	}
	
}

