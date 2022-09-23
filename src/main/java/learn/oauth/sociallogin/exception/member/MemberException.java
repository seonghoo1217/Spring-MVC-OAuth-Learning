package learn.oauth.sociallogin.exception.member;


import learn.oauth.sociallogin.exception.BaseException;
import learn.oauth.sociallogin.exception.BaseExceptionType;

public class MemberException extends BaseException {
    private BaseExceptionType exceptionType;


    public MemberException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
