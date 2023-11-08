package com.ll.global.app;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(final Scanner scanner) { // 무조건 final 은 습관적으로 붙인다, final은 값을 바꾸지 못한다. 상수
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
    }
}
