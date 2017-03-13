package app.guitartext.model.lyrics;

import java.util.List;

/**
 * Created by obywatel on 10.03.2017.
 * Modified by
 */

public class Lyrics
{
	private final List<LyricLine> lines;
	private final int shift;

	public Lyrics(List<LyricLine> lines, int shift)
	{
		this.lines = lines;
		this.shift = ChordShifter.roundShift(shift);
	}

	public List<LyricLine> getLines()
	{
		return lines;
	}

	public int getShift()
	{
		return shift;
	}
}
