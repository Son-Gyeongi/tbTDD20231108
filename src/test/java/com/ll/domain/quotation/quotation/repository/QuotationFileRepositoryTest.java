package com.ll.domain.quotation.quotation.repository;

import com.ll.domain.quotation.quotation.entity.Quotation;
import com.ll.standard.util.Ut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuotationFileRepositoryTest {

    // 메서드 실행 전에 데이터를 깔끔하게 지우자
    @BeforeEach
    void beforeEach() {
        Ut.file.delete(QuotationFileRepository.QUOTATION_DATA_PATH); // 폴더를 지우자
    }

    @Test
    @DisplayName("save를 하면 quotation의 id에 새 번호가 할당된다.")
    void t1() {
        final QuotationFileRepository repository = new QuotationFileRepository();
        final Quotation quotation = new Quotation("작가1", "내용1");
        repository.save(quotation); // 자동으로 quotation의 id가 1로 할당되야 한다.

        assertThat(quotation.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("1번 명언을 저장하면 테이블 폴더에 1.json이 생긴다.")
    void t2() {
        final QuotationFileRepository repository = new QuotationFileRepository();
        final Quotation quotation = new Quotation("작가1", "내용1");
        repository.save(quotation); // 자동으로 quotation의 id가 1로 할당되야 한다.

        assertThat(Ut.file.exists(repository._getQuotationFilePath(quotation))).isTrue();
    }
}
