package app.guitartext.ui.browser;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
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

public class FileBrowserActivity extends AppCompatActivity implements FileBrowserPresenter.View, PathLayout.OnPathItemClickedListener, ItemClickSupport.OnItemClickListener
{
	@BindView(R.id.file_list) RecyclerView fileRecycleView;
	@BindView(R.id.path_layout) PathLayout pathLayout;
	@BindView(R.id.app_bar) AppBarLayout appBarLayout;

	@Inject FileBrowserPresenter fileBrowserPresenter;
	@Inject FileBrowserAdapter fileBrowserAdapter;

	private FileBrowserComponent fileBrowserComponent;
	private RecyclerView.LayoutManager layoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_browse);
		ButterKnife.bind(this);

		createComponent().inject(this);

		pathLayout.setOnPathItemClickedListener(this);
		layoutManager = new LinearLayoutManager(this);

		fileRecycleView.setLayoutManager(layoutManager);
		fileRecycleView.setAdapter(fileBrowserAdapter);

		ItemClickSupport.addTo(fileRecycleView).setOnItemClickListener(this);

		fileBrowserPresenter.fileSelected(fileBrowserComponent.startFileLocation());
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
}
