import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> getBooksByAuthor(String author) {
        return books.stream()
                    .filter(b -> author.equals(b.getAuthor()))
                    .toList();
    }

    public List<Book> getBooksAfterYear(int year) {
        return books.stream()
                    .filter(b -> b.getYear() > year)
                    .toList();
    }

    public List<Book> getBooksSortedByTitle() {
        return books.stream()
                    .sorted(Comparator.comparing(Book::getTitle))
                    .toList();
    }

    public List<Book> getBooksSortedByYearDescending() {
        return books.stream()
                    .sorted(Comparator.comparingInt(Book::getYear).reversed())
                    .toList();
    }

    public Optional<Book> getTopLongestBook() {
        return books.stream()
                    .max(Comparator.comparingInt(Book::getPages));
    }

    public Map<String, List<Book>> groupBooksByAuthor() {
        return books.stream()
                    .collect(Collectors.groupingBy(Book::getAuthor));
    }

    public Optional<Book> findOldestBook() {
        return books.stream()
                    .min(Comparator.comparingInt(Book::getYear));
    }

    public double averagePages() {
        return books.stream()
                    .mapToInt(Book::getPages)
                    .average()
                    .orElse(0.0);
    }

    public List<Book> getBooksByTitleKeyword(String keyword) {
        String lower = keyword.toLowerCase();
        return books.stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(lower))
                    .toList();
    }

    public List<Book> getPage(int pageNumber, int pageSize) {
        return books.stream()
                    .skip((long) pageNumber * pageSize)
                    .limit(pageSize)
                    .toList();
    }

    /**
     * Lädt die CSV-Datei mit dem gegebenen Namen aus einem der folgenden Pfade:
     * - Aktuelles Verzeichnis
     * - Übergeordnetes Verzeichnis
     * - src-Ordner im Projekt
     * @param fileName Name der CSV-Datei
     * @return Gefüllte Library
     * @throws IOException wenn die Datei nicht gefunden oder nicht gelesen werden kann
     */
    public static Library loadFromCsv(String fileName) throws IOException {
        String sep = System.getProperty("file.separator");
        String[] candidates = {
            fileName,
            ".." + sep + fileName,
            "src" + sep + fileName
        };

        Path path = null;
        for (String candidate : candidates) {
            Path p = Path.of(candidate);
            if (Files.exists(p)) {
                path = p;
                break;
            }
        }
        if (path == null) {
            throw new IOException("CSV-Datei '" + fileName + "' nicht gefunden. Suche in: " + java.util.Arrays.toString(candidates));
        }
        System.out.println("Lade CSV von: " + path.toAbsolutePath());

        Library library = new Library();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.lines()
                  .skip(1)
                  .forEach(line -> {
                      String[] parts = line.split(",", -1);
                      String title  = parts[0];
                      String author = parts[1];
                      int year       = Integer.parseInt(parts[2]);
                      int pages      = Integer.parseInt(parts[3]);
                      library.addBook(new Book(title, author, year, pages));
                  });
        }
        return library;
    }

    public static void main(String[] args) {
    String csvFile = (args.length > 0) ? args[0] : "books.csv";
    System.out.println("Arbeitsverzeichnis: " + System.getProperty("user.dir"));
    try {
        Library lib = loadFromCsv(csvFile);
        System.out.println("--- Alle Bücher ---");
        lib.getAllBooks().forEach(b ->
            System.out.println(b.getTitle() + " by " + b.getAuthor() +
                               " (" + b.getYear() + ")" + ", " + b.getPages() + " Seiten")
        );

        System.out.println("--- Bücher von Jane Austen ---");
        lib.getBooksByAuthor("Jane Austen").forEach(b ->
            System.out.println(b.getTitle())
        );

        System.out.println("--- Bücher nach 1950 ---");
        lib.getBooksAfterYear(1950).forEach(b ->
            System.out.println(b.getTitle() + " (" + b.getYear() + ")")
        );

        System.out.println("--- Bücher sortiert nach Jahr (absteigend) ---");
        lib.getBooksSortedByYearDescending().forEach(b ->
            System.out.println(b.getTitle() + " (" + b.getYear() + ")")
        );

        System.out.println("--- Längstes Buch ---");
        lib.getTopLongestBook().ifPresent(b ->
            System.out.println(b.getTitle() + " mit " + b.getPages() + " Seiten")
        );

        System.out.println("--- Ältestes Buch ---");
        lib.findOldestBook().ifPresent(b ->
            System.out.println(b.getTitle() + " von " + b.getYear())
        );

        System.out.println("--- Durchschnittliche Seitenanzahl: " + lib.averagePages());

        System.out.println("--- Bücher mit Keyword 'the' im Titel ---");
        lib.getBooksByTitleKeyword("the").forEach(b ->
            System.out.println(b.getTitle())
        );

        System.out.println("--- Seite 1 (2 Bücher pro Seite) ---");
        lib.getPage(0, 2).forEach(b ->
            System.out.println(b.getTitle())
        );

    } catch (IOException e) {
        System.err.println("Fehler beim Laden der CSV: " + e.getMessage());
    }
   }
}
