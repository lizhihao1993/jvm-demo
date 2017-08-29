package com.ideabook.sample_02_part;

/**
 * @Author: lzh
 * @Description:
 * @Date: Created in 2017/8/29 15:18
 */
public class JVMStackSOF {

    private int stackLength = 1 ;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    /**
     * -Xms20m -Xmx20m -Xss128k
     * 两点：—Xss128k 减小栈内存容量。
     *      定义大量的本地变量，增大此方法帧中 本地变量表的长度。结果：抛出StackOverflowError异常输出时的堆栈深度相应减小。
     * @param args
     */
    public static void main(String[] args) {
        JVMStackSOF oom = new JVMStackSOF();
       try {
           oom.stackLeak();
       }catch (Throwable e){
           System.out.println("stack length : " + oom.stackLength);
           throw e;
       }

       /*
       stack length : 19194
    Exception in thread "main" java.lang.StackOverflowError
	at com.ideabook.sample_02_part.JVMStackSOF.stackLeak(JVMStackSOF.java:14)
	at com.ideabook.sample_02_part.JVMStackSOF.stackLeak(JVMStackSOF.java:14)

	结论 ：在单个线程下，无论是由于栈帧太大还是虚拟机栈容量太小，当内存无法分配的时候，均抛出StackOverflowError。
       */
    }
}
