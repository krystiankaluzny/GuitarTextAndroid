package app.guitartext.model.lyrics;

/**
 * Created by obywatel on 10.03.2017.
 * Modified by
 */

public class TextElement
{
	private final TextElementType textElementType;
	private String text;

	public TextElement(String text, TextElementType textElementType)
	{
		this.text = text;
		this.textElementType = textElementType;
	}


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

	public TextElementType getTextElementType()
	{
		return textElementType;
	}

	public String getText()
	{
		return text;
	}
}
