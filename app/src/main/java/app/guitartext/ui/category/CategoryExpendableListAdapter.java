package app.guitartext.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import javax.inject.Inject;

import app.guitartext.R;
import app.guitartext.presenter.category.ExpendableListEntry;
import app.guitartext.presenter.category.FileCategoryEntry;
import app.guitartext.presenter.category.FileCategoryPresenter;
import app.guitartext.presenter.category.SubFileCategoryEntry;
import app.guitartext.ui.ViewHolder;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class CategoryExpendableListAdapter extends BaseExpandableListAdapter
{
	private final Context context;
	private final FileCategoryPresenter fileCategoryPresenter;
	private List<FileCategoryEntry> categoryEntryList;

	@Inject
	public CategoryExpendableListAdapter(Context context, FileCategoryPresenter fileCategoryPresenter)
	{
		this.context = context;
		this.fileCategoryPresenter = fileCategoryPresenter;
	}

	@Override
	public int getGroupCount()
	{
		return categoryEntryList == null ? 0 : categoryEntryList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		if(categoryEntryList == null) return 0;
		if(groupPosition < 0 || groupPosition >= categoryEntryList.size()) return 0;
		return categoryEntryList.get(groupPosition).getSubFileCategoryEntryList().size();
	}

	@Override
	public Object getGroup(int groupPosition)
	{
		if(groupPosition < 0 || groupPosition >= categoryEntryList.size())
			return null;

		return categoryEntryList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition)
	{
		if(groupPosition < 0 || groupPosition >= categoryEntryList.size()) return null;
		List<SubFileCategoryEntry> subCategories = categoryEntryList.get(groupPosition).getSubFileCategoryEntryList();
		if(childPosition < 0 || childPosition >= subCategories.size()) return null;
		return subCategories.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}

	@Override
	public boolean hasStableIds()
	{
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
	{
		ExpendableListEntry groupEntry = (ExpendableListEntry) getGroup(groupPosition);

		if(groupEntry == null) return convertView;

		GroupViewHolder groupViewHolder;

		if(convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.row_group_entry, parent, false);
			groupViewHolder = new GroupViewHolder(convertView);
			convertView.setTag(groupViewHolder);
		}
		else
		{
			groupViewHolder = (GroupViewHolder) convertView.getTag();
		}

		groupViewHolder.textView.setText(groupEntry.getName());
		groupViewHolder.imageView.setImageResource(groupEntry.getIconResourceId());
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
	{
		ExpendableListEntry childEntry = (ExpendableListEntry) getChild(groupPosition, childPosition);

		if(childEntry == null) return convertView;

		ChildViewHolder childViewHolder;
		if(convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.row_child_entry, parent, false);

			childViewHolder = new ChildViewHolder(convertView);
			convertView.setTag(childViewHolder);
		}
		else
		{
			childViewHolder = (ChildViewHolder) convertView.getTag();
		}

		childViewHolder.textView.setText(childEntry.getName());
		childViewHolder.imageView.setImageResource(childEntry.getIconResourceId());

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return true;
	}

	public void setData(List<FileCategoryEntry> categoryEntryList)
	{
		this.categoryEntryList = categoryEntryList;
		notifyDataSetChanged();
	}

	static class GroupViewHolder extends ViewHolder
	{
		GroupViewHolder(View view)
		{
			super(view);
		}
	}

	private static class ChildViewHolder extends ViewHolder
	{
		ChildViewHolder(View view)
		{
			super(view);
		}
	}
}
