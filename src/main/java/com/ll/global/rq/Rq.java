package com.ll.global.rq;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String action;
    private final String queryString;
    private Map<String, String> params;

    public Rq(String cmd) {
        final String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();
        queryString = cmdBits.length == 2 ? cmdBits[1].trim() : "";
        // Map으로 바껴서 들어간다.
        params = Arrays.stream(queryString.split("&"))
                .filter(param -> param.contains("="))
                .map(param -> param.split("=", 2))
                .collect(Collectors.toMap(
                        paramBits -> paramBits[0].trim(), // key
                        paramBits -> paramBits[1].trim(), // value
                        (existing, replacement) -> replacement)); // merge function, in case of key conflict
        // (existing, replacement) -> replacement : 중복된 거를 어떻게 처리할 건지 물어보는 거, 지금은 몰라도 된다.
    }

    public String getAction() {
        return action;
    }

    public String getParameter(final String paramName) {
        return params.get(paramName); // 있으면 값을 주고 없으면 null을 준다.
    }

    public String getParameter(String paramName, String defaultValue) {
        // Map에 있는 기능 getOrDefault
        return params.getOrDefault(paramName, defaultValue); // 있으면 값을 주고 없으면 defaultValue를 준다.
    }
}
