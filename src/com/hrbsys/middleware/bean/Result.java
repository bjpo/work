package com.hrbsys.middleware.bean;


import java.util.List;

/**
 *
 * @author Li
 */
public class Result{

    private String status;
    private String message;
    private List data;

    public Result() {
    }

    public Result(String status, String message, List data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

}