package app.guitartext.ui.browser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import app.guitartext.R;
import app.guitartext.user.fileInfo.FileInfo;
import app.guitartext.user.fileInfo.ParcelableFileInfoWrapper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FileBrowserActivity extends AppCompatActivity
{

	@BindView(R.id.file_list) ListView fileListView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_browse);
		ButterKnife.bind(this);

		FileInfo fileInfo = getIntent().getParcelableExtra(ParcelableFileInfoWrapper.EXTRA_FILE_INFO);

//		fileListView
	}
}
