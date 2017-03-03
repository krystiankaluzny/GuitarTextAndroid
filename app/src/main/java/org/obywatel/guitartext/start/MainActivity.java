package org.obywatel.guitartext.start;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import org.obywatel.guitartext.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});


		List<Category> categoryList = new ArrayList<>();

		categoryList.add(new Category("Test")
				.addSubCategory(new SubCategory("test1"))
				.addSubCategory(new SubCategory("test2"))
				.addSubCategory(new SubCategory("test3"))
				.addSubCategory(new SubCategory("test4")));

		categoryList.add(new Category("Aud")
				.addSubCategory(new SubCategory("Aud1"))
				.addSubCategory(new SubCategory("Aud2")));

		categoryList.add(new Category("Akars")
				.addSubCategory(new SubCategory("Akars1"))
				.addSubCategory(new SubCategory("Akars2"))
				.addSubCategory(new SubCategory("Akars3")));

		ExpendableListAdapter expendableListAdapter = new ExpendableListAdapter(getApplicationContext(), categoryList);

		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expendable_list_view);
		expandableListView.setAdapter(expendableListAdapter);
	}

}
