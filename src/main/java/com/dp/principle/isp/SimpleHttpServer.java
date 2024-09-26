package com.dp.principle.isp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

//将配置中心的配置信息保存在http server中
public class SimpleHttpServer {

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private String host;
    private int port;
    private Map<String, List<Viewer>> viewers = new HashMap<String, List<Viewer>>();

    public SimpleHttpServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void addViewers(String urlDirectory, Viewer viewer) {
        if (!viewers.containsKey(urlDirectory)) {
            viewers.put(urlDirectory, new ArrayList<Viewer>());
        }
        this.viewers.get(urlDirectory).add(viewer);
    }



    public void run(String urlDirectory) {
    }
}
