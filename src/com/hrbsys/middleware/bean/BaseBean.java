package com.hrbsys.middleware.bean;


import java.util.List;

/**
 *
 * @author Li
 */
public class BaseBean{

    private String status;
    private String message;
    private List data;

    public BaseBean() {
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