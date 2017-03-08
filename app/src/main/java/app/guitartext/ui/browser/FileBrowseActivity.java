package app.guitartext.ui.browser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import app.guitartext.R;
import app.guitartext.user.fileInfo.FileInfo;
import app.guitartext.user.fileInfo.ParcelableFileInfoWrapper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FileBrowseActivity extends AppCompatActivity
{


	@BindView(R.id.textView2) TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_browse);
		ButterKnife.bind(this);

		FileInfo fileInfo = getIntent().getParcelableExtra(ParcelableFileInfoWrapper.EXTRA_FILE_INFO);

		textView.setText(getIntent().getAction() + " " + (fileInfo != null ? fileInfo.getName() : "NULL"));
	}
}
