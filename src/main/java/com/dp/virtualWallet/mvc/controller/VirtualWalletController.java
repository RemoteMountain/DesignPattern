package com.dp.virtualWallet.mvc.controller;

import com.dp.virtualWallet.mvc.service.VirtualWalletService;

import java.math.BigDecimal;

/**
 * @Description : 基于贫血模型的传统MVC开发模式的虚拟钱包controller层  //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-19 20:16  //时间
 */
public class VirtualWalletController {

    private VirtualWalletService virtualWalletService;

    //查询余额
    public BigDecimal getBalance(Long walletId) {
        return virtualWalletService.getBalance(walletId);
    }

    //出账
    public void debit(Long walletId, BigDecimal amount) {
        virtualWalletService.debit(walletId,amount);
    }

    //入账
    public void credit(Long walletId, BigDecimal amount) {
        virtualWalletService.credit(walletId,amount);
    }

    //转账
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount){
        virtualWalletService.transfer(fromWalletId,toWalletId,amount);
    }
}
