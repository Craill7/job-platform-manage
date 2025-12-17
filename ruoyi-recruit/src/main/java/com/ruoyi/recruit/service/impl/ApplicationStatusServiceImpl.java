package com.ruoyi.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recruit.domain.ApplicationStatus;
import com.ruoyi.recruit.mapper.ApplicationStatusMapper;
import com.ruoyi.recruit.service.IApplicationStatusService;

@Service
public class ApplicationStatusServiceImpl implements IApplicationStatusService {

    @Autowired
    private ApplicationStatusMapper applicationStatusMapper;

    @Override
    public ApplicationStatus selectByCode(String code) {
        return applicationStatusMapper.selectByCode(code);
    }
}
