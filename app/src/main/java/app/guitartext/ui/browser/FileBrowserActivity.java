package app.guitartext.ui.browser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import javax.inject.Inject;

import app.guitartext.GuitarTextApplication;
import app.guitartext.R;
import app.guitartext.ui.browser.component.FileBrowserComponent;
import app.guitartext.ui.browser.component.FileBrowserModule;
import app.guitartext.ui.browser.presenter.FileBrowserPresenter;
import app.guitartext.user.fileInfo.ParcelableFileInfoWrapper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FileBrowserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.file_list) ListView fileListView;

	private FileBrowserComponent fileBrowserComponent;

	@Inject FileBrowserPresenter fileBrowserPresenter;
	@Inject FileBrowserAdapter fileBrowserAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_browse);
		ButterKnife.bind(this);

		createComponent();
		fileBrowserComponent.inject(this);

		setSupportActionBar(toolbar);

		fileListView.setAdapter(fileBrowserAdapter);
		fileListView.setOnItemClickListener(this);
	}

	private void createComponent()
	{
		fileBrowserComponent = GuitarTextApplication.get(this)
				.getUserComponent()
				.plus(new FileBrowserModule(this, (ParcelableFileInfoWrapper)getIntent().getParcelableExtra(ParcelableFileInfoWrapper.EXTRA_FILE_INFO)));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		fileBrowserPresenter.fileSelected(position);
		fileBrowserAdapter.notifyDataSetChanged();
	}
}
