package com.game.buff.controller;

import com.game.buff.bean.ConcreteBuff;
import com.game.map.threadpool.TaskQueue;
import com.game.role.bean.ConcreteRole;
import com.game.server.manager.TaskMap;
import com.game.user.threadpool.UserThreadPool;
import com.game.utils.MapUtils;
import io.netty.util.concurrent.Future;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.game.buff.controller.BuffType.*;

/**
 * @ClassName BuffController
 * @Description TODO
 * @Author DELL
 * @Date 2019/6/20 16:59
 * @Version 1.0
 */
@Component
public class BuffController {
    /**
     * 执行Buff
     */
    public void executeBuff(String roleName,String buffName){
        //获取角色
        ConcreteRole role = MapUtils.getMapRolename_Role().get(roleName);
        //获取本地buff
        Map<String, ConcreteBuff> buffMap = MapUtils.getBuffMap();
        ConcreteBuff buff = null;
        //选择Buff
        switch (buffName) {
            case RED :
                buff = buffMap.get("REDBUFF");
                break;
            case BLUE:
                buff = buffMap.get("BLUEBUFF");
                break;
            case DEFEND :
                buff = buffMap.get("DEFENDBUFF");
                break;
            case ATTACK :
                buff = buffMap.get("ATTACKBUFF");
                break;
                default:
                    buff = new ConcreteBuff();
                    break;
        }
        //创建任务
        BuffTask task = new BuffTask(buff,role);
        //把任务加到任务队列
        TaskQueue.getQueue().add(task);

        //把任务丢线程池
        int threadIndex = UserThreadPool.getThreadIndex(role.getChannel().id());
        Future future =  UserThreadPool.executeTask(threadIndex, 5L, 5L, TimeUnit.SECONDS);

        //存在map中
        TaskMap.getFutureMap().put(role.getChannel().id().asLongText(),future);

    }
}
