package com.chinaxaxt.xtzncms.controller.biz;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.chinaxaxt.xtzncms.common.vo.PageVO;
import com.chinaxaxt.xtzncms.common.vo.ResponseVO;
import com.chinaxaxt.xtzncms.entity.biz.CompanyCulture;
import com.chinaxaxt.xtzncms.service.biz.BusinessService;
import com.chinaxaxt.xtzncms.service.biz.CompanyCultureService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Controller
@RequestMapping("/companyculture")
public class CompanyCultureController {

    @Autowired
    CompanyCultureService companyCultureService;
    
    @Autowired
    BusinessService businessService;

    /**
     * 根据Id查询
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyCulture(String uid){
        CompanyCulture companyCulture=new CompanyCulture();
        if(uid!=null&&!"".equals(uid)) {
        	companyCulture = companyCultureService.selectCultureByUid(uid);
        }
        return new ResponseVO(800, "success",companyCulture);
    }
    
    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyCultures(){
        return new ResponseVO(800, "success",companyCultureService.select(new CompanyCulture()));
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
    public ResponseVO getCultures(Integer page,Integer limit,String searchType,String searchValue){
    	String name = "name";
    	String remark = "remark";
    	String type = "type";
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		if("".equals(searchType) || "".equals(searchValue)) {
			pg.setData(companyCultureService.select(new CompanyCulture()));
		}else if(name.equals(searchType)) {
			pg.setData(companyCultureService.selectCultureByName(searchValue));
		}else if(remark.equals(searchType)) {
			pg.setData(companyCultureService.selectCultureByRemark(searchValue));
		}else if(type.equals(searchType)) {
			pg.setData(companyCultureService.selectCultureByType(searchValue));
		}
    	pg.setNowPage(pa.getPageNum());
    	pg.setTotal(pa.getTotal());
        return ResponseVO.success( pg);
    }
    
    /**
     * 添加
     * @param file
     * @param name
     * @param remark
     * @param type
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertCompanyCulture(@RequestParam("file") MultipartFile file,String name,String remark,String type,String createTime){
    	String url=businessService.singleUpdate(file);
    	CompanyCulture cc = new CompanyCulture();
    	cc.setName(name);
    	cc.setRemark(remark);
    	cc.setType(type);
    	try {
			cc.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	cc.setImage(url);
    	try {
			this.companyCultureService.insert(cc);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVO(809,"解析文件出错");
		}
        return ResponseVO.success(null);
    }
    
	/**
	 * 修改详情
	 * @param detail
	 * @param uid
	 * @return
	 */
    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO updateCompanyCulture(@RequestBody String detail,String uid){
        return new ResponseVO(800, "success",companyCultureService.update(JSON.parseObject(detail).getString("detail"),uid));
    }

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
    	String url = companyCultureService.selectCultureByUid(uid).getImage();
    	File file =new File("/usr/java/xtzncms/docs/images/"+url);
    	file.delete();
        return new ResponseVO(800, "success",companyCultureService.deleteByUid(uid));
    }
}
