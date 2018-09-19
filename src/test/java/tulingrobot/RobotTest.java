package tulingrobot;

import com.alibaba.fastjson.JSONObject;

import java.util.Scanner;

public class RobotTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiAddress = "http://openapi.tuling123.com/openapi/api/v2";
        String inputText = "";
        while (!inputText.equals("exit")) {
            inputText = scanner.nextLine();
            JSONObject param = getJsonParam(inputText);
            String result = ConnectionUtil.sendPostForJson(apiAddress, param);
            String tulingResult = handleResult(result);
            System.out.println(tulingResult);
        }
        scanner.close();
        System.out.println("已退出");
    }

    // 解析json 获取返回的text
    private static String handleResult(String result) {
        return JSONObject.parseObject(result).getJSONArray("results")
                .getJSONObject(0).getJSONObject("values")
                .getString("text");
    }

    // 获取需要发送给图灵接口的json
    private static JSONObject getJsonParam(String inputText) {
        String appKey = "a12a64ce315149bbb5de1bd5a1553780";
        String userId = "316330";
        String paramStr = "{" +
                "\"reqType\":0," +
                "    \"perception\": {" +
                "        \"inputText\": {" +
                "            \"text\": \"" + inputText + "\"" +
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
        JSONObject resultJsonObj = JSONObject.parseObject(paramStr);
        return resultJsonObj;
    }
}
