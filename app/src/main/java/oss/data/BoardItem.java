package oss.data;

import android.os.Parcel;
import android.os.Parcelable;

/**게시판 세부 클래스
 *
 * @see Parcelable
 * [Parcelable] Intent로 보내기 위한 인터페이스
 *
 * @now name, info
 * @// TODO: 2021-07-29  글쓴이, 의료기관명, 병실, 환자명, 혈액형, 필요한 혈액 종류, 환자번호 ...
 *
 * */
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
