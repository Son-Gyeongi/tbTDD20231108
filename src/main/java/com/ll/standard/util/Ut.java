package com.ll.standard.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.*;

public class Ut {
    // 내부 클래스
    public static class file {

        // 한 번 객체를 만들어 놓고 두고두고 써라
        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

        @SneakyThrows // try-catch를 자동으로 해준다. exception 나는 거 해결
        public static void save(String filePath, Object obj) {
            // 객체를 JSON 문자열로 직렬화
            String jsonContent = OBJECT_MAPPER.writeValueAsString(obj);

            // JSON 문자열을 파일에 저장
            save(filePath, jsonContent);
        }

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
        public static boolean delete(String filePath) {
            try {
                Files.delete(Paths.get(filePath));
                return true;
            } catch (NoSuchFileException e) {
                return false; // 없는 파일 지우라고 하는 경우
            }
        }

        @SneakyThrows // try-catch를 자동으로 해준다. exception 나는 거 해결
        public static String getContent(String filePath) {
            try {
                return Files.readString(Paths.get(filePath));
                // 없는 파일이면 아예 뻗어버리는 데 try-catch로 예외가 터지면 null로 보내기로 했다.
            } catch (NoSuchFileException e) {
                return null;
            }
        }

        public static long getContentAsLong(String testFilePath, long defaultValue) {
            final String content = getContent(testFilePath);

            if (content == null) return defaultValue;

            try {
                return Long.parseLong(content);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }

        public static void save(String filePath, long content) {
            save(filePath, String.valueOf(content));
        }

        @SneakyThrows
        public static <T> T getContent(String filePath, Class<T> cls) {
            final String content = getContent(filePath);

            if (content == null) {
                return null;
            }

            return OBJECT_MAPPER.readValue(content, cls);
            // 실패하면 오류가 나는 게 좋은 거 같다.
        }
    }
}
