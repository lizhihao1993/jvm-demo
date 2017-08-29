package com.ideabook.sample_02_part;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lzh
 * @Description:
 * @Date: Created in 2017/8/29 14:33
 */
public class HeapOOM {
    static class OOMObject{

    }

    /*
    设置JVM:
    -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
    */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while(true){
            list.add(new OOMObject());
        }
    }
}
