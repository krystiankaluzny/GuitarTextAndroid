package org.obywatel.guitartext.start;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class Category
{
	private String name;
	private List<SubCategory> subCategoryList = new ArrayList<>();

	public Category(String name)
	{
		this.name = name;
	}

	public Category addSubCategory(SubCategory subCategory)
	{
		subCategoryList.add(subCategory);
		return this;
	}

	public String getName()
	{
		return name;
	}

	public List<SubCategory> getSubCategoryList()
	{
		return subCategoryList;
	}
}
