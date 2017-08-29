package com.ideabook.sample_03_part.demo_3_2;

/**
 * @Author: lzh
 * @Description: 在 “可达性分析算法” 中不可达的对象，也并非“非死不可”！！
 *  1、对象可以在被GC时自我拯救
 *  2、这种拯救的机会只有一次，因为对象的finalize()方法最多只会被系统自动调用一次
 *
 *  总之，不用这种方法，该死的 就去死吧！！
 * @Date: Created in 2017/8/29 16:50
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes, I am still alive :");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(" finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象第一次
        SAVE_HOOK = null ;
        System.gc();

        /*finalize方法的优先级太低，所以等一会儿 0.5s*/
            Thread.sleep(500);

        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no , I am died :(");
        }

        //故技重施下
        SAVE_HOOK = null ;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no , I am died :(");
        }

    }

}
