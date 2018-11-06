package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="Role_Data")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String role;

    @OneToMany(mappedBy="roles",fetch=FetchType.EAGER)
    private Collection<User> users;

    public Role(){

    }
    public Role(String role){
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
