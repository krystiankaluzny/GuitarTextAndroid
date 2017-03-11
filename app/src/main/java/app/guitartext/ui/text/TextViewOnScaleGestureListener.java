package app.guitartext.ui.text;

import android.view.ScaleGestureDetector;
import android.widget.TextView;

/**
 * Created by obywatel on 11.03.2017.
 * Modified by
 */

public class TextViewOnScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
{
	private final TextView textViewToScale;
	private float baseSize;
	private float factor = 1.0f;

	public TextViewOnScaleGestureListener(TextView textViewToScale)
	{
		this.textViewToScale = textViewToScale;
		this.baseSize = textViewToScale.getTextSize();
	}

	@Override
	public boolean onScale(ScaleGestureDetector detector)
	{
		factor *= detector.getScaleFactor();
		textViewToScale.setTextSize(baseSize * factor);
		return true;
	}
}
