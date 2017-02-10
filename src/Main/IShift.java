/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.Shift;
import Entities.Worker;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nisan
 */
public class IShift {
    
    
    public static Integer NumOfShifts = 21;
    public static Map<Integer, Worker> Workers;
    public static Map<Integer, Shift> Shifts;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Shifts = createShifts();
        Workers = createWorkers();
        //sortShift();
        printShiftArray();
        
        
        System.out.println();
        System.out.println();
    }
    
    
    
    /**
     * Creates a blank weekly shift page
     * @return 
     */
    public static Map<Integer, Shift> createShifts(){
        Map<Integer, Shift> toReturn = new HashMap<Integer, Shift>();
        for(int i=0; i<=NumOfShifts;i++)
            toReturn.put(i, new Shift(i));
        
        return toReturn;
    }
    
    public static void printShiftArray(){
        for(int i=0; i<Shifts.size();i++)
            System.out.format("%4d",i+1);
        System.out.println();
        for(int i=0; i<Shifts.size();i++)
            if(Shifts.get(i).getWorker()!=null)
            System.out.print(Shifts.get(i).getWorker()+" ");
            else System.out.format("%4s","x");
        
    }

    private static Map<Integer, Worker> createWorkers() {
        Map<Integer, Worker> toReturn = new HashMap<Integer, Worker>();
        
        Worker a = new Worker(1, "Shai", this.Shifts);
        Worker b = new Worker(2, "Nisan", this.Shifts);
        Worker c = new Worker(3, "Evgeni", this.Shifts);
        
        Double pref = 2.0;
        
        //Preferneces
        a.setShiftRate(Shifts.get(4), pref);
        a.setShiftRate(Shifts.get(2), pref);
        a.setShiftRate(Shifts.get(7), pref);
        b.setShiftRate(Shifts.get(7), pref);
        b.setShiftRate(Shifts.get(8), pref);
        b.setShiftRate(Shifts.get(3), pref);
        c.setShiftRate(Shifts.get(1), pref);
        c.setShiftRate(Shifts.get(2), pref);
        c.setShiftRate(Shifts.get(7), pref);
        
        
        //Add to map
        toReturn.put(a.getId(), a);
        toReturn.put(b.getId(), b);
        toReturn.put(c.getId(), c);
        
        
        return toReturn;
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
