package com.epam.testing.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class ImageConverterUtil {
    /**
     *
     * @param blob user avatar
     * @return string that represents encoded image or null if user has no avatar.
     */
    public static String getBase64String(Blob blob) {
        String base64Image = null;
        if(blob == null) {
            return base64Image;
        }

        try(InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);

        } catch (RuntimeException | IOException | SQLException e) {
            e.printStackTrace();
        }

        return base64Image;
    }
}
