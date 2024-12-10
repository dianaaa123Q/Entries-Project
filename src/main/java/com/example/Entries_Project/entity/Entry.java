package com.example.Entries_Project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "entries_tb")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate date;
    private String summary;

    @OneToMany(mappedBy = "entry")
    private List<Image> images;

    @ManyToOne
    @JoinColumn
    private User author;
}
