package com.android.lofm.imagesdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Omar F Martinez on 1/20/17.
 */

public class ModelResponse implements Parcelable {

    public static final Creator<ModelResponse> CREATOR = new Creator<ModelResponse>() {

        @Override
        public ModelResponse createFromParcel(Parcel in) {
            return new ModelResponse(in);
        }

        @Override
        public ModelResponse[] newArray(int size) {
            return new ModelResponse[size];
        }
    };
    private String id;
    private String url;

    protected ModelResponse(Parcel in) {
        id = in.readString();
        url = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(url);
    }
}
