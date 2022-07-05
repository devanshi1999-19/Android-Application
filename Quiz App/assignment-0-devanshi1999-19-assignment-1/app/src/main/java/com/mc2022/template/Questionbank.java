package com.mc2022.template;

import android.os.Parcel;
import android.os.Parcelable;

public class Questionbank implements Parcelable {

    private int answerid;
    public boolean answer;

    public Questionbank(int answerid, boolean answer) {
        this.answerid = answerid;
        this.answer = answer;
    }

    protected Questionbank(Parcel in) {
        answerid = in.readInt();
        answer = in.readByte() != 0;
    }

    public static final Creator<Questionbank> CREATOR = new Creator<Questionbank>() {
        @Override
        public Questionbank createFromParcel(Parcel in) {
            return new Questionbank(in);
        }

        @Override
        public Questionbank[] newArray(int size) {
            return new Questionbank[size];
        }
    };

    public int getAnswerid() {
        return answerid;
    }

    public void setAnswerid(int answerid) {
        this.answerid = answerid;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(answerid);
        parcel.writeByte((byte) (answer ? 1 : 0));
    }
}
