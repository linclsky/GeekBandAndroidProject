package com.geekban.geekbandandroidproject.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2020/3/10.
 */

public class Person implements Parcelable {
    String mName;
    int mAge;
    String mAvatarUrl;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeInt(this.mAge);
        dest.writeString(this.mAvatarUrl);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.mName = in.readString();
        this.mAge = in.readInt();
        this.mAvatarUrl = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
