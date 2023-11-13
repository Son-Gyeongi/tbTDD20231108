package com.ll.domain.quotation.quotation.repository;

import com.ll.domain.quotation.quotation.entity.Quotation;

import java.util.List;
import java.util.Optional;

// 영속 저장에 대한 역할을 맡았다.
public interface QuotationRepository {
    // 인터페이스의 경우 추상 메서드만 넣을 수 있다. 메서드에 public 생략가능

    List<Quotation> findAll();

    void delete(Quotation quotation);

    Optional<Quotation> findById(long id);

    void save(Quotation quotation);
}
