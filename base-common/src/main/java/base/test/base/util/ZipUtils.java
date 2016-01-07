package base.test.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * desc:字符串压缩/解压工具，提供gzip和zip两种方式，压缩后的字符串使用base64转码
 * */

public class ZipUtils {

    private final static Log log = LogFactory.getLog(ZipUtils.class);
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 
     * 使用gzip进行压缩
     */
    public static String gzip(String primStr) {
    	if(StringUtils.isEmpty(primStr)) {
    		return null;
    	}
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String result=null;
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes(DEFAULT_ENCODING));
            gzip.finish();
            result= new String(new Base64().encode(out.toByteArray()), DEFAULT_ENCODING);
        } catch (IOException e) {
        	log.error("gzip", e);
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                	log.error("gzip close", e);
                }
            }
        }
        return result;

    }

    /**
     * 使用gzip进行解压缩
     * 
     * @param compressedStr
     * @return 解压后的字符串
     */
    public static String gunzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new Base64().decode(compressedStr.getBytes(DEFAULT_ENCODING));
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString(DEFAULT_ENCODING);
        } catch (IOException e) {
        	log.error("gunzip", e);
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                	log.error("gunzip close", e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                	log.error("gunzip close", e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                	log.error("gunzip close", e);
                }
            }
        }
        return decompressed;
    }

}
