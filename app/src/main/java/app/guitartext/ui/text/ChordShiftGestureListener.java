package app.guitartext.ui.text;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import app.guitartext.presenter.text.TextPresenter;

/**
 * Created by obywatel on 13.03.2017.
 * Modified by
 */

public class ChordShiftGestureListener extends GestureDetector.SimpleOnGestureListener
{
	private static final Logger logger = LoggerManager.getLogger();
	private static final int DX_MIN_VALUE = 100;

	private final TextPresenter textPresenter;

	public ChordShiftGestureListener(TextPresenter textPresenter)
	{
		this.textPresenter = textPresenter;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		float dX = e2.getX() - e1.getX();
		float dY = e2.getY() - e1.getY();

		if(Math.abs(dX) > Math.abs(dY) && Math.abs(dX) > DX_MIN_VALUE)
		{
			textPresenter.onChordShiftGesture(dX);
		}
		return true;
	}
}
