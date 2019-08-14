package com.game.event.beanevent;

import com.game.event.annotation.EventAnnotation;
import com.game.event.core.IEvent;
import com.game.role.bean.ConcreteRole;
import org.springframework.stereotype.Component;

/**
 * @ClassName AttackedEvent
 * @Description TODO
 * @Author DELL
 * @Date 2019/8/14 18:41
 * @Version 1.0
 */
@Component
@EventAnnotation
public class AttackedEvent extends IEvent {
    private ConcreteRole role;

    public ConcreteRole getRole() {
        return role;
    }

    public void setRole(ConcreteRole role) {
        this.role = role;
    }
}
