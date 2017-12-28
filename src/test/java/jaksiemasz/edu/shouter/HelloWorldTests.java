package jaksiemasz.edu.shouter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void checkHelloWorld() {
        String body = restTemplate.getForObject("/", String.class);
        assertThat(body).isEqualTo("Hello, worl0sd!");
    }
}
