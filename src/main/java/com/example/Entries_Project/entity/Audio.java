package com.example.Entries_Project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "audio_tb")
public class Audio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String path;

    @ManyToOne
    @JoinColumn
    private Entry entry;
}