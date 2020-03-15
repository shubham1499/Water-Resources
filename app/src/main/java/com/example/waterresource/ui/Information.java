package com.example.waterresource.ui;

import android.graphics.Movie;
import android.os.Parcel;
import android.os.Parcelable;

public class Information implements Parcelable {
    String photoUid,typeOfSource,ownership,seasonality,useOfSource,date,recentPhotoUid,recentUserId;
    double pumpCapacity,lati,longi;
    int summerDays,winterDays,monsoonDays;
    int summerHours,winterHours,monsoonHours;
    int counter,totalCount;


    public static final Parcelable.Creator<Information> CREATOR = new Parcelable.Creator<Information>(){
        public Information createFromParcel(Parcel in) {
            return new Information(in);
        }

        public Information[] newArray(int size) {
            return new Information[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photoUid);
        dest.writeString(typeOfSource);
        dest.writeString(ownership);
        dest.writeString(seasonality);
        dest.writeString(useOfSource);
        dest.writeString(date);
        dest.writeString(recentPhotoUid);
        dest.writeString(recentUserId);

        dest.writeDouble(lati);
        dest.writeDouble(longi);

        dest.writeDouble(pumpCapacity);
        dest.writeInt(summerDays);
        dest.writeInt(summerHours);
        dest.writeInt(winterDays);
        dest.writeInt(winterHours);
        dest.writeInt(monsoonDays);
        dest.writeInt(monsoonHours);
        dest.writeInt(counter);
        dest.writeInt(totalCount);
    }
    public Information(Parcel in){
            photoUid = in.readString();
            typeOfSource = in.readString();
            ownership = in.readString();
            seasonality = in.readString();
            useOfSource = in.readString();
            date        = in.readString();
            recentPhotoUid = in.readString();
            recentUserId   = in.readString();

            lati           = in.readDouble();
            longi          = in.readDouble();
            pumpCapacity   = in.readDouble();

            summerDays     = in.readInt();
            summerHours    = in.readInt();
            winterDays     = in.readInt();
            winterHours     = in.readInt();
            monsoonDays     = in.readInt();
            monsoonHours    = in.readInt();
            counter         = in.readInt();
            totalCount      = in.readInt();
    }


    public Information() {
        photoUid = "";
        typeOfSource = "";
        ownership = "";
        seasonality = "";
        useOfSource = "";
        recentPhotoUid = "";
        recentUserId = "";
        date = "";
        lati = -1;
        longi = -1;
        pumpCapacity = summerDays = winterDays = monsoonDays = summerHours = winterHours = monsoonHours = counter = totalCount = -1;
    }
    public Information(String photoUid, String typeOfSource, String ownership, String seasonality, String useOfSource, String date, double lati, double longi, double pumpCapacity, int summerDays, int winterDays, int monsoonDays, int summerHours, int winterHours, int monsoonHours, int counter, int totalCount, String recentPhotoUid, String recentUserId) {
        this.photoUid = photoUid;
        this.typeOfSource = typeOfSource;
        this.ownership = ownership;
        this.seasonality = seasonality;
        this.useOfSource = useOfSource;
        this.date = date;
        this.lati = lati;
        this.longi = longi;
        this.pumpCapacity = pumpCapacity;
        this.summerDays = summerDays;
        this.winterDays = winterDays;
        this.monsoonDays = monsoonDays;
        this.summerHours = summerHours;
        this.winterHours = winterHours;
        this.monsoonHours = monsoonHours;
        this.counter = counter;
        this.totalCount = totalCount;
        this.recentPhotoUid = recentPhotoUid;
        this.recentUserId = recentUserId;
    }


    public String getPhotoUid() {
        return photoUid;
    }

    public void setPhotoUid(String photoUid) {
        this.photoUid = photoUid;
    }

    public String getTypeOfSource() {
        return typeOfSource;
    }

    public void setTypeOfSource(String typeOfSource) {
        this.typeOfSource = typeOfSource;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getSeasonality() {
        return seasonality;
    }

    public void setSeasonality(String seasonality) {
        this.seasonality = seasonality;
    }

    public String getUseOfSource() {
        return useOfSource;
    }

    public void setUseOfSource(String useOfSource) {
        this.useOfSource = useOfSource;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public double getPumpCapacity() {
        return pumpCapacity;
    }

    public void setPumpCapacity(double pumpCapacity) {
        this.pumpCapacity = pumpCapacity;
    }

    public int getSummerDays() {
        return summerDays;
    }

    public void setSummerDays(int summerDays) {
        this.summerDays = summerDays;
    }

    public int getWinterDays() {
        return winterDays;
    }

    public void setWinterDays(int winterDays) {
        this.winterDays = winterDays;
    }

    public int getMonsoonDays() {
        return monsoonDays;
    }

    public void setMonsoonDays(int monsoonDays) {
        this.monsoonDays = monsoonDays;
    }

    public int getSummerHours() {
        return summerHours;
    }

    public void setSummerHours(int summerHours) {
        this.summerHours = summerHours;
    }

    public int getWinterHours() {
        return winterHours;
    }

    public void setWinterHours(int winterHours) {
        this.winterHours = winterHours;
    }

    public int getMonsoonHours() {
        return monsoonHours;
    }

    public void setMonsoonHours(int monsoonHours) {
        this.monsoonHours = monsoonHours;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getRecentPhotoUid() {
        return recentPhotoUid;
    }

    public void setRecentPhotoUid(String recentPhotoUid) {
        this.recentPhotoUid = recentPhotoUid;
    }

    public String getRecentUserId() {
        return recentUserId;
    }

    public void setRecentUserId(String recentUserId) {
        this.recentUserId = recentUserId;
    }

}
