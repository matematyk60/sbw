package jaksiemasz.edu.shouter.api.response;

public class ShoutAddedResponse {
    private long createdShoutId;

    public ShoutAddedResponse() {}

    public ShoutAddedResponse(long createdShoutId) {
        this.createdShoutId = createdShoutId;
    }

    public long getCreatedShoutId() {
        return createdShoutId;
    }

    public void setCreatedShoutId(long createdShoutId) {
        this.createdShoutId = createdShoutId;
    }
}
