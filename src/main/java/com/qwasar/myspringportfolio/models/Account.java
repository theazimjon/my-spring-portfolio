package com.qwasar.myspringportfolio.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;
    private String password;
    private String username;


    @OneToMany(mappedBy = "account")
    private List<Project> projects;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<com.qwasar.myspringportfolio.models.Authority> authorities = new HashSet<>();

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + "'" +
                ", email='" + email + "'" +
                ", authorities=" + authorities +
                "}";
    }


}
