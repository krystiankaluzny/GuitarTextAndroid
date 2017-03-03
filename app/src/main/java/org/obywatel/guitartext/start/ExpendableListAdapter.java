package org.obywatel.guitartext.start;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.obywatel.guitartext.R;

import java.util.List;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class ExpendableListAdapter extends BaseExpandableListAdapter
{
	private final Context context;
	private List<Category> categoryList;

	public ExpendableListAdapter(Context context, List<Category> categoryList)
	{
		this.context = context;
		this.categoryList = categoryList;
	}

	@Override
	public int getGroupCount()
	{
		return categoryList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		if(groupPosition < 0 || groupPosition >= categoryList.size()) return 0;
		return categoryList.get(groupPosition).getSubCategoryList().size();
	}

	@Override
	public Object getGroup(int groupPosition)
	{
		if(groupPosition < 0 || groupPosition >= categoryList.size()) return null;
		return categoryList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition)
	{
		if(groupPosition < 0 || groupPosition >= categoryList.size()) return null;

		List<SubCategory> subCategories = categoryList.get(groupPosition).getSubCategoryList();

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
		if(groupPosition < 0 || groupPosition >= categoryList.size()) return convertView;

		LayoutInflater inflater = LayoutInflater.from(context);
		View rowView = inflater.inflate(R.layout.row_category, parent, false);

		TextView textView = (TextView) rowView.findViewById(R.id.textView);
		textView.setText(categoryList.get(groupPosition).getName());

		return rowView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
	{
		if(groupPosition < 0 || groupPosition >= categoryList.size()) return convertView;

		List<SubCategory> subCategories = categoryList.get(groupPosition).getSubCategoryList();
		if(childPosition < 0 || childPosition >= subCategories.size()) return convertView;

		LayoutInflater inflater = LayoutInflater.from(context);
		View rowView = inflater.inflate(R.layout.row_subcategory, parent, false);

		TextView textView = (TextView) rowView.findViewById(R.id.textView);
		textView.setText(subCategories.get(groupPosition).getName());

		return rowView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return false;
	}
}
