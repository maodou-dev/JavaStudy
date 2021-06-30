package com.example.demo.study.spring.util;

import com.example.demo.study.spring.entity.A;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * TODO
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/5/14
 * @time 22:27
 */
public class QrCodeUtil {

    public static void buildQrCode(String text, int width, int height, String filePath) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        A a = null;
        a.test();
        String c = "123";
        System.out.println();
        Class clasz = c.getClass();
//        A m = a.getClass().cast(a);
        a.getClass();
        clasz.getConstructor();
        String b = String.valueOf("1");
        System.out.println(b);
        //System.out.println(a.getString());
    }
}
