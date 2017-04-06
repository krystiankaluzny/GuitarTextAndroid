package app.guitartext.model.lyrics;

import com.annimon.stream.Optional;
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

	public Optional<Lyrics> readLyrics(FileInfo fileInfo)
	{
		return fileInfoService.readFileLines(fileInfo)
				.map(l -> Lists.transform(l, this::createLine))
				.map(l -> new Lyrics(l, 0));
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

	public Lyrics shiftChordsBy(Lyrics lyrics, int shift)
	{
		List<LyricLine> lines = new ArrayList<>(lyrics.getLines().size());
		for(LyricLine lyricLine : lyrics.getLines())
		{
			List<TextElement> textElementList = new ArrayList<>(lyricLine.getTextElements().size());

			for(TextElement textElement : lyricLine.getTextElements())
			{
				if(TextElementType.CHORDS.equals(textElement.getTextElementType()))
				{
					textElementList.add(TextElement.asChord(ChordShifter.shift(textElement.getText(), shift)));
				}
				else
				{
					textElementList.add(textElement);
				}
			}

			lines.add(new LyricLine(textElementList));
		}

		return new Lyrics(lines, lyrics.getShift() + shift);
	}
}
