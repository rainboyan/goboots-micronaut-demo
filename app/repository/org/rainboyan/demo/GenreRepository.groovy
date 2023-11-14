package org.rainboyan.demo

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

interface GenreRepository {

    Optional<Genre> findById(Long id)

    Genre save(Genre genre)

    Genre saveWithException(@NotBlank String name)

    void delete(Long id)

    List<Genre> findAll(@NotNull Map args)

    int update(long id, @NotBlank String name)

    int count()
}