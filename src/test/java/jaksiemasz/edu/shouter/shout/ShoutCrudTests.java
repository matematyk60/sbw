package jaksiemasz.edu.shouter.shout;

import jaksiemasz.edu.shouter.api.shout.response.ShoutAddedResponse;
import jaksiemasz.edu.shouter.helper.WithAuthenticatedTemplate;
import jaksiemasz.edu.shouter.model.Shout;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static jaksiemasz.edu.shouter.helper.StatusAssertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoutCrudTests extends WithAuthenticatedTemplate {

    private Shout validShout;

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
