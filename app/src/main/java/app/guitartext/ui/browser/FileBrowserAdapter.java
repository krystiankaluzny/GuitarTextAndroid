package app.guitartext.ui.browser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.guitartext.R;
import app.guitartext.ui.ListEntry;
import app.guitartext.ui.ViewHolder;
import app.guitartext.user.fileInfo.FileInfo;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class FileBrowserAdapter extends ArrayAdapter<FileListEntry>
{
	private final Context context;

	@Inject
	public FileBrowserAdapter(Context context)
	{
		super(context, 0);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ListEntry listEntry = getItem(position);

		if(listEntry == null) return convertView;

		ViewHolder viewHolder;

		if(convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.row_file_entry, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.textView.setText(listEntry.getName());
		viewHolder.imageView.setImageResource(listEntry.getIconResourceId());

		return convertView;
	}

	public void setData(List<FileInfo> fileInfoList)
	{
		List<FileListEntry> fileListEntryList = new ArrayList<>(fileInfoList.size());
		for(FileInfo fileInfo : fileInfoList)
		{
			int resourceId = fileInfo.isDirectory() ? R.drawable.folder_base : R.drawable.file_base;
			fileListEntryList.add(new FileListEntry(fileInfo, resourceId));
		}

		clear();
		addAll(fileListEntryList);
	}

}
