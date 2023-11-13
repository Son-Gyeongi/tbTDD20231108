package com.ll.domain.quotation.quotation.service;

import com.ll.domain.quotation.quotation.entity.Quotation;
import com.ll.domain.quotation.quotation.repository.QuotationMemoryRepository;
import com.ll.domain.quotation.quotation.repository.QuotationRepository;

import java.util.List;
import java.util.Optional;

public class QuotationService {
    // 이런 건 컨트롤러(인포데이션 직원)가 가지고 있으면 안된다.
    // System.out.println(), Scanner를 쓰면 안된다. 고객을 몰라야 한다. 고객 응대를 하면 안된다.

    private final QuotationRepository quotationRepository; // 바뀔 가능성이 없는 곳에 final 붙인다.


    public QuotationService() {
        this.quotationRepository = new QuotationMemoryRepository(); // 추상클래스, 인터페이스면 new를 할 수 없음
    }

    // 명언 모두 찾기
    public List<Quotation> findAll() {
        return quotationRepository.findAll();
    }

    // 명언 삭제
    public void remove(Quotation quotation) {
        quotationRepository.delete(quotation);
    }

    // 명언 id로 찾기
    public Optional<Quotation> findById(final long id) {
        // 데이터를 찾는 단순한 일도 리포지터리에 맡겨야 한다.
        return quotationRepository.findById(id);
    }

    // 명언 수정
    public void modify(final Quotation quotation, final String authorName, final String content) {
        quotation.setContent(content);
        quotation.setAuthorName(authorName);

        quotationRepository.save(quotation);
    }

    // 명언 등록
    public Quotation write(final String authorName, final String content) {
        final Quotation quotation = new Quotation(authorName, content);

        quotationRepository.save(quotation);

        return quotation;
    }
}
