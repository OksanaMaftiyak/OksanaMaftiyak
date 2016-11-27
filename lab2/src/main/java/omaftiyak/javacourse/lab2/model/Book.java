package omaftiyak.javacourse.lab2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import omaftiyak.javacourse.lab2.common.IdGenerator;

import java.util.Comparator;
import java.util.Objects;

public class Book {

    public static final String EMPTY_STRING = "";

    private long id;
    private String bookTitle;
    private String author;
    private int yearPublication;
    private String genre;
    private String description;
    private String language;

    private static final IdGenerator ID_GENERATOR = new IdGenerator();

    public Book() {
        this(0, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
    }

    public Book(int yearPublication, String author, String bookTitle, String description, String language, String genre) {
        this(ID_GENERATOR.nextId(), yearPublication, author, bookTitle, description, language, genre);
    }

    public Book(Book book) {
        this(book.id, book.yearPublication, book.author, book.bookTitle, book.description, book.language, book.genre);
    }

    public Book(long id, int yearPublication, String author, String bookTitle, String description, String language, String genre) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.yearPublication = yearPublication;
        this.language = language;
        this.description = description;
        this.genre = genre;
        this.id = id;
    }

    private Book(String str) {
        this(str.split("/"));
    }

    public Book(String[] parts) {
        int fieldIndex = 0;
        this.id = getId();
        this.bookTitle = parts[fieldIndex++];
        this.author = parts[fieldIndex++];
        this.language = parts[fieldIndex++];
        this.description = parts[fieldIndex++];
        this.genre = parts[fieldIndex++];
        this.yearPublication = Integer.parseInt(parts[fieldIndex++]);
    }

    @JsonIgnore
    public String[] getParts() {
        return new String[]{bookTitle, author, language, description, genre, String.valueOf(yearPublication)};
    }

    public static Comparator<Book> YearComparator = new Comparator<Book>() {

        @Override
        public int compare(Book book1, Book book2) {
            return book1.yearPublication - book2.yearPublication;
        }

    };

    public static Comparator<Book> AuthorComparator = new Comparator<Book>() {

        @Override
        public int compare(Book book1, Book book2) {
            return (book1.author.compareToIgnoreCase(book2.author));
        }

    };

    public static Comparator<Book> TitleComparator = new Comparator<Book>() {

        @Override
        public int compare(Book book1, Book book2) {
            return (book1.bookTitle.compareToIgnoreCase(book2.bookTitle));
        }

    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return yearPublication == book.yearPublication &&
                Objects.equals(bookTitle, book.bookTitle) &&
                Objects.equals(author, book.author) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(description, book.description) &&
                Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("id: ").append(id);
        sb.append(", title: ").append(bookTitle);
        sb.append(", author: ").append(author);
        sb.append(", year: ").append(yearPublication);
        sb.append(", genre: ").append(genre);
        sb.append(", description: ").append(description);
        sb.append(", language: ").append(language);
        sb.append("]");
        return sb.toString();
    }

}
