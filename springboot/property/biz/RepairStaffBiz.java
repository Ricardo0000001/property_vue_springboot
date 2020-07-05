package com.eworldlink.api.property.biz;

import com.eworldlink.api.config.biz.BaseBiz;
import com.eworldlink.api.property.entity.RepairRecordEntity;
import com.eworldlink.api.property.mapper.RepairStaffMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Log4j
@Transactional(rollbackFor = Exception.class)
public class RepairStaffBiz extends BaseBiz<RepairStaffMapper, RepairRecordEntity> {
    /**
     * login
     * @param params
     * @return
     */
    public Map<String, Object> repairStaffLoginBiz(Map<String, Object> params){
        return mapper.getLoginCodeAndTime(params);
    }

    /**
     * workOutline
     * @param params
     * @return
     */
    public List<Map<String, Object>> repairStaffWorkOutlineBiz(Map<String, Object> params){
        return mapper.getWorkOutline(params);
    }

    /**
     * work
     * @param params
     * @return
     */
    public RepairRecordEntity repairStaffWorkDetailBiz(Map<String, Object> params){
        return mapper.getWorkDetail(params);
    }
}
