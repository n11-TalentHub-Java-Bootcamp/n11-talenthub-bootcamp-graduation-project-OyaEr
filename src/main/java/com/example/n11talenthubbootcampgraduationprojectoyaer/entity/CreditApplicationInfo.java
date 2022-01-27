package com.example.n11talenthubbootcampgraduationprojectoyaer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name= "CREDIT_APP_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditApplicationInfo {

    @Id
    @GeneratedValue
    @Column(name="ID", nullable = false)
    private Long id;

    @Column(name="CREDIT_STATUS", nullable = false , length=5)
    private String creditStatus;

    @Column(name="CREDIT_LIMIT", nullable = false , precision=19, scale= 2)
    private BigDecimal creditLimit;

    @Column(name = "APPLICATION_DATE")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date applicationDate;

    @ManyToOne(
            optional = false
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "CUSTOMER_ID", foreignKey = @ForeignKey(name = "FK_CUSTOMER_CREDIT_ID"))
    private Customer customer;

}
