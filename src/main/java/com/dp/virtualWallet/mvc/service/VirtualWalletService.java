package com.dp.virtualWallet.mvc.service;

import com.dp.virtualWallet.ddd.service.InvalidAmountException;
import com.dp.virtualWallet.mvc.common.Status;
import com.dp.virtualWallet.mvc.common.TransactionType;
import com.dp.virtualWallet.mvc.repository.*;

import java.math.BigDecimal;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-19 20:19  //时间
 */

public class VirtualWalletService {
    // 通过构造函数或者IOC框架注入
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepo;

    public VirtualWalletBo getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWalletBo walletBo = convert(walletEntity);
        return walletBo;
    }

    private VirtualWalletBo convert(VirtualWalletEntity walletEntity) {
        return BeanUtil.copyProperties(walletEntity, VirtualWalletBo.class);
    }

    public BigDecimal getBalance(Long walletId) {
        VirtualWalletBo virtualWallet = getVirtualWallet(walletId);
        if (virtualWallet == null) {
            return BigDecimal.ZERO;
        }
        return virtualWallet.getBalance();
    }

    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new NoSufficientBalanceException("余额不足，无法出账");
        }
        VirtualWalletTransactionEntity virtualWalletTransactionEntity = buildTransactionEntity(walletId, null, amount, TransactionType.DEBIT);
        transactionRepo.saveTransaction(virtualWalletTransactionEntity);
        walletRepo.updateBalance(walletId, balance.subtract(amount));
    }

    public void credit(Long walletId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("充值金额不能小于0元");
        }
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        VirtualWalletTransactionEntity virtualWalletTransactionEntity = buildTransactionEntity(null, walletId, amount, TransactionType.CREDIT);
        transactionRepo.saveTransaction(virtualWalletTransactionEntity);
        walletRepo.updateBalance(walletId, balance.add(amount));
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        //虚拟钱包非业务性功能，保证转账业务最终一致性,虚拟钱包的交易流水仅仅是用来保证支付的数据一致性。
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