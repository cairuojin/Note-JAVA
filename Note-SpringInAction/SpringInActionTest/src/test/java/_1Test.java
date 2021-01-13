import com.gjsyoung.service.TestConfig;
import com.gjsyoung.test.Boss;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * create by cairuojin on 2019/01/08
 */
@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
//@ContextConfiguration(locations={"classpath:spring*.xml"})//加载spring配置文件
@ContextConfiguration(classes = TestConfig.class)
public class _1Test {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/properties/person.xml");
        Boss boss = context.getBean(Boss.class);
        boss.getCar().drive();
        boss.drive();
    }
}
