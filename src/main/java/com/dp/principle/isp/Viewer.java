package com.dp.principle.isp;

import java.util.Map;

public interface Viewer {

    String outputInPlainText();
    Map<String, String> output();
}
