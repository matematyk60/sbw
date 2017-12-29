package jaksiemasz.edu.shouter;

import jaksiemasz.edu.shouter.model.Shout;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static jaksiemasz.edu.shouter.helper.MyAssertions.assertResponseStatusIs400BAD_REQUEST;
import static jaksiemasz.edu.shouter.helper.MyAssertions.assertResponseStatusIs404NOT_FOUND;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ErrorTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private Shout invalidShout;

    private static final String resource = "/shouts";
    private static final String tooLongContent = RandomStringUtils.randomAlphanumeric(141);

    @Before
    public void initShout() {
        invalidShout = new Shout(tooLongContent);
    }

    @Test
    public void rejectRequestWithoutContent() {
        ResponseEntity<Object> response = restTemplate.postForEntity(resource, new Shout(), Object.class);

        assertResponseStatusIs400BAD_REQUEST(response);
    }

    @Test
    public void rejectRequestWithTooLongContent() {
        ResponseEntity<Object> response = restTemplate.postForEntity(resource, invalidShout, Object.class);

        assertResponseStatusIs400BAD_REQUEST(response);
    }

    @Test
    public void get404OnNonExistingShout() {
        long id = 32331213;

        ResponseEntity<Object> response = restTemplate.getForEntity(resource + "/{id}", Object.class, id);

        assertResponseStatusIs404NOT_FOUND(response);
    }
}