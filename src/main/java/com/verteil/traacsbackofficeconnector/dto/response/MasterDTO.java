package com.verteil.traacsbackofficeconnector.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MasterDTO {
    private String STR_ACTION = "NEW";
    private String STR_INVOICE_DATE;
    private String STR_COST_CENTRE_CODE;
    private String STR_DEPARTMENT_CODE;
    private String STR_ACCOUNT_CODE;
    private String STR_SELLING_CUR_CODE;
    private String STR_CC_NO;
    private String STR_POS_ID;
}
