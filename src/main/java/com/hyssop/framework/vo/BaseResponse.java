package com.hyssop.framework.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/24$ 16:38$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/24$ 16:38$
 * @Version:        1.0
 */
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -2328227715784163138L;
    private int ret;
    private int errCode;
    private String errMsg;
    T data;

    public BaseResponse() {
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.ret == 1;
    }

    public static <V> BaseResponse<V> newSuccessResp(@NotNull V data) {
        if (data == null) {
            throw new NullPointerException();
        } else {
            BaseResponse<V> BaseResponse = new BaseResponse();
            BaseResponse.ret = 1;
            BaseResponse.data = data;
            return BaseResponse;
        }
    }

    public static <V> BaseResponse<V> newFailResp() {
        return newFailResp(-1, "ERROR");
    }

    public static <V> BaseResponse<V> newFailResp(int errCode, String errMsg) {
        BaseResponse<V> BaseResponse = new BaseResponse();
        BaseResponse.ret = 0;
        BaseResponse.errCode = errCode;
        BaseResponse.errMsg = errMsg;
        return BaseResponse;
    }

    public static <V> BaseResponse<V> newFailResp(BaseResultCode baseErrorCode) {
        BaseResponse<V> BaseResponse = new BaseResponse();
        BaseResponse.ret = 0;
        BaseResponse.errCode = Integer.parseInt(baseErrorCode.getCode());
        BaseResponse.errMsg = baseErrorCode.getDesc();
        return BaseResponse;
    }

    public int getRet() {
        return this.ret;
    }

    public int getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public T getData() {
        return this.data;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "BaseResponse(ret=" + this.getRet() + ", errCode=" + this.getErrCode() + ", errMsg=" + this.getErrMsg() + ", data=" + this.getData() + ")";
    }
}
