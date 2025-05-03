package com.example.loginauthapi.domain.role;

import com.example.loginauthapi.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //states that roles and users have a relationship and
    //Automatically links roles back to their users via user_roles table.
    @ManyToMany(mappedBy = "roles")
    private Set<User> users =  new HashSet<>();
}
