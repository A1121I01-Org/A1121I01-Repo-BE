package com.example.demologin.entity.customer;
import com.example.demologin.entity.Account;

import com.example.demologin.util.EmailCustom;
import com.example.demologin.util.PhoneCustom;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
//@JsonDeserialize(as=Account.class)
@Entity
@Table(name = "customer")
public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @NotBlank(message ="Tên không được để trống")
    @Size(min=6 ,max = 50 , message = "Độ dài kí tự từ 6-50 - BE")
    @Pattern(regexp = "^[A-ZÀ|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ|È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ|Ì|Í|Ị|Ỉ|Ĩ|Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ|Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ|Ỳ|Ý|Ỵ|Ỷ|Ỹ|Đ][a-zà|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ|è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ|ì|í|ị|ỉ|ĩ|ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ|ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ|ỳ|ý|ỵ|ỷ|ỹ]*([ ][A-ZÀ|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ|È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ|Ì|Í|Ị|Ỉ|Ĩ|Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ|Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ|Ỳ|Ý|Ỵ|Ỷ|Ỹ|Đ][a-zà|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ|è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ|ì|í|ị|ỉ|ĩ|ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ|ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ|ỳ|ý|ỵ|ỷ|ỹ]*)*$")
    private String customerName;

    @Column(unique = true)
    @NotBlank(message = "Không được để trống")
//    @PhoneCustom(message = "Số điện thoại đã tồn tại")
    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$")
    private String customerPhone;

    @NotBlank(message ="email không được để trống")
//    @Email(message = "Email không đúng định dạng")
//    @EmailCustom(message = "Email đã tồn tại")
    @Column(unique = true)
    @Size(min=6 ,max = 50 , message = "Độ dài kí tự từ 6-50 - BE")
    private String customerEmail;

    private String customerImage;

    @NotBlank(message = "Không được để trống")
    @Length(max = 390)
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
