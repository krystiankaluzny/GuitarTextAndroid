package app.guitartext.ui.browser.presenter;

import app.guitartext.ui.ListEntry;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public interface FileBrowserPresenter
{
	int getFileCount();

	ListEntry getFileEntry(int filePosition);

	void fileSelected(int filePosition);
}
