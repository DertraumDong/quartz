package com.traum.quartz.entity;

import org.omg.PortableInterceptor.SUCCESSFUL;

public class ResultBody {

    /**
     * 统一code
     * */
    private String code;

    /**
     * 统一返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private Object object;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ResultBody() {
    }

    public ResultBody(Object object) {
        this.object = object;
    }

    private static final String SUCCESS = "200";

    private static final String FAIL = "500";

    public static ResultBody success(){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(SUCCESS);
        resultBody.setMsg("操作成功");
        return resultBody;
    }

    public static ResultBody success(String msg){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(SUCCESS);
        resultBody.setMsg(msg);
        return resultBody;
    }

    public static ResultBody success(String msg,Object data){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(SUCCESS);
        resultBody.setMsg(msg);
        resultBody.setObject(data);
        return resultBody;
    }

    public static ResultBody fail(){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(FAIL);
        resultBody.setMsg("操作成功");
        return resultBody;
    }

    public static ResultBody fail(String msg){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(FAIL);
        resultBody.setMsg(msg);
        return resultBody;
    }

    public static ResultBody fail(String msg,Object data){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(FAIL);
        resultBody.setMsg(msg);
        resultBody.setObject(data);
        return resultBody;
    }

}
