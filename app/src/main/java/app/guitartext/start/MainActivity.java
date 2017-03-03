package app.guitartext.start;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import app.guitartext.R;
import app.guitartext.presenters.fileCategory.FileCategoryPresenter;
import app.guitartext.presenters.fileCategory.impl.FileCategoryPresenterImpl;

public class MainActivity extends AppCompatActivity
{
	private static FileCategoryPresenter fileCategoryPresenter;

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


		if(fileCategoryPresenter == null)
		{
			fileCategoryPresenter = new FileCategoryPresenterImpl(getApplicationContext());
		}

		ExpendableListAdapter expendableListAdapter = new ExpendableListAdapter(getApplicationContext(), fileCategoryPresenter);

		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expendable_list_view);
		expandableListView.setAdapter(expendableListAdapter);
	}

}
