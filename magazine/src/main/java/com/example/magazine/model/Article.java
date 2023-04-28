package com.example.magazine.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "articles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "magazine_id")
    private Magazine magazine;
}

