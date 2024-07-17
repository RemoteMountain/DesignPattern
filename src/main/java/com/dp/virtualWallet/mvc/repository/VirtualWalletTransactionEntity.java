package com.dp.virtualWallet.mvc.repository;

import com.dp.virtualWallet.mvc.common.TransactionType;

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
    //出账钱包账号
    private Long fromWalletId;
    //入账钱包账号
    private Long toWalletId;
    private TransactionType type;

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

    public void setType(TransactionType type) {
        this.type = type;
    }
}
