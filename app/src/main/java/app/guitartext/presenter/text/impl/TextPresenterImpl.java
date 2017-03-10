package app.guitartext.presenter.text.impl;

import javax.inject.Inject;

import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.fileInfo.FileInfoService;
import app.guitartext.presenter.text.TextPresenter;

/**
 * Created by obywatel on 09.03.2017.
 * Modified by
 */

public class TextPresenterImpl implements TextPresenter
{
	private final View view;
	private final FileInfoService fileInfoService;

	@Inject
	public TextPresenterImpl(View view, FileInfoService fileInfoService)
	{
		this.view = view;
		this.fileInfoService = fileInfoService;
	}

	@Override
	public void prepareFile(FileInfo fileInfo)
	{
		view.onTextRead(fileInfoService.readFile(fileInfo));
	}
}
