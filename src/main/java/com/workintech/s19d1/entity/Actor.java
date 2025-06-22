package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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
@Table(name = "actor", schema = "fsweb")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "İsim boş olamaz!")
    @Size(min = 2, max = 50, message = "İsim 2 ile 50 karakter arasında olmalıdır!")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Soyisim boş olamaz!")
    @Size(min = 2, max = 50, message = "Soyisim 2 ile 50 karakter arasında olmalıdır!")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Cinsiyet boş olamaz!")
    private Gender gender;

    @Column(name = "birth_date")
    @NotNull(message = "Doğum tarihi boş olamaz!")
    @Past(message = "Doğum tarihi geçmiş bir tarih olmalıdır!")
    private LocalDate birthDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "actor_movie", schema = "fsweb", joinColumns = @JoinColumn(name = "actor_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;

    public void addMovie(Movie movie) {
        if(movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(movie);
    }
}
