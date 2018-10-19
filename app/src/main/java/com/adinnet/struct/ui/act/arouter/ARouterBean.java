package com.adinnet.struct.ui.act.arouter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ms.Li on 2018/10/16.
 */

public class ARouterBean implements Parcelable {
    private String id;
    private String name;

    public ARouterBean(String id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "ARouterBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    protected ARouterBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Creator<ARouterBean> CREATOR = new Creator<ARouterBean>() {
        @Override
        public ARouterBean createFromParcel(Parcel source) {
            return new ARouterBean(source);
        }

        @Override
        public ARouterBean[] newArray(int size) {
            return new ARouterBean[size];
        }
    };
}

