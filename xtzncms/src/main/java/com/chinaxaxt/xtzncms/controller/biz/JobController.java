package com.chinaxaxt.xtzncms.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinaxaxt.xtzncms.common.vo.PageVO;
import com.chinaxaxt.xtzncms.common.vo.ResponseVO;
import com.chinaxaxt.xtzncms.entity.biz.Job;
import com.chinaxaxt.xtzncms.service.biz.JobService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author 吴佳涛
 * 2018-6-29
 */
@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;

    /**
     * 根据id查询
     * @param uid
     * @return
     */
    @RequestMapping(value="/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getJob(@PathVariable("uid") String uid){
        Job job=new Job();
        if(uid!=null&&!"".equals(uid)) {
        	job.setUid(uid);
        }
        return new ResponseVO(800, "success",jobService.select(job));
    }
    
    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO getJobs(){
        return new ResponseVO(800, "success",jobService.select(new Job()));
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
    public ResponseVO getJobs(Integer page,Integer limit,String searchType,String searchValue){
    	String name = "name";
    	String address = "address";
    	Page<Object> pa = PageHelper.startPage(page, limit, "create_time");
    	PageVO pg = new PageVO();
		if("".equals(searchType) || "".equals(searchValue)) {
			pg.setData(jobService.select(new Job()));
		}else if(name.equals(searchType)) {
			pg.setData(jobService.selectJobsByName(searchValue));
		}else if(address.equals(searchType)) {
			pg.setData(jobService.selectJobsByAddress(searchValue));
		}
    	pg.setNowPage(pa.getPageNum());
    	pg.setTotal(pa.getTotal());
        return ResponseVO.success( pg);
    }

    /**
     * 添加
     * @param name
     * @param address
     * @param duty
     * @param demand
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO insertJob(String name,String address,String duty,String demand){
    	Job job = new Job();
    	job.setName(name);
    	job.setAddress(address);
    	job.setDuty(duty);
    	job.setDemand(demand);
		this.jobService.insert(job);
        return ResponseVO.success(null);
    }

//    @RequestMapping(value="",method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseVO updateJob(@RequestBody Job job){
//        return new ResponseVO(800, "success",jobService.update(job));
//    }

    /**
     * 根据id删除
     * @param uid
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseVO deleteByUid(String uid){
        return new ResponseVO(800, "success",jobService.deleteByUid(uid));
    }

}
