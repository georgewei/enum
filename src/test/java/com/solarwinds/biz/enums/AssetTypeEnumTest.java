package com.solarwinds.biz.enums;

import com.solarwinds.util.enums.ITypeEnum;

import org.testng.Assert;
import org.testng.annotations.*;

@Test
public class AssetTypeEnumTest {

    @Test
    void testFromDb() {
        Assert.assertTrue(AssetTypeEnum.RIGHTS == ITypeEnum.fromDb(AssetTypeEnum.class, "R"));
        Assert.assertTrue(AssetTypeEnum.HOUSE == ITypeEnum.fromDb(AssetTypeEnum.class, "h"));
        Assert.assertTrue(null == ITypeEnum.fromDb(AssetTypeEnum.class, "house"));
    }

    @Test
    void testToDb() {
        assert(AssetTypeEnum.CIP.toDb().equals("c"));
    }

    @Test
    void testEqualsToDb() {
        assert(AssetTypeEnum.STOCK.equalsToDb("s"));
        assert(AssetTypeEnum.CIP.equalsToDb("C"));
    }

    @Test
    void testGetDesc() {
        assert("stock".equals(AssetTypeEnum.STOCK.getDesc()));
    }
}