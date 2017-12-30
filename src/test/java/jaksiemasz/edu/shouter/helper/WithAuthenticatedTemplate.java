package jaksiemasz.edu.shouter.helper;

import jaksiemasz.edu.shouter.api.user.request.UserRegisterRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static jaksiemasz.edu.shouter.helper.StatusAssertions.assertResponseStatusIs201CREATED;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WithAuthenticatedTemplate {

    @Autowired
    protected TestRestTemplate restTemplate;

    private ResponseEntity<Object> response;

    @Before
    public void setUp() {
        String randomEmail = randomAlphabetic(5) + "@" + randomAlphabetic(4) + ".com";
        String randomNickname = randomAlphanumeric(5);
        String randomPassword = randomAlphanumeric(8);

        UserRegisterRequest request = createRequest(randomEmail, randomNickname, randomPassword);

        response = restTemplate.postForEntity("/users", request, Object.class);

        restTemplate = restTemplate.withBasicAuth(randomNickname, randomPassword);
    }

    @Test
    public void postingUserTest() {
        assertResponseStatusIs201CREATED(response);
    }

    private UserRegisterRequest createRequest(String randomEmail, String randomNickname, String randomPassword) {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setEmail(randomEmail);
        request.setUsername(randomNickname);
        request.setPassword(randomPassword);

        return request;
    }

}
