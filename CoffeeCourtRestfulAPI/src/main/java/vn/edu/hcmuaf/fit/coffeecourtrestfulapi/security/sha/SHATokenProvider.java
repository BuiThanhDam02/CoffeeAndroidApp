package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.security.sha;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;

@Component
public class SHATokenProvider {



    public String generateToken(String content) {
        // Tạo JWT token từ username và khóa bí mật

        try {
            MessageDigest sha256Digest=MessageDigest.getInstance("SHA-256");
            byte[] inputBytes = content.getBytes();
            byte[] output=sha256Digest.digest(inputBytes);
            BigInteger num=new BigInteger(1,output);
            return num.toString(16);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}