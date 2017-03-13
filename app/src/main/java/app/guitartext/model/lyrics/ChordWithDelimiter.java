package app.guitartext.model.lyrics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obywatel on 12.03.2017.
 * Modified by
 */
/*package*/ class ChordWithDelimiter
{
	final String chord;
	final String nextDelimiter;

	/*package*/ ChordWithDelimiter(String chord, String nextDelimiter)
	{
		this.chord = chord;
		this.nextDelimiter = nextDelimiter;
	}

	/*package*/
	static List<ChordWithDelimiter> fromString(String chords, String[] DELIMITERS)
	{
		List<ChordWithDelimiter> result = new ArrayList<>(1);

		int lowestDelimiterPosition = Integer.MAX_VALUE;
		String firstDelimiter = "";

		for(String delimiter : DELIMITERS)
		{
			int index = chords.indexOf(delimiter);
			if(index != -1 && index < lowestDelimiterPosition)
			{
				lowestDelimiterPosition = index;
				firstDelimiter = delimiter;
			}
		}

		if(lowestDelimiterPosition == Integer.MAX_VALUE)
		{
			result.add(new ChordWithDelimiter(chords, ""));
		}
		else
		{
			String firstChord = chords.substring(0, lowestDelimiterPosition);
			String restChords = chords.substring(lowestDelimiterPosition + firstDelimiter.length());

			result.add(new ChordWithDelimiter(firstChord, firstDelimiter));
			result.addAll(fromString(restChords, DELIMITERS));
		}

		return result;
	}
}
