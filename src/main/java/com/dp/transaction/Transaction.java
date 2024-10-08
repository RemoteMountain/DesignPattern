package com.dp.transaction;

import com.dp.idgenerator.IdGenerator;
import com.dp.idgenerator.RandomIdGenerator;
import com.google.common.annotations.VisibleForTesting;

import javax.transaction.InvalidTransactionException;


/**
 * @author LiWang
 * @description: 交易业务
 * @date 2024/9/27 10:31
 */
public class Transaction {
    private String id;
    private Long buyerId;
    private Long sellerId;
    private Long productId;
    private String orderId;
    private Long createTimestamp;
    private Double amount;
    private STATUS status;
    private String walletTransactionId;

    private IdGenerator idGenerator;

    private WalletRpcService walletRpcService;

    private TransactionLock transactionLock;


    // ...get() methods...

    public Transaction(String preAssignedId, Long buyerId, Long sellerId, Long productId, String orderId, Double amount) {
        idGenerator = new RandomIdGenerator();
        fillTransactionId(preAssignedId);
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.orderId = orderId;
        this.amount = amount;
        this.status = STATUS.TO_BE_EXECUTD;
        this.createTimestamp = System.currentTimeMillis();
    }

    private void fillTransactionId(String preAssignedId) {
        if (preAssignedId != null && !preAssignedId.isEmpty()) {
            this.id = preAssignedId;
        } else {
            this.id = idGenerator.generate();
        }
        if (!this.id.startsWith("t_")) {
            this.id = "t_" + preAssignedId;
        }
    }

    public void setWalletRpcService(WalletRpcService walletRpcService) {
        this.walletRpcService = walletRpcService;
    }

    public void setTransactionLock(TransactionLock transactionLock) {
        this.transactionLock = transactionLock;
    }

    public STATUS getStatus() {
        return this.status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public boolean execute() throws InvalidTransactionException {

        if (buyerId == null || sellerId == null || amount < 0.0) {
            throw new InvalidTransactionException("交易请求参数不合法");
        }
        if (status == STATUS.EXECUTED) return true;
        boolean isLocked = false;
        try {
            isLocked = transactionLock.lock(id);
            if (!isLocked) {
                return false; // 锁定未成功，返回false，job兜底执行
            }
            if (status == STATUS.EXECUTED) return true; // double check
            if (isExpired()) {
                this.status = STATUS.EXPIRED;
                return false;
            }
            String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId, amount);
            if (walletTransactionId != null) {
                this.walletTransactionId = walletTransactionId;
                this.status = STATUS.EXECUTED;
                return true;
            } else {
                this.status = STATUS.FAILED;
                return false;
            }
        } finally {
            if (isLocked) {
                transactionLock.unlock(id);
            }
        }
    }

    @VisibleForTesting
    protected boolean isExpired() {
        long executionInvokedTimestamp = System.currentTimeMillis();
        return (executionInvokedTimestamp - createTimestamp) > 14 * 24 * 60 * 60 * 1000;
    }
}
