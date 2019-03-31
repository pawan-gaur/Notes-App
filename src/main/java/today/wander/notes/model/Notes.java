package today.wander.notes.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    private Date doc = new Date();

    private Date dou = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notes() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDoc() {
        return doc;
    }

    public void setDoc(Date doc) {
        this.doc = doc;
    }

    public Date getDou() {
        return dou;
    }

    public void setDou(Date dou) {
        this.dou = dou;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", doc=" + doc +
                ", dou=" + dou +
                ", user=" + user +
                '}';
    }
}
