package com.ll.global.app;

import com.ll.standard.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    @DisplayName("프로그램 시작시에 \"== 명언 앱 ==\" 출력")
    void t1() {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        new App().run();

        // new App()에서 실행되는 모든 출력문이 out으로 모인다.
        String out = byteArrayOutputStream.toString().trim();
        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);

        assertThat(out)
                .contains("== 명언 앱 ==");
    }
}
