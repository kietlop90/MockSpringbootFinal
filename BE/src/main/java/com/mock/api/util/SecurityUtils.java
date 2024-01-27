package com.mock.api.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class SecurityUtils {

    public static String generateToken() {
        byte[] tokenBytes = new byte[32];
        new SecureRandom().nextBytes(tokenBytes);
        Charset charset = StandardCharsets.UTF_8;
        byte[] encoded = Base64.getEncoder().encode(tokenBytes);
        return new String(encoded, charset);
    }
}
