package com.verteil.traacsbackofficeconnector.dto.mapper;

import com.google.type.Date;

import java.util.List;

public class Ticket {

    private DateOfIssue dateOfIssue;
    private volatile String ticketNumber;
    private String ticketDocTypeCode;
    private List<Coupon> coupons;

    public DateOfIssue getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(DateOfIssue dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTicketDocTypeCode() {
        return ticketDocTypeCode;
    }

    public void setTicketDocTypeCode(String ticketDocTypeCode) {
        this.ticketDocTypeCode = ticketDocTypeCode;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public List<Coupon> getCouponsList() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "dateOfIssue=" + dateOfIssue +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", ticketDocTypeCode=" + ticketDocTypeCode +
                ", coupons=" + coupons +
                '}';
    }
}
