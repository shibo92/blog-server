package java8;

import com.blog.entity.User;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectorsTest {
    @Test
    public void testFuncation(){
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
        // 将用户列表按删除和未删除分组
        userList.sort((u1,u2) -> u2.getId().compareTo(u1.getId()));
    }

    @Test
    public void testReduce(){
        int[] arr = {4,4,2};
        // Arrays.stream(arr).reduce((x,y) -> x+y).ifPresent(System.out::println);
        System.out.println(Arrays.stream(arr).reduce((x,y) -> x*y).orElse(0));;
        System.out.println(Arrays.stream(arr).reduce(-1, (x, y) -> x + y));
    }


    @Test
    public void testCount(){
        String[] arr = {"4","4","2"};
        IntPredicate lt10 = e -> e < 3;

        System.out.println(Arrays.asList(arr).stream().mapToInt(Integer::valueOf).anyMatch(lt10));
    }

    @Test
    public void testCollect(){
        Integer[] arr = {4,4,2};
        List<Integer> list = Arrays.stream(arr).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void testCollect2(){
        Integer[] arr = {4,3,2};
        List<Integer> list = Arrays.stream(arr).collect(Collectors.toList());
        Map<String,String> map = list.stream().collect(Collectors.toMap(String::valueOf, a -> String.valueOf(a*a)));
        System.out.println(map);
    }

    @Test
    public void testPeek() {
        Integer[] arr = {4,3,2};
        List<Integer> list = new LinkedList<>();
        long count = Arrays.stream(arr).filter(x -> x > 2).peek(list::add).count();
        System.out.println(list);
        System.out.println(count);
    }

    @Test
    public void testThen(){
        Integer[] arr = {4,3,2};
        Consumer<Integer> out = (x) -> System.out.println("out consume:"+x);
        Consumer<Integer> err = (x) -> System.err.println("err consume:"+x);
//        Arrays.stream(arr).forEach(out.andThen(err));
        Arrays.stream(arr).forEach(err.andThen(out));
    }
}
