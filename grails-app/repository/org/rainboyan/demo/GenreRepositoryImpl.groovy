package org.rainboyan.demo

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.PersistenceException
import javax.persistence.TypedQuery
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository("genreRepository")
class GenreRepositoryImpl implements GenreRepository {

    private static final List<String> VALID_PROPERTY_NAMES = ['id', 'name']

    @Autowired
    @PersistenceContext
    EntityManager entityManager

    @Override
    @Transactional(readOnly = true)
    Optional<Genre> findById(Long id) {
        Optional.ofNullable(entityManager.find(Genre, id))
    }

    @Override
    @Transactional
    Genre save(Genre genre) {
        entityManager.persist(genre)
        genre
    }

    @Override
    @Transactional
    void delete(Long id) {
        findById(id).ifPresent(entityManager::remove)
    }

    @Transactional(readOnly = true)
    List<Genre> findAll(@NotNull Map args) {
        String qlString = 'SELECT g FROM Genre as g'
        if (args.order && args.sort && VALID_PROPERTY_NAMES.contains(args.sort)) {
            qlString += ' ORDER BY g.' + args.sort + ' ' + args.order
        }
        TypedQuery<Genre> query = entityManager.createQuery(qlString, Genre)
        query.maxResults = args.max ?: 10
        query.setFirstResult(args.offset ?: 0)

        query.resultList
    }

    @Override
    @Transactional
    int update(long id, @NotBlank String name) {
        entityManager.createQuery('UPDATE Genre g SET name = :name where id = :id')
                .setParameter('name', name)
                .setParameter('id', id)
                .executeUpdate()
    }

    @Override
    @Transactional
    Genre saveWithException(@NotBlank String name) {
        save(name)
        throw new PersistenceException()
    }

    @Override
    int count() {
        entityManager.createQuery('SELECT count(*) FROM Genre').getSingleResult() as Integer
    }
}
