package gsc.backend.apiPayLoad.exception.handler;


import gsc.backend.apiPayLoad.code.BaseErrorCode;
import gsc.backend.apiPayLoad.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
