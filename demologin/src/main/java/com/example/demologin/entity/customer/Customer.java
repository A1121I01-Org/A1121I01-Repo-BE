package com.example.demologin.entity.customer;
import com.example.demologin.entity.Account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
//@JsonDeserialize(as=Account.class)
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @NotBlank(message ="Tên không được để trống")
    @Size(min=6 ,max = 50 , message = "Độ dài kí tự từ 6-50 - BE")
    private String customerName;

    private String customerPhone;

    private String customerEmail;

    private String customerImage;

    private String customerAddress;

    private Boolean customerFlag = false;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_account_id")
    private Account customerAccountId;

    public Customer() {
    }

    public Customer(Long customerId, String customerName, String customerPhone, String customerEmail,
                    String customerImage, String customerAddress, Boolean customerFlag,
                    Account customerAccountId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerImage = customerImage;
        this.customerAddress = customerAddress;
        this.customerFlag = customerFlag;
        this.customerAccountId = customerAccountId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Boolean getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(Boolean customerFlag) {
        this.customerFlag = customerFlag;
    }

    public Account getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(Account customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    //    public String getPrettyTime() {
//        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
//        return pt.format(convertToDateViaInstant(getCreationDate()));
//    }

//    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
//        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
//    }


    @Override
    public String toString() {
        return this.customerName;
    }
}
