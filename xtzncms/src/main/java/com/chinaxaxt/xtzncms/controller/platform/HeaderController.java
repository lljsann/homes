package com.chinaxaxt.xtzncms.controller.platform;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinaxaxt.xtzncms.common.command.Media;

/**
 * 
 * @author 吴佳涛
 *
 */
@Controller
@RequestMapping("header")
public class HeaderController {

	/**
	 * 上传图片
	 * @param header
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/{header:[a-zA-Z0-9\\\\.]+}")
	public void header(@PathVariable("header")String header, HttpServletRequest request, HttpServletResponse response) throws IOException{
		String suffix = header.substring(header.indexOf(".")+1, header.length());
		String type = Media.getContextType(suffix);
//		System.out.println(type);
		OutputStream os=null;
		String isNull = "null";
		try {
		  response.setContentType(type);
	      os = response.getOutputStream();
	      byte[] img=null;
	      if(!isNull.equals(header)) {
		      String hp="/usr/java/xtzncms/docs/images/"+header;
		      File file=new File(hp);
		      if(file.exists()) {
		          img = getBytes(file);
		      }else {
		    	  
		      }
	      }else {
	    	  
	      }
	      os.write(img);
    	  os.flush();
	    }catch (IOException e){
	    	e.printStackTrace();
	    }finally {
	    	os.close();
		}
	}
	
	private byte[] getBytes(File file){  
        byte[] buffer = null;  
        try {  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }

	public byte[] image2byte(File file) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(file);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}

}
