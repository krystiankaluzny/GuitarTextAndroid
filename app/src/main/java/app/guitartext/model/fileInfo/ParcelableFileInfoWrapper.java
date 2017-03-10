package app.guitartext.model.fileInfo;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class ParcelableFileInfoWrapper extends FileInfo implements Parcelable
{
	public static final String EXTRA_FILE_INFO = "app.guitartext.model.fileInfo.ParcelableFileInfoWrapper";

	protected ParcelableFileInfoWrapper(FileInfo fileInfo)
	{
		super(fileInfo.getId(), fileInfo.isDirectory(), fileInfo.getPath(), fileInfo.getName());
	}

	protected ParcelableFileInfoWrapper(Parcel in)
	{
		super(in.readInt(), in.readByte() != 0, in.readString(), in.readString());
	}

	public static ParcelableFileInfoWrapper wrap(FileInfo fileInfo)
	{
		return new ParcelableFileInfoWrapper(fileInfo);
	}

	public static ParcelableFileInfoWrapper fromIntent(Intent intent)
	{
		return (ParcelableFileInfoWrapper) intent.getParcelableExtra(ParcelableFileInfoWrapper.EXTRA_FILE_INFO);
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(this.getId());
		dest.writeByte(this.isDirectory() ? (byte) 1 : (byte) 0);
		dest.writeString(this.getPath());
		dest.writeString(this.getName());
	}


	public static final Creator<ParcelableFileInfoWrapper> CREATOR = new Creator<ParcelableFileInfoWrapper>()
	{
		@Override
		public ParcelableFileInfoWrapper createFromParcel(Parcel source)
		{
			return new ParcelableFileInfoWrapper(source);
		}

		@Override
		public ParcelableFileInfoWrapper[] newArray(int size)
		{
			return new ParcelableFileInfoWrapper[size];
		}
	};
}
