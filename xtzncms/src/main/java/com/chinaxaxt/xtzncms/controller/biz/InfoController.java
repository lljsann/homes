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
import com.chinaxaxt.xtzncms.entity.biz.Info;
import com.chinaxaxt.xtzncms.service.biz.BusinessService;
import com.chinaxaxt.xtzncms.service.biz.InfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Controller
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;
    
    @Autowired
    BusinessService businessService;

    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @RequestMapping(value="/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getInfo(@PathVariable("uid") String uid){
        Info info=new Info();
        if(uid!=null&&!"".equals(uid)) {
        	info.setUid(uid);
        }
        return new ResponseVO(800, "success",infoService.select(info));
    }
    
    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getInfos(){
        return new ResponseVO(800, "success",infoService.select(new Info()));
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
    public ResponseVO getInfos(Integer page,Integer limit,String searchType,String searchValue){
    	String title = "title";
    	String remark = "remark";
    	String author = "author";
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		if("".equals(searchType) || "".equals(searchValue)) {
			pg.setData(infoService.select(new Info()));
		}else if(title.equals(searchType)) {
			pg.setData(infoService.selectInfoByTitle(searchValue));
		}else if(remark.equals(searchType)) {
			pg.setData(infoService.selectInfoByRemark(searchValue));
		}else if(author.equals(searchType)) {
			pg.setData(infoService.selectInfoByAuthor(searchValue));
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
    public ResponseVO insertInfo(@RequestParam("file") MultipartFile file,String title,String remark,String author){
    	String url=businessService.singleUpdate(file);
    	Info in = new Info();
    	in.setTitle(title);
    	in.setRemark(remark);
    	in.setAuthor(author);
    	in.setImageUrl(url);
    	try {
			this.infoService.insert(in);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVO(809,"解析文件出错");
		}
        return ResponseVO.success(null);
    }
    

//    @RequestMapping(value="",method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseVO updateInfo(@RequestBody Info info){
//        return new ResponseVO(800, "success",infoService.update(info));
//    }


    /**
     * 根据id删除
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
    	String url = infoService.selectInfoByUid(uid).getImageUrl();
    	File file =new File("/usr/java/xtzncms/docs/images/"+url);
    	file.delete();
        return new ResponseVO(800, "success",infoService.deleteByUid(uid));
    }
}
