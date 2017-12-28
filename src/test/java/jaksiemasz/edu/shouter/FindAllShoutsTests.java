package jaksiemasz.edu.shouter;

import jaksiemasz.edu.shouter.model.Shout;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindAllShoutsTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private final static int numberOfShouts = 44;

    private List<Shout> createdShouts;

    @Before
    public void postShoutsToServer() {
        initShoutList();
        postAllShouts();
    }

    @Test
    public void checkIfResponseContainsCreatedShouts() {
        ResponseEntity<Shout[]> responseEntity = restTemplate.getForEntity("/shouts", Shout[].class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        List<Shout> responseList = Arrays.asList(responseEntity.getBody());

        createdShouts.forEach(shout -> assertTrue(responseList.contains(shout)));
    }

    private void initShoutList() {
        createdShouts = new ArrayList<>();
        IntStream.range(0, numberOfShouts)
                .forEach(v -> createdShouts
                        .add(new Shout(RandomStringUtils.randomAlphanumeric(33))));
    }

    private void postAllShouts() {
        createdShouts.forEach(
                shout -> restTemplate.postForObject("/shouts", shout, Object.class)
        );
    }

}
