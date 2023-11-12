package com.ll.domain.quotation.quotation.controller;

import com.ll.domain.quotation.quotation.entity.Quotation;
import com.ll.global.rq.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuotationController {
    private final Scanner scanner;
    private final List<Quotation> quotations;
    private long lastQuotationId;

    public QuotationController(final Scanner scanner) {
        this.scanner = scanner;
        quotations = new ArrayList<>();
        lastQuotationId = 0;
    }

    public void actionRemove(Rq rq) {
        long id = rq.getParameterAsLong("id", 0);

        quotations
                .stream()
                .filter(quotation -> quotation.getId() == id)
                .findFirst() // 하나라도 있으면 줘라. Optional로 준다.
                .ifPresentOrElse(
                        quotation -> {
                            quotations.remove(quotation);
                            System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
                        },
                        () -> System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id))
                );
    }

    public void actionModify(final Rq rq) {
        long id = rq.getParameterAsLong("id", 0);

        quotations
                .stream()
                .filter(_quotation -> _quotation.getId() == id)
                .findFirst() // 하나라도 있으면 줘라. Optional로 준다.
                .ifPresentOrElse(
                        quotation -> {
                            System.out.println("명언(기존) : %s".formatted(quotation.getContent()));
                            System.out.println("명언 : ");
                            final String content = scanner.nextLine().trim();
                            System.out.println("작가(기존) : %s".formatted(quotation.getAuthorName()));
                            System.out.println("작가 : ");
                            final String authorName = scanner.nextLine().trim();

                            quotation.setContent(content);
                            quotation.setAuthorName(authorName);

                            System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
                        },
                        () -> System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id))
                );
    }

    public void actionShowList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        quotations
                .reversed() // 뒤집어준다.
                .forEach(
                        quotation -> System.out.println(
                                "%d / %s / %s".formatted(
                                        quotation.getId(),
                                        quotation.getAuthorName(),
                                        quotation.getContent()
                                )
                        )
                );
    }

    public void actionWrite() {
        System.out.println("명언 : ");
        final String content = scanner.nextLine().trim();
        System.out.println("작가 : ");
        final String authorName = scanner.nextLine().trim();

        final long id = ++lastQuotationId; // 바뀔 가능성이 없는 곳에 final 붙인다.

        final Quotation quotation = new Quotation(id, authorName, content);
        quotations.add(quotation);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
    }

    // dispatch는 라우팅 역할, 분배하는 것도 QuotationController가 다 알아서 해야한다.
    public void dispatch(Rq rq) {
        switch (rq.getAction()) {
            case "삭제" -> actionRemove(rq);
            case "수정" -> actionModify(rq);
            case "목록" -> actionShowList();
            case "등록" -> actionWrite();
        }
    }
}
