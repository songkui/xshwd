package com.xshwd.framework.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @Auther: SK ON  2018/11/2 15:57
 * @Description: 
 */
public class ApiOut<T> {
    @ApiModelProperty(value = " 状态名称 [Success 表示成功，否则失败]" )
    private String state;
    @ApiModelProperty(value = " 状态码[200 表示成功，否则失败]" )
    private Integer code;
    @ApiModelProperty(value = " 状态说明" )
    private String message;
    @ApiModelProperty(value = " 数据" )
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long doTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pagination pagination;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDoTime() {
        return doTime;
    }

    public void setDoime(Long doTime) {
        this.doTime = doTime;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ApiOut<String> Success(){
        return new ApiOut.Builder<String>().code(ResponseCode.Success).build();
    }
    public static ApiOut<String> Success(String value){
        return new Builder<String>().data(value).code(ResponseCode.Success).build();
    }

    public static ApiOut<String> Failure(String message,ResponseCode responseCode){
        if(StringUtils.isNotBlank(message)){
            return new Builder<String>().code(responseCode).message(message).build();
        }
        return new ApiOut.Builder<String>().code(responseCode).build();
    }




    public static ApiOut<String> Failure(){
        return new ApiOut.Builder<String>().code(ResponseCode.SystemException).build();
    }

    public static final class Builder<T> {
        private boolean debug = false;
        private ResponseCode responseCode = ResponseCode.Success;
        private Integer code;
        private String message;
        private Long doTime;
        private Long startTime;
        private Integer totalRow;
        private Integer pageSize;
        private Integer pageIndex;
        private T data;

        public Builder() {
            if (this.debug) {
                startTime = System.currentTimeMillis();
            }
        }

        public Builder(boolean debug) {
            this.debug = debug;
            if (this.debug) {
                startTime = System.currentTimeMillis();
            }
        }

        public Builder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder<T> code(ResponseCode responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }


        public Builder<T> doTime(long elapsedTime) {
            if (elapsedTime > 0) {
                this.doTime = elapsedTime;
            }
            return this;
        }

        public Builder<T> totalRow(int totalRow) {
            if (totalRow > 0) {
                this.totalRow = totalRow;
            }
            return this;
        }

        public Builder<T> pageSize(int pageSize) {
            if (pageSize > 0) {
                this.pageSize = pageSize;
            }
            return this;
        }

        public Builder<T> pageIndex(int pageIndex) {
            if (pageIndex > 0) {
                this.pageIndex = pageIndex;
            }
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiOut<T> build() {
            if (data instanceof Page) {
                Page page = (Page) data;
                this.totalRow = (int) page.getTotal();
                this.pageSize = page.getSize();
                this.pageIndex = page.getCurrent();
            }
            return new ApiOut<T>(this);
        }
    }
    public ApiOut(){}
    private ApiOut(Builder<T> builder) {
        this.code = Optional.ofNullable(builder.code).orElse(builder.responseCode.code());
        this.state = camelToUnderline(Optional.of(builder.responseCode).orElse(ResponseCode.Success).name()).toUpperCase();
        this.message = StringUtils.isBlank(builder.message) ? builder.responseCode.desc() : builder.message;

        if (null != builder.doTime) {
            this.doTime = builder.doTime;
        } else if (builder.debug) {
            this.doTime = System.currentTimeMillis() - builder.startTime;
        }

        if (isPositive(builder.totalRow) ||  isPositive(builder.pageSize) ||  isPositive(builder.pageIndex)) {
            this.pagination = new Pagination(builder.totalRow, builder.pageSize, builder.pageIndex);
        }
        this.data = builder.data;
    }



    private  boolean isPositive(Number value) {
        if (null != value) {
            if (value instanceof Integer) {
                return value.intValue() > 0;
            } else if (value instanceof Long) {
                return value.longValue() > 0;
            } else if (value instanceof Byte) {
                return value.byteValue() > 0;
            } else if (value instanceof Double) {
                return value.doubleValue() > 0;
            } else if (value instanceof Float) {
                return value.floatValue() > 0;
            } else if (value instanceof Short) {
                return value.shortValue() > 0;
            }
        }
        return false;
    }


    /**
     * 将驼峰形式字符串转换成下划线形式
     * downloadCount => download_count
     *
     * @param str 需要转换的字符串
     * @return 变成下划线形式字符串
     */
    private  String camelToUnderline(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }



}

