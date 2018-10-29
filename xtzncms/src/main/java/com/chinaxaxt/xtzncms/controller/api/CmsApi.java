 package com.chinaxaxt.xtzncms.controller.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chinaxaxt.xtzncms.common.vo.ResponseVO;
import com.chinaxaxt.xtzncms.entity.biz.Business;
import com.chinaxaxt.xtzncms.entity.biz.Communication;
import com.chinaxaxt.xtzncms.entity.biz.Community;
import com.chinaxaxt.xtzncms.entity.biz.CompanyAptitude;
import com.chinaxaxt.xtzncms.entity.biz.CompanyCulture;
import com.chinaxaxt.xtzncms.entity.biz.CompanyNews;
import com.chinaxaxt.xtzncms.entity.biz.CompanyProfile;
import com.chinaxaxt.xtzncms.entity.biz.Cooperation;
import com.chinaxaxt.xtzncms.entity.biz.Government;
import com.chinaxaxt.xtzncms.entity.biz.Job;
import com.chinaxaxt.xtzncms.entity.biz.Police;
import com.chinaxaxt.xtzncms.entity.biz.Work;
import com.chinaxaxt.xtzncms.entity.dto.CommunityDto;
import com.chinaxaxt.xtzncms.entity.dto.CompanyNewsDto;
import com.chinaxaxt.xtzncms.entity.dto.CompanyProfileDto;
import com.chinaxaxt.xtzncms.entity.dto.GovernmentDto;
import com.chinaxaxt.xtzncms.entity.dto.JobDto;
import com.chinaxaxt.xtzncms.entity.dto.PoliceDto;
import com.chinaxaxt.xtzncms.entity.dto.WorkDto;
import com.chinaxaxt.xtzncms.service.biz.BusinessService;
import com.chinaxaxt.xtzncms.service.biz.CommunicationService;
import com.chinaxaxt.xtzncms.service.biz.CommunityService;
import com.chinaxaxt.xtzncms.service.biz.CompanyAptitudeService;
import com.chinaxaxt.xtzncms.service.biz.CompanyCultureService;
import com.chinaxaxt.xtzncms.service.biz.CompanyNewsService;
import com.chinaxaxt.xtzncms.service.biz.CompanyProfileService;
import com.chinaxaxt.xtzncms.service.biz.CompanyStructService;
import com.chinaxaxt.xtzncms.service.biz.CooperationService;
import com.chinaxaxt.xtzncms.service.biz.GovernmentService;
import com.chinaxaxt.xtzncms.service.biz.IndustryNewsService;
import com.chinaxaxt.xtzncms.service.biz.InfoService;
import com.chinaxaxt.xtzncms.service.biz.JobService;
import com.chinaxaxt.xtzncms.service.biz.PoliceService;
import com.chinaxaxt.xtzncms.service.biz.WorkService;

/**
 * 接口类
 * @author 吴佳涛
 * 2018-7-16
 */
@RestController
@RequestMapping("/api")
public class CmsApi {
	
	@Autowired
    InfoService infoService;
	
	@Autowired
    BusinessService businessService;
	
	@Autowired
    WorkService workService;
	
	@Autowired
    CooperationService cooperationService;
	
	@Autowired
    CompanyProfileService companyProfileService;
	
	@Autowired
    CompanyStructService companyStructService;
	
	@Autowired
    CompanyCultureService companyCultureService;
	
	@Autowired
    CompanyAptitudeService companyAptitudeService;
	
	@Autowired
    CompanyNewsService companyNewsService;
	
	@Autowired
    IndustryNewsService industryNewsService;
	
	@Autowired
    PoliceService policeService;
	
	@Autowired
    GovernmentService governmentService;
	
	@Autowired
    CommunityService communityService;
	
	@Autowired
    JobService jobService;
	
	@Autowired
    CommunicationService communicationService;
    
    /**
     * 查询所有新闻资讯
     * @return
     */
    @RequestMapping(value="/info/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getInfos(){
    	List<CompanyNews> cnl = companyNewsService.selectByTime();
    	List<CompanyNewsDto> cnld = new ArrayList<CompanyNewsDto>();
    	for (CompanyNews companyNews : cnl) {
			CompanyNewsDto cnd = new CompanyNewsDto();
			cnd.setUid(companyNews.getUid());
			cnd.setTitle(companyNews.getTitle());
			cnd.setImageUrl(companyNews.getImageUrl());
//			if(companyNews.getRemark().length()>=66) {
//				cnd.setRemark(companyNews.getRemark().substring(0, 66));
//			}
			cnd.setRemark(companyNews.getRemark());
//			cnd.setAuthor(companyNews.getAuthor());
//			cnd.setType(companyNews.getType());
//			cnd.setContext(companyNews.getContext());
//			cnd.setHitCount(companyNews.getHitCount());
//			cnd.setApplaudCount(companyNews.getApplaudCount());
			cnd.setCreateTime(new SimpleDateFormat("MM月dd日").format(companyNews.getCreateTime()));
//			System.out.println(new SimpleDateFormat("MM月dd日").format(companyNews.getCreateTime()));
			cnld.add(cnd);
		}
        return new ResponseVO(800, "success",cnld);
    }
    
    /**
     * 查询办公环境
     * @return 所有办公环境实体
     */
    @RequestMapping(value="/business/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getBusinesss(){
        return new ResponseVO(800, "success",businessService.select(new Business()));
    }
    
//    /**
//     * 根据Id查询智能模块
//     * @param uid
//     * @return
//     */
//    @RequestMapping(value="/work",method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseVO getWork(String uid){
//        Work work=new Work();
//        if(uid!=null&&!"".equals(uid)) {
//        	work.setUid(uid);
//        }
//        return new ResponseVO(800, "success",workService.select(work));
//    }
    
    /**
     * 查询所有智能模块
     * @return
     */
    @RequestMapping(value="/work/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getWorks(){
    	List<Work> wlist = workService.select(new Work());
    	List<WorkDto> wdlist = new ArrayList<WorkDto>();
    	for (Work work : wlist) {
			WorkDto wd = new WorkDto();
			wd.setUid(work.getUid());
			wd.setName(work.getName());
			wd.setAuthor(work.getAuthor());
			wd.setRemark(work.getRemark());
			wd.setImage(work.getImage().split(","));
			wd.setCreateTime(work.getCreateTime());
			wdlist.add(wd);
		}
        return new ResponseVO(800, "success",wdlist);
    }
    
    /**
     * 根据id查询合作单位
     * @param uid
     * @return
     */
//    @RequestMapping(value="/cooperation",method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseVO getCooperation(String uid){
//        Cooperation cooperation=new Cooperation();
//        if(uid!=null&&!"".equals(uid)) {
//        	cooperation.setUid(uid);
//        }
//        return new ResponseVO(800, "success",cooperationService.select(cooperation));
//    }
    
    /**
     * 查询所有合作单位
     * @return
     */
    @RequestMapping(value="/cooperation/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCooperations(){
        return new ResponseVO(800, "success",cooperationService.selectBySort());
    }
    
    /**
     * 查询公司简介
     * @return
     */
    @RequestMapping(value="/companyprofile/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyProfiles(){
    	List<CompanyProfile> cplist = companyProfileService.select(new CompanyProfile());
    	CompanyProfileDto cpd = new CompanyProfileDto();
    	cpd.setUid(cplist.get(0).getUid());
    	cpd.setContent(cplist.get(0).getContent().split("/"));
//    	for (int i = 0; i < cpd.getContent().length; i++) {
//			System.out.println(cpd.getContent()[i]);
//		}
    	cpd.setImage(cplist.get(0).getImage().split(","));
    	cpd.setCreateTime(cplist.get(0).getCreateTime());
        return new ResponseVO(800, "success",cpd);
    }
    
    /**
     * 查询公司架构
     * @return
     */
//    @RequestMapping(value="/companystruct/list",method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseVO getCompanyStructs(){
//    	List<CompanyStruct> cslist = companyStructService.select(new CompanyStruct());
//    	CompanyStructDto csd = new CompanyStructDto();
//    	csd.setAuthor(cslist.get(0).getAuthor());
//    	csd.setUid(cslist.get(0).getUid());
//    	csd.setPhoto(cslist.get(0).getPhoto());
//    	csd.setImage(cslist.get(0).getImage().split(","));
//    	csd.setCreateTime(cslist.get(0).getCreateTime());
//        return new ResponseVO(800, "success",csd);
//    }
    
    /**
     * 根据Id查询团队建设
     * @param uid
     * @return
     */
    @RequestMapping(value="/companyProDetail",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyCultureByUid(String uid){
        CompanyCulture companyCulture=new CompanyCulture();
        if(uid!=null&&!"".equals(uid)) {
        	companyCulture = companyCultureService.selectCultureByUid(uid);
        }
        return new ResponseVO(800, "success",companyCulture);
    }
    
    /**
     * 查询所有团队建设
     */
    @RequestMapping(value="/companyPro",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyCulture(){
    	String type = "团队建设";
        return new ResponseVO(800, "success",companyCultureService.selectPro(type));
    }
    
    /**
     * 查询企业文化
     * @param uid
     * @return
     */
    @RequestMapping(value="/companyculture",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyCultureByType(){
    	String type = "企业文化";
        return new ResponseVO(800, "success",companyCultureService.selectCultureByType(type));
    }
    
    /**
     * 根据Id查询企业荣誉
     * @param uid
     * @return
     */
//    @RequestMapping(value="/companyaptitude",method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseVO getCompanyAptitude(String uid){
//        CompanyAptitude companyAptitude=new CompanyAptitude();
//        if(uid!=null&&!"".equals(uid)) {
//        	companyAptitude.setUid(uid);
//        }
//        return new ResponseVO(800, "success",companyAptitudeService.select(companyAptitude));
//    }
    
    /**
     * 查询所有企业荣誉
     * @return
     */
    @RequestMapping(value="/companyaptitude/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyAptitudes(){
        return new ResponseVO(800, "success",companyAptitudeService.select(new CompanyAptitude()));
    }
    
    /**
     * 根据Id查询公司新闻详情并更新阅读次数
     * @param uid
     * @return
     */
    @RequestMapping(value="/companynews",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyNews(String uid){
        CompanyNews companyNews=new CompanyNews();
        if(uid!=null&&!"".equals(uid)) {
        	companyNewsService.updateHitCount(uid);
        	companyNews = companyNewsService.selectNewsByUid(uid);
        }
        return new ResponseVO(800, "success",companyNews);
    }
    
    /**
     * 根据Id查询公司新闻详情并更新点赞次数
     * @param uid
     * @return
     */
    @RequestMapping(value="/companynews/applaudcount",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO updateApplaudCount(String uid) {
    	CompanyNews companyNews=new CompanyNews();
    	if(uid!=null&&!"".equals(uid)) {
        	companyNewsService.updateApplaudCount(uid);
        	companyNews = companyNewsService.selectNewsByUid(uid);
//        	System.out.println(companyNews.getContext());
        }
    	return new ResponseVO(800, "success",companyNews);
    }
    
    /**
     * 查询所有公司新闻
     * @return
     */
    @RequestMapping(value="/companynews/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCompanyNewss(){
        return new ResponseVO(800, "success",companyNewsService.selectAll(new CompanyNews()));
    }
    
    /**
     * 查询所有智慧警务产品
     * @return
     */
    @RequestMapping(value="/police/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getPolices(){
    	List<Police> pl = policeService.selectBySort();
    	List<PoliceDto> pld = new ArrayList<PoliceDto>();
    	for (Police police : pl) {
			PoliceDto pd = new PoliceDto();
			pd.setUid(police.getUid());
			pd.setSynopsis(police.getSynopsis());
			pd.setName(police.getName());
			pd.setContent(police.getContent());
//			pd.setVirtue(police.getVirtue().split("、"));
			if(police.getImageName()!=null && !"".equals(police.getImageName())) {
				pd.setImageName(police.getImageName().split("、"));
			}
			if(police.getImage()!=null && !"".equals(police.getImage())) {
				pd.setImage(police.getImage().split(","));
			}
			pd.setUrl(police.getUrl());
			pd.setCreateTime(police.getCreateTime());
			pld.add(pd);
		}
        return new ResponseVO(800, "success",pld);
    }
    
    /**
     * 查询所有智慧政务
     * @return
     */
    @RequestMapping(value="/government/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getGovernments(){
    	List<Government> gl = governmentService.selectBySort();
    	List<GovernmentDto> gld = new ArrayList<GovernmentDto>();
    	for (Government government : gl) {
    		GovernmentDto gd = new GovernmentDto();
    		gd.setUid(government.getUid());
    		gd.setName(government.getName());
    		gd.setSynopsis(government.getSynopsis());
    		gd.setContent(government.getContent());
//    		gd.setVirtue(government.getVirtue().split("、"));
    		if(government.getImageName()!=null && !"".equals(government.getImageName())) {
    			gd.setImageName(government.getImageName().split("、"));
    		}
    		if(government.getImage()!=null && !"".equals(government.getImage())) {
    			gd.setImage(government.getImage().split(","));
    		}
    		gd.setUrl(government.getUrl());
    		gd.setCreateTime(government.getCreateTime());
    		gld.add(gd);
		}
        return new ResponseVO(800, "success",gld);
    }
    
    /**
     * 查询所有智慧社区产品
     * @return
     */
    @RequestMapping(value="/community/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getCommunitys(){
    	List<Community> cl = communityService.selectBySort();
    	List<CommunityDto> cld = new ArrayList<CommunityDto>();
    	for (Community community : cl) {
    		CommunityDto cd = new CommunityDto();
    		cd.setUid(community.getUid());
    		cd.setName(community.getName());
    		cd.setSynopsis(community.getSynopsis());
    		cd.setContent(community.getContent());
//    		cd.setVirtue(community.getVirtue().split("、"));
    		if(community.getImageName()!=null && !"".equals(community.getImageName())) {
    			cd.setImageName(community.getImageName().split("、"));
    		}
    		if(community.getImage()!=null && !"".equals(community.getImage())) {
    			cd.setImage(community.getImage().split(","));
    		}
    		cd.setUrl(community.getUrl());
    		cd.setCreateTime(community.getCreateTime());
    		cld.add(cd);
		}
        return new ResponseVO(800, "success",cld);
    }
    
    /**
     * 查询所有岗位
     * @return
     */
    @RequestMapping(value="/job/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getJobs(){
    	List<Job> li = jobService.select(new Job());
    	List<JobDto> dli = new ArrayList<JobDto>();
    	for (Job job : li) {
    		JobDto jd = new JobDto();
    		jd.setName(job.getName());
    		jd.setAddress(job.getAddress());
			jd.setDuty(job.getDuty().split("；"));
			jd.setDemand(job.getDemand().split("；"));
			dli.add(jd);
		}
        return new ResponseVO(800, "success",dli);
    }
    
    /**
     * 查询公司联系方式
     * @return
     */
    @RequestMapping(value="/communication/mine",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getContact(){
        return new ResponseVO(800, "success",communicationService.select(new Communication()));
    }
}
