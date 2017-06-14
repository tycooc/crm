package com.dabai.web.action;

import com.dabai.domain.Customer;
import com.dabai.domain.Dict;
import com.dabai.domain.PageBean;
import com.dabai.service.CustomerService;
import com.dabai.utils.FastJsonUtil;
import com.dabai.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer customer = new Customer();

    public Customer getModel() {
        return customer;
    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    //属性驱动的方式
    //当前页,默认值是1
    private Integer pageCode = 1;

    public void setPageCode(Integer pageCode) {
        if (pageCode == null) {
            pageCode = 1;
        }
        this.pageCode = pageCode;
    }

    //每页显示的数据的条数
    private Integer pageSize = 2;

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 分页的查询方法
     *
     * @return
     */
    public String findByPage() {
        //调用service业务层
        //获取离线查询对象
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        //拼接查询条件
        //拼接客户名称
        String cust_name = customer.getCust_name();
        if (cust_name != null && !cust_name.trim().isEmpty()) {
            //说明,有输入客户名称
            criteria.add(Restrictions.like("cust_name", "%" + cust_name + "%"));
        }
        //拼接客户级别
        Dict level = customer.getLevel();
        if (level != null && !level.getDict_id().trim().isEmpty()) {
            //说明,有选择了客户的级别
            criteria.add(Restrictions.eq("level.dict_id",level.getDict_id()));
        }
        //拼接客户来源
        Dict source = customer.getSource();
        if (source!=null && !source.getDict_id().trim().isEmpty())
        {
            //说明,有选择了客户的来源
            criteria.add(Restrictions.eq("source.dict_id",source.getDict_id()));
        }
        //查询
        PageBean<Customer> page = customerService.findByCode(pageCode, pageSize, criteria);
        //压栈
        //获取值栈对象
        ValueStack vs = ActionContext.getContext().getValueStack();
        //栈顶是map
        vs.set("page", page);
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
     * 文件的上传，需要在CustomerAction类中定义成员的属性，命名是有规则的！！
     * private File upload;		// 表示要上传的文件
     * private String uploadFileName;	表示是上传文件的名称（没有中文乱码）
     * private String uploadContentType;	表示上传文件的MIME类型
     * 提供set方法，拦截器就注入值了
     */

    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * 保存客户的方法
     * @return
     */
    public String save() throws IOException {
        //做文件的上传,说明用户选择了上传的文件
        if (uploadFileName!=null)
        {
            //把文件的名称处理一下
            String uuidName = UploadUtils.getUUIDName(uploadFileName);
            //把文件上传到D:\\JAVA\\apache-tomcat-7.0.76\\webapps\\upload
            String path="D:\\JAVA\\apache-tomcat-7.0.76\\webapps\\upload\\";
            //创建file对象
            File file=new File(path+uuidName);
            //简单方式,把上传的文件传入到file中
            FileUtils.copyFile(upload,file);
            //把上传的文件路径,保存到客户表中
            customer.setFilepath(path+uuidName);
        }
        customerService.save(customer);
        return "save";
    }

    /**
     * 删除客户
     * @return
     */
    public String delete()
    {
        //删除客户
        //先获取客户的信息获取到
        customer=customerService.findById(customer.getCust_id());
        //获取上传文件的路径
        String filepath = customer.getFilepath();
        //删除客户
        customerService.delete(customer);
        //再删除文件
        if (filepath!=null && !filepath.trim().isEmpty())
        {
            File file=new File(filepath);
            if (file.exists())
            {
                file.delete();
            }
        }
        return "delete";
    }

    /**
     * 跳转到初始化修改的页面
     * @return
     */
    public String initUpdate()
    {
        // 默认customer压栈的了，Action默认压栈，model是Action类的书写  getModel(返回customer对象)
        customer=customerService.findById(customer.getCust_id());
        return "initUpdate";
    }

    /**
     * 修改客户的功能
     * @return
     */
    public String update() throws IOException {
        //判断
        //说明,客户上传了新的图片
        if (uploadFileName!=null)
        {
            //先删除旧的图片
            String oldfilepath = customer.getFilepath();
            if (oldfilepath!=null && !oldfilepath.trim().isEmpty())
            {
                //说明,旧的路径是存在,删除图片
                File f=new File(oldfilepath);
                f.delete();
            }
            //再上传新的图片
            //先处理文件的名称的问题
            String uuidName = UploadUtils.getUUIDName(uploadFileName);
            //把文件上传到D:\\JAVA\\apache-tomcat-7.0.76\\webapps\\upload
            String path="D:\\JAVA\\apache-tomcat-7.0.76\\webapps\\upload\\";
            File file=new File(path+uuidName);
            //简单方式,把上传的文件传入到file中
            FileUtils.copyFile(upload,file);
            //把客户新图片的路径更新到数据库中
            customer.setFilepath(path+uuidName);
        }
        //更新客户的信息就可以了
        customerService.update(customer);

        return "update";
    }

    /**
     * 查询所有的客户
     * @return
     */
    public String findAll()
    {
        List<Customer> list=customerService.findAll();
        //转换成json
        String jsonString = FastJsonUtil.toJSONString(list);
        HttpServletResponse response = ServletActionContext.getResponse();
        FastJsonUtil.write_json(response,jsonString);
        return NONE;
    }

    /**
     * 客户来源统计
     * @return
     */
    public String findBySource()
    {
        List<Object[]> list= (List<Object[]>) customerService.findBySource();
        //压栈
        ValueStack vs = ActionContext.getContext().getValueStack();
        vs.set("list",list);
        return "findBySource";
    }
}
