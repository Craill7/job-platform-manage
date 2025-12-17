package com.ruoyi.recruit.service;

import com.ruoyi.recruit.domain.ApplicationStatus;

public interface IApplicationStatusService {
    ApplicationStatus selectByCode(String code);
}
