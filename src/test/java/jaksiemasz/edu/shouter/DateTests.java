package jaksiemasz.edu.shouter;

import jaksiemasz.edu.shouter.api.response.ShoutAddedResponse;
import jaksiemasz.edu.shouter.model.Shout;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static jaksiemasz.edu.shouter.helper.MyAssertions.assertResponseStatusIs200OK;
import static jaksiemasz.edu.shouter.helper.MyAssertions.assertResponseStatusIs201CREATED;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DateTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private long createdShoutId;

    private static final String resource = "/shouts";

    @Before
    public void addSingleShout() {
        ResponseEntity<ShoutAddedResponse> response = restTemplate.postForEntity(resource,
                new Shout("Random text"), ShoutAddedResponse.class);

        assertResponseStatusIs201CREATED(response);

        createdShoutId = response.getBody().getCreatedShoutId();
    }

    @Test
    public void checkIfDateIsValid() {
        ResponseEntity<Shout> response = restTemplate.getForEntity(resource + "/{id}",
                Shout.class, String.valueOf(createdShoutId));

        assertResponseStatusIs200OK(response);

        Shout body = response.getBody();

        assertEquals("Dates should be equal", LocalDate.now(), body.getDate());
    }

}
