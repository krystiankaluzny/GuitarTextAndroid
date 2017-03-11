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

	public TextElementType getTextElementType()
	{
		return textElementType;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}
}
