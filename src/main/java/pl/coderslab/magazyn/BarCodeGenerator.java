package pl.coderslab.magazyn;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;


import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
@Component
public class BarCodeGenerator {
    public byte[] generateBarcode(String code) throws Exception {
        if (code == null || code.length() != 9) {
            throw new IllegalArgumentException("Code must be a 10-digit string");
        }

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(code, BarcodeFormat.CODE_128, 300, 40, hints);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
            return baos.toByteArray();
        }
    }
}
