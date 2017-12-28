package jaksiemasz.edu.shouter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Shout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 140)
    private String content;

    public Shout(String content) {
        this.content = content;
    }

    public Shout() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shout shout = (Shout) o;
        return Objects.equals(content, shout.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(content);
    }
}
