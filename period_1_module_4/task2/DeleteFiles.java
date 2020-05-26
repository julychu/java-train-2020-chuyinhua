package task2;

import java.io.File;

/**
 * @author julychu
 * @date   2020/5/24
 * @desc   实现将指定目录中的所有内容删除，包含子目录中的内容都要全部删除。
 */
public class DeleteFiles {

    public static void main(String[] args) {

        String path = "/Users/julychu/data/delDir"; //TODO： 此处修改你想删除的目录路径
        File file = new File(path);
        if(null == file || !file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        delFiles(file);
//        file.delete(); //此处放开,即可删除根目录
    }

    private static void delFiles(File file) {
        File[] files = file.listFiles();
        if (null == files || files.length == 0) {
            return;
        }
        for (File f : files) {
            if (f.isFile()) {
                boolean res = f.delete();
                System.out.println(f.getPath() + "删除文件: " + (res ? "成功" : "失败"));
            } else if (f.isDirectory()) {
                delFiles(f);
                boolean res = f.delete(); //此处不可遗漏
                System.out.println(f.getPath() + "删除目录: " + (res ? "成功" : "失败"));
            }
        }
    }
}
