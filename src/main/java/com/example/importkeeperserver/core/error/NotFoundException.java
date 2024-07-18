package com.example.importkeeperserver.core.error;

import com.example.importkeeperserver.core.utils.ApiUtils;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){ super(message);}

    public ApiUtils.ApiResult<?> body(){return ApiUtils.error("Not Found", getMessage(), HttpStatus.NOT_FOUND);}

    public HttpStatus status(){return HttpStatus.NOT_FOUND;}
}
