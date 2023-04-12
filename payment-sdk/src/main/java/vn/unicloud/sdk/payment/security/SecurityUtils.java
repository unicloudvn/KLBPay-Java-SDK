package vn.unicloud.sdk.payment.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public class SecurityUtils {

    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";
    private static final String HMAC_ALGORITHM = "HmacSHA256";

    private SecurityUtils() {
        throw new IllegalStateException();
    }

    public static String getSecureRandomKey(int keySize) {
        byte[] secureRandomKeyBytes = new byte[keySize];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secureRandomKeyBytes);
        Key key = new SecretKeySpec(secureRandomKeyBytes, "AES");
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return Hex.encodeHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encryptAES(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            byte[] iv = Arrays.copyOf(Hex.decodeHex(key), 16);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKey priKey = new SecretKeySpec(Hex.decodeHex(key), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, priKey, ivParameterSpec);
            byte[] plainText = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptAES(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            byte[] iv = Arrays.copyOf(Hex.decodeHex(key), 16);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKey priKey = new SecretKeySpec(Hex.decodeHex(key), "AES");
            cipher.init(Cipher.DECRYPT_MODE, priKey, ivParameterSpec);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(data));
            return new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getEncryptKey(int keySize) {
        byte[] secureRandomKeyBytes = new byte[keySize / 8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secureRandomKeyBytes);
        Key key = new SecretKeySpec(secureRandomKeyBytes, AES_CIPHER_ALGORITHM);
        return Hex.encodeHexString(key.getEncoded()).toUpperCase();
    }

    public static String getAESKey(int keySize) {
        try {
            SecureRandom securerandom
                = new SecureRandom();
            KeyGenerator keygenerator
                = KeyGenerator.getInstance("AES");

            keygenerator.init(keySize, securerandom);
            SecretKey key = keygenerator.generateKey();

            return Hex.encodeHexString(key.getEncoded()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            log.error("get key error: {}", e.getMessage());
        }
        return "";
    }

    public static String hmacEncode(String data, String key) {
        try {
            Mac sha256HMAC = Mac.getInstance(HMAC_ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM);
            sha256HMAC.init(secretKey);
            return Hex.encodeHexString(sha256HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.error("encode error: {}", e.getMessage());
        }
        return null;
    }

    public static String buildRawSignature(String clientId, String timestamp, String data) {
        return String.format("%s|%s|%s", clientId, timestamp, data);
    }

}
