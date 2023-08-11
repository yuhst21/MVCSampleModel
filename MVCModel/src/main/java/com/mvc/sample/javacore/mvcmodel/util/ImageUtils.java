package com.mvc.sample.javacore.mvcmodel.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Map;
import java.util.ResourceBundle;

public class ImageUtils {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("application");

    private static Map authConfig = ObjectUtils.asMap(
            "cloud_name", bundle.getString("cloudinary.cloud_name"),
            "api_key", bundle.getString("cloudinary.api_key"),
            "api_secret", bundle.getString("cloudinary.api_secret"),
            "secure", true);

    public static String upload(Part part) {
        try {
            InputStream inputStream = part.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            String fileData = "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
            inputStream.close();
            outputStream.close();
            Cloudinary cloudinary = new Cloudinary(authConfig);
            Map uploadResult = cloudinary.uploader().upload(fileData, ObjectUtils.emptyMap());
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}