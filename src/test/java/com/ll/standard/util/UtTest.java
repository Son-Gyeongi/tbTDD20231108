package com.ll.standard.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtTest {
    // 파일 테스트

    private final String testFilePath = "temp/test.txt";

    // 테스트 메서드 전에 실행
    @BeforeEach // junit의 기능
    void beforeEach() {
        // Utility 모아두는 곳 Ut
        Ut.file.save(testFilePath, "내용");
    }

    // 테스트 메서드 후에 실행
    @AfterEach // junit의 기능
    void afterEach() {
        // 실행 후 삭제하기
        Ut.file.delete(testFilePath);
    }

    @Test
    @DisplayName("파일 생성, 삭제")
    void t1() {
        // 파일이 있는지 체크
        assertThat(Ut.file.exists(testFilePath)).isTrue();
    }

    @Test
    @DisplayName("파일내용 읽기")
    void t2() {
        final String content = Ut.file.getContent(testFilePath);
        assertThat(content).isEqualTo("내용");
    }

    @Test
    @DisplayName("파일내용을 읽은 후 long 타입으로 변환")
    void t3() {
        // 내용이 없거나 숫자 변환 실패시
        final long id = Ut.file.getContentAsLong(testFilePath, 0);
        assertThat(id).isEqualTo(0);

        // 숫자 변환 성공 시
        final String test2FilePath = "temp/test2.txt";
        Ut.file.save(test2FilePath, 100);
        final long age = Ut.file.getContentAsLong(test2FilePath, 0);
        assertThat(age).isEqualTo(100);
    }
}
