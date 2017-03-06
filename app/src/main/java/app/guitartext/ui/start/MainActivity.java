package app.guitartext.ui.start;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import javax.inject.Inject;

import app.guitartext.GuitarTextApplication;
import app.guitartext.R;
import app.guitartext.ui.presenters.fileCategory.FileCategoryPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
{
	@BindView(R.id.fab) FloatingActionButton floatingActionButton;
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.expendable_list_view) ExpandableListView expandableListView;

	private MainActivityComponent mainActivityComponent;

	@Inject FileCategoryPresenter fileCategoryPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		createComponent();
		mainActivityComponent.inject(this);


		setSupportActionBar(toolbar);

		floatingActionButton.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show());

		expandableListView.setAdapter(mainActivityComponent.expendableListAdapter());
	}

	private void createComponent()
	{
		mainActivityComponent = GuitarTextApplication.get(this)
				.getUserComponent()
				.plus(new MainActivityModule());
	}
}
