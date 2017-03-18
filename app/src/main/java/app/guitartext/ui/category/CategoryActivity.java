package app.guitartext.ui.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.List;

import javax.inject.Inject;

import app.guitartext.GuitarTextApplication;
import app.guitartext.R;
import app.guitartext.dagger.activity.CategoryComponent;
import app.guitartext.dagger.activity.CategoryComponent2;
import app.guitartext.dagger.activity.CategoryModule;
import app.guitartext.presenter.category.FileCategoryEntry;
import app.guitartext.presenter.category.FileCategoryPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener, FileCategoryPresenter.View
{
	private Logger logger = LoggerManager.getLogger();

	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.expendable_list_view) ExpandableListView expandableListView;

	@Inject FileCategoryPresenter fileCategoryPresenter;
	@Inject CategoryExpendableListAdapter categoryExpendableListAdapter;

	private CategoryComponent categoryComponent;

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


	@Override
	protected void onResume()
	{
		super.onResume();
		fileCategoryPresenter.updateCategory();
	}

	private void createComponent()
	{
		categoryComponent = GuitarTextApplication.get(this)
				.getUserComponent()
				.plus(new CategoryModule(this, this));

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

	@Override
	public void onCategoriesUpdated(List<FileCategoryEntry> categoryEntryList)
	{
		categoryExpendableListAdapter.setData(categoryEntryList);
	}
}
