package com.dp.virtualWallet.ddd.service;

import com.dp.virtualWallet.mvc.service.InsufficientBalanceException;

import java.math.BigDecimal;

/**
 * @Description :基于充血模型的DDD开发模式 Domain   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-20 10:00  //时间
 */

public class VirtualWallet {

    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public BigDecimal balance() {
        return this.balance;
    }

    public void debit(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("");
        }
        this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("");
        }
        this.balance.add(amount);
    }
}
