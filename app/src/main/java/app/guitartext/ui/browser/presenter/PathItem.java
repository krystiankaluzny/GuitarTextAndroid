package app.guitartext.ui.browser.presenter;

/**
 * Created by obywatel on 09.03.2017.
 * Modified by
 */
public class PathItem
{
	private String name;
	private Object tag;

	public PathItem(String name)
	{
		this(name, null);
	}

	public PathItem(String name, Object tag)
	{
		this.name = name;
		this.tag = tag;
	}

	public String getName()
	{
		return name;
	}

	public Object getTag()
	{
		return tag;
	}
}
