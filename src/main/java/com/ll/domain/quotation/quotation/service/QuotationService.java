package com.ll.domain.quotation.quotation.service;

import com.ll.domain.quotation.quotation.entity.Quotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuotationService {
    // 이런 건 컨트롤러(인포데이션 직원)가 가지고 있으면 안된다.
    // System.out.println(), Scanner를 쓰면 안된다. 고객을 몰라야 한다. 고객 응대를 하면 안된다.
    private final List<Quotation> quotations;
    private long lastQuotationId;

    public QuotationService() {
        quotations = new ArrayList<>();
        lastQuotationId = 0;
    }

    // 명언 모두 찾기
    public List<Quotation> findAll() {
        return quotations;
    }

    // 명언 삭제
    public void remove(Quotation quotation) {
        quotations.remove(quotation);
    }

    // 명언 id로 찾기
    public Optional<Quotation> findById(long id) {
        return quotations
                .stream()
                .filter(quotation -> quotation.getId() == id)
                .findFirst();

    }

    // 명언 수정
    public void modify(Quotation quotation, String authorName, String content) {
        quotation.setContent(content);
        quotation.setAuthorName(authorName);
    }

    // 명언 등록
    public Quotation write(String authorName, String content) {
        final long id = ++lastQuotationId; // 바뀔 가능성이 없는 곳에 final 붙인다.

        final Quotation quotation = new Quotation(id, authorName, content);
        quotations.add(quotation);

        return quotation;
    }
}
