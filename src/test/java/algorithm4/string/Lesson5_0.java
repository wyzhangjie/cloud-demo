package algorithm4.string;

import java.util.ArrayList;
import java.util.List;

public class Lesson5_0 {
    //键索引计数法
    public static class Student {
        int group;
        String name;

        public Student(int group, String name) {
            this.group = group;
            this.name = name;
        }
    }

    public static void main(String[] args) {

        Student a = new Student(0, "张杰1");
        Student b = new Student(1, "张杰2");
        Student c = new Student(0, "张杰1_1");
        Student d = new Student(1, "张杰2_2");
        Student[] students = {a, b, c, d};
        int N = students.length;
        int R = 2;
        Student[] aux = new Student[N];
        int[] count = new int[R + 1];
        for (int i = 0; i < N; i++) {
            //计算出现的频率
            count[students[i].group + 1]++;
        }
        //将频率转为索引
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }
        //将元素分类
        for (int i = 0; i < N; i++) {
            aux[count[students[i].group] ++] = students[i];
        }
        //回写
        for (int i = 0; i < N; i++) {
            students[i] = aux[i];
            System.out.println(students[i].group+"|"+students[i].name);
        }

    }
}
