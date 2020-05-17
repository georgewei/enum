package com.solarwinds.biz.enums;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Test
public class EnumUtilTest {

    @Test
    void testToListTypeEnum() {
        List<Map<String, Object>> result = EnumUtil.getValueList(AssetTypeEnum.class, 0);
        assert result.size() == 5;
    }

    @Test
    void testToListStatusEnum() {
        List<Map<String, Object>> result = EnumUtil.getValueList(FlowStatusEnum.class);
        assert result.size() == 4;

        for (Map<String, Object> m: result) {
            int value = (Integer)m.get("code");
            switch (value) {
                case 1:
                    assert m.get("name").equals("Unsubmitted");
                    break;
                case 2:
                    assert m.get("name").equals("Submitted");
                    break;
                case 5:
                    assert m.get("name").equals("Checked");
                    break;
                case 6:
                    assert m.get("name").equals("Check Failed");
                    break;
                default:
            }
        }
    }
}
