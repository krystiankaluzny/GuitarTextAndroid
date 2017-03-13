package app.guitartext.model.lyrics;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by obywatel on 12.03.2017.
 * Modified by
 */

/*package*/ class ChordShifter
{
	private static final String[] DELIMITERS = { " ", ",", "(", ")", "-", ";", "/" };
	static private String[] MAJOR_CHORDS = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A",
			"B", "H" };
	public static final int CHORDS_COUNT = MAJOR_CHORDS.length;
	private static ImmutableMap<String, Integer> CHORD_INDEX_MAP;

	static
	{
		ImmutableMap.Builder<String, Integer> builder = ImmutableMap.builder();
		for(int i = 0; i < MAJOR_CHORDS.length; i++)
		{
			builder.put(MAJOR_CHORDS[i], i);
		}
		CHORD_INDEX_MAP = builder.build();
	}

	/**
	 * Round to 0-11 range
	 *
	 * @param shift
	 * @return
	 */
	/*package*/
	static int roundShift(int shift)
	{
		return (shift = shift % ChordShifter.CHORDS_COUNT) < 0 ? shift + ChordShifter.CHORDS_COUNT : shift;
	}

	/*package*/
	static String shift(String chords, int shift)
	{
		return Joiner.on("").join(shift(ChordWithDelimiter.fromString(chords, DELIMITERS), roundShift(shift)));
	}

	private static List<String> shift(List<ChordWithDelimiter> chordList, final int shift)
	{
		return Lists.transform(chordList, input -> shiftChord(input.chord, shift) + input.nextDelimiter);
	}

	private static String shiftChord(String chord, int shift)
	{
		if(Strings.isNullOrEmpty(chord)) return "";

		boolean isMinor = Character.isLowerCase(chord.charAt(0));

		String chordBase = String.valueOf(Character.toUpperCase(chord.charAt(0)));
		if(chord.indexOf('#', 1) == 1 || chord.indexOf("is", 1) == 1)
		{
			chordBase += '#';
		}

		String chordSuffix = chord.substring(chordBase.length());

		Integer chordIndex = CHORD_INDEX_MAP.get(chordBase);
		if(chordIndex == null) return chord;

		int shiftedChordIndex = roundShift(chordIndex + shift);

		String chordBaseShifted = MAJOR_CHORDS[shiftedChordIndex];

		if(isMinor)
		{
			chordBaseShifted = chordBaseShifted.toLowerCase();
		}

		return chordBaseShifted + chordSuffix;
	}
}
