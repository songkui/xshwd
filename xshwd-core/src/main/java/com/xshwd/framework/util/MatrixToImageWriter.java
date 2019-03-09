package com.xshwd.framework.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * @Auther: SK ON  2018/11/19 16:31
 * @Description:  二维码生成
 */
public class MatrixToImageWriter {
    /**
     * 用于设置图案的颜色
     * */
    private static final int BLACK = 0xFF000000;
    /**
     * 用于背景色
     * */
    private static final int WHITE = 0xFFFFFFFF;
    private MatrixToImageWriter() {

    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));
            }
        }
        return image;
    }

    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    public static BitMatrix createQRCode(String contents, int size) throws WriterException {
        HashMap<EncodeHintType, Object> hints = new HashMap<>(16);
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN, 1);
        return new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, size, size, hints);
    }
}
