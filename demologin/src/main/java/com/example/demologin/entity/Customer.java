package com.example.demologin.entity;
import com.example.demologin.service.BeanUtil;
import lombok.Getter;
import lombok.Setter;
import org.ocpsoft.prettytime.PrettyTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @NotBlank(message ="Tên không được để trống")
    @Size(min=6 ,max = 50 , message = "Độ dài kí tự từ 6-50 - BE")
    private String customerName;
    @NotBlank(message ="Địa chỉ không được để trống")
    @Size(min=6 ,max = 255 , message = "Độ dài kí tự từ 6-255 - BE")
    private String customerAddress;

    public Customer() {
    }

    public Customer(Long customerId, @NotBlank(message = "Tên không được để trống") @Size(min = 6, max = 50, message = "Độ dài kí tự từ 6-50 - BE") String customerName, @NotBlank(message = "Địa chỉ không được để trống") @Size(min = 6, max = 255, message = "Độ dài kí tự từ 6-255 - BE") String customerAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
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
