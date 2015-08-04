package com.lnet.tms.common;

public class JsonResult {

    private boolean success = false;
    private String message;
    private Object content;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    /* constructor */
    public JsonResult() {
    }

    public JsonResult(Boolean success, String message, Object content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }

   /* static */

    public static JsonResult create(){
        return new JsonResult();
    }

    public static JsonResult success(String message, Object content) {
        return new JsonResult(true, message, content);
    }

    public static JsonResult success(String message) {
        return new JsonResult(true, message, null);
    }

    public static JsonResult success(Object content) {
        return new JsonResult(true, null, content);
    }

    public static JsonResult success() {
        return new JsonResult(true, null, null);
    }

    public static JsonResult error(String message, Object content) {
        return new JsonResult(false, message, content);
    }

    public static JsonResult error(String message) {
        return new JsonResult(false, message, null);
    }

    public static JsonResult error() {
        return new JsonResult(false, null, null);
    }
}
