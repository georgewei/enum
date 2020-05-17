package com.solarwinds.biz.enums;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test
public class EnumUtilTest {

    @Test
    void testToListTypeEnum() {
        List<Map<String, Object>> result = EnumUtil.getValueList(AssetTypeEnum.class, 0);
        assert result.size() == 5;

        for (Map<String, Object> m: result) {
            String value = (String)m.get("code");
            switch (value) {
                case "r":
                    assert m.get("name").equals("creditor's rights");
                    break;
                case "h":
                    assert m.get("name").equals("house");
                    break;
                case "s":
                    assert m.get("name").equals("stock");
                    break;
                case "c":
                    assert m.get("name").equals("construction in progress");
                    break;
                case "l":
                    assert m.get("name").equals("land");
                    break;
                default:
            }
        }
    }

    @Test
    void testToListStatusEnum() {
        List<Map<String, Object>> result = EnumUtil.getValueList(FlowStatusEnum.class,
                new HashMap<String, String>(){{
                    put(EnumUtil.KEY_NAME_FOR_VALUE, "type");
                    put(EnumUtil.KEY_NAME_FOR_DESC, "name");
                }});
        assert result.size() == 4;

        for (Map<String, Object> m: result) {
            int value = (Integer)m.get("type");
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
