package com.dp.principle.isp;

public class Application {

    public static ConfigSource configSource = new ZookeeperConfigSource(/*省略参数*/);
    public static final RedisConfig redisConfig = new RedisConfig(configSource);
    //public static final KafkaConfig kafkaConfig = new KakfaConfig(configSource);
    //public static final MySqlConfig mysqlConfig = new MysqlConfig(configSource);

    public static void main(String[] args) {
        ScheduledUpdater redisConfigUpdater = new ScheduledUpdater(redisConfig, 300, 300);
        redisConfigUpdater.run();
        /*ScheduledUpdater kafkaConfigUpdater = new ScheduledUpdater(kafkaConfig, 60, 60);
        redisConfigUpdater.run();*/
    }
}
