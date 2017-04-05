package app.guitartext.model.lyrics;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by obywatel on 10.03.2017.
 * Modified by
 */

@RequiredArgsConstructor
@Getter
public class TextElement
{
	private final String text;
	private final TextElementType textElementType;


	public static TextElement asChord(String chord)
	{
		return new TextElement(chord, TextElementType.CHORDS);
	}

	public static TextElement asText(String text)
	{
		return new TextElement(text, TextElementType.TEXT);
	}

	public static TextElement asBracket(String bracket)
	{
		return new TextElement(bracket, TextElementType.BRACKET);
	}
}
