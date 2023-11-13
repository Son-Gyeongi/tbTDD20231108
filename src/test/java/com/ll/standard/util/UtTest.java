package com.ll.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtTest {

    // 파일 테스트
    @Test
    @DisplayName("파일 생성")
    void t1() {
        String filePath = "temp/test.txt";
        // Utility 모아두는 곳 Ut
        Ut.file.save(filePath, "내용");

        // 파일이 있는지 체크
        assertThat(Ut.file.exists(filePath)).isTrue();

        // 실행 후 삭제하기
        Ut.file.delete(filePath);
    }
}
