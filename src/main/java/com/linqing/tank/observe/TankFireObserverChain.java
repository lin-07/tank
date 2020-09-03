package com.linqing.tank.observe;

import com.linqing.tank.PropertyManager;
import com.linqing.tank.Tank;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lin-PC
 */
public class TankFireObserverChain implements TankFireObserver<Tank> {

    List<TankFireObserver> observers = new LinkedList<TankFireObserver>();

    public TankFireObserverChain(){
        init();
    }

    private void init(){
        String tankFireObservesStr = (String) PropertyManager.getInstance().getKey("tankFireObserves");
        String[] tankFireObserveArr = tankFireObservesStr.split(",");
        for(String tankFireObserve : tankFireObserveArr){
            try{
                observers.add((TankFireObserver) Class.forName(tankFireObserve).newInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public boolean handler(TankFireEvent<Tank> tankFireEvent) {
        for (TankFireObserver observer : observers){
            boolean b = observer.handler(tankFireEvent);
            if(!b){
                break;
            }
        }
        return true;
    }
}
