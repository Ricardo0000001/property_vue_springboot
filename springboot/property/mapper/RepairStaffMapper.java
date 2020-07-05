package com.eworldlink.api.property.mapper;

import com.eworldlink.api.property.entity.RepairRecordEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RepairStaffMapper extends Mapper<RepairRecordEntity> {
    public Map<String, Object> getLoginCodeAndTime (Map<String, Object> params);
    public List<Map<String, Object>> getWorkOutline (Map<String, Object> params);
    public RepairRecordEntity getWorkDetail (Map<String, Object> params);
}
