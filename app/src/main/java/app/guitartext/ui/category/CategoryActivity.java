package app.guitartext.ui.category;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import javax.inject.Inject;

import app.guitartext.GuitarTextApplication;
import app.guitartext.R;
import app.guitartext.ui.category.component.CategoryActivityComponent;
import app.guitartext.ui.category.component.CategoryActivityComponent2;
import app.guitartext.ui.category.component.CategoryActivityModule;
import app.guitartext.ui.category.presenter.FileCategoryPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener
{
	@BindView(R.id.fab) FloatingActionButton floatingActionButton;
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.expendable_list_view) ExpandableListView expandableListView;

	private CategoryActivityComponent categoryActivityComponent;

	@Inject FileCategoryPresenter fileCategoryPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		createComponent();
		categoryActivityComponent.inject(this);


		setSupportActionBar(toolbar);

		floatingActionButton.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show());

		expandableListView.setAdapter(categoryActivityComponent.expendableListAdapter());

		expandableListView.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);
		expandableListView.setOnGroupClickListener(this);
		expandableListView.setOnChildClickListener(this);
	}

	private void createComponent()
	{
		categoryActivityComponent = GuitarTextApplication.get(this)
				.getUserComponent()
				.plus(new CategoryActivityModule(this));

		CategoryActivityComponent2 categoryActivityComponent2;
	}

	@Override
	public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
	{
		fileCategoryPresenter.categorySelected(groupPosition);
		return false;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
	{
		fileCategoryPresenter.subCategorySelected(groupPosition, childPosition);
		return false;
	}
}
