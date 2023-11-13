package com.ll.standard.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
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

    @Test
    @DisplayName("객체가 파일로 저장될 수 있다.") // 객체를 파일로 저장하려면 jackson
    void t5() {
        Ut.file.save(testFilePath, new TempArticle(1, "제목", "내용"));

        final String content = Ut.file.getContent(testFilePath);

//        System.out.println(content); // content 확인하면 json형식으로 저장된다.

        assertThat(content).isNotBlank(); // 값이 있어야 참
    }

    // 읽기만 하면 된다. 객체를 파일로 저장했으니 읽기도 가능해야한다. 이거만 하면 영속성 다 할 수 있다.
    @Test
    @DisplayName("JSON 형식으로 파일에 저장된 객체를 읽을 수 있다.")
    void t6() {
        Ut.file.save(testFilePath, new TempArticle(1, "제목", "내용"));

        // testFilePath 경로에 있는 것들을 TempArticle클래스 기준으로 객체화해서 준다.
        final TempArticle tempArticle = Ut.file.getContent(testFilePath, TempArticle.class);

        assertThat(tempArticle).isNotNull();
    }
}

// 객체 저장
@Getter
@AllArgsConstructor
class TempArticle {
    private final long id;
    private final String title;
    private final String content;
}