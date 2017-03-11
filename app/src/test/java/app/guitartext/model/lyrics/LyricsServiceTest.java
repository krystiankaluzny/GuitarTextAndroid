package app.guitartext.model.lyrics;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by obywatel on 11.03.2017.
 * Modified by
 */
public class LyricsServiceTest
{
	@Test
	public void createLine() throws Exception
	{
		LyricsService lyricsService = new LyricsService(null);

		String line;
		LyricLine lyricLine;

		line = "Jakiś tam tekst";
		lyricLine = lyricsService.createLine(line);

		line = "Jakiś [tam tekst";
		lyricLine = lyricsService.createLine(line);

		line = "Jakiś [tam ] tekst";
		lyricLine = lyricsService.createLine(line);

		line = "Jakiś [ta[m ] tekst";
		lyricLine = lyricsService.createLine(line);

		line = "Jakiś [ta[m ]] tekst";
		lyricLine = lyricsService.createLine(line);

		line = "Jakiś [ ta [ m ]sda] tekst";
		lyricLine = lyricsService.createLine(line);

		line = "Jakiś [ta[m ]] tek]st";
		lyricLine = lyricsService.createLine(line);
	}

}