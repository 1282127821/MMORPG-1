package com.game.gang.bean;

public enum JobPermission {
    /**
     * 工会职业权限
     */
    HANDLE_APPLICATION("处理申请"), KICK("踢人"), EXIT("退出"), DISMISS("解散");
    String name;
    JobPermission(String name){
        this.name = name;
    }
}
