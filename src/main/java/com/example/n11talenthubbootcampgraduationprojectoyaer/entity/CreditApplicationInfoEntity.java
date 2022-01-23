package com.example.n11talenthubbootcampgraduationprojectoyaer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name= "CREDIT_APP_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationInfoEntity {

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
    @JoinColumn(name = "CLIENT_ID", foreignKey = @ForeignKey(name = "FK_CLIENT_CREDIT_ID"))
    private ClientEntity client;

}
