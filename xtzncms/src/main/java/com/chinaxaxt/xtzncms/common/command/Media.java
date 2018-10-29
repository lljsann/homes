package com.chinaxaxt.xtzncms.common.command;

import java.util.HashMap;
import java.util.Map;

public class Media {
	
	private static final Map<String,String> media=new HashMap<>();
	
	static {
		media.put("flv", "flv-application/octet-stream");
		media.put("mp4", "video/mp4");
		media.put("jpg","image/jpeg");
		media.put("png","image/jpeg");
	}
	
	public static String getContextType(String suffix) {
		return media.containsKey(suffix)?media.get(suffix):"";
	}
	
}
