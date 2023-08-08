/**
  * Copyright 2022 bejson.com 
  */
package cn.wycfight.cloud.es.entity;

import lombok.Data;
import lombok.ToString;

/**
 * Auto-generated: 2022-03-22 12:56:41
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@ToString
public class InvoiceResponseDetail {

    private String addTaxManager;

    private String billDetailId;
    private String favourableFlag;
    private String goodsCode;
    private String goodsCodeSelf;
    private String goodsModel;
    private String goodsName;
    private Double goodsNum;
    private String goodsPrice;
    private String goodsUnit;
    private String sendBillCompanyProperty;
    private String sumPrice;
    private String taxPrice;
    private String taxRate;
    private String taxRateFlag;
}