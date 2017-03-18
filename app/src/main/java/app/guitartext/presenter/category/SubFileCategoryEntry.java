package app.guitartext.presenter.category;

import app.guitartext.presenter.category.ExpendableListEntry;
import app.guitartext.model.fileInfo.FileInfo;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class SubFileCategoryEntry extends ExpendableListEntry
{
	private FileInfo fileInfo;

	public SubFileCategoryEntry(FileInfo fileInfo, int iconResourceId)
	{
		super(fileInfo.getName(), iconResourceId);
		this.fileInfo = fileInfo;
	}

	public SubFileCategoryEntry(String name)
	{
		super(name, android.R.drawable.btn_radio);
	}

	public SubFileCategoryEntry(String name, int iconResourceId)
	{
		super(name, iconResourceId);
	}

	public FileInfo getFileInfo()
	{
		return fileInfo;
	}
}
