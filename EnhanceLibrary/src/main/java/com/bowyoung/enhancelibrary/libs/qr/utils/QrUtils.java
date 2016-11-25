package com.bowyoung.enhancelibrary.libs.qr.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;

import com.bowyoung.enhancelibrary.config.Constants;
import com.bowyoung.enhancelibrary.libs.qr.camera.BitmapLuminanceSource;
import com.bowyoung.enhancelibrary.libs.qr.camera.CameraManager;
import com.bowyoung.enhancelibrary.libs.qr.decoding.DecodeFormatManager;
import com.bowyoung.enhancelibrary.utils.StringUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by S0S on 16/4/28.
 */
public class QrUtils {

    public static final String RESULT_TYPE = "result_type";
    public static final String RESULT_STRING = "result_string";
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAILED = 2;


    /**
     * 解析二维码图片工具类
     *
     * @param analyzeListener
     */
    public static void analyzeBitmap(String path, AnalyzeListener analyzeListener) {
        /**
         * 首先判断图片的大小,若图片过大,则执行图片的裁剪操作,防止OOM
         */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 先获取原大小
        options.inJustDecodeBounds = false; // 获取新的大小
        int sampleSize = (int) (options.outHeight / 400.0f);
        if (sampleSize <= 0)
            sampleSize = 1;
        options.inSampleSize = sampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        analyzeBitmap(bitmap, analyzeListener);
    }

    public static void analyzeBitmap(Bitmap bitmap, AnalyzeListener analyzeListener) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        // 解码的参数
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>(2);
        // 可以解析的编码类型
        Vector<BarcodeFormat> decodeFormats = new Vector<BarcodeFormat>();
        if (decodeFormats == null || decodeFormats.isEmpty()) {
            decodeFormats = new Vector<BarcodeFormat>();
            // 设置可扫描的类型
            decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        }
        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
        // 设置继续的字符编码格式为UTF8
        // hints.put(DecodeHintType.CHARACTER_SET, "UTF8");
        // 设置解析配置参数
        multiFormatReader.setHints(hints);

        // 开始对图像资源解码
        Result rawResult = null;
        try {
            rawResult = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(bitmap))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rawResult != null) {
            if (analyzeListener != null) {
                analyzeListener.onSuccess(bitmap, rawResult.getText());
            }
        } else {
            if (analyzeListener != null) {
                analyzeListener.onFailed();
            }
        }
    }

    public static Bitmap createQrBitmap(String text, int w, int h) {
        return createQrBitmap(text, w, h, null);
    }

    public static Bitmap createQrBitmap(String text, int longer) {
        return createQrBitmap(text, longer, null);
    }

    public static Bitmap createQrBitmap(String text, int longer, Bitmap icon) {
        return createQrBitmap(text, longer, longer, icon);
    }

    /**
     * @param text 字符串
     * @param w    二维码宽
     * @param h    二维码高
     * @param icon 中间的 icon
     * @return
     */
    public static Bitmap createQrBitmap(String text, int w, int h, Bitmap icon) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        try {
            Bitmap scaleLogo = getScaleIcon(icon, w, h);

            int offsetX = w / 2;
            int offsetY = h / 2;

            int scaleWidth = 0;
            int scaleHeight = 0;
            if (scaleLogo != null) {
                scaleWidth = scaleLogo.getWidth();
                scaleHeight = scaleLogo.getHeight();
                offsetX = (w - scaleWidth) / 2;
                offsetY = (h - scaleHeight) / 2;
            }
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, Constants.Encoding.UTF_8);
            //容错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边距的宽度
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, w, h, hints);
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (x >= offsetX && x < offsetX + scaleWidth && y >= offsetY && y < offsetY + scaleHeight) {
                        int pixel = scaleLogo.getPixel(x - offsetX, y - offsetY);
                        if (pixel == 0) {
                            if (bitMatrix.get(x, y)) {
                                pixel = 0xff000000;
                            } else {
                                pixel = 0xffffffff;
                            }
                        }
                        pixels[y * w + x] = pixel;
                    } else {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * w + x] = 0xff000000;
                        } else {
                            pixels[y * w + x] = 0xffffffff;
                        }
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(w, h,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Bitmap getScaleIcon(Bitmap icon, int w, int h) {
        if (icon == null) return null;
        Matrix matrix = new Matrix();
        float scaleFactor = Math.min(w * 1.0f / 5 / icon.getWidth(), h * 1.0f / 5 / icon.getHeight());
        matrix.postScale(scaleFactor, scaleFactor);
        Bitmap result = Bitmap.createBitmap(icon, 0, 0, icon.getWidth(), icon.getHeight(), matrix, true);
        return result;
    }

    /**
     * 设置是否打开闪光灯
     *
     * @param isEnable
     */
    public static void isLightEnable(boolean isEnable) {
        Camera camera = CameraManager.get().getCamera();
        if (camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            String value = isEnable ? Camera.Parameters.FLASH_MODE_TORCH : Camera.Parameters.FLASH_MODE_OFF;
            camera.setParameters(parameters);
        }
    }

    public interface AnalyzeListener {

        void onSuccess(Bitmap bitmap, String result);

        public void onFailed();
    }
}
