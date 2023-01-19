package com.example.spring5webapp.bootstrap;

import com.example.spring5webapp.domain.Author;
import com.example.spring5webapp.domain.Book;
import com.example.spring5webapp.domain.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher mcghill = new Publisher();
        mcghill.setName("McGraw Hill");
        mcghill.setAddressLine1("145 King St W");
        mcghill.setCity("Toronto");
        mcghill.setState("ON");
        mcghill.setZip("M5H 1J8");

        publisherRepository.save(mcghill);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        mcghill.getBooks().add(ddd);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(mcghill);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(mcghill);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");

        mcghill.getBooks().add(noEJB);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(mcghill);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(mcghill);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Number of books published by " + mcghill.getName() + ": " + mcghill.getBooks().size());
    }
}
