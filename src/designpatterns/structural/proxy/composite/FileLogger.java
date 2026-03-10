package designpatterns.structural.proxy.composite;

public class FileLogger {

    public void logToFile(String log) {
        System.out.println("파일 로깅: " + log);
        // 실제: BufferedWriter로 파일 쓰기 (e.g., try-with-resources)
    }

}
