package com.ll.global.app;

import com.ll.standard.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private String run(String cmd) {
        final Scanner scanner = TestUtil.genScanner(cmd.stripIndent().trim());

        final ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        new App(scanner).run();

        // new App()에서 실행되는 모든 출력문이 out으로 모인다.
        final String out = byteArrayOutputStream.toString().trim();
        TestUtil.clearSetOutToByteArray(byteArrayOutputStream); // 원상태로 돌려준다.

        return out.trim();
    }

    @Test
    @DisplayName("프로그램 시작시에 \"== 명언 앱 ==\" 출력")
    void t1() {
        final String out = run("""
                종료
                """);

        assertThat(out)
                .contains("== 명언 앱 ==");
    }

    @Test
    @DisplayName("종료 누르면 꺼진다.")
    void t2() {
        final String out = run("""
                종료
                """);
    }

    @Test
    @DisplayName("등록")
    void t3() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :");
    }
}
