package com.ll.global.app;

import com.ll.domain.quotation.quotation.controller.QuotationController;
import com.ll.global.rq.Rq;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(final Scanner scanner) { // 무조건 final 은 습관적으로 붙인다, final은 값을 바꾸지 못한다. 상수
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        final QuotationController quotationController = new QuotationController(scanner);

        while (true) {
            final String cmd = scanner.nextLine().trim();

            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                // dispatch는 라우팅 역할, 분배하는 것도 QuotationController가 다 알아서 해야한다.
                // 삭제, 수정, 등록, 목록 모두 quotationController.dispatch(rq)가 실행된다.
                case "삭제", "수정", "등록", "목록" -> quotationController.dispatch(rq);
                case "종료" -> {
                    return;
                }
            }
        }
    }
}
