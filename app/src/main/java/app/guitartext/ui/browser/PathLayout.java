package app.guitartext.ui.browser;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import app.guitartext.R;
import app.guitartext.ui.browser.presenter.PathItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by obywatel on 09.03.2017.
 * Modified by
 */

public class PathLayout extends LinearLayout
{
	@BindView(R.id.path_container) LinearLayout pathContainerLayout;

	private OnPathItemClickedListener pathItemClickedListener;

	public PathLayout(Context context)
	{
		this(context, null);
	}

	public PathLayout(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public PathLayout(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);

		View view = inflate(context, R.layout.path_layout_base, null);
		addView(view);

		ButterKnife.bind(this, view);
	}

	public void setPath(List<PathItem> pathItemList)
	{
		clearButtons();
		int i = 0;
		for(PathItem pathItem : pathItemList)
		{
			addButton(i, pathItem);
		}
	}

	public void setOnPathItemClickedListener(OnPathItemClickedListener pathItemClickedListener)
	{
		this.pathItemClickedListener = pathItemClickedListener;
	}

	private void clearButtons()
	{
		pathContainerLayout.removeAllViews();
	}

	private void addButton(int position, PathItem pathItem)
	{
		Button button = new Button(getContext());
		button.setText(pathItem.getName());
		button.setOnClickListener(new OnPathButtonClickListener(position, pathItem));

		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		pathContainerLayout.addView(button, lp);
	}

	public interface OnPathItemClickedListener
	{
		void onPathItemClicked(int position, PathItem pathItem);
	}

	private class OnPathButtonClickListener implements OnClickListener
	{
		final int position;
		final PathItem pathItem;

		private OnPathButtonClickListener(int position, PathItem pathItem)
		{
			this.position = position;
			this.pathItem = pathItem;
		}

		@Override
		public void onClick(View v)
		{
			if(pathItemClickedListener != null)
			{
				pathItemClickedListener.onPathItemClicked(position, pathItem);
			}
		}
	}
}
