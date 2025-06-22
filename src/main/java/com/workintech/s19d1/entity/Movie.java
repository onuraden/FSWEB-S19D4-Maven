package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "movie", schema = "fsweb")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Film adı boş olamaz!")
    @Size(min = 2, max = 100, message = "Film adı 2 ile 100 karakter arasında olmalıdır!")
    private String name;

    @Column(name = "director_name")
    @NotBlank(message = "Yönetmen adı boş olamaz!")
    @Size(min = 2, max = 100, message = "Yönetmen adı 2 ile 100 karakter arasında olmalıdır!")
    private String directorName;

    @Column(name = "rating")
    @DecimalMin(value = "0.0", message = "Puan 0'dan küçük olamaz")
    @DecimalMax(value = "10.0", message = "Puan 10'dan büyük olamaz")
    private double rating;

    @Column(name = "release_date")
    @PastOrPresent(message = "Yayın tarihi bugünden ileri bir tarih olamaz")
    private LocalDate releaseDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "movies", fetch = FetchType.EAGER)
    private List<Actor> actors;

    public void addActor(Actor actor) {
        if(actors == null) {
            actors = new ArrayList<>();
        }
        actors.add(actor);
    }
}
