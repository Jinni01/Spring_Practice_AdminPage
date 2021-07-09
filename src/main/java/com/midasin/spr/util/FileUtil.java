package com.midasin.spr.util;

import com.midasin.spr.user.UserVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class FileUtil {

    public String SaveFile(MultipartFile uploadfile, HttpServletRequest request){
        if(uploadfile.isEmpty()) return "";

        UUID uuid = UUID.randomUUID();
        String saveName = uuid + "_" + uploadfile.getOriginalFilename();
        String savePath = getSavePath(request);
        File dir = new File(savePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File saveFile = new File(savePath, saveName);

        try {
            uploadfile.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return saveName;
    }

    public void DownLoadFile(HttpServletRequest request, HttpServletResponse response, String targetFileName) throws IOException {
        String savePath = getSavePath(request);
        String filePath = savePath + "/" + targetFileName;

        BufferedOutputStream out = null;
        InputStream in = null;

        String encodeFileName = new String(targetFileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

        try {
            response.setContentType("image/*; charset=UTF-8");
            response.setHeader("Content-Disposition", "inline;filename="+encodeFileName);
            File file = new File(filePath);
            if(file.exists()) {
                in = new FileInputStream(file);
                out = new BufferedOutputStream(response.getOutputStream());
                int len;
                byte[] buf = new byte[1024];
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(out != null) { out.flush();}
            if(out != null) { out.close();}
            if(out != null) { in.close();}
        }
    }

    public void RemoveFile(HttpServletRequest request, String targetFileName){
        String savePath = getSavePath(request);
        String filePath = savePath + "/" + targetFileName;
        File file = new File(filePath);
        if(file.exists())
        {
            file.delete();
        }
    }

    private String getRootPath(HttpServletRequest request){
        return  request.getSession().getServletContext().getRealPath("/");
    }

    private String getUploadPath(){
        return "resources/upload";
    }

    private String getSavePath(HttpServletRequest request){
        return getRootPath(request) + getUploadPath();
    }
}
