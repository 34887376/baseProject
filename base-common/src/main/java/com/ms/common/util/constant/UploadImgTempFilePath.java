/**
 * 
 */
package com.ms.common.util.constant;

public class UploadImgTempFilePath {

    // linux系统下路径
    public static final String UPLOAD_PATH_LINUX = "/usr/local/tempImgFile";

    // win系统下路径
    public static final String UPLOAD_PATH_WIN = "D:\\uploade\\";

    private static String filePath;

    public static String getUploadFilePath() {
        filePath = UPLOAD_PATH_WIN;
        String osName = System.getProperty("os.name"); // 操作系统名称
//        System.out.println(osName);
        if (osName.contains("win")) {
            filePath = UPLOAD_PATH_WIN;
        }
        System.out.println(filePath);
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        UploadImgTempFilePath.getUploadFilePath();
    }

}
