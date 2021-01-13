import com.gjsyoung.controller.SpittleController;
import com.gjsyoung.domain.Spittle;
import com.gjsyoung.service.SpittleService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * create by cairuojin on 2019/01/08
 */
//@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
//@ContextConfiguration(locations = "classpath:springconfig.xml")//加载spring配置文件
//@ContextConfiguration(classes = TestConfig.class)
public class _1Test {
    @Test
    public void test01() throws Exception {
        Spittle exectedSpittle = new Spittle("Hello", new Date());

        SpittleService mockService = mock(SpittleService.class);
        when(mockService.findOneSpittles(12345)).thenReturn(exectedSpittle);   //假设调用findOne(12345)会返回exectedSpittle对象

        SpittleController controller = new SpittleController(mockService);
        MockMvc mockMvc = standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/jsp/spittles.jsp")).build();

        mockMvc.perform(get("/spittles/12345"))      //从外部传值12345
                .andExpect(view().name("spittle"))
                .andExpect(model().attributeExists("spittle"));
    }

    @Test
    public void shouldProcessRegistration() throws Exception {
        Date date = new Date();

        SpittleService spittleService = mock(SpittleService.class);
        Spittle unsave = new Spittle("unsave", date, 10.0, 1.0);
        Spittle save = new Spittle("save", date, 30.0, 3.0);
        when(spittleService.save(unsave)).thenReturn(save);                             //假设传入unsave 得到save

        SpittleController controller = new SpittleController(spittleService);
        MockMvc mockMvc = standaloneSetup(controller).build();                      //构建MockMvc
        mockMvc.perform(post("/spittles/register")                      //对url进行post
                .param("message", "unsave")                         //传入unsave参数
                .param("time", date.toString())
                .param("latitude", "10.0")
                .param("longitude", "1.0")
        ).andExpect(redirectedUrl("/spittles/unsave"));        //预期重定向到新的url
            verify(spittleService , atLeastOnce()).save(unsave);           //校验service最终会真正用来保存表单上传的数据
    }
}
