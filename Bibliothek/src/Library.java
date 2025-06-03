import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public record Library(String name, Map<Book, Status> books) {

	public void addBook(Book book) {
		books.put(book, Status.AVAILABLE);
	}

	public Optional<Book> getBookByTitle(String title) {
		for (Book b : books.keySet()) {
			if (b.getTitle().equals(title)) {
				return Optional.of(b);
			}
		}
		return Optional.empty();

	}

	public List<Paperbook> getPaperbookByStatus(Status status) {
		List<Paperbook> paperBooks = new ArrayList<>();
	      for (Entry<Book, Status> entry : books.entrySet()) {
	         if (entry.getKey() instanceof Paperbook p && entry.getValue().equals(status)) {
	            paperBooks.add(p);
	         }
	      }
	      return paperBooks;
	}
}
