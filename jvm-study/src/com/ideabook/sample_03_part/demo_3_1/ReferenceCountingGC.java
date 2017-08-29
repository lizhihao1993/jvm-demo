package com.ideabook.sample_03_part.demo_3_1;

/**
 * @Author: lzh
 * @Description:
 * @Date: Created in 2017/8/29 16:22
 */
/*
测试jvm的垃圾回收 是不是引用计数法？
*/
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    /**
     * 打印日志
     * -verbose:gc
    *
     *  日志详情
     * -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();

        /*
        简单输出：
[GC (System.gc())  6758K->752K(125952K), 0.0093376 secs]
[Full GC (System.gc())  752K->584K(125952K), 0.0091070 scs]

由数值变化 可以看出虚拟机回收了,所以 并不是使用 “引用计数法”进行垃圾回收
        */

    }
}
