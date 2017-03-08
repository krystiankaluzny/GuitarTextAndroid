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
import app.guitartext.ui.category.component.CategoryComponent;
import app.guitartext.ui.category.component.CategoryComponent2;
import app.guitartext.ui.category.component.CategoryModule;
import app.guitartext.ui.category.presenter.FileCategoryPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener
{
	@BindView(R.id.fab) FloatingActionButton floatingActionButton;
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.expendable_list_view) ExpandableListView expandableListView;

	private CategoryComponent categoryComponent;

	@Inject FileCategoryPresenter fileCategoryPresenter;
	@Inject CategoryExpendableListAdapter categoryExpendableListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);

		ButterKnife.bind(this);

		createComponent();
		categoryComponent.inject(this);

		setSupportActionBar(toolbar);

		expandableListView.setAdapter(categoryExpendableListAdapter);

		expandableListView.setChoiceMode(ExpandableListView.CHOICE_MODE_SINGLE);
		expandableListView.setOnGroupClickListener(this);
		expandableListView.setOnChildClickListener(this);
	}

	private void createComponent()
	{
		categoryComponent = GuitarTextApplication.get(this)
				.getUserComponent()
				.plus(new CategoryModule(this));

		CategoryComponent2 categoryComponent2;
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
