package com.eworldlink.api.property.controller;

import com.eworldlink.api.config.controller.BaseController;
import com.eworldlink.api.property.biz.RepairStaffBiz;
import com.eworldlink.api.property.entity.RepairRecordEntity;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * /repairStaff
 *              /login          前端发来的数据是手机号码 返回手机号码对应的验证码和验证码的时间
 *              /workOutline    前端还是发来手机号码和时间 返回手机号码对应的工作概况
 *                              是一个list因为同一个维修工有可能有多条数据条目
 *              /workDetail     前端发来的是数据条目对应的id 返回的是id对应的唯一的数据 类型就是完整数据条目的实体
 */
@RestController
@RequestMapping("/repairStaff")
@Log4j
public class RepairStaffController extends BaseController<RepairStaffBiz, RepairRecordEntity> {

    /**
     * 前端传参
     * 'mobile': this.phoneNumber
     * @param data
     * @return  code
     *          time
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginController(@RequestBody Map<String, Object> data){
        return baseBiz.repairStaffLoginBiz(data);
    }

    /**
     * 前端传参
     * 'mobile': this.$route.params.mobile
     * @param data
     * @return list
     */
    @RequestMapping(value="/workOutline",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> workOutlineController(@RequestBody Map<String, Object> data){
        return baseBiz.repairStaffWorkOutlineBiz(data);
    }

    /**
     * 前端传参
     * 'id': this.$route.params.id
     * @param data
     * @return entity
     */
    @RequestMapping(value="/workDetail",method = RequestMethod.POST)
    @ResponseBody
    public RepairRecordEntity workDetailController(@RequestBody Map<String, Object> data){
        return baseBiz.repairStaffWorkDetailBiz(data);
    }
}
