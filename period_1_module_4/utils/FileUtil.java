package utils;

import java.io.File;

public class FileUtil {
    /**
     * 创建目标目录
     * @param file
     */
    public static void createDistDir(File file) {
        if(!file.getParentFile().exists()) {
            createDistDir(file.getParentFile());
        }
        file.mkdir();
    }
}
