package app.guitartext.presenter.text.impl;

import com.annimon.stream.Optional;

import javax.inject.Inject;

import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.lyrics.Lyrics;
import app.guitartext.model.lyrics.LyricsService;
import app.guitartext.model.user.UserFileService;
import app.guitartext.presenter.text.TextPresenter;

/**
 * Created by obywatel on 09.03.2017.
 * Modified by
 */

public class TextPresenterImpl implements TextPresenter
{
	private final View view;
	private final LyricsService lyricsService;
	private final UserFileService userFileService;
	private Optional<Lyrics> lyrics;

	@Inject
	public TextPresenterImpl(View view, LyricsService lyricsService, UserFileService userFileService)
	{
		this.view = view;
		this.lyricsService = lyricsService;
		this.userFileService = userFileService;
	}

	@Override
	public void prepareFile(FileInfo fileInfo)
	{
		lyrics = lyricsService.readLyrics(fileInfo);
		lyrics.ifPresentOrElse(l -> {
					view.onLyricsUpdated(l);
					userFileService.fileOpened(fileInfo);
				},
				() -> view.onCannotRead(fileInfo.getName()));
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
