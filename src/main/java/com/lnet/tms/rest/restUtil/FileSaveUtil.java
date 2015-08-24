package com.lnet.tms.rest.restUtil;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by Administrator on 2015/8/18.
 */
public class FileSaveUtil {
    public static void save(String name,String photoString){
        if(photoString!=null){
            // 对base64数据进行解码 生成 字节数组，不能直接用Base64.decode（）；进行解密
            String rootPath = "";
            FileOutputStream out = null;
            Path storePath = Paths.get(rootPath, "pic", name + ".jpg");
            File destFile = new File(storePath.toUri());
            if (!destFile.getParentFile().exists()) destFile.getParentFile().mkdirs();
            System.out.println("filePath:"+destFile.toString());
            if(photoString!=null){
                try {
                    byte[] photoimg = new BASE64Decoder().decodeBuffer(photoString);
                    for (int i = 0; i < photoimg.length; ++i) {
                        if (photoimg[i] < 0) {
                            // 调整异常数据
                            photoimg[i] += 256;
                        }
                    }
                    out = new FileOutputStream(destFile);
                    out.write(photoimg);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(out!=null){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
