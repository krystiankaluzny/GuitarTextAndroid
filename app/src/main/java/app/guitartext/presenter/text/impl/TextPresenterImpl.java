package app.guitartext.presenter.text.impl;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.inject.Inject;

import app.guitartext.presenter.text.TextPresenter;
import app.guitartext.model.fileInfo.FileInfo;

/**
 * Created by obywatel on 09.03.2017.
 * Modified by
 */

public class TextPresenterImpl implements TextPresenter
{
	private final View view;

	@Inject
	public TextPresenterImpl(View view)
	{
		this.view = view;
	}

	@Override
	public void prepareFile(FileInfo fileInfo)
	{
		view.onTextRead(readFile(fileInfo));
	}

	private String readFile(FileInfo fileInfo)
	{
		String string = "";
		try
		{
			string = Files.toString(new File(fileInfo.getPath()), Charset.forName("UTF-8"));
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		return string;
	}
}
