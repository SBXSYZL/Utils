import com.sun.istack.internal.NotNull;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Base64_Util {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

    /*
     * @Author:  Yzl
     * @Param:   图片路径
     * @Desc:    图片转成Base64字符串
     * @return:  转换后的Base64字符串
     * */
    public static String imageToBase_64(@NotNull String url) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(url);
            data = new byte[in.available()];
            int read = in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder base64Encoder = new BASE64Encoder();

        return data == null ? "" : base64Encoder.encode(data);

    }

    /*
     * @Author:  YZL
     * @Param:   base64字符串
     * @Desc:    将Base64字符串转成图片，并存入路径
     * @retutn:  转换成功返回true,否则返回false
     * */
    public static boolean base64ToImage(@NotNull String baseStr, @NotNull String path) {
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] bytes = base64Decoder.decodeBuffer(baseStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            OutputStream outputStream = new FileOutputStream(path);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author:  YZL
     * @Desc:    将当前时间转成字符串作为图片名称返回
     * @retutn:  生成的图片名称
     * */
    public static String getNewImageNameByDate() {
        Date date = new Date();
        return format.format(date);
    }

}
