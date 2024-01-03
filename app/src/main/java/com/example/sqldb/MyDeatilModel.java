package com.example.sqldb;

public class MyDeatilModel {

    int _id;
    String _fname;
    String _lname;

    private boolean isChecked;
    public MyDeatilModel(int _id, String _fname, String _lname) {
        this._id = _id;
        this._fname = _fname;
        this._lname = _lname;
    }

    public MyDeatilModel(String _fname, String _lname) {
        this._fname = _fname;
        this._lname = _lname;
    }

    public MyDeatilModel() {

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_fname() {
        return _fname;
    }

    public void set_fname(String _fname) {
        this._fname = _fname;
    }

    public String get_lname() {
        return _lname;
    }

    public void set_lname(String _lname) {
        this._lname = _lname;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
