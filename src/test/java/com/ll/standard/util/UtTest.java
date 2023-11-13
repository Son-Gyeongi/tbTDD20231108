package com.ll.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtTest {

    // 파일 테스트
    @Test
    @DisplayName("파일 생성, 삭제")
    void t1() {
        final String filePath = "temp/test.txt";
        // Utility 모아두는 곳 Ut
        Ut.file.save(filePath, "내용");

        // 파일이 있는지 체크
        assertThat(Ut.file.exists(filePath)).isTrue();

        // 실행 후 삭제하기
        Ut.file.delete(filePath);
    }

    @Test
    @DisplayName("파일내용 읽기")
    void t2() {
        final String filePath = "temp/test.txt";
        Ut.file.save(filePath, "내용");

        final String content = Ut.file.getContent(filePath);

        // 파일이 있는지 체크
        assertThat(content).isEqualTo("내용");

        // 실행 후 삭제하기
        Ut.file.delete(filePath);
    }
}
