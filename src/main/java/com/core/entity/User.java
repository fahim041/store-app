package com.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Profile profile;

    public void addAddress(Address address){
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
        address.setUser(null);
    }

    public void addTag(String tagName){
        var tag = new Tag();
        tag.setName(tagName);
        tags.add(tag);
        tag.getUsers().add(this);
    }

    public void removeTag(Tag tag){
        if(tags.contains(tag)){
            tags.remove(tag);
            tag.getUsers().remove(this);
        }
    }

    public void addProfile(Profile profileObj){
        profile = profileObj;
        profileObj.setUser(this);
    }

    public void removeProfile(Profile profileObj){
        profile = null;
        profileObj.setUser(null);
    }
}
