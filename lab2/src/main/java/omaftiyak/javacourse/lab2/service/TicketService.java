package omaftiyak.javacourse.lab2.service;


        import omaftiyak.javacourse.lab2.model.Abonent;
        import omaftiyak.javacourse.lab2.model.Book;
        import omaftiyak.javacourse.lab2.model.Library;
        import omaftiyak.javacourse.lab2.model.Ticket;

        import java.time.LocalDateTime;

        public class TicketService {

            private BookService bookService = new BookService();
    private AbonentService abonentService = new AbonentService();

            public void takeBook(long bookId, long abonentId, int pay) {
                Book book = bookService.findBookById(bookId);
                Abonent abonent = abonentService.findAbonentById(abonentId);
                if (book == null) {
                        throw new IllegalArgumentException("Book not found");
                    }
                if (abonent == null) {
                        throw new IllegalArgumentException("Abonent not found");
                    }
                Ticket ticket = new Ticket(book.getId(), abonent.getId(),LocalDateTime.now(), null, pay);
                Library.getLibrary().getTickets().add(ticket);
            }

            public void returnBook(int bookId) {
                Ticket ticket = findTicketByBookId(bookId);
               if (ticket == null) {
                        throw new IllegalArgumentException("For provided book id there is no any open tickets");
                    }
                ticket.setDateReturning(LocalDateTime.now());
                Library.getLibrary().getTickets().remove(ticket);
                Library.getLibrary().getTicketHistory().add(ticket);
           }

            public Ticket findTicketByBookId(int bookId) {
              return   Library.getLibrary().getTickets().stream()
                        .filter(ticket -> ticket.getBookId()==bookId)
                        .findFirst()
                        .orElse(null);
            }


        }