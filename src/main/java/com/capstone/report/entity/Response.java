package com.capstone.report.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Response {
    private boolean bool;
    private Object data;
    private String msg;

    public Response(boolean bool, String msg){
        this.bool = bool;
        this.msg = msg;
    }
}