package app.guitartext.model.fileInfo;

import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by obywatel on 10.03.2017.
 * Modified by
 */

public class FileInfoManager
{
	private static final FileInfo ROOT_LOCATION = new FileInfo(0, true, "/", "/");

	@Inject
	public FileInfoManager()
	{
	}

	public static FileInfo getRootLocation()
	{
		return ROOT_LOCATION;
	}

	/**
	 * If {@code fileInfo} is valid directory return it.
	 * If {@code fileInfo} is valid file function return it's parent directory.
	 * If {{@code fileInfo} is invalid file info for root will be return.
	 *
	 * @param fileInfo
	 * @return
	 */
	public FileInfo getValidateDirectory(FileInfo fileInfo)
	{
		if(fileInfo == null) return ROOT_LOCATION;

		File file = new File(fileInfo.getPath());
		if(!file.exists())
		{
			return ROOT_LOCATION;
		}

		if(!file.isDirectory())
		{
			file = file.getParentFile();
			return new FileInfo(0, true, file.getAbsolutePath(), file.getName());
		}

		return fileInfo;
	}

	public List<FileInfo> getLocationContent(FileInfo location)
	{
		File file = new File(location.getPath());
		File[] subFiles = file.listFiles();

		List<FileInfo> locationContent;
		if(subFiles == null || subFiles.length == 0)
		{
			return Collections.emptyList();
		}

		locationContent = new ArrayList<>(subFiles.length);
		int i = 0;
		for(File subFile : subFiles)
		{
			locationContent.add(createFileInfo(i, subFile));
			i++;
		}

		return locationContent;
	}

	public List<FileInfo> getFileWithAncestors(FileInfo fileInfo)
	{
		LinkedList<FileInfo> pathItemList = new LinkedList<>();

		File file = new File(fileInfo.getPath());

		FileInfo info = createFileInfo(0, file);

		while(file != null && !TextUtils.isEmpty(info.getName()))
		{
			pathItemList.addFirst(fileInfo);
			file = file.getParentFile();
			info = createFileInfo(0, file);
		}

		pathItemList.addFirst(ROOT_LOCATION);
		return pathItemList;
	}

	private FileInfo createFileInfo(int position, File file)
	{
		if(file == null) return new FileInfo(position, false, "", "");
		return new FileInfo(position, file.isDirectory(), file.getAbsolutePath(), file.getName());
	}
}
