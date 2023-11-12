package com.ll.global.app;

import com.ll.standard.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private String run(String cmd) {
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

    @Test
    @DisplayName("등록")
    void t3() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :");
    }
    /*
    꼼수를 써서 최대한 구현을 늦추자.
    테스트 케이스(TC)만들고 -> 꼼수를 써서 해결하고 -> refactoring 반복하자.
     */

    @Test
    @DisplayName("등록")
    void t3_2() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록되었습니다.");
    }
    /*
    구현하지 말고 꼼수 쓰자.
    피할 수 있을 때 까지 개발하지 말자.
    막다른 길까지 계속 개발을 피하자
     */

    @Test
    @DisplayName("등록할 때 마다 번호가 1씩 증가, 1건 등록")
    void t4() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .doesNotContain("2번 명언이 등록되었습니다.");
    }
    /*
    하나의 테스트에 하나의 assert문 들어가게 하자.
     */

    @Test
    @DisplayName("등록할 때 마다 번호가 1씩 증가, 2건 등록")
    void t5() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.")
                .doesNotContain("3번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록")
    void t6() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("목록 2")
    void t7() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                홍길동
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 홍길동 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }
    /*
    t6에서는 그냥 하드코딩으로 출력문에 집어넣었는데
    t7에서 에러가 난다. 이렇게 막다른 골목에 왔을 때 구현을 하면 된다.
     */

    @Test
    @DisplayName("삭제")
    void t8() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                홍길동
                삭제?id=1
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 홍길동 / 과거에 집착하지 마라.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("삭제 2")
    void t9() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                홍길동
                삭제?id=2
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .doesNotContain("2 / 홍길동 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }
    /*
    TDD를 할 때 바로 Rq를 안 만든다. 오버 엔지니어링을 피하게 된다.
     */

    @Test
    @DisplayName("수정")
    void t10() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                홍길동
                수정?id=2
                과거에 집착하지 마라!
                홍길동님
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .doesNotContain("2 / 홍길동 / 과거에 집착하지 마라.")
                .contains("2 / 홍길동님 / 과거에 집착하지 마라!")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("존재하지 않는 명언에 대한 삭제 예외처리")
    void t11() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                홍길동
                삭제?id=2
                삭제?id=3
                """);

        assertThat(out)
                .contains("3번 명언은 존재하지 않습니다.")
                .contains("2번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("존재하지 않는 명언에 대한 수정 예외처리")
    void t12() {
        final String out = run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                홍길동
                수정?id=2
                과거에 집착하지 마라!
                홍길동님
                수정?id=3
                """);

        assertThat(out)
                .contains("3번 명언은 존재하지 않습니다.")
                .contains("2번 명언이 수정되었습니다.");
    }
}
