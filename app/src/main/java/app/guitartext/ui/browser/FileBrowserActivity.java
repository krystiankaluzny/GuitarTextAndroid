package app.guitartext.ui.browser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import app.guitartext.GuitarTextApplication;
import app.guitartext.R;
import app.guitartext.dagger.activity.FileBrowserComponent;
import app.guitartext.dagger.activity.FileBrowserModule;
import app.guitartext.presenter.browser.FileBrowserPresenter;
import app.guitartext.presenter.browser.PathItem;
import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.fileInfo.ParcelableFileInfoWrapper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class FileBrowserActivity extends AppCompatActivity implements FileBrowserPresenter.View, PathLayout.OnPathItemClickedListener
{
	@BindView(R.id.file_list) RecyclerView fileRecycleView;
	@BindView(R.id.path_layout) PathLayout pathLayout;

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

//	@OnItemClick(R.id.file_list)
//	public void onItemClick(int position)
//	{
//		fileBrowserPresenter.fileSelected(position);
//	}

	@Override
	public void onPathItemClicked(int position, PathItem pathItem)
	{
		fileBrowserPresenter.fileSelected((FileInfo) pathItem.getTag());
	}

	@Override
	public void onPathChanged(List<PathItem> path, List<FileInfo> pathContent)
	{
		pathLayout.setPath(path);
		fileBrowserAdapter.setData(pathContent);
	}
}
