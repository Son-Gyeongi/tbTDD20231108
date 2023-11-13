package com.ll.domain.quotation.quotation.repository;

import com.ll.domain.quotation.quotation.entity.Quotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 영속 저장에 대한 역할을 맡았다.
public class QuotationMemoryRepository implements QuotationRepository {
    private final List<Quotation> quotations;
    private long lastId;

    public QuotationMemoryRepository() {
        quotations = new ArrayList<>();
        lastId = 0;
    }

    @Override
    public List<Quotation> findAll() {
        return quotations;
    }

    @Override
    public void delete(final Quotation quotation) {
        quotations.remove(quotation);
    }

    @Override
    public Optional<Quotation> findById(final long id) {
        return quotations
                .stream()
                .filter(quotation -> quotation.getId() == id)
                .findFirst();
    }

    @Override
    public void save(final Quotation quotation) {
        if (quotation.getId() == null) { // 신규 저장
            quotation.setId(++lastId);
            quotations.add(quotation);
        }
    }
}
