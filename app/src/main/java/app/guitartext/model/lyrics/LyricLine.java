package app.guitartext.model.lyrics;

import java.util.List;

/**
 * Created by obywatel on 10.03.2017.
 * Modified by
 */

public class LyricLine
{
	private List<TextElement> textElements;

	public LyricLine(List<TextElement> textElements)
	{
		this.textElements = textElements;
	}

	public List<TextElement> getTextElements()
	{
		return textElements;
	}
}
