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
import com.chinaxaxt.xtzncms.entity.biz.Business;
import com.chinaxaxt.xtzncms.service.biz.BusinessService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 办公环境
 * @author 吴佳涛
 *
 */
@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    BusinessService businessService;
    /**
     * 根据ID查询办公环境
     * @param uid
     * @return 办公环境实体
     */
    @RequestMapping(value="/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getBusiness(@PathVariable("uid") String uid){
        Business business=new Business();
        if(uid!=null&&!"".equals(uid)){
            business.setUid(uid);
        }
        return new ResponseVO(800, "success",businessService.select(business));
    }
    
    /**
     * 
     * @return 所有办公环境实体
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getBusinesss(){
        return new ResponseVO(800, "success",businessService.select(new Business()));
    }

    /**
     * 
     * @param page
     * @param limit
     * @return 办公环境分页结果
     */
    @RequestMapping(value="/page",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getBusiness(Integer page,Integer limit){
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		pg.setData(businessService.select(new Business()));
    	pg.setNowPage(pa.getPageNum());
    	pg.setTotal(pa.getTotal());
        return ResponseVO.success( pg);
    }
    
    /**
     * 
     * @param file
     * @param name
     * @param remark
     * @param author
     * @return 添加成功为1，失败为0
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertCompanyNews(@RequestParam("file") MultipartFile file,String name,String remark,String author){
    	String contenturl=businessService.singleUpdate(file);
    	Business bu = new Business();
    	bu.setName(name);
    	bu.setRemark(remark);
    	bu.setAuthor(author);
    	bu.setContent(contenturl);
    	try {
			this.businessService.insert(bu);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVO(809,"解析文件出错");
		}
        return ResponseVO.success(null);
    }

//    @RequestMapping(value="",method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseVO updateBusiness(@RequestBody Business business){
//        return new ResponseVO(800, "success",businessService.update(business));
//    }


    /**
     * 
     * @param uid
     * @return 删除结果
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
    	String url = businessService.selectBusinessByUid(uid).getContent();
    	File file =new File("/usr/java/xtzncms/docs/images/"+url);
    	file.delete();
        return new ResponseVO(800, "success",businessService.deleteByUid(uid));
    }
}
