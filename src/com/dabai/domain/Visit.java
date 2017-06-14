package com.dabai.domain;

/**
 * 客户拜访的javaBean
 */
public class Visit {

    //主键
    private String visit_id;
    //拜访时间
    private String visit_time;
    //被拜访人
    private String visit_interviewee;
    //拜访地点
    private String visit_addr;
    //拜访详情
    private String visit_detail;
    //下次拜访时间
    private String visit_nexttime;

    //外键,和客户
    private Customer customer;
    //外键,和用户
    private User user;

    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public String getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(String visit_time) {
        this.visit_time = visit_time;
    }

    public String getVisit_interviewee() {
        return visit_interviewee;
    }

    public void setVisit_interviewee(String visit_interviewee) {
        this.visit_interviewee = visit_interviewee;
    }

    public String getVisit_addr() {
        return visit_addr;
    }

    public void setVisit_addr(String visit_addr) {
        this.visit_addr = visit_addr;
    }

    public String getVisit_detail() {
        return visit_detail;
    }

    public void setVisit_detail(String visit_detail) {
        this.visit_detail = visit_detail;
    }

    public String getVisit_nexttime() {
        return visit_nexttime;
    }

    public void setVisit_nexttime(String visit_nexttime) {
        this.visit_nexttime = visit_nexttime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
