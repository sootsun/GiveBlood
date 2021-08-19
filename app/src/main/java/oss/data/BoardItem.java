package oss.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 게시판 내용
 *
 * @ TODO: 2021-07-29  의료기관명, 병실, 환자명, 혈액형, 필요한 혈액 종류, 환자번호 ...
 * @see Parcelable <br>
 * [Parcelable] Intent로 보내기 위한 인터페이스
 */
public class BoardItem implements Parcelable {
    //public int id;
    public String title;
    public String content;

    public String writer;
    public String email;

    public String key;

    public BoardItem() {
    }

    public BoardItem(String name, String info, UserData userData) {
        this.content = info;
        this.title = name;
        this.writer = userData.userName;
        this.email = userData.userMail;
        this.key = "not yet";
    }

    protected BoardItem(Parcel in) {
        title = in.readString();
        content = in.readString();
        writer = in.readString();
        email = in.readString();
        key = in.readString();
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
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(writer);
        dest.writeString(email);
        dest.writeString(key);
    }
}
