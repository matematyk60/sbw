package jaksiemasz.edu.shouter.api.shout.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShoutAddedResponse {
    private long createdShoutId;

    public ShoutAddedResponse(long createdShoutId) {
        this.createdShoutId = createdShoutId;
    }
}
