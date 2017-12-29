package jaksiemasz.edu.shouter;

import jaksiemasz.edu.shouter.api.response.ShoutAddedResponse;
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

import static jaksiemasz.edu.shouter.helper.MyAssertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoutCrudTests {

    private Shout validShout;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String validContent = RandomStringUtils.randomAlphanumeric(43);

    private static final String resource = "/shouts";

    @Before
    public void initShout() {
        validShout = new Shout(validContent);
    }

    @Test
    public void addShoutTest() {
        ResponseEntity<ShoutAddedResponse> response = restTemplate.postForEntity(resource, validShout, ShoutAddedResponse.class);

        assertResponseStatusIs201CREATED(response);

        long createdShoutId = response.getBody().getCreatedShoutId();
        ResponseEntity<Shout> getResponse = restTemplate.getForEntity(resource + "/{id}", Shout.class, createdShoutId);

        assertResponseStatusIs200OK(getResponse);

        assertThat(getResponse.getBody().getContent())
                .isEqualTo(validShout.getContent());
    }

    @Test
    public void deleteShoutTest() {
        ResponseEntity<ShoutAddedResponse> response = restTemplate.postForEntity(resource, validShout, ShoutAddedResponse.class);

        assertResponseStatusIs201CREATED(response);

        long createdShoutId = response.getBody().getCreatedShoutId();

        restTemplate.delete(resource + "/{id}", createdShoutId);

        ResponseEntity<Shout> getResponse = restTemplate.getForEntity(resource + "/{id}", Shout.class, createdShoutId);

        assertResponseStatusIs404NOT_FOUND(getResponse);
    }


}
