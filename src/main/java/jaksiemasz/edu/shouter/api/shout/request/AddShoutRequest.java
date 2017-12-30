package jaksiemasz.edu.shouter.api.shout.request;

import jaksiemasz.edu.shouter.model.Shout;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AddShoutRequest {
    @NotNull
    @Size(max = 140)
    private String content;

    public AddShoutRequest(String content) {
        this.content = content;
    }

    public Shout extractShout() {
        return new Shout(content);
    }

}
