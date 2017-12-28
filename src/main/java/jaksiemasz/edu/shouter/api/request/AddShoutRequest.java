package jaksiemasz.edu.shouter.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class AddShoutRequest {
    @NotNull
    private String content;

    @JsonCreator
    public AddShoutRequest(@JsonProperty(required = true, value = "content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
