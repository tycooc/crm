package com.dabai.dao;

import com.dabai.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 以后所有的Dao的实现类,都可以继承BaseDaoImpl,增删改查分页方法都不用在编写了
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    //定义成员属性
    private Class clazz;
    public BaseDaoImpl()
    {
        //this表示的是子类,c表示的就是CustomerDaoImpl的class对象
        Class c = this.getClass();
        //CustomerDaoImpl extends BaseDaoImpl<Customer>
        //第2步,获取的是BaseDaoImpl<Customer>
        Type type = c.getGenericSuperclass();
        //目的:把type接口转换成子接口
        if (type instanceof ParameterizedType)
        {
            ParameterizedType ptype= (ParameterizedType) type;
            //获取Customer
            Type[] types = ptype.getActualTypeArguments();
            this.clazz= (Class) types[0];
        }
    }

    public void save(T t) {
        this.getHibernateTemplate().save(t);
    }

    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    public T findById(Long id) {
        return (T)this.getHibernateTemplate().get(clazz,id);
    }

    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
    }

    /**
     * 分页查询
     * @param pageCode
     * @param pageSize
     * @param criteria
     * @return
     */
    public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        //创建分页的对象
        PageBean<T> page=new PageBean<T>();
        //一个一个设置
        page.setPageCode(pageCode);
        page.setPageSize(pageSize);

        //先查询总记录数 select count(*)
        criteria.setProjection(Projections.rowCount());
        List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
        if (list!=null&&list.size()>0)
        {
            int totalCount = list.get(0).intValue();
            //总记录数
            page.setTotalCount(totalCount);
        }
        //强调:把select count(*) 先清空,变成 select *  from...
        criteria.setProjection(null);

        List<T> beanList = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);
        //每页显示的数据
        page.setBeanList(beanList);

        return page;
    }
}
