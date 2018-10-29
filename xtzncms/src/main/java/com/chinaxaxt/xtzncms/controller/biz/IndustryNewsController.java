package com.chinaxaxt.xtzncms.controller.biz;


import java.io.File;

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
import com.chinaxaxt.xtzncms.entity.biz.IndustryNews;
import com.chinaxaxt.xtzncms.service.biz.BusinessService;
import com.chinaxaxt.xtzncms.service.biz.IndustryNewsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author 吴佳涛
 * 2018-6-25
 */
@Controller
@RequestMapping("/industrynews")
public class IndustryNewsController {

    @Autowired
    IndustryNewsService industryNewsService;
    
    @Autowired
    BusinessService businessService;

    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getIndustryNews(String uid){
        IndustryNews industryNews=new IndustryNews();
        if(uid!=null&&!"".equals(uid)) {
        	industryNews = industryNewsService.selectNewsByUid(uid);
        }
        return new ResponseVO(800, "success",industryNews);
    }
    
    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getIndustryNews(){
        return new ResponseVO(800, "success",industryNewsService.select(new IndustryNews()));
    }
    
    /**
     * 分页查询与关键字查询
     * @param page
     * @param limit
     * @param searchType
     * @param searchValue
     * @return
     */
    @RequestMapping(value="/page",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getIndustryNews(Integer page,Integer limit,String searchType,String searchValue){
    	String title = "title";
    	String remark = "remark";
    	String author = "author";
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		if("".equals(searchType) || "".equals(searchValue)) {
			pg.setData(industryNewsService.select(new IndustryNews()));
		}else if(title.equals(searchType)) {
			pg.setData(industryNewsService.selectNewsByTitle(searchValue));
		}else if(remark.equals(searchType)) {
			pg.setData(industryNewsService.selectNewsByRemark(searchValue));
		}else if(author.equals(searchType)) {
			pg.setData(industryNewsService.selectNewsByAuthor(searchValue));
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
    public ResponseVO insertIndustryNews(@RequestParam("file") MultipartFile file,String title,String remark,String author){
    	String url=businessService.singleUpdate(file);
    	IndustryNews in = new IndustryNews();
    	in.setTitle(title);
    	in.setRemark(remark);
    	in.setAuthor(author);
    	in.setImageUrl(url);
    	try {
			this.industryNewsService.insert(in);
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
    public ResponseVO updateIndustryNews(@RequestBody String context,String uid){
        return new ResponseVO(800, "success",industryNewsService.update(JSON.parseObject(context).getString("context"),uid));
    }

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
    	String url = industryNewsService.selectNewsByUid(uid).getImageUrl();
    	File file =new File("/usr/java/xtzncms/docs/images/"+url);
    	file.delete();
        return new ResponseVO(800, "success",industryNewsService.deleteByUid(uid));
    }
}
