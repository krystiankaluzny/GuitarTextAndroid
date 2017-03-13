package app.guitartext.model.lyrics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obywatel on 11.03.2017.
 * Modified by
 */
/*package*/ class LyricLineBuilder
{
	private List<TextElement> textElementList = new ArrayList<>();

	private final String line;

	/*package*/ LyricLineBuilder(String line)
	{
		this.line = line;
	}

	/*package*/ void addText(int start, int end)
	{
		add(start, end, TextElementType.TEXT);
	}

	/*package*/ void addBracket(int position)
	{
		add(position, position + 1, TextElementType.BRACKET);
	}

	/*package*/ void addChord(int start, int end)
	{
		add(start, end, TextElementType.CHORDS);
	}

	/*package*/ void add(int start, int end, TextElementType type)
	{
		TextElement textElement = new TextElement(line.substring(start, end), type);
		textElementList.add(textElement);
	}

	/*package*/LyricLine build()
	{
		return new LyricLine(textElementList);
	}
}
