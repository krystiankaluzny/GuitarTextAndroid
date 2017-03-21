package app.guitartext.ui.browser;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import app.guitartext.GuitarTextApplication;
import app.guitartext.R;
import app.guitartext.dagger.activity.FileBrowserComponent;
import app.guitartext.dagger.activity.FileBrowserModule;
import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.fileInfo.ParcelableFileInfoWrapper;
import app.guitartext.presenter.browser.FileBrowserPresenter;
import app.guitartext.presenter.browser.PathItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FileBrowserActivity extends AppCompatActivity implements FileBrowserPresenter.View, PathLayout.OnPathItemClickedListener, ItemClickSupport.OnItemClickListener, ItemClickSupport.OnItemLongClickListener
{
	@BindView(R.id.file_list) RecyclerView fileRecycleView;
	@BindView(R.id.path_layout) PathLayout pathLayout;
	@BindView(R.id.app_bar) AppBarLayout appBarLayout;

	@Inject FileBrowserPresenter fileBrowserPresenter;
	@Inject FileBrowserAdapter fileBrowserAdapter;

	private FileBrowserComponent fileBrowserComponent;
	private RecyclerView.LayoutManager layoutManager;

	private AlertDialog addToBaseFileDialog = null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_browse);
		ButterKnife.bind(this);

		createComponent().inject(this);

		viewInit();

		fileBrowserPresenter.fileSelected(fileBrowserComponent.startFileLocation());
	}

	@Override
	protected void onDestroy()
	{
		closeAddToBaseDialog();
		super.onDestroy();
	}

	private FileBrowserComponent createComponent()
	{
		return fileBrowserComponent = GuitarTextApplication.get(this)
				.getUserComponent()
				.plus(new FileBrowserModule(
						this,
						ParcelableFileInfoWrapper.fromIntent(getIntent()),
						this));
	}

	private void viewInit()
	{
		pathLayout.setOnPathItemClickedListener(this);
		layoutManager = new LinearLayoutManager(this);

		fileRecycleView.setLayoutManager(layoutManager);
		fileRecycleView.setAdapter(fileBrowserAdapter);

		ItemClickSupport.addTo(fileRecycleView)
				.setOnItemClickListener(this)
				.setOnItemLongClickListener(this);

		DividerItemDecoration dividerDecoration = new DividerItemDecoration(getBaseContext(), DividerItemDecoration.VERTICAL);
		fileRecycleView.addItemDecoration(dividerDecoration);
	}

	@Override
	public void onPathChanged(List<PathItem> path, List<FileInfo> pathContent)
	{
		pathLayout.setPath(path);
		fileBrowserAdapter.setData(pathContent);
		appBarLayout.setExpanded(true);
		fileRecycleView.scrollToPosition(0);
	}

	@Override
	public void onPathItemClicked(int position, PathItem pathItem)
	{
		fileBrowserPresenter.fileSelected((FileInfo) pathItem.getTag());
	}

	@Override
	public void onItemClicked(RecyclerView recyclerView, int position, View v)
	{
		fileBrowserPresenter.fileSelected(position);
	}

	@Override
	public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v)
	{
		closeAddToBaseDialog();

		AlertDialog.Builder dialogAlert = new AlertDialog.Builder(this);
		dialogAlert.setTitle(R.string.add_to_base);

		dialogAlert.setPositiveButton(R.string.yes, (dialog, which) -> fileBrowserPresenter.addBaseFile(position))
		.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());
		addToBaseFileDialog = dialogAlert.show();
		return false;
	}

	private void closeAddToBaseDialog()
	{
		if(addToBaseFileDialog != null && addToBaseFileDialog.isShowing())
			addToBaseFileDialog.dismiss();

		addToBaseFileDialog = null;
	}
}
