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
import com.chinaxaxt.xtzncms.entity.biz.CompanyNews;
import com.chinaxaxt.xtzncms.service.biz.BusinessService;
import com.chinaxaxt.xtzncms.service.biz.CompanyNewsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
@Controller
@RequestMapping("/companynews")
public class CompanyNewsController {

    @Autowired
    CompanyNewsService companyNewsService;
    
    @Autowired
    BusinessService businessService;
    
    /**
     * 根据Id查询
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyNews(String uid){
        CompanyNews companyNews=new CompanyNews();
        if(uid!=null&&!"".equals(uid)) {
        	companyNews = companyNewsService.selectNewsByUid(uid);
        }
        return new ResponseVO(800, "success",companyNews);
    }
    
    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyNewss(){
        return new ResponseVO(800, "success",companyNewsService.select(new CompanyNews()));
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
    public ResponseVO getFiles(Integer page,Integer limit,String searchType,String searchValue){
    	String title = "title";
    	String remark = "remark";
    	String author = "author";
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		if("".equals(searchType) || "".equals(searchValue)) {
			pg.setData(companyNewsService.select(new CompanyNews()));
		}else if(title.equals(searchType)) {
			pg.setData(companyNewsService.selectNewsByTitle(searchValue));
		}else if(remark.equals(searchType)) {
			pg.setData(companyNewsService.selectNewsByRemark(searchValue));
		}else if(author.equals(searchType)) {
			pg.setData(companyNewsService.selectNewsByAuthor(searchValue));
		}
    	pg.setNowPage(pa.getPageNum());
    	pg.setTotal(pa.getTotal());
        return ResponseVO.success( pg);
    }
    
    /**
     * 添加
     * @param file
     * @param title
     * @param remark
     * @param author
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertCompanyNews(@RequestParam("file") MultipartFile file,String title,String remark,String createTime,String type,String author){
    	String url=businessService.singleUpdate(file);
    	CompanyNews cn = new CompanyNews();
    	cn.setTitle(title);
    	cn.setRemark(remark);
    	try {
			cn.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
    	cn.setType(type);
    	cn.setAuthor(author);
    	cn.setImageUrl(url);
    	try {
			this.companyNewsService.insert(cn);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVO(809,"解析文件出错");
		}
        return ResponseVO.success(null);
    }

    /**
     * 修改详情
     * @param context
     * @param uid
     * @return
     */
    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO updateCompanyNews(@RequestBody String context,String uid){
    	System.out.println(context);
        return new ResponseVO(800, "success",companyNewsService.update(JSON.parseObject(context).getString("context"),uid));
    }

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
    	String url = companyNewsService.selectNewsByUid(uid).getImageUrl();
    	File file =new File("/usr/java/xtzncms/docs/images/"+url);
    	file.delete();
        return new ResponseVO(800, "success",companyNewsService.deleteByUid(uid));
    }
}
