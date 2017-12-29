package jaksiemasz.edu.shouter.user;

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
public class UserCrudTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private UserRegisterRequest request;

    private String randomEmail;
    private String randomNickname;
    private String randomPassword;

    private static final String resource = "/users";

    @Before
    public void setUp() {
        randomEmail = randomAlphabetic(5) + "@" + randomAlphabetic(4) + ".com";
        randomNickname = randomAlphanumeric(5);
        randomPassword = randomAlphanumeric(8);

        request = new UserRegisterRequest();

        request.setEmail(randomEmail);
        request.setNickname(randomNickname);
        request.setPassword(randomPassword);
    }

    @Test
    public void get201afterPostingUser() {
        ResponseEntity<Object> response = restTemplate.postForEntity(resource, request, Object.class);

        assertResponseStatusIs201CREATED(response);
    }
}
