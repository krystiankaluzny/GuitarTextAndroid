package org.obywatel.guitartext.start;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.obywatel.guitartext.R;
import org.obywatel.guitartext.presenters.fileCategory.FileCategoryPresenter;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class ExpendableListAdapter extends BaseExpandableListAdapter
{
	private final Context context;
	private final FileCategoryPresenter fileCategoryPresenter;

	class GroupViewHolder
	{
		TextView textView;
		ImageView imageView;
	}

	class ChildViewHolder extends GroupViewHolder
	{
	}

	public ExpendableListAdapter(Context context, FileCategoryPresenter fileCategoryPresenter)
	{
		this.context = context;
		this.fileCategoryPresenter = fileCategoryPresenter;
	}

	@Override
	public int getGroupCount()
	{
		return fileCategoryPresenter.getGroupCount();
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		return fileCategoryPresenter.getChildrenCount(groupPosition);
	}

	@Override
	public Object getGroup(int groupPosition)
	{
		return fileCategoryPresenter.getChildrenCount(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition)
	{
		return fileCategoryPresenter.getChildEntry(groupPosition, childPosition);
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
		ExpendableListEntry groupEntry = fileCategoryPresenter.getGroupEntry(groupPosition);

		if(groupEntry == null) return convertView;

		GroupViewHolder groupViewHolder;

		if(convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.row_group_entry, parent, false);

			groupViewHolder = new GroupViewHolder();
			groupViewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
			groupViewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

			convertView.setTag(groupViewHolder);
		}
		else
		{
			groupViewHolder = (GroupViewHolder) convertView.getTag();
		}

		groupViewHolder.textView.setText(groupEntry.getName());
		groupViewHolder.imageView.setImageResource(groupEntry.iconResourceId);

		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
	{
		ExpendableListEntry childEntry = fileCategoryPresenter.getChildEntry(groupPosition, childPosition);

		if(childEntry == null) return convertView;

		ChildViewHolder childViewHolder;
		if(convertView == null)
		{
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.row_child_entry, parent, false);

			childViewHolder = new ChildViewHolder();
			childViewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
			childViewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

			convertView.setTag(childViewHolder);
		}
		else
		{
			childViewHolder = (ChildViewHolder) convertView.getTag();
		}


		childViewHolder.textView.setText(childEntry.getName());
		childViewHolder.imageView.setImageResource(childEntry.iconResourceId);

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return false;
	}
}
