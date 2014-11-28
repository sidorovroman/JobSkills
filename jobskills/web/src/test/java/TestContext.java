import com.onedeveloperstudio.jobskills.server.service.JobService;
import com.onedeveloperstudio.jobskills.server.service.JobServiceImpl;
import com.onedeveloperstudio.jobskills.server.service.NewsService;
import com.onedeveloperstudio.jobskills.server.service.NewsServiceImpl;
import com.onedeveloperstudio.jobskills.web.component.viewcontrollers.JobsViewController;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * User: y.zakharov
 * Date: 24.11.14
 */
@Configuration
public class TestContext {

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    messageSource.setBasename("i18n/messages");
    messageSource.setUseCodeAsDefaultMessage(true);

    return messageSource;
  }

  @Bean
  public NewsService newsService() {
    return Mockito.mock(NewsServiceImpl.class);
  }

  @Bean
  public JobsViewController getJobsViewController() {
    return Mockito.mock(JobsViewController.class);
  }

  @Bean
  public JobService jobsService() {
    return Mockito.mock(JobServiceImpl.class);
  }

  @Bean
  public Mapper getMapper(){
    return Mockito.mock(DozerBeanMapper.class);
  }
}
