package app.guitartext.start;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import app.guitartext.R;
import app.guitartext.presenters.fileCategory.FileCategoryPresenter;
import app.guitartext.presenters.fileCategory.impl.FileCategoryPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
{
	@BindView(R.id.fab) FloatingActionButton floatingActionButton;
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.expendable_list_view) ExpandableListView expandableListView;

	private static FileCategoryPresenter fileCategoryPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);

		floatingActionButton.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show());

		if(fileCategoryPresenter == null)
		{
			fileCategoryPresenter = new FileCategoryPresenterImpl(getApplicationContext());
		}

		ExpendableListAdapter expendableListAdapter = new ExpendableListAdapter(getApplicationContext(), fileCategoryPresenter);

		expandableListView.setAdapter(expendableListAdapter);
	}
}
