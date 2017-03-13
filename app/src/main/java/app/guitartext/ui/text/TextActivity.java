package app.guitartext.ui.text;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.TextView;

import javax.inject.Inject;

import app.guitartext.GuitarTextApplication;
import app.guitartext.R;
import app.guitartext.dagger.activity.TextComponent;
import app.guitartext.dagger.activity.TextModule;
import app.guitartext.model.fileInfo.ParcelableFileInfoWrapper;
import app.guitartext.model.lyrics.LyricLine;
import app.guitartext.model.lyrics.Lyrics;
import app.guitartext.model.lyrics.TextElement;
import app.guitartext.model.lyrics.TextElementType;
import app.guitartext.presenter.text.TextPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TextActivity extends AppCompatActivity implements TextPresenter.View
{
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.text_lyrics) TextView textView;

	private TextComponent textComponent;

	@Inject TextPresenter textPresenter;

	private ScaleGestureDetector scaleGestureDetector;
	private GestureDetectorCompat gestureDetectorCompat;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_scrolling);
		ButterKnife.bind(this);

		createComponent().inject(this);

		setSupportActionBar(toolbar);

		scaleGestureDetector = new ScaleGestureDetector(this, new TextViewOnScaleGestureListener(textView));
		gestureDetectorCompat = new GestureDetectorCompat(this, new ChordShiftGestureListener(textPresenter));

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
	public void onLyricsUpdated(Lyrics lyrics)
	{
		SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

		for(LyricLine line : lyrics.getLines())
		{
			for(TextElement element : line.getTextElements())
			{
				TextElementType t = element.getTextElementType();

				int start = stringBuilder.length();
				stringBuilder.append(element.getText());
				int end = stringBuilder.length();

				if(TextElementType.CHORDS.equals(t))
				{
					stringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.chord)),
							start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
				else if(TextElementType.BRACKET.equals(t))
				{
					stringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.bracket)),
							start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}

			}
			stringBuilder.append("\n");
		}

		textView.setText(stringBuilder, TextView.BufferType.SPANNABLE);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event)
	{
		scaleGestureDetector.onTouchEvent(event);
		gestureDetectorCompat.onTouchEvent(event);
		return super.dispatchTouchEvent(event);
	}
}
