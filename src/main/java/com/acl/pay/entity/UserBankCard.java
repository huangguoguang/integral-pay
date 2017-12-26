package com.acl.pay.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by huangguang on 2017/5/4.
 */
public class UserBankCard implements Serializable{
    //银行卡ID
    private String bankId;
    //用户ID
    private String userId;
    //银行卡号
    private String bankNo;
    //银行名称
    private String bankName;
    //银行简称
    private String bankAbbr;
    //银行LOGO
    private String bankIcon;
    //开户人姓名
    private String userName;
    //开户人身份证号
    private String idNo;
    //银行卡绑定的手机号
    private String userMobile;
    //是否设为默认
    private String isDefault;
    //创建时间
    private Date createDate;
    //修改时间
    private Date updateTime;
    //是否可用
    private String isUse;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankIcon() {
        return bankIcon;
    }

    public void setBankIcon(String bankIcon) {
        this.bankIcon = bankIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getBankAbbr() {
        return bankAbbr;
    }

    public void setBankAbbr(String bankAbbr) {
        this.bankAbbr = bankAbbr;
    }
}
