package com.eleven.util;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author zhaojinhui
 * @date 2021/2/22 13:59
 * @apiNote
 */
public class QrCodeUtils {

    /**
     * 生成二维码
     * @param url 图片路径
     * @param content 要生成的内容
     * @return 返回生成的文件流
     */
    public static InputStream generateQrCode(String url,String content){
        File file = new File("D:/im/" + getFileName(url));
        //如果文件不存在的话就生成新的文件
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //生成二维码图片
        QrCodeUtil.generate(content,
                QrConfig.create().setImg(bufferedImage(url)).setErrorCorrection(ErrorCorrectionLevel.H),
                file);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将网络地址转换成 BufferedImage
     * @param url 网络地址
     * @return 返回转换之后的内存图片
     * @author eleven
     */
    public static BufferedImage bufferedImage(String url){
        HttpResponse execute = HttpRequest.get(url)
                .execute();
        InputStream is = execute.bodyStream();
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析二维码
     * @param is 输入流
     * @return
     */
    public static Object parseQrCode(InputStream is) throws IOException {
        return QrCodeUtil.decode(ImageIO.read(is));
    }

    /**
     * 解析二维码
     * @param url 图片地址
     * @return
     */
    public static Object parseQrCode(String url){
        return QrCodeUtil.decode(bufferedImage(url));
    }

    /**
     * 获取缩略图的文件名称
     * @param url 缩略图的路径
     * @return
     */
    public static String getFileName(String url){
        return url.substring(url.lastIndexOf("/")+ 1 );
    }

    public static void main(String[] args) {
        generateQrCode("https://ftp.bmp.ovh/imgs/2021/02/4e6f90e873bc999d_thumb.jpg","2496290990@qq.com");
        System.out.println("生成成功");
    }
}
