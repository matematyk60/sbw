package jaksiemasz.edu.shouter.user;

import jaksiemasz.edu.shouter.api.shout.request.AddShoutRequest;
import jaksiemasz.edu.shouter.helper.WithAuthenticatedTemplate;
import jaksiemasz.edu.shouter.model.Shout;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

import static jaksiemasz.edu.shouter.helper.StatusAssertions.assertResponseStatusIs200OK;
import static jaksiemasz.edu.shouter.helper.StatusAssertions.assertResponseStatusIs201CREATED;
import static org.junit.Assert.assertEquals;

public class postAndGetUserShoutsBackTests extends WithAuthenticatedTemplate {

    private AddShoutRequest request;

    private static final int numberOfShouts = 4;

    @Before
    public void postShouts() {
        for(int i = 0 ; i < numberOfShouts ; i++) {
            request = createRandomRequest();
            ResponseEntity<Object> response = restTemplate.postForEntity("/shouts", request, Object.class);
            assertResponseStatusIs201CREATED(response);
        }
    }

    @Test
    public void getBackCreatedShoutsWithValidEmail() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/shouts/byUser")
                .queryParam("email", randomEmail);

        ResponseEntity<Shout[]> response = restTemplate.getForEntity(builder.toUriString(), Shout[].class);

        assertResponseStatusIs200OK(response);

        List<Shout> responseShouts = Arrays.asList(response.getBody());

        assertEquals("There should be as much shouts as was posted", numberOfShouts, responseShouts.size());

        responseShouts
                .forEach(shout -> assertEquals("Response emails should be same as poster",
                        randomEmail, shout.getAuthorEmail()));
    }

    private AddShoutRequest createRandomRequest() {
        AddShoutRequest newRequest = new AddShoutRequest();
        newRequest.setContent(RandomStringUtils.randomAlphanumeric(44));

        return newRequest;
    }
}
