package com.game.user.threadpool;

import com.game.map.threadpool.TaskQueue;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MapThreadPool
 * @Description 用户线程池
 * @Author DELL
 * @Date 2019/6/10 20:48
 * @Version 1.0
 */
public class UserThreadPool {
    public static final int DEFAULT_THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    public static final String FACTORY_NAME = "user_thread";
    //声明
    public static final SingleThread[] ACCOUNT_SERVICE = new SingleThread[DEFAULT_THREAD_POOL_SIZE];
    static {
        ThreadFactory threadFactory = new DefaultThreadFactory(FACTORY_NAME);
        //初始化
        for (int i=0;i<ACCOUNT_SERVICE.length;i++){
            ACCOUNT_SERVICE[i] = new SingleThread(i+1,null,threadFactory,true);
        }
    }
    //对外开放接口
    public static SingleThread getThreadPool(int index){
        return ACCOUNT_SERVICE[index];
    }

    //计算线程序号
    public static int getThreadIndex(Object number){
        int id = Math.abs(number.hashCode());
         return id% DEFAULT_THREAD_POOL_SIZE;
    }

    /**
     * 执行任务
     * @param threadIndex
     * @param initialDelay
     * @param period
     * @param timeUnit
     */
    public static Future executeTask(int threadIndex, long initialDelay, long period, TimeUnit timeUnit){
        ScheduledFuture<?> scheduledFuture = UserThreadPool.getThreadPool(threadIndex).scheduleAtFixedRate(() -> {
                    Iterator<Runnable> iterator = TaskQueue.getQueue().iterator();
                    while (iterator.hasNext()) {
                        Runnable runnable = iterator.next();
                        if (Objects.nonNull(runnable)) {
                            runnable.run();
                        }
                    }

                },
                initialDelay, period, timeUnit);
        return scheduledFuture;
    }
}

