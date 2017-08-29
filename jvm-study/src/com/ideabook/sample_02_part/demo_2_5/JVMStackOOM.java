package com.ideabook.sample_02_part.demo_2_5;

/**
 * @Author: lzh
 * @Description:
 * @Date: Created in 2017/8/29 16:04
 */
public class JVMStackOOM {
    private void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });

            thread.start();
        }
    }

    public static void main(String[] args) {
        JVMStackOOM oom = new JVMStackOOM();
        oom.stackLeakByThread();
    }
}
