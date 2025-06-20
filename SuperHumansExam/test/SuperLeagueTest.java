import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

class SuperLeagueTest {

	private SuperLeague<Hero> avengers;
	@Mock
	private Hero superman;
	@Mock
	private Hero ironman;
	@Mock
	private Hero spiderman;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		avengers = new SuperLeague<>("Avengers", Universe.MARVEL, new HashMap<>());
		avengers.members().put(ironman, true);
		avengers.members().put(spiderman, false);

	}

	@Test
	public void testAddSuperHuman() {
		assertThrows(WrongUniverseException.class, () -> {
			avengers.addSuperHuman(superman);
		});
	}

	@Test
	public void testGetAllAvailableSuperHumans() {
		assert avengers.getAllAvailableSuperHumans().size() == 1;
	}

	@Test
	public void testGetMostPowerfulSuperHuman() {
		when(ironman.power()).thenReturn(7);
		when(spiderman.power()).thenReturn(8);
		assertEquals(Optional.of(spiderman), avengers.getMostPowerfulSuperHuman());
	}

}
