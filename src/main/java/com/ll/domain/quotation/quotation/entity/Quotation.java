package com.ll.domain.quotation.quotation.entity;

import lombok.*;

import java.util.Objects;

@Getter
//@AllArgsConstructor // 생성자에 모든 필드가 만들어진다.
@RequiredArgsConstructor // final, @NonNull이 들어간 필드만 생성자로 만든다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// jackson라이브러리 쓸 때 인자 없는 생성자가 있어야 한다. 굳이 공개할 필요없다.
// jackson을 통해서 객체를 복원하겠다면 인자 없는 생성자가 있어야 한다.
public class Quotation {
    @Setter
    private Long id;
    @Setter
    @NonNull
    private String authorName;
    @Setter
    @NonNull
    private String content;

    /*
    자바에서는 객체 내용이 같다라고 해도 같은 객체로 인정 안 해준다.
    그래서 기준, 룰을 세워줘야 한다.
    자바 클래스에서는 어떤 두 객체가 같다는 것을 자바가 스스로 모르고
    우리가 알려줘야 한다.
    아래와 같이 하면 Quotation 객체 2개를 같은지 다른지 오직 id가 같은 걸로 비교한다.
    id가 서로 같으면 같은 객체이다.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quotation quotation = (Quotation) o;

        return Objects.equals(id, quotation.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
