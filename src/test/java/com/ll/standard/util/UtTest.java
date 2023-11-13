package com.ll.standard.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UtTest {
    // 파일 테스트

    private static final String TEMP_DIR = "temp/";
    private final String testFilePath = TEMP_DIR + "test.txt";
    private final String test2FilePath = TEMP_DIR + "test2.txt";

    // 테스트 메서드 전에 실행
    @BeforeEach
    // junit의 기능
    void beforeEach() {
        // Utility 모아두는 곳 Ut
        Ut.file.save(testFilePath, "내용");
    }

    // 테스트 메서드 후에 실행
    @AfterEach
    // junit의 기능
    void afterEach() {
        // 실행 후 삭제하기
        Ut.file.delete(testFilePath);
        // 만약 test2.txt 파일도 여러 테스트에 걸쳐 사용된다면, afterEach에서 삭제하는 것이 좋습니다.
        Ut.file.delete(test2FilePath);
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
        Ut.file.save(test2FilePath, 100);
        final long age = Ut.file.getContentAsLong(test2FilePath, 0);
        assertThat(age).isEqualTo(100);
    }

    @Test
    @DisplayName("없는 파일 읽으라는 시도를 하면 null 반환")
    void t4() {
        final String nonExistentFilePath = TEMP_DIR + "not-exists.txt";
        final String content = Ut.file.getContent(nonExistentFilePath);

        assertThat(content).isNull();
    }
}
