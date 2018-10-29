package com.chinaxaxt.xtzncms.common.utils.tools;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 字符串工具类
 * @author 吴佳涛
 *
 */
public class StringUtil {

	/**
	 * 交集
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static String[] intersect(String[] arr1, String[] arr2){
        List<String> l = new LinkedList<String>();
        Set<String> common = new HashSet<String>();                  
        for(String str:arr1){
            if(!l.contains(str)){
                l.add(str);
            }
        }
        for(String str:arr2){
            if(l.contains(str)){
                common.add(str);
            }
        }
        String[] result={};
        return common.toArray(result);
    }
	
	/**
	 * 求两个数组的差集   
	 * @param arr1
	 * @param arr2
	 * @return
	 */
    public static String[] substract(String[] arr1, String[] arr2) {   
        LinkedList<String> list = new LinkedList<String>();   
        for (String str : arr1) {   
            if(!list.contains(str)) {   
                list.add(str);   
            }   
        }   
        for (String str : arr2) {   
            if (list.contains(str)) {   
                list.remove(str);   
            } 
        }   
        String[] result = {};   
        return list.toArray(result);   
    }   
}
