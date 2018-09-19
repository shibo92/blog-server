// import com.blog.service.RedisCacheService;
import org.junit.runner.RunWith;
        import org.springframework.test.context.ContextConfiguration;

/**
 * Created by shibo on 2017/7/13 0013.
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/spring.xml","classpath:test/spring-mvc.xml",
        "classpath:test/spring-mybatis.xml",
        "classpath:test/spring-redis.xml"})
public class BlogTest {
}