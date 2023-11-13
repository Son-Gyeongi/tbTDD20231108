package com.ll.standard.util;

import lombok.SneakyThrows;

public class Ut {
    // 내부 클래스
    public static class file {

        @SneakyThrows // try-catch를 자동으로 해준다.
        public static void save(String filePath, String content) {

        }

        // 파일이 있는지 체크
        public static boolean exists(String filePath) {
            return false;
        }

        @SneakyThrows // try-catch를 자동으로 해준다.
        public static void delete(String filePath) {

        }
    }
}
