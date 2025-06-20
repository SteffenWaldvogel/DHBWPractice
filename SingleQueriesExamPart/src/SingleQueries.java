import java.time.LocalDate;
import java.util.List;
import java.util.OptionalDouble;

public record SingleQueries(List<Single> singles) {

	public void printAllSinglesWithMoreThan25MillionSalesPerCountry() {
		singles.stream().filter(s -> s.salesInMillions() > 25).map(Single::artist).map(Artist::country).sorted()
				.forEach(System.out::println);
	}

	public void printAverageBirthYearOfAllDeceasedArtist() {
		OptionalDouble average = singles.stream().map(Single::artist).filter(artist -> !artist.isAlive())
				.mapToInt(artist -> artist.birthdate().getYear()).average();

		if (average.isPresent()) {
			System.out.println("Durchschnittliches Geburtsjahr: " + average.getAsDouble());
		} else {
			System.out.println(-1);
		}
	}

	public boolean isAnySingleFromChinaWithMoreThan10MillionSales() {
		return singles.stream().filter(s -> s.salesInMillions() > 10).anyMatch(s -> s.artist().country().equals("CHN"));
	}

	public List<Single> getAllSinglesFromEdSheeran() {
		Artist sheeran = new Artist("Ed Sheeran", Counttry.GBR, LocalDate.of(1991, 2, 17), true);
		List<Single> edSheeranSingles = singles.stream().filter(s -> s.artist().equals(sheeran)).toList();
		return edSheeranSingles;
	}

}
