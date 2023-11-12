package com.ll.domain.quotation.quotation.controller;

import com.ll.domain.quotation.quotation.entity.Quotation;
import com.ll.domain.quotation.quotation.service.QuotationService;
import com.ll.global.rq.Rq;

import java.util.Optional;
import java.util.Scanner;

public class QuotationController {
    private final Scanner scanner;
    private final QuotationService quotationService;

    public QuotationController(final Scanner scanner) {
        this.scanner = scanner;
        quotationService = new QuotationService();
    }

    // 명언 삭제
    public void actionRemove(Rq rq) {
        long id = rq.getParameterAsLong("id", 0);

        quotationService.findAll()
                .stream()
                .filter(quotation -> quotation.getId() == id)
                .findFirst() // 하나라도 있으면 줘라. Optional로 준다.
                .ifPresentOrElse(
                        quotation -> {
                            // remove도 Service한테 요청해야 한다.
                            quotationService.remove(quotation);
                            System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
                        },
                        () -> System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id))
                );
    }

    // 명언 수정
    public void actionModify(final Rq rq) {
        long id = rq.getParameterAsLong("id", 0);

        // 내가 직접 찾는 게 아닌 서비스에 넘긴다.
        // 있을 수도 있고 없을 수도 있으면 Optional로 감싼다.
        Optional<Quotation> quotationOpt = quotationService.findById(id);

        quotationOpt
                .ifPresentOrElse(
                        quotation -> { // 값이 있으면
                            System.out.println("명언(기존) : %s".formatted(quotation.getContent()));
                            System.out.println("명언 : ");
                            final String content = scanner.nextLine().trim();
                            System.out.println("작가(기존) : %s".formatted(quotation.getAuthorName()));
                            System.out.println("작가 : ");
                            final String authorName = scanner.nextLine().trim();

                            // 데이터 수정도 서비스에 맡겨야 한다.
                            quotationService.modify(quotation, authorName, content);

                            System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
                        },
                        // 값이 없으면
                        () -> System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id))
                );
    }

    // 명언 모두 찾기
    public void actionShowList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        quotationService.findAll()
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

    // 명언 등록
    public void actionWrite() {
        System.out.println("명언 : ");
        final String content = scanner.nextLine().trim();
        System.out.println("작가 : ");
        final String authorName = scanner.nextLine().trim();

        final Quotation quotation = quotationService.write(authorName, content);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(quotation.getId()));
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
