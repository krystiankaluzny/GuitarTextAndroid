package app.guitartext.ui.browser.presenter.impl;

import android.app.Activity;

import app.guitartext.ui.ListEntry;
import app.guitartext.ui.browser.presenter.FileBrowserPresenter;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class FileBrowserPresenterImpl implements FileBrowserPresenter
{
	private final Activity activity;

	public FileBrowserPresenterImpl(Activity activity)
	{
		this.activity = activity;
	}

	@Override
	public int getFileCount()
	{
		return 0;
	}

	@Override
	public ListEntry getFileEntry(int filePosition)
	{
		return null;
	}

	@Override
	public void fileSelected(int filePosition)
	{

	}
}
