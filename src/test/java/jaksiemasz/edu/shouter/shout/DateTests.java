package jaksiemasz.edu.shouter.shout;

import jaksiemasz.edu.shouter.api.shout.response.ShoutAddedResponse;
import jaksiemasz.edu.shouter.helper.WithAuthenticatedTemplate;
import jaksiemasz.edu.shouter.model.Shout;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static jaksiemasz.edu.shouter.helper.StatusAssertions.assertResponseStatusIs200OK;
import static jaksiemasz.edu.shouter.helper.StatusAssertions.assertResponseStatusIs201CREATED;
import static org.junit.Assert.assertEquals;

public class DateTests extends WithAuthenticatedTemplate {

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
