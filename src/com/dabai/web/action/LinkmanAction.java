package com.dabai.web.action;

import com.dabai.domain.Customer;
import com.dabai.domain.Linkman;
import com.dabai.domain.PageBean;
import com.dabai.service.LinkmanService;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Administrator on 2017/5/23 0023.
 */
public class LinkmanAction extends BaseAction implements ModelDriven<Linkman> {

    private Linkman linkman=new Linkman();
    public Linkman getModel() {
        return linkman;
    }

    private LinkmanService linkmanService;
    public void setLinkmanService(LinkmanService linkmanService) {
        this.linkmanService = linkmanService;
    }

    /**
     * 分页查询
     * @return
     */
    public String findByPage()
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
        //获取到联系人的名称
        String lkm_name = linkman.getLkm_name();
        if (lkm_name!=null && !lkm_name.trim().isEmpty())
        {
            criteria.add(Restrictions.like("lkm_name","%"+lkm_name+"%"));
        }
        //获取客户
        Customer c = linkman.getCustomer();
        if (c!=null&&c.getCust_id()!=null)
        {
            //拼接查询的条件
            criteria.add(Restrictions.eq("customer.cust_id",c.getCust_id()));
        }
        //调用业务层
        PageBean<Linkman> page=linkmanService.findByPage(this.getPageCode(),this.getPageSize(),criteria);
        //压栈
        this.setVs("page",page);
        return "page";
    }

    /**
     *初始化到添加的页面
     * @return
     */
    public String initAddUI()
    {

        return "initAddUI";
    }

    /**
     * 保存联系人的方法
     * @return
     */
    public String save() {
        linkmanService.save(linkman);
        return "save";
    }
}
