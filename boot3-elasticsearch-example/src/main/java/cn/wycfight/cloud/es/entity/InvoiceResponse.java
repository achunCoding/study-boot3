/**
  * Copyright 2022 bejson.com 
  */
package cn.wycfight.cloud.es.entity;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Auto-generated: 2022-03-22 12:56:41
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@ToString
public class InvoiceResponse {

    private String billCodeHelp;
    private String billCodeNum;
    private List<InvoiceResponseDetail> billDetails;
    private String billFlag;
    private String billId;
    private String billNote;
    private String billSerialNum;
    private String billType;
    private String billTypeCode;
    private String buyCompanyAdrTel;
    private String buyCompanyBankCode;
    private String buyCompanyName;
    private String companyCode;
    private String creatDate;
    private String creatResult;
    private String dealNumber;
    private String delFlag;
    private String deviceNum;
    private String errMsg;
    private String ifred;
    private String invoiceType;
    private String billCode;
    private String billNum;
    private String oldBillCode;
    private String oldBillNum;
    private String phoneSk;
    private String prStringFlag;
    private String sumBillPrice;
    private String sumupPrice;
    private String url;
    private String userFh;
    private String userKp;
    private String userSk;
    private String userType;
    private String oldSerialNum;
    private String originalOrderNum;

}