package com.game.utils;

import com.game.backpack.bean.Goods;
import com.game.backpack.bean.Type;
import com.game.buff.bean.ConcreteBuff;
import com.game.map.bean.ConcreteMap;
import com.game.map.bean.MapMapping;
import com.game.npc.bean.ConcreteMonster;
import com.game.npc.bean.ConcreteNPC;
import com.game.npc.bean.MapNPCMapping;
import com.game.npc.bean.MonsterMapMapping;
import com.game.role.bean.ConcreteRole;
import com.game.skill.bean.ConcreteSkill;

import java.util.*;

/**
 * @ClassName CacheUtils
 * @Description 记录上线角色信息
 * @Author DELL
 * @Date 2019/6/310:58
 * @Version 1.0
 */
public class CacheUtils {
    private CacheUtils() {}
    /**
     * 用户名-角色缓存
     */
    private static volatile Map<String, ConcreteRole> mapUsername_Role = null;
    /**
     * 角色名-角色缓存
     */
    private static volatile Map<String,ConcreteRole> mapRolename_Role = null;
    /**
     * 地图映射缓存
     */
    private static volatile List<MapMapping> mapList = null;

    /**
     * id_地图缓存
     */
    private static volatile Map<Integer, ConcreteMap> id_ConcreteMap_Map = null;
    /**
     * name_地图缓存
     */
    private static volatile Map<String, ConcreteMap> name_ConcreteMap_Map = null;
    /**
     * NPC容器
     */
    private static volatile Map<Integer,ConcreteNPC> npcMap = null;
    /**
     *
     * map和npc的映射容器
     */
    private static volatile List<MapNPCMapping> mapNPCMappingList = null;
    /**
     * key:monster的id
     * value:ConcreteMonster
     * 怪兽容器
     */
    private static volatile Map<Integer, ConcreteMonster> monsterMap = null;
    /**
     *map和monster映射容器
     */
    private static volatile List<MonsterMapMapping> monsterMapMappingList = null;

    /**
     * key:name
     * value:goods
     */
    private static volatile Map<String, Goods> goodsMap = null;
    /**
     * key:技能id
     * value:ConcreteSkill
     */
    private static volatile Map<String, ConcreteSkill> skillMap = null;
    /**
     * key:技能name+技能level
     * value:ConcreteSkill
     */
    private static volatile Map<String, ConcreteSkill> skillMap2 = null;
    /**
     * key:id
     * values:type
     */
    private static volatile Map<Integer, Type> typeMap = null;
    /**
     * key:id
     * value:ConcreteBuff
     */
    private static volatile Map<String, ConcreteBuff> buffMap = null;



    private static volatile List<Goods> goodsList = null;


    public static List<Goods> getGoodsList(){
        if(goodsList==null){
            synchronized (CacheUtils.class){
                if(goodsList==null){
                    goodsList = new ArrayList<>();
                }
            }
        }
        return goodsList;
    }

    public static Map<String, ConcreteMap>  getName_ConcreteMap_Map(){
        if(name_ConcreteMap_Map==null){
            synchronized (CacheUtils.class){
                if(name_ConcreteMap_Map==null){
                    name_ConcreteMap_Map = new HashMap<>();
                }
            }
        }
        return name_ConcreteMap_Map;
    }
    public static Map<Integer, ConcreteMap>  getId_ConcreteMap_Map(){
        if(id_ConcreteMap_Map==null){
            synchronized (CacheUtils.class){
                if(id_ConcreteMap_Map==null){
                    id_ConcreteMap_Map = new HashMap<>();
                }
            }
        }
        return id_ConcreteMap_Map;
    }
    /**
     * buff的map
     * @return
     */
    public static Map<String, ConcreteBuff>  getBuffMap(){
        if(buffMap==null){
            synchronized (CacheUtils.class){
                if(buffMap==null){
                    buffMap = new HashMap<>();
                }
            }
        }
        return buffMap;
    }
    /**
     * 物品类型map
     * @return
     */
    public static Map<String, Goods> getGoodsMap(){
        if(goodsMap==null){
            synchronized (CacheUtils.class){
                if(goodsMap==null){
                    goodsMap = new HashMap<>();
                }
            }
        }
        return goodsMap;
    }

    /**
     * 物品类型map
     * @return
     */
    public static Map<Integer, Type> getTypeMap(){
        if(typeMap==null){
            synchronized (CacheUtils.class){
                if(typeMap==null){
                    typeMap = new HashMap<>();
                }
            }
        }
        return typeMap;
    }
    /**
     * 技能map容器
     * @return
     */
    public static Map<String,ConcreteSkill> getSkillMap_keyName(){
        if(skillMap2==null){
            synchronized (CacheUtils.class){
                if(skillMap2==null){
                    skillMap2 = new HashMap<>();
                }
            }
        }
        return skillMap2;
    }
    /**
     * 技能map容器
     * @return
     */
    public static Map<String,ConcreteSkill> getSkillMap_keyId(){
        if(skillMap==null){
           synchronized (CacheUtils.class){
               if(skillMap==null){
                   skillMap = new HashMap<>();
               }
           }
        }
        return skillMap;
    }

    /**
     * map和monster映射容器
     * @return
     */
    public static List<MonsterMapMapping> getMonsterMapMappingList(){
        if(monsterMapMappingList==null){
            synchronized (CacheUtils.class){
                if(monsterMapMappingList==null){
                    monsterMapMappingList = new ArrayList<>();
                }
            }
        }
        return monsterMapMappingList;
    }

    /**
     * 怪兽容器
     * @return
     */
    public static Map<Integer, ConcreteMonster> getMonsterMap(){
        if(monsterMap==null){
            synchronized (CacheUtils.class){
                if (monsterMap==null){
                    monsterMap = new HashMap<>();
                }
            }
        }
        return monsterMap;
    }


    /**
     * map<mapId,list<npcId>>
     */
    public static Map<Integer,List<Integer>> mapIdnpcIdMap(){
        Iterator<MapNPCMapping> iterator = getMapNPCMappingList().iterator();
        Map<Integer,List<Integer>> mapID_npcId_Map = new HashMap<>();
        List<Integer> list = null;
        while(iterator.hasNext()){
            MapNPCMapping mapNPCMapping = iterator.next();
            if (mapID_npcId_Map.containsKey(mapNPCMapping.getMapId())) {
                list.add(mapNPCMapping.getNpcId());
                mapID_npcId_Map.put(mapNPCMapping.getMapId(),list);
            }else{
                list = new ArrayList<>();
                list.add(mapNPCMapping.getNpcId());
                mapID_npcId_Map.put(mapNPCMapping.getMapId(),list);
            }
        }
        return mapID_npcId_Map;
    }
    /**
     * 地图map和npc的映射容器
     * @return
     */
    public static List<MapNPCMapping> getMapNPCMappingList(){
        if(mapNPCMappingList==null){
            synchronized (CacheUtils.class){
                if(mapNPCMappingList==null){
                    mapNPCMappingList = new ArrayList<>();
                }
            }
        }
        return mapNPCMappingList;
    }
    /**
     * 地图角色map
     * @return 用户名(非角色名)，role实体
     */
    public static Map<String, ConcreteRole> getMapUsername_Role() {
        if (mapUsername_Role == null) {
            synchronized (CacheUtils.class) {
                if (mapUsername_Role == null) {
                    mapUsername_Role = new HashMap<>();
                    return mapUsername_Role;
                }
            }
        }
        return mapUsername_Role;
    }

    /**
     * key:rolename角色名
     * value:Role实体类
     * @return
     */
    public static Map<String, ConcreteRole> getMapRolename_Role() {
        if (mapRolename_Role == null) {
            synchronized (CacheUtils.class) {
                if (mapRolename_Role == null) {
                    mapRolename_Role = new HashMap<>();
                    return mapRolename_Role;
                }
            }
        }
        return mapRolename_Role;
    }

    /**
     * 地图映射列表
     *
     * @return 地图Map_Mapping实体
     */
    public static List<MapMapping> getMapList() {
        if (mapList == null) {
            synchronized (CacheUtils.class) {
                if (mapList == null) {
                    mapList = new ArrayList<>();
                }
            }
        }
        return mapList;
    }

    /**
     * NPC容器
     * @return
     */
    public static Map<Integer, ConcreteNPC> getNpcMap(){
        if(npcMap==null){
            synchronized (CacheUtils.class){
                if(npcMap==null){
                    npcMap = new HashMap<>();
                }
            }
        }
        return npcMap;
    }
    /**
     * 切换地图是否可达
     *
     * @param src_id
     * @param dest_id
     * @return
     */
    public static boolean isReach(int src_id, int dest_id) {
        Iterator<MapMapping> iterator = getMapList().iterator();
        while (iterator.hasNext()) {
            MapMapping mapMapping = iterator.next();
            if (mapMapping.getSrcMap() == src_id && mapMapping.getDestMap() == dest_id) {
                return true;
            }
        }
        return false;
    }

    /**
     * 打印map
     * @param map
     */
    public static void printMap(Map map){
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry next = (Map.Entry) iterator.next();
            System.out.println(next.getKey()+":"+next.getValue());
        }
    }


}