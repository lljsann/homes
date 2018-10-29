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
import com.chinaxaxt.xtzncms.entity.biz.CompanyStruct;
import com.chinaxaxt.xtzncms.service.biz.BusinessService;
import com.chinaxaxt.xtzncms.service.biz.CommunityService;
import com.chinaxaxt.xtzncms.service.biz.CompanyStructService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author 吴佳涛
 * 2018-7-4
 */
@Controller
@RequestMapping("/companystruct")
public class CompanyStructController {

    @Autowired
    CompanyStructService companyStructService;
    
    @Autowired
    BusinessService businessService;
    
    @Autowired
    CommunityService communityService;

    /**
     * 根据Id查询
     * @param uid
     * @return
     */
    @RequestMapping(value="/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyStruct(@PathVariable("uid") String uid){
        CompanyStruct companyStruct=new CompanyStruct();
        if(uid!=null&&!"".equals(uid)) {
        	companyStruct.setUid(uid);
        }
        return new ResponseVO(800, "success",companyStructService.select(companyStruct));
    }
    
    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyStructs(){
        return new ResponseVO(800, "success",companyStructService.select(new CompanyStruct()));
    }

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value="/page",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyStruct(Integer page,Integer limit){
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		pg.setData(companyStructService.select(new CompanyStruct()));
    	pg.setNowPage(pa.getPageNum());
    	pg.setTotal(pa.getTotal());
        return ResponseVO.success( pg);
    }
    
    /**
     * 添加
     * @param files
     * @param file
     * @param author
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertCompanyNews(@RequestParam("file[]") MultipartFile[] files,@RequestParam("file") MultipartFile file,String author){
    	String photourl=businessService.singleUpdate(file);
    	List<String> list=communityService.multiUpdate(files);
		StringBuffer sbr=new StringBuffer();
		if(list.size()!=0){
			for (String string : list) {
				sbr.append(string+",");
			}
			sbr.substring(0, sbr.length()-1);
		}
    	CompanyStruct cs = new CompanyStruct();
    	cs.setAuthor(author);
    	cs.setPhoto(photourl);
    	cs.setImage(sbr.toString());
    	try {
			this.companyStructService.insert(cs);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVO(809,"解析文件出错");
		}
        return ResponseVO.success(null);
    }


//    @RequestMapping(value="",method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseVO updateCompanyStruct(@RequestBody CompanyStruct companyStruct){
//        return new ResponseVO(800, "success",companyStructService.update(companyStruct));
//    }


    /**
     * 根据id删除
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
    	String url = companyStructService.selectCompanyStructByUid(uid).getPhoto();
    	File file =new File("/usr/java/xtzncms/docs/images/"+url);
    	file.delete();
    	String[] image = companyStructService.selectCompanyStructByUid(uid).getImage().split(",");
    	for (int i = 0; i < image.length; i++) {
			String string = image[i];
			File files =new File("/usr/java/xtzncms/docs/images/"+string);
	    	files.delete();
		}
        return new ResponseVO(800, "success",companyStructService.deleteByUid(uid));
    }
}
