package com.chinaxaxt.xtzncms.controller.biz;

import java.io.File;
import java.util.List;

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
import com.chinaxaxt.xtzncms.entity.biz.Work;
import com.chinaxaxt.xtzncms.service.biz.CommunityService;
import com.chinaxaxt.xtzncms.service.biz.WorkService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    WorkService workService;
    
    @Autowired
    CommunityService communityService;

    /**
     * 根据Id查询
     * @param uid
     * @return
     */
    @RequestMapping(value="/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getWork(@PathVariable("uid") String uid){
        Work work=new Work();
        if(uid!=null&&!"".equals(uid)) {
        	work.setUid(uid);
        }
        return new ResponseVO(800, "success",workService.select(work));
    }
    
    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getWorks(){
        return new ResponseVO(800, "success",workService.select(new Work()));
    }


    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value="/page",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getBusiness(Integer page,Integer limit){
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		pg.setData(workService.select(new Work()));
    	pg.setNowPage(pa.getPageNum());
    	pg.setTotal(pa.getTotal());
        return ResponseVO.success( pg);
    }
    
    /**
     * 添加
     * @param files
     * @param name
     * @param remark
     * @param author
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertCompanyNews(@RequestParam("file[]") MultipartFile[] files,String name,String remark,String author){
    	List<String> list=communityService.multiUpdate(files);
		StringBuffer sbr=new StringBuffer();
		if(list.size()!=0){
			for (String string : list) {
				sbr.append(string+",");
			}
			sbr.substring(0, sbr.length()-1);
		}
    	Work work = new Work();
    	work.setName(name);
    	work.setRemark(remark);
    	work.setAuthor(author);
    	work.setImage(sbr.toString());
    	try {
			this.workService.insert(work);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVO(809,"解析文件出错");
		}
        return ResponseVO.success(null);
    }


//    @RequestMapping(value="",method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseVO updateWork(@RequestBody Work work){
//        return new ResponseVO(800, "success",workService.update(work));
//    }


    /**
     * 根据id删除
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
    	String[] image = workService.selectWorkByUid(uid).getImage().split(",");
    	for (int i = 0; i < image.length; i++) {
			String string = image[i];
			File file = new File("/usr/java/xtzncms/docs/images/"+string);
			file.delete();
		}
        return new ResponseVO(800, "success",workService.deleteByUid(uid));
    }
}
