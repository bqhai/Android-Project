package com.example.tudienanhviet;

public class IrregularVerbs {
    private String v1;
    private String v2;
    private String v3;
    private String detail;

    public IrregularVerbs (){}

    public IrregularVerbs(String v1,String v2,String v3,String detail){
        this.v1=v1;
        this.v2=v2;
        this.v3=v3;
        this.detail=detail;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
