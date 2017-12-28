package jaksiemasz.edu.shouter.api.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddShoutRequest {
    @NotNull
    @Size(max = 140)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
