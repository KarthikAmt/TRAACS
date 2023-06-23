package com.verteil.traacsbackofficeconnector.dto.response;

import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class JSONDataResponseDTO {
    private AuthenticationKeyDTO str_authentication_key;
    private MasterDTO json_master;
    private List<TicketDTO> json_Ticket;

    public AuthenticationKeyDTO getStr_authentication_key() {
        return str_authentication_key;
    }

    public void setStr_authentication_key(AuthenticationKeyDTO str_authentication_key) {
        this.str_authentication_key = str_authentication_key;
    }

    public MasterDTO getJson_master() {
        return json_master;
    }

    public void setJson_master(MasterDTO json_master) {
        this.json_master = json_master;
    }

    public List<TicketDTO> getJson_Ticket() {
        return json_Ticket;
    }

    public void setJson_Ticket(List<TicketDTO> json_Ticket) {
        this.json_Ticket = json_Ticket;
    }

    @Override
    public String toString() {
        return "JSONDataResponseDTO{" +
                "str_authentication_key=" + str_authentication_key +
                ", json_master=" + json_master +
                ", json_Ticket=" + json_Ticket +
                '}';
    }
}
