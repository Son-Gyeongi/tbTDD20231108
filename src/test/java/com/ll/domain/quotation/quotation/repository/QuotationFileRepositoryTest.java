package com.ll.domain.quotation.quotation.repository;

import com.ll.domain.quotation.quotation.entity.Quotation;
import com.ll.standard.util.Ut;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuotationFileRepositoryTest {

    // 메서드 실행 전에 데이터를 깔끔하게 지우자
    @BeforeEach
    void beforeEach() {
        Ut.file.delete("data/prod/quotation"); // 폴더를 지우자
    }

    @Test
    @DisplayName("save를 하면 quotation의 id에 새 번호가 할당된다.")
    void t1() {
        final QuotationFileRepository repository = new QuotationFileRepository();
        final Quotation quotation = new Quotation("작가1", "내용1");
        repository.save(quotation); // 자동으로 quotation의 id가 1로 할당되야 한다.

        Assertions.assertThat(quotation.getId()).isEqualTo(1L);
    }
}
