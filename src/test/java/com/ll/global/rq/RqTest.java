package com.ll.global.rq;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RqTest {

    @Test
    @DisplayName("getAction")
    void t1() {
        final Rq rq = new Rq("삭제?id=1");

        assertThat(rq.getAction()).isEqualTo("삭제");
    }

    @Test
    @DisplayName("getParameter")
    void t2() {
        final Rq rq = new Rq("삭제?이름=Paul");

        assertThat(rq.getParameter("이름")).isEqualTo("Paul");
    }

    @Test
    @DisplayName("getParameter 2")
    void t3() {
        final Rq rq = new Rq("삭제?이름=Alice");

        assertThat(rq.getParameter("이름")).isEqualTo("Alice");
    }

    @Test
    @DisplayName("getParameter 3")
    void t4() {
        final Rq rq = new Rq("삭제?id=4&이름=Alice");

        assertThat(rq.getParameter("id")).isEqualTo("4");
        assertThat(rq.getParameter("이름")).isEqualTo("Alice");
    }

    @Test
    @DisplayName("getParameterOr") // Map에 있는 기능 getOrDefault
    void t5() {
        final Rq rq = new Rq("삭제?id=4&이름=Alice");

        String age = rq.getParameter("나이", "100");

        assertThat(age).isEqualTo("100");
    }

    @Test
    @DisplayName("getParameterAsLong") // id 값을 Long으로 타입을 바꿔준다. / 형변환
    void t6() {
        final Rq rq = new Rq("삭제?id=4&이름=Alice");

        long age = rq.getParameterAsLong("id", 0);

        assertThat(age).isEqualTo(4);
    }

    @Test
    @DisplayName("getParameterAsLong 2") // 4번은 실패로 0이 반환된다.
    void t7() {
        final Rq rq = new Rq("삭제?id=4번&이름=Alice");

        long age = rq.getParameterAsLong("id", 0);

        assertThat(age).isEqualTo(0);
    }
}
