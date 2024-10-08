package com.dp.transaction;

import org.junit.Test;
import org.mockito.Mockito;

import javax.transaction.InvalidTransactionException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author LiWang
 * @description: 交易业务 ut
 * @date 2024/9/27 10:47
 */
public class TransactionTest {


    //1、交易成功 设置交易id 和交易状态为已执行 返回rue
    @Test
    public void testExecute_inSuccess() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        String orderId = "456";
        Double amount = 100.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount);

        WalletRpcService walletRpcService = Mockito.mock(WalletRpcService.class);
        when(walletRpcService.moveMoney(any(), any(), any(), any())).thenReturn("123456");
        transaction.setWalletRpcService(walletRpcService);

        TransactionLock transactionLock = Mockito.mock(TransactionLock.class);
        when(transactionLock.lock(any())).thenReturn(true);
        doNothing().when(transactionLock).unlock(anyString());
        transaction.setTransactionLock(transactionLock);

        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
        assertEquals(STATUS.EXECUTED, transaction.getStatus());
    }


    //2、参数不合法  抛出异常
    @Test
    public void testExecute_paramInvalid() {
        Long buyerId = null;
        Long sellerId = 234L;
        Long productId = 345L;
        String orderId = "456";
        Double amount = 100.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount);
        try {
            transaction.execute();
        } catch (InvalidTransactionException e) {
            assertEquals("交易请求参数不合法", e.getMessage());
        }
    }

    //3、交易已过期  设置交易状态过期 返回false
    @Test
    public void testExecute_executeIsExpired() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        String orderId = "456";
        Double amount = 100.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount) {
            protected boolean isExpired() {
                return true;
            }
        };

        TransactionLock transactionLock = Mockito.mock(TransactionLock.class);
        when(transactionLock.lock(any())).thenReturn(true);
        doNothing().when(transactionLock).unlock(anyString());
        transaction.setTransactionLock(transactionLock);

        boolean executedResult = transaction.execute();
        assertFalse(executedResult);
        assertEquals(STATUS.EXPIRED, transaction.getStatus());

    }


    //4、交易已经执行过了  返回true
    @Test
    public void testExecute_executeIsExecuted() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        String orderId = "456";
        Double amount = 100.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount);
        transaction.setStatus(STATUS.EXECUTED);

        TransactionLock transactionLock = Mockito.mock(TransactionLock.class);
        when(transactionLock.lock(any())).thenReturn(true);
        doNothing().when(transactionLock).unlock(anyString());
        transaction.setTransactionLock(transactionLock);

        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
        assertEquals(STATUS.EXECUTED, transaction.getStatus());
    }

    //5、交易失败  设置交易状态失败 返回false
    @Test
    public void testExecute_inFail() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        String orderId = "456";
        Double amount = 100.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount);

        WalletRpcService walletRpcService = Mockito.mock(WalletRpcService.class);
        when(walletRpcService.moveMoney(any(), any(), any(), any())).thenReturn(null);
        transaction.setWalletRpcService(walletRpcService);

        TransactionLock transactionLock = Mockito.mock(TransactionLock.class);
        when(transactionLock.lock(any())).thenReturn(true);
        doNothing().when(transactionLock).unlock(anyString());
        transaction.setTransactionLock(transactionLock);

        boolean executedResult = transaction.execute();
        assertFalse(executedResult);
        assertEquals(STATUS.FAILED, transaction.getStatus());
    }


    //6、交易正在进行中 不会被重复执行 返回false
    @Test
    public void testExecute_inExecuting() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        String orderId = "456";
        Double amount = 100.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount);

        TransactionLock transactionLock = Mockito.mock(TransactionLock.class);
        when(transactionLock.lock(any())).thenReturn(false);
        doNothing().when(transactionLock).unlock(anyString());
        transaction.setTransactionLock(transactionLock);

        boolean executedResult = transaction.execute();
        assertFalse(executedResult);
    }

}
