package app.guitartext.ui.start;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import app.guitartext.R;
import app.guitartext.ui.presenters.fileCategory.FileCategoryPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class ExpendableListAdapter extends BaseExpandableListAdapter
{
	private final Context context;
	private final FileCategoryPresenter fileCategoryPresenter;

	@Inject
	public ExpendableListAdapter(Context context, FileCategoryPresenter fileCategoryPresenter)
	{
		this.context = context;
		this.fileCategoryPresenter = fileCategoryPresenter;
	}

	@Override
	public int getGroupCount()
	{
		return fileCategoryPresenter.getCategoryCount();
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		return fileCategoryPresenter.getSubCategoryCount(groupPosition);
	}

	@Override
	public Object getGroup(int groupPosition)
	{
		return fileCategoryPresenter.getSubCategoryCount(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition)
	{
		return fileCategoryPresenter.getSubCategoryEntry(groupPosition, childPosition);
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
		ExpendableListEntry groupEntry = fileCategoryPresenter.getCategoryEntry(groupPosition);

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
		ExpendableListEntry childEntry = fileCategoryPresenter.getSubCategoryEntry(groupPosition, childPosition);

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

	static class GroupViewHolder
	{
		@BindView(R.id.textView) TextView textView;
		@BindView(R.id.imageView) ImageView imageView;

		GroupViewHolder(View view)
		{
			ButterKnife.bind(this, view);
		}
	}

	static class ChildViewHolder extends GroupViewHolder
	{
		ChildViewHolder(View view)
		{
			super(view);
		}
	}
}
