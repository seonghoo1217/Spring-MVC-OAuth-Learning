package learn.oauth.sociallogin.oauth.exception;

public class TokenValidFailedException extends RuntimeException{
    public TokenValidFailedException(){
        super("Failed to Generated Token");
    }

    private TokenValidFailedException(String message){
        super(message);
    }
}
