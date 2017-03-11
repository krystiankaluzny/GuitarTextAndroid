package app.guitartext.model.lyrics;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.fileInfo.FileInfoService;

/**
 * Created by obywatel on 10.03.2017.
 * Modified by
 */

public class LyricsService
{
	private final FileInfoService fileInfoService;
	private static final char OPENING_BRACKET = '[';
	private static final char CLOSING_BRACKET = ']';

	@Inject
	public LyricsService(FileInfoService fileInfoService)
	{
		this.fileInfoService = fileInfoService;
	}

	public Lyrics readLyrics(FileInfo fileInfo)
	{
		List<String> lines = fileInfoService.readFileLines(fileInfo);
		List<LyricLine> lyricLines = Lists.transform(lines, this::createLine);
		return new Lyrics(lyricLines, 0);
	}

	public LyricLine createLine(String line)
	{
		LyricLineBuilder lyricLineBuilder = new LyricLineBuilder(line);

		int startIndex = 0;

		int bracketDepth = 0;
		for(int i = 0; i < line.length(); i++)
		{
			char singleChar = line.charAt(i);
			if(singleChar == OPENING_BRACKET)
			{
				if(i > startIndex)
				{
					if(bracketDepth == 0)
					{
						lyricLineBuilder.addText(startIndex, i);
					}
					else
					{
						lyricLineBuilder.addChord(startIndex, i);
					}
				}

				lyricLineBuilder.addBracket(i);
				bracketDepth++;
				startIndex = i + 1; //start after bracket
			}

			if(singleChar == CLOSING_BRACKET)
			{
				if(bracketDepth > 0)
				{
					if(i > startIndex)
					{
						lyricLineBuilder.addChord(startIndex, i);
					}

					lyricLineBuilder.addBracket(i);
					bracketDepth--;
					startIndex = i + 1; //start after bracket
				}
			}
		}

		if(startIndex < line.length())
		{
			lyricLineBuilder.addText(startIndex, line.length());
		}
		return lyricLineBuilder.build();
	}

	private class LyricLineBuilder
	{
		List<TextElement> textElementList = new ArrayList<>();

		final String line;

		private LyricLineBuilder(String line)
		{
			this.line = line;
		}

		void addText(int start, int end)
		{
			add(start, end, TextElementType.TEXT);
		}

		void addBracket(int position)
		{
			add(position, position + 1, TextElementType.BRACKET);
		}

		void addChord(int start, int end)
		{
			add(start, end, TextElementType.CHORD);
		}

		void add(int start, int end, TextElementType type)
		{
			TextElement textElement = new TextElement(line.substring(start, end), type);
			textElementList.add(textElement);
		}

		LyricLine build()
		{
			return new LyricLine(textElementList);
		}
	}
}
