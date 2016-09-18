import java.util.Comparator;
import java.util.Objects;

public class Book {

    public static final String EMPTY_STRING = "";
    private final String bookTitle;
    private final String author;
    private final int yearPublication;
    private final String language;
    private final String description;
    private final String genre;
    private final int id;
    private Ticket ticket;

    private static int nextId = 1;

    public Book() {
        this(0, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, null);
    }

    public Book(int yearPublication, String author, String bookTitle, String description, String language, String genre, Ticket ticket) {
        this(nextId++, yearPublication, author, bookTitle, description, language, genre, ticket);
    }

    public Book(Book book) {
        this(book.id, book.yearPublication, book.author, book.bookTitle, book.description, book.language, book.genre, book.ticket);
    }

    private Book(int id, int yearPublication, String author, String bookTitle, String description, String language, String genre, Ticket ticket) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.yearPublication = yearPublication;
        this.language = language;
        this.description = description;
        this.genre = genre;
        this.id = id;
        this.ticket = ticket;
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

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("title: ").append(bookTitle);
        sb.append("author: ").append(author);
        sb.append("year: ").append(yearPublication);
        sb.append("language: ").append(language);
        sb.append("description: ").append(description);
        sb.append("genre: ").append(genre);
        sb.append("id: ").append(id);
        sb.append("has ticket: ").append(ticket == null ? "no" : "yes");
        sb.append("]");
        return sb.toString();
    }

}
