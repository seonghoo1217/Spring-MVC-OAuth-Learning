package learn.oauth.sociallogin.util;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtil {
    private final static String HEADER_AUTHORIZATION="Authorization";
    private final static String TOKEN_PREFIX="Bearer ";

    public static String getAccessToken(HttpServletRequest request){
        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header==null){
            return null;
        }

        if (header.startsWith(TOKEN_PREFIX)){
            return header.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}
