package com.project.yagmurquestapp.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${yagmurquestapp.app.secret}")
    private String APP_SECRET;//bu application a özel key oluştururken kullanacağımız secret özel bir key buna göre token oluşturcaz

    @Value("${yagmurquestapp.expires.in}")
    private long EXPIRES_IN; //kaç saniyede bir geçerliliğini yitiriyor

    public String generateJwtToken(Authentication auth){
        JwtUserDetails userDetails=(JwtUserDetails) auth.getPrincipal(); //getPrincipal authenticate edeceğimiz user demeek
        Date expireDate=new Date(new Date().getTime()+EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,APP_SECRET).compact();
    }
    Long getUserIdFromJwt(String token){ //jwt den userıd ye dönüştürme
        Claims claims=Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody(); //bu key e göre bu token ı çöz
        return Long.parseLong(claims.getSubject()); //şifremizi geri çözdük içinden userid yi aldık
    }
    boolean validateToken(String token){  //frontend tarafından istek geldiğinde token ile geliyor.Token ı doğrulamamız(validate) gerekiyor.Süresi doldu mu ya da bu kişiye ait bir token mı
        try{
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token); //parse edebiliyorsak APP_SECRET i kullanarak bu bizim uygulamamızın oluşturduğu bir keydir. Bu şekilde doğrulama yapmış oluruz
            return !isTokenExpired(token); //Expired olmadıysa true dönsün
        }
        catch (SignatureException e ){
            return false;
        }catch (MalformedJwtException e ) {
            return false;
        }
        catch (ExpiredJwtException e ) {
            return false;
        }catch (UnsupportedJwtException e ){
            return  false;
        }
        catch (IllegalArgumentException e ){
            return  false;
        }

    }
    private boolean isTokenExpired(String token){
        Date expiration=Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration(); //gelen token bu zamanda expire olcakmış(Date expiration)
        return expiration.before(new Date()); //bugün expire olması gerekiyordu //Date expiration burdaki tarih örn:15 bugün ayın 22 si, 15 i 22 sinden öncemi evet o zaman true döner 33.satır
    }
}