package app.guitartext.ui.text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.androidessence.lib.RichTextView;

import javax.inject.Inject;

import app.guitartext.GuitarTextApplication;
import app.guitartext.R;
import app.guitartext.ui.text.component.TextModule;
import app.guitartext.ui.text.presenter.TextComponent;
import app.guitartext.ui.text.presenter.TextPresenter;
import app.guitartext.user.fileInfo.ParcelableFileInfoWrapper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TextActivity extends AppCompatActivity implements TextPresenter.View
{
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.text_lyrics) RichTextView richTextView;

	private TextComponent textComponent;

	@Inject TextPresenter textPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_scrolling);
		ButterKnife.bind(this);

		createComponent().inject(this);

		setSupportActionBar(toolbar);

		textPresenter.prepareFile(ParcelableFileInfoWrapper.fromIntent(getIntent()));
	}

	private TextComponent createComponent()
	{
		return textComponent = GuitarTextApplication.get(this)
				.getUserComponent()
				.plus(new TextModule(
						this,
						this));
	}

	@Override
	public void textRead(String text)
	{
		richTextView.setText(text);
	}
}
