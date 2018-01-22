package jaksiemasz.edu.shouter.api.shout.request;

import jaksiemasz.edu.shouter.model.Shout;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddShoutRequest {
    @NotNull
    @Size(max = 140)
    private String content;

    public AddShoutRequest(String content) {
        this.content = content;
    }

    public AddShoutRequest() {
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getContent() {
        return content;
    }

    public Shout extractShout() {
        return new Shout(content);
    }


}
