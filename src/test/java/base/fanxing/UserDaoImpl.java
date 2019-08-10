package base.fanxing;

import com.hyssop.framework.vo.BaseRequest;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/8/1$ 12:48$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/8/1$ 12:48$
 * @Version:        1.0
 */
public class UserDaoImpl extends BaseDaoImpl<BaseRequest> implements BaseDao<BaseRequest> {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.deleteById("where a =1 and b=2");
    }
}
