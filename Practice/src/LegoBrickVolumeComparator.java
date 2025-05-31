import java.util.Comparator;

public class LegoBrickVolumeComparator implements Comparator<LegoBrick>{
	
	public int compare(LegoBrick lego1, LegoBrick lego2) {
	    int lego1Volume = 1;
	    int lego2Volume = 1;

	    for (int d : lego1.getDimensions()) {
	        lego1Volume *= d;
	    }
	    for (int d : lego2.getDimensions()) {
	        lego2Volume *= d;
	    }

	    return Integer.compare(lego1Volume, lego2Volume);
	}
}
