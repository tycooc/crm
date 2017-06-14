package com.dabai.web.action;

import com.dabai.domain.Dict;
import com.dabai.service.DictService;
import com.dabai.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 字典的控制层
 * Created by Administrator on 2017/5/21 0021.
 */
public class DictAction extends ActionSupport implements ModelDriven<Dict> {

    private Dict dict=new Dict();
    public Dict getModel() {
        return dict;
    }

    private DictService dictService;
    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }

    /**
     * 通过字段type_code值,查询客户级别或者客户来源
     * @return
     */
    public String findByCode()
    {
        //调用业务层
        //把数据传入模型驱动里面
        List<Dict> list=dictService.findByCode(dict.getDict_type_code());

        //List<Dict> list = dictService.findByCode("002");
        //使用fastjson,把list转换成json字符串
        String jsonString = FastJsonUtil.toJSONString(list);
        //把json字符串写到浏览器
        HttpServletResponse response = ServletActionContext.getResponse();

        //输出
        FastJsonUtil.write_json(response,jsonString);

        return NONE;
    }
}
