package com.students.webappwithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_email", insertable = false, updatable = false)
    private String user;
    @Column(name = "path")
    private String path;
    @Column(name = "message", length = 350)
    private String message;
    @Column(name = "date")
    private Date date;
    @ManyToOne()
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User users;

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
