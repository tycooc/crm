package com.dabai.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Administrator on 2017/5/23 0023.
 * Action的父类
 */
public class BaseAction extends ActionSupport {

    //属性驱动的方式
    //当前页,默认值是1
    private Integer pageCode = 1;

    public void setPageCode(Integer pageCode) {
        if (pageCode == null) {
            pageCode = 1;
        }
        this.pageCode = pageCode;
    }

    public Integer getPageCode() {
        return pageCode;
    }

    //每页显示的数据的条数
    private Integer pageSize = 2;

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 调用值栈对象的set方法
     * 从值栈中设置值
     */
    public void setVs(String key,Object obj)
    {
        ActionContext.getContext().getValueStack().set(key,obj);
    }

    /**
     * 调用值栈对象的push方法
     */
    public void pushVs(Object obj)
    {
        ActionContext.getContext().getValueStack().push(obj);
    }
}
