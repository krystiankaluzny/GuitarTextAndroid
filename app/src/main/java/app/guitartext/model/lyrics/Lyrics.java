package app.guitartext.model.lyrics;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by obywatel on 10.03.2017.
 * Modified by
 */

@RequiredArgsConstructor
@Getter
public class Lyrics
{
	private final List<LyricLine> lines;
	private final int shift;
}
