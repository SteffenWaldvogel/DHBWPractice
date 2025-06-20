import java.security.KeyStore.Entry;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record SuperLeague<T extends SuperHuman>(String name, Universe universe, Map<T, Boolean> members) {
	public void addSuperHuman(T t) throws WrongUniverseException {
		if (!t.universe().equals(universe)) {
			throw new WrongUniverseException();
		} else {
			members.put(t, true);
		}
	}

	public Optional<T> getMostPowerfulSuperHuman() {
		return members.entrySet().stream().map(Map.Entry::getKey).max(Comparator.comparing(SuperHuman::power));
	}

	public List<T> getAllAvailableSuperHumans() {
		return members.entrySet().stream().filter(Map.Entry::getValue)).map(Map.Entry::getKey).toList();
	}

}
