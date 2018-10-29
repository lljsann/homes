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
import com.chinaxaxt.xtzncms.entity.biz.CompanyProfile;
import com.chinaxaxt.xtzncms.service.biz.CommunityService;
import com.chinaxaxt.xtzncms.service.biz.CompanyProfileService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Controller
@RequestMapping("/companyprofile")
public class CompanyProfileController {

    @Autowired
    CompanyProfileService companyProfileService;
    
    @Autowired
    CommunityService communityService;

    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @RequestMapping(value="/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyProfile(@PathVariable("uid") String uid){
        CompanyProfile companyProfile=new CompanyProfile();
        if(uid!=null&&!"".equals(uid)) {
        	companyProfile.setUid(uid);
        }
        return new ResponseVO(800, "success",companyProfileService.select(companyProfile));
    }
    
    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyProfiles(){
        return new ResponseVO(800, "success",companyProfileService.select(new CompanyProfile()));
    }
    
    /**
     * 分页查询与关键字查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value="/page",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getProfiles(Integer page,Integer limit){
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		pg.setData(companyProfileService.select(new CompanyProfile()));
    	pg.setNowPage(pa.getPageNum());
    	pg.setTotal(pa.getTotal());
        return ResponseVO.success( pg);
    }
    
    /**
     * 添加
     * @param files
     * @param content
     * @param slogan
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertProfile(@RequestParam("file[]") MultipartFile[] files,String content,String slogan){
    	List<String> list=communityService.multiUpdate(files);
		StringBuffer sbr=new StringBuffer();
		if(list.size()!=0){
			for (String string : list) {
				sbr.append(string+",");
			}
			sbr.substring(0, sbr.length()-1);
		}
    	CompanyProfile cf = new CompanyProfile();
    	cf.setContent(content);
    	cf.setSlogan(slogan);
    	cf.setImage(sbr.toString());
    	try {
			this.companyProfileService.insert(cf);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseVO(809,"解析文件出错");
		}
        return ResponseVO.success(null);
    }

//    @RequestMapping(value="",method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseVO updateCompanyProfile(@RequestBody CompanyProfile companyProfile){
//        return new ResponseVO(800, "success",companyProfileService.update(companyProfile));
//    }


    /**
     * 根据id删除
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
    	String[] url = companyProfileService.selectProfileByUid(uid).getImage().split(",");
    	for (int i = 0; i < url.length; i++) {
			String string = url[i];
			File file =new File("/usr/java/xtzncms/docs/images/"+string);
	    	file.delete();
		}
        return new ResponseVO(800, "success",companyProfileService.deleteByUid(uid));
    }
}
