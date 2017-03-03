package app.guitartext.util;

import java.util.Random;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class RandomString
{
	private static final char[] symbols;

	static
	{
		StringBuilder tmp = new StringBuilder();
		for(char ch = '0'; ch <= '9'; ++ch)
		{
			tmp.append(ch);
		}
		for(char ch = 'a'; ch <= 'z'; ++ch)
		{
			tmp.append(ch);
		}
		symbols = tmp.toString().toCharArray();
	}

	private Random random = new Random();

	private final char[] buf;

	public RandomString()
	{
		this(new Random().nextInt(100) + 1);
	}

	public RandomString(int length)
	{
		if(length < 1)
		{
			throw new IllegalArgumentException("length < 1: " + length);
		}
		buf = new char[length];
	}

	public String nextString()
	{
		for(int idx = 0; idx < buf.length; ++idx)
		{
			buf[idx] = symbols[random.nextInt(symbols.length)];
		}
		return new String(buf);
	}

	public String nextString(int length)
	{
		return new RandomString(length).nextString();
	}

	public static String random()
	{
		return new RandomString().nextString();
	}

	public static String randomFor(int maxLen)
	{
		return new RandomString(new Random().nextInt(maxLen) + 1).nextString();
	}
}