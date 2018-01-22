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
public abstract class WithAuthenticatedTemplate {

    @Autowired
    protected TestRestTemplate restTemplate;

    protected String randomEmail;
    protected String randomNickname;
    protected String randomPassword;

    private ResponseEntity<Object> response;

    @Before
    public void setUp() {
        randomEmail = randomAlphabetic(5) + "@" + randomAlphabetic(4) + ".com";
        randomNickname = randomAlphanumeric(5);
        randomPassword = randomAlphanumeric(8);

        UserRegisterRequest request = createRequest();

        response = restTemplate.postForEntity("/users", request, Object.class);

        assertResponseStatusIs201CREATED(response);

        restTemplate = restTemplate.withBasicAuth(randomNickname, randomPassword);
    }

    private UserRegisterRequest createRequest() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setEmail(randomEmail);
        request.setUsername(randomNickname);
        request.setPassword(randomPassword);

        return request;
    }

}
