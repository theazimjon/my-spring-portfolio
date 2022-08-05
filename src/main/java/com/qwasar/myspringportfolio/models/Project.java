package com.qwasar.myspringportfolio.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String description;
    private String url;
    private String imgUrl;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", description='" + description + "'" +
                ", url='" + url + "'" +
                ", imgUrl='" + imgUrl + "'" +
                "}";
    }

}
