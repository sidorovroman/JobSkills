import com.onedeveloperstudio.jobskills.common.dto.NewsDto;
import com.onedeveloperstudio.jobskills.server.service.NewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User: y.zakharov
 * Date: 24.11.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class NewsContollerTest {

  private MockMvc mockMvc;
  @Autowired
  private NewsService newsService;

  @Test
  public void loadListNews() throws Exception {
    NewsDto news1 = new NewsDto();
    news1.setId(1l);
    news1.setAddDate(new Date());
    news1.setCaption("1");
    news1.setBody("1");
    news1.setLink("1");
    news1.setRating(1);
    news1.setTags("1");

    NewsDto news2 = new NewsDto();
    news2.setId(2l);
    news2.setAddDate(new Date());
    news2.setCaption("2");
    news2.setBody("2");
    news2.setLink("2");
    news2.setRating(2);
    news2.setTags("2");
    when(newsService.loadAll()).thenReturn(Arrays.asList(news1, news2));
    mockMvc.perform(MockMvcRequestBuilders.get("/news/list"))
        .andExpect(status().isOk());
    verify(newsService, times(1)).loadAll();
    verifyNoMoreInteractions(news1);
  }
}
