package com.dp.virtualWallet.ddd.service.domain;

import com.dp.virtualWallet.ddd.service.InvalidAmountException;
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
    // 2024-7-17 更新 透支和冻结
    //是否允许透支
    private boolean isAllowedOverdraft = true;
    //可透支金额
    private BigDecimal overdraftAmount = BigDecimal.ZERO;
    //冻结金额
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public BigDecimal balance() {
        return this.balance;
    }

    public void freeze(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("冻结金额不能小于0");
        }
        this.frozenAmount.add(amount);

    }

    public void unfreeze(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("解冻金额不能小于0");
        }
        this.frozenAmount.subtract(amount);
    }

    public void increaseOverdraftAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("增加透支金额，不能小于0");
        }
        this.overdraftAmount.add(amount);
    }

    public void decreaseOverdraftAmount(BigDecimal amount) {
        if (this.overdraftAmount.compareTo(amount) <= 0) {
            throw new InvalidAmountException("减少透支金额，不能为小于等于0");
        }
        this.overdraftAmount.subtract(amount);
    }

    public void closeOverdraft() {
        this.isAllowedOverdraft = false;
    }

    public void openOverdraft() {
        this.isAllowedOverdraft = true;
    }

    public BigDecimal getAvailableBalance() {
        BigDecimal totalAvailableBalance = this.balance.subtract(this.frozenAmount);
        if (isAllowedOverdraft) {
            totalAvailableBalance.add(this.overdraftAmount);
        }
        return totalAvailableBalance;
    }

    public void debit(BigDecimal amount) {
        BigDecimal availableBalance = getAvailableBalance();

        if (availableBalance.compareTo(amount) < 0) {
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
