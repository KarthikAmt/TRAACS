package com.verteil.traacsbackofficeconnector.dto.mapper;

public class FareComponent {
    private volatile String rbd;

    public String getRbd() {
        return rbd;
    }

    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    @Override
    public String toString() {
        return "FareComponent{" +
                "rbd='" + rbd + '\'' +
                '}';
    }
}
