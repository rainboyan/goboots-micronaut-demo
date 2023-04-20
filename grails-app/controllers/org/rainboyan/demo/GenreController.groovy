package org.rainboyan.demo

import static org.springframework.http.HttpStatus.*

class GenreController {
    GenreRepository genreRepository

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond genreRepository.findAll(params), model:[genreCount: genreRepository.count()]
    }

    def show(Long id) {
        respond genreRepository.findById(id).orElse(null)
    }

    def create() {
        respond new Genre(name: params.name)
    }

    def save(String name) {
        Genre genre = new Genre(name: name)
        genreRepository.save(genre)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'genre.label', default: 'Genre'), genre.id])
                redirect(action: 'show', id: genre.id)
            }
            '*' { respond genre, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond genreRepository.findById(id).orElse(null)
    }

    def update(Long id) {
        Genre genre = genreRepository.findById(id).get()
        if (genre == null) {
            notFound()
            return
        }

        genre.name = params.name
        genreRepository.save(genre)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'genre.label', default: 'Genre'), genre.id])
                redirect genre
            }
            '*'{ respond genre, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        genreRepository.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'genre.label', default: 'Genre'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'genre.label', default: 'Genre'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
