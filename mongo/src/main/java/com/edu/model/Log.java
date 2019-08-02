package com.edu.model;

import java.util.Date;

/**
 *日志实体
 * @author Alex.Xiao
 * @since 2019-08-02
 */
public class Log {
    private String level;//日志级别
    private String stacTrace;//堆栈信息
    private String message;//日志消息
    private Date createDate;//日志创建时间

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStacTrace() {
        return stacTrace;
    }

    public void setStacTrace(String stacTrace) {
        this.stacTrace = stacTrace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
