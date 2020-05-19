package com.solarwinds.biz.enums;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test
public class EnumUtilsTest {

    @Test
    void testToListTypeEnum() {
        List<Map<String, Object>> result = EnumUtils.getValueList(AssetTypeEnum.class, 0);
        assert result.size() == 5;

        for (Map<String, Object> m: result) {
            String value = (String)m.get("value");
            switch (value) {
                case "r":
                    assert m.get("desc").equals("creditor's rights");
                    break;
                case "h":
                    assert m.get("desc").equals("house");
                    break;
                case "s":
                    assert m.get("desc").equals("stock");
                    break;
                case "c":
                    assert m.get("desc").equals("construction in progress");
                    break;
                case "l":
                    assert m.get("desc").equals("land");
                    break;
                default:
            }
        }
    }

    @Test
    void testToListStatusEnum() {
        List<Map<String, Object>> result = EnumUtils.getValueList(FlowStatusEnum.class,
                new HashMap<String, String>(){{
                    put(EnumUtils.KEY_NAME_FOR_VALUE, "type");
                    put(EnumUtils.KEY_NAME_FOR_DESC, "name");
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
