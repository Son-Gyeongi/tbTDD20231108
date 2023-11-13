package com.ll.standard.util;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Ut {
    // 내부 클래스
    public static class file {

        @SneakyThrows // try-catch를 자동으로 해준다. exception 나는 거 해결
        public static void save(String filePath, String content) {
            final Path path = Paths.get(filePath);

            try {
                // TRUNCATE_EXISTING : 해당 경로에 파일이 있으면 삭제하고 덮어쓰기
                Files.writeString(Paths.get(filePath), content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                // 부모 디렉토리가 없어서 발생한 예외인지 확인합니다.
                final Path parentDir = path.getParent();
                if (parentDir != null && Files.notExists(parentDir)) {
                    Files.createDirectories(parentDir);
                    // 디렉토리를 생성한 후 다시 시도합니다.
                    Files.writeString(Paths.get(filePath), content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                } else {
                    // 다른 종류의 IOException이면 그대로 예외를 던집니다.
                    throw e;
                }
            }
        }

        // 파일이 있는지 체크
        public static boolean exists(String filePath) {
            return Files.exists(Paths.get(filePath));
        }

        @SneakyThrows // try-catch를 자동으로 해준다. exception 나는 거 해결
        public static void delete(String filePath) {
            Files.delete(Paths.get(filePath));
        }

        @SneakyThrows // try-catch를 자동으로 해준다. exception 나는 거 해결
        public static String getContent(String filePath) {
            return Files.readString(Paths.get(filePath));
        }

        public static long getContentAsLong(String testFilePath, long defaultValue) {
            return -1;
        }

        public static void save(String filePath, long content) {
            save(filePath, String.valueOf(content));
        }
    }
}
