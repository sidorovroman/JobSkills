import com.onedeveloperstudio.jobskills.server.service.NewsService;
import com.onedeveloperstudio.jobskills.server.service.NewsServiceImpl;
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
  public NewsService todoService() {
    return Mockito.mock(NewsServiceImpl.class);
  }
}
