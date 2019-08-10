package base.fanxing;

import java.lang.reflect.ParameterizedType;

/**
 * @Description: 父类获得子类的泛型类型
 * @Author: zhjie.zhang
 * @CreateDate: 2019/8/1$ 12:44$
 * @UpdateUser: zhjie.zhang
 * @UpdateDate: 2019/8/1$ 12:44$
 * @Version: 1.0
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
    private Class<T> beanClass;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        beanClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public void deleteById(String param) {
        String sql="delete from "+beanClass.getSimpleName()+" "+param;

        System.out.println(sql);
    }
}
