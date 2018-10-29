package com.chinaxaxt.xtzncms.controller.biz;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinaxaxt.xtzncms.common.vo.PageVO;
import com.chinaxaxt.xtzncms.common.vo.ResponseVO;
import com.chinaxaxt.xtzncms.entity.biz.Cooperation;
import com.chinaxaxt.xtzncms.service.biz.BusinessService;
import com.chinaxaxt.xtzncms.service.biz.CooperationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Controller
@RequestMapping("/cooperation")
public class CooperationController {

    @Autowired
    CooperationService cooperationService;
    
    @Autowired
    BusinessService businessService;

    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @RequestMapping(value="/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCooperation(@PathVariable("uid") String uid){
        Cooperation cooperation=new Cooperation();
        if(uid!=null&&!"".equals(uid)) {
        	cooperation.setUid(uid);
        }
        return new ResponseVO(800, "success",cooperationService.select(cooperation));
    }
    
    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCooperations(){
        return new ResponseVO(800, "success",cooperationService.select(new Cooperation()));
    }

    /**
     * 分页查询与关键字查询
     * @param page
     * @param limit
     * @param searchValue
     * @return
     */
    @RequestMapping(value="/page",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCooperations(Integer page,Integer limit,String searchValue){
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		if("".equals(searchValue)) {
			pg.setData(cooperationService.select(new Cooperation()));
		} else {
			pg.setData(cooperationService.selectCooperationByName(searchValue));
		}
    	pg.setNowPage(pa.getPageNum());
    	pg.setTotal(pa.getTotal());
        return ResponseVO.success( pg);
    }
    
    /**
     * 添加
     * @param file
     * @param name
     * @param url
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertCooperation(@RequestParam("file") MultipartFile file,String name){
    	String photo=businessService.singleUpdate(file);
    	Cooperation co = new Cooperation();
    	co.setName(name);
//    	co.setUrl(url);
    	co.setPhoto(photo);
    	try {
			this.cooperationService.insert(co);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVO(809,"解析文件出错");
		}
        return ResponseVO.success(null);
    }

//    @RequestMapping(value="",method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseVO updateCooperation(@RequestBody Cooperation cooperation){
//        return new ResponseVO(800, "success",cooperationService.update(cooperation));
//    }

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
    	String url = cooperationService.selectCooperationByUid(uid).getPhoto();
    	File file =new File("/usr/java/xtzncms/docs/images/"+url);
    	file.delete();
        return new ResponseVO(800, "success",cooperationService.deleteByUid(uid));
    }
}
