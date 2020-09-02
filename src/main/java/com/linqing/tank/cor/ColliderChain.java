package com.linqing.tank.cor;

import com.linqing.tank.GameObject;
import com.linqing.tank.PropertyManager;

import java.util.LinkedList;
import java.util.List;

/**
 * 碰撞器链
 * @author lin-PC
 */
public class ColliderChain implements Collider<GameObject> {

    private List<Collider<GameObject>> colliders = new LinkedList<Collider<GameObject>>();

    public ColliderChain(){
        init();
    }

    private void init(){
        String collidersStr = (String)PropertyManager.getInstance().getKey("colliders");
        String[] collidersArr = collidersStr.split(",");
        for(String collider : collidersArr){
            try {
                addCollider((Collider) Class.forName(collider).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Collider<GameObject> addCollider(Collider collider){
        colliders.add(collider);
        return this;
    }



    public boolean collideWith(GameObject o1, GameObject o2) {
        for(Collider collider : colliders){
            boolean b = collider.collideWith(o1, o2);
            if(!b){
                return false;
            }
        }
        return true;
    }
}
