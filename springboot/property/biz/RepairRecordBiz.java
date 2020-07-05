package com.eworldlink.api.property.biz;

import com.eworldlink.api.config.biz.BaseBiz;
import com.eworldlink.api.property.entity.RepairRecordEntity;
import com.eworldlink.api.property.mapper.RepairRecordMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
@Transactional(rollbackFor = Exception.class)
public class RepairRecordBiz extends BaseBiz<RepairRecordMapper, RepairRecordEntity> {}
