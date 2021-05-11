package hikingBuddy.user;

import hikingBuddy.comments.Comment;
import hikingBuddy.eventComments.EventComment;
import hikingBuddy.events.Event;
import hikingBuddy.posts.Post;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email(message = "Invalid email address! Please provide a valid email address")
    @NotEmpty(message = "Please provide an email address")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "bio")
    private String bio;

    @Length(min = 5, max = 100, message = "Password length most be between 5-100 characters")
    @NotEmpty(message = "Please provide a password")
    @Column(name = "password")
    private String password;

    @Length(min = 3, max = 100, message = "Name must be between 3-100 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Event> events;

    @OneToMany(mappedBy = "user")
    private List<EventComment> eventComments;

    // @ManyToOne
    // private User userFollowed;

    // @OneToMany(mappedBy = "user")
    // private List<User> followersList;

    @ManyToMany
    private Collection<User> followersList;

    // Hibernate needs a default constructor to function
    public User() {
    }

    public User(
            @Email(message = "Invalid email address! Please provide a valid email address") @NotEmpty(message = "Please provide an email address") String email,
            @Length(min = 5, max = 100, message = "Password length most be between 5-100 characters") String password,
            @Length(min = 3, max = 100, message = "Name must be between 3-100 characters") String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User setUpdateUser(User updateUser) {
        if (updateUser.getImageUrl() == null) {
            updateUser.setImageUrl(this.getImageUrl());
        }
        if (updateUser.getBio() == null) {
            updateUser.setBio(this.getBio());
        }
        if (updateUser.getName() == null) {
            updateUser.setName(this.getName());
        }
        if (updateUser.getPassword() == null) {
            updateUser.setPassword(this.getPassword());
        }
        if (updateUser.getEmail() == null) {
            updateUser.setEmail(this.getEmail());
        }
        return updateUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<EventComment> getEventComments() {
        return eventComments;
    }

    public void setEventComments(List<EventComment> eventComments) {
        this.eventComments = eventComments;
    }

    public Collection<User> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(Collection<User> followersList) {
        this.followersList = followersList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void addFollower(User follower) {
        this.followersList.add(follower);
    }
    
}
