package app.guitartext.presenter.text;

import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.lyrics.Lyrics;

/**
 * Created by obywatel on 09.03.2017.
 * Modified by
 */

public interface TextPresenter
{
	void prepareFile(FileInfo fileInfo);

	void onChordShiftGesture(float dX);

	interface View
	{
		void onLyricsUpdated(Lyrics lyrics);
	}
}
