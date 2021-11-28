package com.pgr301.exam;

public class BackEndException extends RuntimeException {

    public String throwsException() throws Exception {
        throw new Exception("Backend Exception");
    }
}
