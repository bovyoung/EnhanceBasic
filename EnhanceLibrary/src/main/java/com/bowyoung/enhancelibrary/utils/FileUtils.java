package com.bowyoung.enhancelibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.bowyoung.enhancelibrary.config.Constants;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by S0S on 2016/11/15.
 * <p>
 * Desc:文件操作
 */

public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    public static boolean writeFile(InputStream in, File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024 * 8];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null) fos.close(); else return false;
                if (in != null) in.close(); else return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static String readAssetsFile(Context context, String fileName) {
        StringBuffer sb = new StringBuffer();
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = context.getAssets().open(fileName);
            br = new BufferedReader(new InputStreamReader(is, Constants.Encoding.UTF_8));
            String readLine;
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String readFileToString(String filePath) {
        return readFileToString(new File(filePath));
    }

    public static String readFileToString(File file) {
        StringBuffer sb = new StringBuffer();
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(is, Constants.Encoding.UTF_8));
            String readLine;
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static byte[] readStreamToBytes(InputStream is) {
        byte[] result = new byte[0];
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 8];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            result = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (baos != null) baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static byte[] readFileToBytes(String filePath) {
        return readFileToBytes(new File(filePath));
    }

    public static byte[] readFileToBytes(File file) {
        try {
            return readStreamToBytes(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean writeFile(File file, String content) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null) fos.close(); else return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean copyFile(File sourceFile, File targetFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(sourceFile);
            byte[] buffer = new byte[1024 * 8];
            int len = -1;
            fos = new FileOutputStream(targetFile);
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null) fos.close(); else return false;
                if (fis != null) fis.close(); else return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static <T> T readObject(File file, Class<T> clazz) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(file));
            return (T) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean writeObject(File file, Serializable object) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (oos != null) oos.close(); else return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean isFileExists(File file) {
        return file.exists();
    }

    public static boolean isFileExists(String filePath) {
        return isFileExists(new File(filePath));
    }

    public static boolean saveBitmap(Bitmap bitmap, String filePath) {
        File file = createFile(filePath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)) {
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (fos != null) fos.close(); else return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static File createFile(String filePath) {
        File file = new File(filePath);
        if (isFileExists(file)) {
            return file;
        }
        String fileDirPath = getFileDirPath(filePath);
        createDirs(fileDirPath);
        try {
            file.createNewFile();
            return new File(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File createDirs(String fileDirPath) {
        File file = new File(fileDirPath);
        if (isFileExists(file)) {
            return file;
        }
        file.mkdirs();
        return new File(fileDirPath);
    }

    public static String getFileDirPath(String filePath) {
        File file = new File(filePath);
        String parentDir = file.getParent();
        return new File(parentDir).getPath();
    }

    public static void deleteFile(String fileName) {
        if (isFileExists(fileName)) {
            File file = new File(fileName);
            file.delete();
        }
    }
}
