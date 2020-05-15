package com.solarwinds.biz.enums;

import com.solarwinds.util.enums.IStatusEnum;

import org.testng.Assert;
import org.testng.annotations.*;

@Test
public class FlowStatusEnumTest {

    @Test
    void testFromDb() {
        Assert.assertTrue(FlowStatusEnum.CHECKED == IStatusEnum.fromDb(FlowStatusEnum.class, 5));
        Assert.assertTrue(null == IStatusEnum.fromDb(FlowStatusEnum.class, 3));
    }

    @Test
    void testToDb() {
        assert(FlowStatusEnum.CHECK_FAILED.toDb() == 6);
    }

    @Test
    void testGetDesc() {
        assert("已提交".equals(FlowStatusEnum.SUBMITTED.getDesc()));
    }
}