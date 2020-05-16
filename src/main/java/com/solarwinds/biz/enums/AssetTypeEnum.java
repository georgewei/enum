package com.solarwinds.biz.enums;

import com.solarwinds.util.enums.IType;
import com.solarwinds.util.enums.ITypeEnum;

public enum AssetTypeEnum implements ITypeEnum {
    @IType(value = "r", desc = "creditor's rights") RIGHTS,
    @IType(value = "h", desc = "house") HOUSE,
    @IType(value = "s", desc = "stock") STOCK,
    @IType(value = "c", desc = "construction in progress") CIP;
}