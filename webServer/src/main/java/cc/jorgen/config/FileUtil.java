package cc.jorgen.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Purpose of this class ...
 * <p>
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-03-28.
 */
public class FileUtil {


    /**
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static Stream<String> fileToStream(String filePath) throws IOException {
        return readFile(filePath);
    }

    /**
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String fileToString(String filePath) throws IOException {
        Stream<String> fileData = readFile(filePath);

        if (fileData == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        fileData.forEachOrdered(line -> {
            sb.append(line);
        });

        return sb.toString();
    }

    public static byte[] fileToBytes(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        if (!fileExists(path)) {
            return null;
        }

        return Files.readAllBytes(path);
    }


    private static Stream<String> readFile(String fullPath) throws IOException {
        Path path = Paths.get(fullPath);

        if (!fileExists(path)) {
            return null;
        }

        return Files.lines(path, StandardCharsets.UTF_8);
    }

    public static boolean fileExists(String filePath) {
        Path path = Paths.get(filePath);
        return fileExists(path);
    }

    private static boolean fileExists(Path path) {
        return Files.exists(path);
    }
}
