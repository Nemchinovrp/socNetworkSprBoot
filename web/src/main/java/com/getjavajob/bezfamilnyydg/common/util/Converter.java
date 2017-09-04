package com.getjavajob.bezfamilnyydg.common.util;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class Converter {
    public static String convertToStringBase64(Blob blob) throws SQLException {
        if (blob == null) {
            return null;
        }
        return new String(Base64.getEncoder().encode(blob.getBytes(1, (int) blob.length())));

    }

    public static String convertToStringBase64(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        return new String(Base64.getEncoder().encode(byteArray));
    }
}
