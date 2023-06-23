package com.verteil.traacsbackofficeconnector.dto.mapper;


import java.util.List;

public class TicketDocInfo {
    private List<Ticket> tickets;
    private TravelAgency bookingAgency;
    private TravelAgency serviceAgency;

    public List<Ticket> getTicketsList() {
        return tickets;
    }

    public void setTicket(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public TravelAgency getBookingAgency() {
        return bookingAgency;
    }

    public void setBookingAgency(TravelAgency bookingAgency) {
        this.bookingAgency = bookingAgency;
    }

    public TravelAgency getServiceAgency() {
        return serviceAgency;
    }

    public void setServiceAgency(TravelAgency serviceAgency) {
        this.serviceAgency = serviceAgency;
    }

    @Override
    public String toString() {
        return "TicketDocInfo{" +
                "tickets=" + tickets +
                ", bookingAgency=" + bookingAgency +
                ", serviceAgency=" + serviceAgency +
                '}';
    }
}
