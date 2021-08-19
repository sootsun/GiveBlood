package oss.data;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable {

    public String userName;
    public String userMail;

    public UserData() {
    }

    protected UserData(Parcel in) {
        userName = in.readString();
        userMail = in.readString();
    }

    public UserData(String userName, String userMail) {
        this.userMail = userMail;
        this.userName = userName;
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(userMail);
    }
}
