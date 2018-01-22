package jaksiemasz.edu.shouter.shout;

import jaksiemasz.edu.shouter.api.shout.request.AddShoutRequest;
import jaksiemasz.edu.shouter.helper.WithAuthenticatedTemplate;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static jaksiemasz.edu.shouter.helper.StatusAssertions.assertResponseStatusIs400BAD_REQUEST;
import static jaksiemasz.edu.shouter.helper.StatusAssertions.assertResponseStatusIs404NOT_FOUND;

public class ErrorTests extends WithAuthenticatedTemplate {

    private AddShoutRequest invalidShoutRequest;

    private static final String resource = "/shouts";
    private static final String tooLongContent = RandomStringUtils.randomAlphanumeric(141);

    @Before
    public void initShout() {
        invalidShoutRequest = new AddShoutRequest(tooLongContent);
    }

    @Test
    public void rejectRequestWithoutContent() {
        ResponseEntity<Object> response = restTemplate.postForEntity(resource, new AddShoutRequest(), Object.class);

        assertResponseStatusIs400BAD_REQUEST(response);
    }

    @Test
    public void rejectRequestWithTooLongContent() {
        ResponseEntity<Object> response = restTemplate.postForEntity(resource, invalidShoutRequest, Object.class);

        assertResponseStatusIs400BAD_REQUEST(response);
    }

    @Test
    public void get404OnNonExistingShout() {
        long id = 32331213;

        ResponseEntity<Object> response = restTemplate.getForEntity(resource + "/{id}", Object.class, id);

        assertResponseStatusIs404NOT_FOUND(response);
    }

}
