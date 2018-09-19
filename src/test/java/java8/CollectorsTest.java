package java8;

import com.blog.entity.User;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class CollectorsTest {
    @Test
    public void Test1(){
        List<User> userList = new LinkedList<>();
        for (int i = 0; i <5; i++) {
            User u = new User();
            u.setId(String.valueOf(i));
            u.setIsDel(i%2);
            userList.add(u);
        }
        Map map = groupByKey(userList);
        System.out.println(map);
        User u = getMaxId(userList);
        System.out.println(u);
        String ids = getIdAppendStr(userList);
        System.out.println(ids);

        sortByKey(userList);
        System.out.println("排序后");
        userList.stream().forEach(uu -> System.out.print(uu.getId()));
    }

    public static Map<Integer, List<User>> groupByKey(List<User> userList){
        // 将用户列表按删除和已删除分组
        return userList.stream().collect(Collectors.groupingBy(user -> user.getIsDel()));
    }

    public static User getMaxId(List<User> userList){
        // 获取ID最大的用户
        return userList.stream().collect(Collectors.maxBy(Comparator.comparing(User::getId))).get();
    }

    public static String getIdAppendStr(List<User> userList){
        return userList.stream().map(User::getId).reduce((x,y) -> {
            StringBuilder sb1 = new StringBuilder(x);
            StringBuilder sb2 = new StringBuilder(y);
            return String.valueOf(sb1.append(sb2));
        }).get();
    }

    public static void sortByKey(List<User> userList){
        // 将用户列表按删除和已删除分组
        userList.sort((u1,u2) -> u2.getId().compareTo(u1.getId()));
    }
}
