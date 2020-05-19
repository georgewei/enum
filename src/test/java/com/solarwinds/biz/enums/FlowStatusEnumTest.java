package com.solarwinds.biz.enums;

import org.testng.Assert;
import org.testng.annotations.*;

@Test
public class FlowStatusEnumTest {

    @DataProvider
    private static final Object[][] getParams() {
        return new Object[][] {
            {FlowStatusEnum.UNSUBMITTED, 1},
            {FlowStatusEnum.CHECKED, 5},
            {FlowStatusEnum.CHECK_FAILED, 6},
            {null, 3}
        };
    }

    @Test(dataProvider = "getParams")
    void testFromDb(FlowStatusEnum e, int dbValue) {
        Assert.assertTrue(e == EnumUtils.fromDb(FlowStatusEnum.class, dbValue));
    }

    @Test(dataProvider = "getParams")
    void testToDb(FlowStatusEnum e, int dbValue) {
        java.util.Optional.ofNullable(e).ifPresent(
            e1 -> Assert.assertTrue(e1.toDb() == dbValue));
    }

    @Test(dataProvider = "getParams")
    void testEqualsToDb(FlowStatusEnum e, int dbValue) {
        java.util.Optional.ofNullable(e).ifPresent(
            e1 -> Assert.assertTrue(e1.equalsToDb(dbValue)));
    }

    @Test
    void testGetDesc() {
        Assert.assertTrue("Submitted".equals(FlowStatusEnum.SUBMITTED.getDesc()));
    }
}