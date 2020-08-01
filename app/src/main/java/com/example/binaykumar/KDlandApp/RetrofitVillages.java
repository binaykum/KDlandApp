package com.example.binaykumar.KDlandApp;

public class RetrofitVillages {
    private int snno;
    private String villagename;

    public RetrofitVillages(int snno, String villagename) {
        this.snno = snno;
        this.villagename = villagename;
    }

    public int getSnno() {
        return snno;
    }

    public void setSnno(int snno) {
        this.snno = snno;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }
}
