package com.dp.principle.ocp.alert;

public class Demo {

    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        // ...省略设置apiStatInfo数据值的代码
        apiStatInfo.setApi("");
        apiStatInfo.setErrorCount(1);
        apiStatInfo.setDurationOfSeconds(1);
        apiStatInfo.setRequestCount(1);
        apiStatInfo.setTimeoutCount(1);
        ApplicationContext.getInstance().getAlert().check(apiStatInfo);
    }
}
