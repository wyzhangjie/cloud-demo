package leetcode.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/10$ 21:01$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/10$ 21:01$
 * @Version:        1.0
 */
public class Fanxing {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<String>();
        List<Integer> l2 = new ArrayList<Integer>();
        System.out.println(l1.getClass() == l2.getClass());
        List<Integer>[] iListArray;
        iListArray=new ArrayList[10];
       /* iListArray=new ArrayList<Integer>[10];//编译时错误*/
        /**
        * 事实上，泛型之所以叫泛型，就是因为它对其所有可能的类型参数有同样的行为。
         * 类的静态变量和静态方法在所有的实例间共享，
         * 这就是为什么在静态变量的声明和初始化时或者在静态方法或静态初始化代码中使用类型参数是不合法的原因，
         * 类型参数是属于具体实例的.
        * */
        /**
         * 在使用泛型类时，虽然传入了不同的泛型实参，但并没有真正意义上生成不同的类型，可以接收不同泛型实参的泛型类在内存中只有一个，是原来的最基本的类型（上例中为List），当然，在逻辑上我们可以理解成多个不同的泛型类型。
         *
         * 究其原因，Java泛型是通过java编译器的称为擦除(erasure)的前端处理来实现的，
         * Java中的泛型只作用于代码编译阶段，在编译过程中正确检验泛型结果后，会将泛型的相关信息擦除(erasure)，也就是说，
         * 成功编译之后的.class文件中是不包含任何泛型信息的，泛型信息不会进入运行时阶段。你可以把它认为是（基本上就是）一个从源码到源码的转换，
         * 它把泛型版本转换成非泛型版本。
        * */
        /**
        * 　　基本上，擦除(erasure)去掉了所有的泛型类型信息，所有在尖括号之间的类型信息都被扔掉了，
         * 比如说，一个 List<String>类型被转换为List，所有对类型变量的引用被替换成类型变量的上限(通常是Object)。
         * 对此总结成一句话：泛型类型在逻辑上可以看成是多个不同的类型，但实际上都是相同的基本类型。
        * */
        Collection cs = new ArrayList<String>();
        Collection<String> cstr = (Collection<String>) cs; //Unchecked cast: 'java.util.Collection' to 'java.util.Collection<java.lang.String>'
        /**
         * 泛型类被其所有实例(instances)共享的另一个暗示就是检查一个实例是不是某一个特定类型的泛型类是没有意义的
        * */
      /*  if(cs instanceof  Collection<String>){//Illegal generic type for instanceof

        }*/
    }
}
