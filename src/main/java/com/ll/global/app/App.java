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
            final String action = rq.getAction();

            switch (action) {
                case "삭제" -> {
                    quotationController.actionRemove(rq);
                }
                case "수정" -> {
                    quotationController.actionModify(rq);
                }
                case "등록" -> {
                    quotationController.actionWrite(); // Show 라고 안 붙인 거는 CUD가 일어난다. Create, Update, Delete
                }
                case "목록" -> {
                    quotationController.actionShowList(); // Show 라고 붙인 거는 R, Read만 일어난다.
                }
                case "종료" -> {
                    return;
                }
            }
        }
    }
}
