package base.test.base.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.ms.common.util.constant.UploadImgTempFilePath;


public class ImageUtil {

    // 截取的正方形图片边长为 800
    public static final int IMG_CUT_LEN = 700;

    /**
     * 截取一个图像的中央区域
     * 
     * @param image
     *            图像File
     * @param w
     *            需要截图的宽度
     * @param h
     *            需要截图的高度
     * @return 返回一个
     * @throws IOException
     */
    public static void cutImage(File image, int w, int h, String newFilePath) throws IOException {

        // 判断参数是否合法
        if (null == image || 0 == w || 0 == h) {
            new Exception("哎呀，截图出错！！！");
        }
        InputStream inputStream = new FileInputStream(image);
        // 用ImageIO读取字节流
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        BufferedImage distin = null;
        // 返回源图片的宽度。
        int srcW = bufferedImage.getWidth();
        // 返回源图片的高度。
        int srcH = bufferedImage.getHeight();
        if (w > srcW)
            w = srcW;
        if (h > srcH)
            h = srcH;
        int x = 0, y = 0;
        // 使截图区域居中
        x = srcW / 2 - w / 2;
        y = srcH / 2 - h / 2;
        int srcW1 = srcW / 2 + w / 2;
        int srcH1 = ((srcH / 2) + (h / 2));
        // 生成图片
        distin = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = distin.getGraphics();
        g.drawImage(bufferedImage, 0, 0, w, h, x, y, srcW1, srcH1, null);
        // ImageIO.write(distin, "jpg", new File("F://picCutTest/Tulips_cut.jpg"));
        ImageIO.write(distin, "jpg", new File(newFilePath));
    }

    public static void main(String[] args) throws Exception {
//        File file = new File("D:/uploade/cf2964b7-bc95-4b97-9d75-0d7a3d13288e.jpg");
//        cutImage(file, 500, 500, "D:/uploade/c082cde3-6d2c-44e2-9119-094ab5c3ae01_cut.jpg");
//    	String path = Thread.currentThread().getClass().getResource("/").getPath();
    	String path = ServletActionContext.getRequest().getRealPath("/upload");
    	System.out.println(path);
    }

    /**
     * 存储发布的话题的 原始图片到本地
     * 
     * @author chenchangqun@jd.com
     * @date 2015年12月7日 上午10:31:36
     * @param file
     * @param tempFileName
     * @return String
     */
    public static String saveFile(File file, String tempFileName, String aimPath) {
    	String path = ServletActionContext.getRequest().getRealPath("/upload");
        int lastDotIndex = tempFileName.lastIndexOf(".");
        String imgType = tempFileName.substring(lastDotIndex, tempFileName.length());
        String tempName = UploadImgTempFilePath.getUploadFilePath() + UUID.randomUUID() + imgType;
        if(StringUtils.isNotBlank(aimPath)){
        	tempName = aimPath+ "\\"+UUID.randomUUID() + imgType;
        }
        try {

            // 建立文件输出流
            FileOutputStream fos = new FileOutputStream(tempName);
            // 建立文件上传流
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fis.close();
            fos.close();
        } catch (Exception e) {
            return null;
        }

        return tempName;

    }
    
    /**
     * 删除指定文件
     * @param tempFilePath
     */
    public static void delTempFile(String tempFilePath){
    	File tempFile= new File(tempFilePath);
    	tempFile.deleteOnExit();
    }

    
    /**
     * 存储图片的截图到本地
     * 
     * @author chenchangqun@jd.com
     * @date 2015年12月7日 上午10:29:51
     * @param len
     *            截取的正方形图片边长
     * @param tempFileName
     * @return String
     */
    public static String saveCutFile(String tempName, int len) {
        String cutFileName = tempName.substring(0, tempName.indexOf(".")) + "_cut" + tempName.substring(tempName.indexOf("."));
        File originFile = new File(tempName);
        try {
            cutImage(originFile, len, len, cutFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cutFileName;

    }

}
