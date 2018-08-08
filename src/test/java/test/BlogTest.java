package test;

import com.blog.aop.MyLog;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.service.RedisCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shibo on 2017/7/13 0013.
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/spring.xml","classpath:test/spring-mvc.xml",
        "classpath:test/spring-mybatis.xml",
        "classpath:test/spring-redis.xml"})
public class BlogTest {

    //redis连接池
    @Autowired
    public JedisPool jedisPool;

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private UserService userServiceImpl;

    @Test
    public void saveUserToRedis() throws Exception {
        User u = new User();
        String uId = "dfbdac4b29b24d0cad99f57214ab5f64";
        u.setId(uId);
        User resultU = userServiceImpl.getUserTest(u);
        // redisCacheService.setCacheObject(uId,resultU);
        System.out.println(resultU);
    }

    @Test
    public void getUserById() throws Exception {
        String uId = "dfbdac4b29b24d0cad99f57214ab5f64";
        User userFromRedis = (User) redisCacheService.getCacheObject(uId);
        if(null == userFromRedis){
            userFromRedis = new User();
            userFromRedis.setId(uId);
            userFromRedis = userServiceImpl.getUserTest(userFromRedis);
        }
        System.out.println(userFromRedis);
    }
}