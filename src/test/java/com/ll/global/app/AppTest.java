package com.ll.global.app;

import com.ll.standard.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

// AppTest는 프로그램의 시작과 종료를 테스트
public class AppTest {

    public static String run(String cmd) {
        final Scanner scanner = TestUtil.genScanner(cmd.stripIndent().trim() + "\n종료");

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
        final String out = run("");

        assertThat(out)
                .contains("== 명언 앱 ==");
    }

    @Test
    @DisplayName("종료 누르면 꺼진다.")
    void t2() {
        final String out = run("");
    }
}
