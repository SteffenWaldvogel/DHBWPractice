
public class EBook extends Book{
	final FileFormat fileformat;
	final int fileSizeinKb;

	public EBook(Author author, String title, FileFormat fileformat, int fileSizeinKb) throws WrongFileSizeException {
		super(author, title);
		this.fileformat = fileformat;
		if(fileSizeinKb <= 0) {
			throw new WrongFileSizeException();
		}else {
		this.fileSizeinKb = fileSizeinKb;}
	}
	public FileFormat fileformat() {
		return fileformat;
	}
	public int fileSizeinKb() {
		return fileSizeinKb;
	}
	

}
