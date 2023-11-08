package com.ll.global.app;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(final Scanner scanner) { // 무조건 final 은 습관적으로 붙인다, final은 값을 바꾸지 못한다. 상수
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        long lastQuotationId = 0;

        while (true) {
            final String cmd = scanner.nextLine().trim();

            if (cmd.equals("등록")) {
                System.out.println("명언 : ");
                final String content = scanner.nextLine().trim();
                System.out.println("작가 : ");
                final String authorName = scanner.nextLine().trim();

                ++lastQuotationId;
                final long id = lastQuotationId; // 바뀔 가능성이 없는 곳에 final 붙인다.

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            } else if (cmd.equals("종료"))  return;
        }
    }
}
