import java.util.UUID;

public abstract class Book {
	private final UUID id;
	private final Author author;
	private final String title;
	
	public Book(Author author, String title) {
		id = UUID.randomUUID();
		this.author = author;
		this.title = title;
	}
	
	public UUID getId() {
		return id;
	}
	public Author getAuthor() {
		return author;
		
	}
	public String getTitle() {
		return title;
	}
}
