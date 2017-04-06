package app.guitartext.presenter.text.impl;

import com.annimon.stream.Optional;

import javax.inject.Inject;

import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.lyrics.Lyrics;
import app.guitartext.model.lyrics.LyricsService;
import app.guitartext.presenter.text.TextPresenter;

/**
 * Created by obywatel on 09.03.2017.
 * Modified by
 */

public class TextPresenterImpl implements TextPresenter
{
	private final View view;
	private final LyricsService lyricsService;
	private Optional<Lyrics> lyrics;

	@Inject
	public TextPresenterImpl(View view, LyricsService lyricsService)
	{
		this.view = view;
		this.lyricsService = lyricsService;
	}

	@Override
	public void prepareFile(FileInfo fileInfo)
	{
		lyrics = lyricsService.readLyrics(fileInfo);
		lyrics.ifPresentOrElse(view::onLyricsUpdated, () -> view.onCannotRead(fileInfo.getName()));
	}

	@Override
	public void onChordShiftGesture(float dX)
	{
		int shift = dX > 0 ? 1 : -1;
		lyrics.ifPresent(l -> {
			lyrics = Optional.of(lyricsService.shiftChordsBy(l, shift));
			view.onLyricsUpdated(lyrics.get());
		});
	}
}
