package com.linqing.tank.observe;

import com.linqing.tank.PropertyManager;
import com.linqing.tank.Tank;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lin-PC
 */
public class TankFireObserverChain implements TankFireObserver<Tank> {

    private static TankFireObserverChain instance = new TankFireObserverChain();

    List<TankFireObserver> observers = new LinkedList<TankFireObserver>();

    private TankFireObserverChain(){
    }

    static {
        instance.init();
    }

    public static TankFireObserverChain getInstance(){
        return instance;
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
