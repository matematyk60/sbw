package jaksiemasz.edu.shouter.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusAssertions {
    public static void assertResponseStatusIs200OK(ResponseEntity<?> response) {
        assertResponseStatusIsEqual(response, HttpStatus.OK);
    }

    public static void assertResponseStatusIs201CREATED(ResponseEntity<?> response) {
        assertResponseStatusIsEqual(response, HttpStatus.CREATED);
    }

    public static void assertResponseStatusIs400BAD_REQUEST(ResponseEntity<?> response) {
        assertResponseStatusIsEqual(response, HttpStatus.BAD_REQUEST);
    }

    public static void assertResponseStatusIs404NOT_FOUND(ResponseEntity<?> response) {
        assertResponseStatusIsEqual(response, HttpStatus.NOT_FOUND);
    }

    private static void assertResponseStatusIsEqual(ResponseEntity<?> response, HttpStatus expected) {
        assertThat(response.getStatusCode())
                .isEqualTo(expected);
    }
}
