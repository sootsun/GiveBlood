package oss.data;

import android.os.Parcel;
import android.os.Parcelable;

public class BoardItem implements Parcelable {
    public String name;
    public String info;

    public BoardItem(){}

    public BoardItem(String name, String info) {
        this.info = info;
        this.name = name;
    }

    protected BoardItem(Parcel in) {
        name = in.readString();
        info = in.readString();
    }

    public static final Creator<BoardItem> CREATOR = new Creator<BoardItem>() {
        @Override
        public BoardItem createFromParcel(Parcel in) {
            return new BoardItem(in);
        }

        @Override
        public BoardItem[] newArray(int size) {
            return new BoardItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(info);
    }
}
