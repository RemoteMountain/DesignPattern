package com.dp.transaction;

import com.dp.idgenerator.IdGenerator;
import com.dp.idgenerator.RandomIdGenerator;

/**
 * @author LiWang
 * @description: 钱包rpc服务
 * @date 2024/9/27 10:45
 */
public class WalletRpcService {

    private IdGenerator idGenerator;

    public WalletRpcService() {
        this.idGenerator = new RandomIdGenerator();
    }

    /**
     * 转账
     *
     * @param id
     * @param buyerId
     * @param sellerId
     * @param amount
     * @return 交易id
     */
    public String moveMoney(String id, Long buyerId, Long sellerId, Double amount) {
        return idGenerator.generate();
    }
}
