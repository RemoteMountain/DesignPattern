package com.dp.virtualWallet.mvc.repository;

import java.math.BigDecimal;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-19 20:27  //时间
 */
public class VirtualWalletTransactionEntity {

    private Long walletId;
    private BigDecimal amount;
    private Long createTime;
    private Long fromWalletId;
    private Long toWalletId;
    private Status status;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setFromWalletId(Long fromWalletId) {
        this.fromWalletId = fromWalletId;
    }

    public void setToWalletId(Long toWalletId) {
        this.toWalletId = toWalletId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
