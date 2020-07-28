//package com.edge.authentication.authserver.service;
//
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.Claims;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import java.nio.charset.Charset;
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class JwtTokenProvider {
//
//    //@Value("")
//    private String secretKey = "secret-key";
//
//    //    @Value("${security.jwt.token.expire-length:60000}")
//    private long validityInMilliseconds = 6000000;
//
//    @Autowired
//    private MyUserDetails myUserDetails;
//
//    @PostConstruct
//    protected void init() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//    }
//
//    public String createToken(String username, List<String> roles) {
//
//        Claims claims = Jwts.claims().setSubject(username);
//        //claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));
//
//        Date now = new Date();
//        Date validity = new Date(System.currentTimeMillis() + validityInMilliseconds);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    public Authentication getAuthentication(String token) {
//        UserDetails myUserDetails = this.myUserDetails.loadUserByUsername(getUsername(token));
//        return new UsernamePasswordAuthenticationToken(myUserDetails, "", myUserDetails.getAuthorities());
//    }
//
//    public String getUsername(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    public String resolveToken(HttpServletRequest req) {
//        String bearerToken = req.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    public boolean validateToken(String token) throws Exception {
//        try {
//            Jwts.parser().setSigningKey("secret-key".getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            throw new Exception("Expired or invalid JWT token");
//        }
//    }
//}