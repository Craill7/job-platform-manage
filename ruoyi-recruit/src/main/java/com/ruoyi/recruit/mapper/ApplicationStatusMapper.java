package com.ruoyi.recruit.mapper;

import com.ruoyi.recruit.domain.ApplicationStatus;

public interface ApplicationStatusMapper {
    ApplicationStatus selectByCode(String code);
}
