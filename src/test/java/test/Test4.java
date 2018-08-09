package test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Test4 {
    public static void main(String[] args) {
        List<Map<String, Object>> input = new ArrayList<Map<String, Object>>() {{
            add(Collections.singletonMap("one", 1));
            add(Collections.singletonMap("two", 2));
            add(Collections.singletonMap("three", 3));
            trimToSize();
        }};
        // 开始转换
        List<Map<String,String>> output = input.parallelStream().map(Test4::convert).collect(Collectors.toList());
        // 验证
        output.forEach(m->{
            m.forEach((k, v)-> System.out.println(k + "\t" + v));
        });
    }
    /**
     * 业务
     * */
    private static Map<String, String> convert(Map<String, Object> map) {
        Objects.requireNonNull(map);
        Map<String, String>  result = new ConcurrentHashMap<>(map.size());
        // 业务比较繁杂的，可以用compute方法
        map.forEach((key, value) -> result.put(key, value.toString()));
        return result;
    }
}
