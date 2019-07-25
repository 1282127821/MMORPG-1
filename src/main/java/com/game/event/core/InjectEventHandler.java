package com.game.event.core;

import com.game.annotation.ExcelAnnotation;
import com.game.event.bean.MonsterDeadEvent;
import com.game.event.handler.IHandler;
import com.game.event.handler.KillMonsterHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName InjectEventHandler
 * @Description TODO
 * @Author DELL
 * @Date 2019/7/25 22:03
 * @Version 1.0
 */
@ExcelAnnotation
public class InjectEventHandler {

    @ExcelAnnotation
    public void injectData(){
        Map<Class<? extends IEvent>, List<IHandler>> eventMap = EventBusManager.getEventMap();
        List<IHandler> handlerList = new ArrayList<>();
        handlerList.add(new KillMonsterHandler());
        eventMap.put(MonsterDeadEvent.class,handlerList);
    }
}
