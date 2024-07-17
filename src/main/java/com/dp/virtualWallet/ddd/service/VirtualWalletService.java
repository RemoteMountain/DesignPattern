package com.dp.virtualWallet.ddd.service;

import com.dp.virtualWallet.ddd.service.domain.VirtualWallet;
import com.dp.virtualWallet.mvc.common.Status;
import com.dp.virtualWallet.mvc.common.TransactionType;
import com.dp.virtualWallet.mvc.repository.VirtualWalletEntity;
import com.dp.virtualWallet.mvc.repository.VirtualWalletRepository;
import com.dp.virtualWallet.mvc.repository.VirtualWalletTransactionEntity;
import com.dp.virtualWallet.mvc.repository.VirtualWalletTransactionRepository;
import com.dp.virtualWallet.mvc.service.BeanUtil;
import com.dp.virtualWallet.mvc.service.InsufficientBalanceException;

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
        VirtualWallet virtualWallet = getVirtualWallet(walletId);
        if (virtualWallet == null) {
            return BigDecimal.ZERO;
        }
        return virtualWallet.balance();
    }

    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.debit(amount);
        VirtualWalletTransactionEntity virtualWalletTransactionEntity = buildTransactionEntity(walletId, null, amount, TransactionType.DEBIT);
        transactionRepo.saveTransaction(virtualWalletTransactionEntity);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    private VirtualWallet convert(VirtualWalletEntity walletEntity) {
        return BeanUtil.copyProperties(walletEntity,VirtualWallet.class);
    }

    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.credit(amount);
        VirtualWalletTransactionEntity virtualWalletTransactionEntity = buildTransactionEntity(null, walletId, amount, TransactionType.CREDIT);
        transactionRepo.saveTransaction(virtualWalletTransactionEntity);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        VirtualWalletTransactionEntity transactionEntity = buildTransactionEntity(fromWalletId, toWalletId, amount, TransactionType.TRANSFER);
        Long transactionId = transactionRepo.saveTransaction(transactionEntity);
        try {
            debit(fromWalletId, amount);
            credit(toWalletId, amount);
        } catch (InsufficientBalanceException e) {
            transactionRepo.updateStatus(transactionId, Status.CLOSED);
//      ...rethrow exception e...
        } catch (Exception e) {
            transactionRepo.updateStatus(transactionId, Status.FAILED);
//      ...rethrow exception e...
        }
        transactionRepo.updateStatus(transactionId, Status.EXECUTED);
    }

    private VirtualWalletTransactionEntity buildTransactionEntity(Long fromWalletId, Long toWalletId, BigDecimal amount, TransactionType transactionType) {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        transactionEntity.setType(transactionType);
        return transactionEntity;
    }
}