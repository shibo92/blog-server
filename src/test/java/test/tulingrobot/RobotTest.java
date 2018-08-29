package test.tulingrobot;

import com.alibaba.fastjson.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RobotTest {
    // http://openapi.tuling123.com/openapi/api/v2
    // 316330
    // a12a64ce315149bbb5de1bd5a1553780

    public static void main(String[] args) {
        String inputText = "你好";
        String address = "http://openapi.tuling123.com/openapi/api/v2";
        JSONObject param = getJsonParam(inputText);
        String result = ConnectionUtil.sendPostForJson(address, param);
        System.out.println(result);
    }
    
    private static JSONObject getJsonParam(String inputText){
        String appKey = "a12a64ce315149bbb5de1bd5a1553780";
        String userId = "316330";
        String paramStr = "{" +
                "\"reqType\":0," +
                "    \"perception\": {" +
                "        \"inputText\": {" +
                "            \"text\": \""+ appKey +"\"" +
                "        }," +
                "        \"inputImage\": {" +
                "            \"url\": \"imageUrl\"" +
                "        }," +
                "        \"selfInfo\": {}" +
                "    }," +
                "    \"userInfo\": {" +
                "        \"apiKey\": \"" + appKey + "\"," +
                "        \"userId\": \"" + userId + "\"" +
                "    }" +
                "}";
        JSONObject param = JSONObject.parseObject(paramStr);
        return param;
    }
}
