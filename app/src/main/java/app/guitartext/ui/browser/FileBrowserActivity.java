package app.guitartext.ui.browser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

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
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.file_list) ListView fileListView;
	@BindView(R.id.path_layout) PathLayout pathLayout;

	private FileBrowserComponent fileBrowserComponent;

	@Inject FileBrowserPresenter fileBrowserPresenter;
	@Inject FileBrowserAdapter fileBrowserAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_browse);
		ButterKnife.bind(this);

		createComponent().inject(this);

		setSupportActionBar(toolbar);

		pathLayout.setOnPathItemClickedListener(this);
		fileListView.setAdapter(fileBrowserAdapter);

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

	@OnItemClick(R.id.file_list)
	public void onItemClick(int position)
	{
		fileBrowserPresenter.fileSelected(position);
	}

	@Override
	public void onPathItemClicked(int position, PathItem pathItem)
	{
		fileBrowserPresenter.fileSelected((FileInfo) pathItem.getTag());
	}

	@Override
	public void pathChanged(List<PathItem> path, List<FileInfo> pathContent)
	{
		pathLayout.setPath(path);
		fileBrowserAdapter.setData(pathContent);
	}
}
