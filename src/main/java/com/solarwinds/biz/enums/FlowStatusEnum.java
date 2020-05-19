package com.solarwinds.biz.enums;

import com.solarwinds.util.enums.IStatus;
import com.solarwinds.util.enums.IStatusEnum;

public enum FlowStatusEnum implements IStatusEnum {
    @IStatus(value = 1, desc = "Unsubmitted") UNSUBMITTED,
    @IStatus(value = 2, desc = "Submitted") SUBMITTED,
    @IStatus(value = 5, desc = "Checked") CHECKED,
    @IStatus(value = 6, desc = "Check Failed") CHECK_FAILED
}