
public class Paperbook extends Book{
	private final int pages;
	
	public Paperbook(Author author, String title, int pages) {
		super(author, title);
		this.pages = pages;
	}
}
