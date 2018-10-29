package com.chinaxaxt.xtzncms.controller.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinaxaxt.xtzncms.common.vo.ResponseVO;
import com.chinaxaxt.xtzncms.entity.biz.Communication;
import com.chinaxaxt.xtzncms.service.biz.CommunicationService;


/**
 * 
 * @author 吴佳涛
 * 2018-6-27
 */
@Controller
@RequestMapping("/communication")
public class CommunicationController {

    @Autowired
    CommunicationService communicationService;

    @RequestMapping(value="/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCommunication(@PathVariable("uid") String uid){
        Communication communication=new Communication();
        if(uid!=null&&!"".equals(uid)) {
        	communication.setUid(uid);
        }
        return new ResponseVO(800, "success",communicationService.select(communication));
    }
    
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCommunications(){
        return new ResponseVO(800, "success",communicationService.select(new Communication()));
    }
    
    @RequestMapping(value="/mine",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getContact(){
        return new ResponseVO(800, "success",communicationService.select(new Communication()));
    }


    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertCommunication(@RequestBody Communication communication){
        return new ResponseVO(800, "success",communicationService.insert(communication));
    }
    
    /**
     * 上传LOGO
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadLogo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO uploadLogo(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		String uid = new String("2018062713244780959abe2cb73e022e");
		return ResponseVO.success( communicationService.uploadLogo(file, uid));
	}
    
    /**
     * 上传二维码
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadQrCode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO uploadQrCode(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		String uid = new String("2018062713244780959abe2cb73e022e");
		return ResponseVO.success( communicationService.uploadQrCode(file, uid));
	}

    /**
     * 修改
     * @param communication
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseVO updateCommunication(@RequestBody Communication communication){
        return new ResponseVO(800, "success",communicationService.update(communication));
    }

}
