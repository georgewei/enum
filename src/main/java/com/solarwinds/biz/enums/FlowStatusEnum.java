package com.solarwinds.biz.enums;

import com.solarwinds.util.enums.IStatus;
import com.solarwinds.util.enums.IStatusEnum;

public enum FlowStatusEnum implements IStatusEnum {
    @IStatus(value = 1, desc = "未提交") UNSUBMITTED,
    @IStatus(value = 2, desc = "已提交") SUBMITTED,
    @IStatus(value = 5, desc = "审核通过") CHECKED,
    @IStatus(value = 6, desc = "审核失败") CHECK_FAILED;
}