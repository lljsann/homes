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
import com.chinaxaxt.xtzncms.entity.biz.CompanyAptitude;
import com.chinaxaxt.xtzncms.service.biz.BusinessService;
import com.chinaxaxt.xtzncms.service.biz.CompanyAptitudeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Controller
@RequestMapping("/companyaptitude")
public class CompanyAptitudeController {

    @Autowired
    CompanyAptitudeService companyAptitudeService;
    
    @Autowired
    BusinessService businessService;

    /**
     * 根据Id查询
     * @param uid
     * @return
     */
    @RequestMapping(value="/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyAptitude(@PathVariable("uid") String uid){
        CompanyAptitude companyAptitude=new CompanyAptitude();
        if(uid!=null&&!"".equals(uid)) {
        	companyAptitude.setUid(uid);
        }
        return new ResponseVO(800, "success",companyAptitudeService.select(companyAptitude));
    }
    
    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyAptitudes(){
        return new ResponseVO(800, "success",companyAptitudeService.select(new CompanyAptitude()));
    }

    /**
     * 分页查询和关键字查询
     * @param page
     * @param limit
     * @param searchType
     * @param searchValue
     * @return
     */
    @RequestMapping(value="/page",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getAptitudes(Integer page,Integer limit,String searchType,String searchValue){
    	String title = "title";
    	String detail = "detail";
    	String type = "type";
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		if("".equals(searchType) || "".equals(searchValue)) {
			pg.setData(companyAptitudeService.select(new CompanyAptitude()));
		}else if(title.equals(searchType)) {
			pg.setData(companyAptitudeService.selectAptitudeByTitle(searchValue));
		}else if(detail.equals(searchType)) {
			pg.setData(companyAptitudeService.selectAptitudeByDetail(searchValue));
		}else if(type.equals(searchType)) {
			pg.setData(companyAptitudeService.selectAptitudeByType(searchValue));
		}
    	pg.setNowPage(pa.getPageNum());
    	pg.setTotal(pa.getTotal());
        return ResponseVO.success( pg);
    }
    
    /**
     * 添加
     * @param file
     * @param title
     * @param detail
     * @param type
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertAptitude(@RequestParam("file") MultipartFile file,String title,String detail,String type){
    	String url=businessService.singleUpdate(file);
    	CompanyAptitude ca = new CompanyAptitude();
    	ca.setTitle(title);
    	ca.setDetail(detail);
    	ca.setType(type);
    	ca.setImage(url);
    	try {
			this.companyAptitudeService.insert(ca);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVO(809,"解析文件出错");
		}
        return ResponseVO.success(null);
    }
    
//    @RequestMapping(value="",method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseVO updateCompanyAptitude(@RequestBody CompanyAptitude companyAptitude){
//        return new ResponseVO(800, "success",companyAptitudeService.update(companyAptitude));
//    }

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
    	String url = companyAptitudeService.selectAptitudeByUid(uid).getImage();
    	File file =new File("/usr/java/xtzncms/docs/images/"+url);
    	file.delete();
        return new ResponseVO(800, "success",companyAptitudeService.deleteByUid(uid));
    }

}
