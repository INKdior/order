package com.ordersystem.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "payment_instructions")
@Data
public class PaymentInstructionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 指令ID

    private String instructionNumber; // 指令编号

    private String source; // 指令来源

    private String type; // 指令类型

    private String businessType; // 业务类型

    private String projectName; // 项目名称

    private String customerName; // 客户名称

    private String payerAccount; // 付款账号

    private String payerName; // 付款人名

    private String payeeAccount; // 收款账号

    private String payeeName; // 收款人名

    private String bank; // 收款开户行

    @Column(precision = 18, scale = 2)
    private BigDecimal amount; // 金额

    @Column(length = 10)
    private String currency; // 币种

    @Temporal(TemporalType.DATE)
    private String transferDate; // 划款日期

    private String purpose; // 用途

    private Boolean immediateTransfer; // 是否立即划款

    @Lob
    private String remarks; // 流程备注

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt; // 创建时间

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt; // 更新时间

    @Column
    private Integer paymentType; // 付款类型（行内转账、跨行转账、智能支付）

    @Column
    private Integer largeAmountLineNumber; // 大额行号

    private String largeAmount; // 大写金额

}