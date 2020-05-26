package task3;

import utils.FileUtil;

import java.io.*;

/**
 * @author julychu
 * @date 2020/5/24
 * @desc 复制内容的任务
 */
public class CopyDirRunnable implements Runnable {
    private File srcFile;
    private File distFile;

    public CopyDirRunnable(String srcPath, String distPath) {
        if (null != srcPath && !srcPath.isEmpty()) {
            this.srcFile = new File(srcPath);
        }
        if (null != distPath && !distPath.isEmpty()) {
            this.distFile = new File(distPath);
        }
    }

    @Override
    public void run() {
        if (null == srcFile && !srcFile.exists()) {
            System.out.println("文件不存在");
            return;
        }
        if (null == distFile) {
            System.out.println("目标文件为空");
            return;
        }
        if (!distFile.exists()) {
            FileUtil.createDistDir(distFile);
        }
        copyFiles(srcFile);
    }

    /**
     * 递归新建目录和复制文件
     * @param tempSrcFile
     */
    private void copyFiles(File tempSrcFile) {
        File[] files = tempSrcFile.listFiles();
        for (File file : files) {
            if(".DS_Store".equals(file.getName())) {
                continue;
            }
            String relativePath = file.getAbsolutePath().substring(srcFile.getAbsolutePath().length());
            String dirPath = distFile.getAbsolutePath() + relativePath;
            File f = new File(dirPath);
            if (file.isFile()) {
                // 复制文件
                copyFile(file, f);
            } else if (file.isDirectory()) {
                //新建目标目录
                boolean res = f.mkdir();
                System.out.println(dirPath + " " + res); //打印新建目录的结果
                // 递归
                copyFiles(file);
            }
        }
    }

    /**
     * 复制文件
     * @param srcFile
     * @param distFile
     */
    private void copyFile(File srcFile, File distFile) {
        BufferedInputStream bfInputStream = null;
        BufferedOutputStream brOutputStream = null;
        try {
            bfInputStream = new BufferedInputStream(new FileInputStream(srcFile));
            brOutputStream = new BufferedOutputStream(new FileOutputStream(distFile));
            byte[] arr = new byte[1024];
            int readRes;
            while ((readRes = bfInputStream.read(arr)) != -1) {
                brOutputStream.write(arr, 0, readRes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != brOutputStream) {
                try {
                    brOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bfInputStream) {
                try {
                    bfInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
