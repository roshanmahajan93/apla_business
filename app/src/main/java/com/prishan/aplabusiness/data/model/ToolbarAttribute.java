package com.prishan.aplabusiness.data.model;

public class ToolbarAttribute {
    int leftbtn;
    int leftBusy;
    String title;
    int rightbtn;
    int rightBusy;

    public ToolbarAttribute(int leftbtn, int leftBusy, String title, int rightbtn, int rightBusy) {
        this.leftbtn = leftbtn;
        this.leftBusy = leftBusy;
        this.title = title;
        this.rightbtn = rightbtn;
        this.rightBusy = rightBusy;
    }

    public int getLeftbtn() {
        return leftbtn;
    }

    public void setLeftbtn(int leftbtn) {
        this.leftbtn = leftbtn;
    }

    public int getLeftBusy() {
        return leftBusy;
    }

    public void setLeftBusy(int leftBusy) {
        this.leftBusy = leftBusy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRightbtn() {
        return rightbtn;
    }

    public void setRightbtn(int rightbtn) {
        this.rightbtn = rightbtn;
    }

    public int getRightBusy() {
        return rightBusy;
    }

    public void setRightBusy(int rightBusy) {
        this.rightBusy = rightBusy;
    }

}
