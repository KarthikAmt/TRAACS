package com.verteil.traacsbackofficeconnector.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MasterDTO {

    private String STR_ACTION = "NEW";
    private String STR_INVOICE_DATE;
    private String STR_COST_CENTRE_CODE;
    private String STR_DEPARTMENT_CODE;
    private String STR_ACCOUNT_CODE;
    private String STR_SELLING_CUR_CODE;
    private String STR_CC_NO;
    private String STR_POS_ID;

    public String getSTR_ACTION() {
        return STR_ACTION;
    }

    public void setSTR_ACTION(String STR_ACTION) {
        this.STR_ACTION = STR_ACTION;
    }

    public String getSTR_INVOICE_DATE() {
        return STR_INVOICE_DATE;
    }

    public void setSTR_INVOICE_DATE(String STR_INVOICE_DATE) {
        this.STR_INVOICE_DATE = STR_INVOICE_DATE;
    }

    public String getSTR_COST_CENTRE_CODE() {
        return STR_COST_CENTRE_CODE;
    }

    public void setSTR_COST_CENTRE_CODE(String STR_COST_CENTRE_CODE) {
        this.STR_COST_CENTRE_CODE = STR_COST_CENTRE_CODE;
    }

    public String getSTR_DEPARTMENT_CODE() {
        return STR_DEPARTMENT_CODE;
    }

    public void setSTR_DEPARTMENT_CODE(String STR_DEPARTMENT_CODE) {
        this.STR_DEPARTMENT_CODE = STR_DEPARTMENT_CODE;
    }

    public String getSTR_ACCOUNT_CODE() {
        return STR_ACCOUNT_CODE;
    }

    public void setSTR_ACCOUNT_CODE(String STR_ACCOUNT_CODE) {
        this.STR_ACCOUNT_CODE = STR_ACCOUNT_CODE;
    }

    public String getSTR_SELLING_CUR_CODE() {
        return STR_SELLING_CUR_CODE;
    }

    public void setSTR_SELLING_CUR_CODE(String STR_SELLING_CUR_CODE) {
        this.STR_SELLING_CUR_CODE = STR_SELLING_CUR_CODE;
    }

    public String getSTR_CC_NO() {
        return STR_CC_NO;
    }

    public void setSTR_CC_NO(String STR_CC_NO) {
        this.STR_CC_NO = STR_CC_NO;
    }

    public String getSTR_POS_ID() {
        return STR_POS_ID;
    }

    public void setSTR_POS_ID(String STR_POS_ID) {
        this.STR_POS_ID = STR_POS_ID;
    }

    @Override
    public String toString() {
        return "MasterDTO{" +
                "STR_ACTION='" + STR_ACTION + '\'' +
                ", STR_INVOICE_DATE='" + STR_INVOICE_DATE + '\'' +
                ", STR_COST_CENTRE_CODE='" + STR_COST_CENTRE_CODE + '\'' +
                ", STR_DEPARTMENT_CODE='" + STR_DEPARTMENT_CODE + '\'' +
                ", STR_ACCOUNT_CODE='" + STR_ACCOUNT_CODE + '\'' +
                ", STR_SELLING_CUR_CODE='" + STR_SELLING_CUR_CODE + '\'' +
                ", STR_CC_NO='" + STR_CC_NO + '\'' +
                ", STR_POS_ID='" + STR_POS_ID + '\'' +
                '}';
    }
}
