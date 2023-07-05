package com.verteil.traacsbackofficeconnector.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MasterDTO {
    @JsonProperty("STR_ACTION")
    private String STR_ACTION = "NEW";
    @JsonProperty("STR_INVOICE_DATE")
    private String STR_INVOICE_DATE;
    @JsonProperty("STR_COST_CENTRE_CODE")
    private String STR_COST_CENTRE_CODE = "CC001";
    @JsonProperty("STR_DEPARTMENT_CODE")
    private String STR_DEPARTMENT_CODE = "DP001";
    @JsonProperty("STR_ACCOUNT_CODE")
    private String STR_ACCOUNT_CODE = "TESTCCA";
    @JsonProperty("STR_SELLING_CUR_CODE")
    private String STR_SELLING_CUR_CODE = "AED";
    @JsonProperty("STR_CC_NO")
    private String STR_CC_NO;
    @JsonProperty("STR_POS_ID")
    private String STR_POS_ID = "TEST POS Machine";
}
