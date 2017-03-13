package app.guitartext.ui.browser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.guitartext.R;
import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.presenter.ListEntry;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class FileBrowserAdapter extends RecyclerView.Adapter<FileBrowserAdapter.ViewHolder>
{
	private static final ListEntry EMPTY_ITEM = new ListEntry("", 0);

	private final Context context;
	private ArrayList<FileListEntry> fileListEntryList = new ArrayList<>();

	public static class ViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.textView) public TextView textView;
		@BindView(R.id.imageView) public ImageView imageView;

		public ViewHolder(View view)
		{
			super(view);
			ButterKnife.bind(this, view);
		}
	}

	@Inject
	public FileBrowserAdapter(Context context)
	{
		this.context = context;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		View rowView = inflater.inflate(R.layout.row_file_entry, parent, false);
		return new ViewHolder(rowView);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		ListEntry listEntry = getItem(position);

		holder.textView.setText(listEntry.getName());
		holder.imageView.setImageResource(listEntry.getIconResourceId());
	}

	@Override
	public int getItemCount()
	{
		return fileListEntryList.size();
	}

	/**
	 * Return EMPTY_ITEM if item given position not exist or is null.
	 * It'a prevent {@link NullPointerException} and {@link IndexOutOfBoundsException}.
	 *
	 * @param position
	 * @return valid {@link ListEntry} item or EMPTY_ITEM
	 */
	private ListEntry getItem(int position)
	{
		if(position < 0 || position >= getItemCount()) return EMPTY_ITEM;
		ListEntry item = fileListEntryList.get(position);

		return item != null ? item : EMPTY_ITEM;
	}

	public void setData(List<FileInfo> fileInfoList)
	{
		fileListEntryList.clear();
		fileListEntryList.ensureCapacity(fileInfoList.size());

		for(FileInfo fileInfo : fileInfoList)
		{
			int resourceId = fileInfo.isDirectory() ? R.drawable.folder_base : R.drawable.file_base;
			fileListEntryList.add(new FileListEntry(fileInfo, resourceId));
		}

		notifyDataSetChanged();
	}
}
