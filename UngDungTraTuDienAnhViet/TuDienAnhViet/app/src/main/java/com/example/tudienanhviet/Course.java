package com.example.tudienanhviet;

class Course {
    private String courseName;
    private String courseDetail;
    private String url;

    public Course(String name,String detail,String url){
        this.courseName=name;
        this.courseDetail=detail;
        this.url=url;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
