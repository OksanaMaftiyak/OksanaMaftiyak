import java.util.Comparator;
import java.util.Objects;

public class Book {

    private final String bookTitle;
    private final String author;
    private final int yearPublication;
    private final String language;
    private final String description;
    private final String genre;
    private final int id;
    private Ticket ticket;

    public Book() {
        this("", "", 0, "", "", "", 0000,null);
    }

    public Book(Book book) {

        this(book.bookTitle, book.author, book.yearPublication, book.language, book.description, book.genre, book.id,book.ticket);
    }

    public Book(String bookTitle, String author, int yearPublication, String language, String description, String genre, int id, Ticket ticket) {
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
            if (book1.yearPublication > book2.yearPublication) {
                return 1;
            } else if (book1.yearPublication < book2.yearPublication) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    public static Comparator<Book> AuthorComparator = new Comparator<Book>() {

        @Override
        public int compare(Book book1, Book book2) {
            return (book1.author.compareToIgnoreCase(book2.author));
        }
    };

    public static Comparator<Book> BookTitleComparator = new Comparator<Book>() {

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
}
