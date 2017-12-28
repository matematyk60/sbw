package jaksiemasz.edu.shouter;

import jaksiemasz.edu.shouter.model.Shout;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddNewShoutTest {

    private Shout validShout;
    private Shout invalidShout;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String validContent = RandomStringUtils.randomAlphanumeric(43);
    private static final String tooLongContent = RandomStringUtils.randomAlphanumeric(141);

    @Before
    public void initShout() {
        validShout = new Shout(validContent);
        invalidShout = new Shout(tooLongContent);
    }

    @Test
    public void addShoutAndGet201() {
        ResponseEntity<Object> response = restTemplate.postForEntity("/shouts", validShout, Object.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void rejectRequestWithoutContent() {
        ResponseEntity<Object> response = restTemplate.postForEntity("/shouts", new Shout(), Object.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void rejectRequestWithTooLongContent() {
        ResponseEntity<Object> response = restTemplate.postForEntity("/shouts", invalidShout, Object.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
