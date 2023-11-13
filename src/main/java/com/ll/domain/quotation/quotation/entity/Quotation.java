package com.ll.domain.quotation.quotation.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
//@AllArgsConstructor // 생성자에 모든 필드가 만들어진다.
@RequiredArgsConstructor // final, @NonNull이 들어간 필드만 생성자로 만든다.
public class Quotation {
    @Setter
    private Long id;
    @Setter
    @NonNull
    private String authorName;
    @Setter
    @NonNull
    private String content;
}
