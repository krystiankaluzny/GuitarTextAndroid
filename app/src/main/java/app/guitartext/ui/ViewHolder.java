package app.guitartext.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.guitartext.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */
public class ViewHolder
{
	@BindView(R.id.textView) public TextView textView;
	@BindView(R.id.imageView) public ImageView imageView;

	public ViewHolder(View view)
	{
		ButterKnife.bind(this, view);
	}
}
