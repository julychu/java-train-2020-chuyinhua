package task3;

import java.io.File;

/**
 * @author julychu
 * @date   2020/5/24
 * @desc   使用线程池将一个目录中的所有内容拷贝到另外一个目录中，包含子目录中的内容。
 */
public class CopyFiles {

    public static void main(String[] args) {

        String path = "/Users/julychu/data/delDir"; //TODO： 此处修改你想copy的目录路径
        String distPath = "/Users/julychu/data/copyDir/dist"; //TODO： 此处修改你想copy到的目录路径

        CopyDirRunnable runnable = new CopyDirRunnable(path, distPath);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
