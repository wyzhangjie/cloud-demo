package com.hyssop.framework.util;

import java.io.Serializable;

/**
 * Created by duomn.hu on 2015/8/21.
 * 元组结构
 */
public class Tuple<V1, V2> implements Serializable {
    private static final long serialVersionUID = 1387359465966798481L;

    private V1 v1;

    private V2 v2;

    private Tuple() {
    }

    public Tuple(V1 v1, V2 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public V1 getV1() {
        return v1;
    }

    public V2 getV2() {
        return v2;
    }

    public void setV1(V1 v1) {
        this.v1 = v1;
    }

    public void setV2(V2 v2) {
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                '}';
    }

    /*
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{
        // non-static and non-transient
        s.defaultWriteObject();

        // write v1
        if (v1 == null) {
            s.writeInt(0);
        } else {
            s.writeInt(1);
            s.writeObject(v1);
        }

        if (v2 == null) {
            s.writeInt(0);
        } else {
            s.writeInt(1);
            s.writeObject(v2);
        }
    }

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        // non-static and non-transient
        s.defaultReadObject();

        int v1Exists = s.readInt();
        if (v1Exists == 1) {
            v1 = (V1) s.readObject();
        }
        int v2Exists = s.readInt();
        if (v2Exists == 1) {
            v2 = (V2) s.readObject();
        }
    }
    */


}
