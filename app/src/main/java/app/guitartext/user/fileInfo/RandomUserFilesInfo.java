package app.guitartext.user.fileInfo;

import app.guitartext.util.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class RandomUserFilesInfo implements UserFilesInfo
{
	private List<FileInfo> baseList = new ArrayList<>();
	private List<FileInfo> favouriteList = new ArrayList<>();
	private List<FileInfo> recentList = new ArrayList<>();

	private static final Random random = new Random();

	public RandomUserFilesInfo()
	{
		fillRandom(baseList, random.nextInt(5) + 1);
		fillRandom(favouriteList, random.nextInt(10) + 1);
		fillRandom(recentList, random.nextInt(15) + 1);
	}

	@Override
	public List<FileInfo> getBaseFiles()
	{
		return baseList;
	}

	@Override
	public List<FileInfo> getFavouriteFiles()
	{
		return favouriteList;
	}

	@Override
	public List<FileInfo> getRecentOpenedFiles()
	{
		return recentList;
	}

	private void fillRandom(List<FileInfo> list, int size)
	{
		for(int i = 0; i < size; i++)
		{
			list.add(new FileInfo(i, random.nextBoolean(), RandomString.randomFor(20), RandomString.randomFor(8)));
		}
	}
}
