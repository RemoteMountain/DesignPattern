package com.dp.virtualWallet.ddd.service;

import com.dp.virtualWallet.mvc.repository.VirtualWalletEntity;
import com.dp.virtualWallet.mvc.repository.VirtualWalletRepository;
import com.dp.virtualWallet.mvc.repository.VirtualWalletTransactionRepository;
import com.dp.virtualWallet.mvc.service.BeanUtil;

import java.math.BigDecimal;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-20 10:04  //时间
 */
public class VirtualWalletService {

    // 通过构造函数或者IOC框架注入
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepo;

    public VirtualWallet getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        return wallet;
    }

    public BigDecimal getBalance(Long walletId) {
        return walletRepo.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.debit(amount);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    private VirtualWallet convert(VirtualWalletEntity walletEntity) {
        return BeanUtil.copyProperties(walletEntity,VirtualWallet.class);
    }

    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.credit(amount);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        //...跟基于贫血模型的传统开发模式的代码一样...
    }
}