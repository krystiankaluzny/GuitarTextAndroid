package app.guitartext.ui.browser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import javax.inject.Inject;

import app.guitartext.R;
import app.guitartext.ui.ListEntry;
import app.guitartext.ui.ViewHolder;
import app.guitartext.ui.browser.presenter.FileBrowserPresenter;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class FileBrowserAdapter extends BaseAdapter
{
	private final Context context;
	private final FileBrowserPresenter fileBrowserPresenter;

	@Inject
	public FileBrowserAdapter(Context context, FileBrowserPresenter fileBrowserPresenter)
	{
		this.context = context;
		this.fileBrowserPresenter = fileBrowserPresenter;
	}

	@Override
	public int getCount()
	{
		return fileBrowserPresenter.getFileCount();
	}

	@Override
	public Object getItem(int position)
	{
		return fileBrowserPresenter.getFileEntry(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ListEntry listEntry = fileBrowserPresenter.getFileEntry(position);

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

}
