package hikingBuddy.postlikes;

import com.fasterxml.jackson.annotation.*;
import hikingBuddy.posts.Post;
import hikingBuddy.user.User;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(nullable = false)
    @NotNull
    @JsonIgnore
    private Post likedPost;

    @ManyToOne
    @JsonIgnoreProperties({ "posts", "likedPosts" })
    @JoinColumn(nullable = false)
    private User likedUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(Post likedPost) {
        this.likedPost = likedPost;
    }

    public User getLikedUser() {
        return likedUser;
    }

    public void setLikedUser(User likedUser) {
        this.likedUser = likedUser;
    }
}