package com.example.binaykumar.KDlandApp;

public class RetrofitComplaints {



    private Integer snno;
    private String villagename;

    public RetrofitComplaints() {
    }

    public RetrofitComplaints(Integer snno, String villagename) {
        this.snno = snno;
        this.villagename = villagename;
    //zz    this.lastName = villagename;
      //  this.email = subject;
        //this.password = actionby;
    }

    public Integer getsnno() {
        return snno;
    }

   // public void setId(Integer id) {         this.id = id;    }

    public String getvillagename() {
        return villagename;
    }

  //  public void setFirstName(String firstName) {         this.firstName = firstName;    }


}
