package com.solarwinds.biz.enums;

import org.testng.Assert;
import org.testng.annotations.*;

@Test
public class AssetTypeEnumTest {

    @DataProvider
    private static final Object[][] getParams() {
        return new Object[][] {
            {AssetTypeEnum.RIGHTS, "R"},
            {AssetTypeEnum.HOUSE, "h"},
            {AssetTypeEnum.CIP, "c"},
            {null, "house"}
        };
    }

    @Test(dataProvider = "getParams")
    void testFromDb(AssetTypeEnum e, String dbValue) {
        Assert.assertTrue(e == EnumUtils.fromDb(AssetTypeEnum.class, dbValue));
    }

    @Test(dataProvider = "getParams")
    void testToDb(AssetTypeEnum e, String dbValue) {
        java.util.Optional.ofNullable(e).ifPresent(
            e1 -> Assert.assertTrue(e1.toDb().equals(dbValue.toLowerCase())));
    }

    @Test(dataProvider = "getParams")
    void testEqualsToDb(AssetTypeEnum e, String dbValue) {
        java.util.Optional.ofNullable(e).ifPresent(
            e1 -> Assert.assertTrue(e1.equalsToDb(dbValue)));
    }

    @Test
    void testGetDesc() {
        Assert.assertTrue("stock".equals(AssetTypeEnum.STOCK.getDesc()));
    }
}