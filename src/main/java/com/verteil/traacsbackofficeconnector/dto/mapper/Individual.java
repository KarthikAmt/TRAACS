package com.verteil.traacsbackofficeconnector.dto.mapper;

public class Individual {
    private volatile String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "surname='" + surname + '\'' +
                '}';
    }
}
