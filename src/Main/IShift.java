/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.Shift;
import Entities.Worker;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author nisan
 */
public class IShift {
    
    
    public static Integer NumOfShifts = 21;
    public static Map<Worker, Integer> Workers;
    public static Shift shiftHead;
    public static Map<Integer, Shift> Shifts;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        shiftHead = createShifts();
        Workers = createWorkers();
        printShiftWorkPrefArray();
        sortShift();
        //printShiftArray();
        
        
        System.out.println();
        System.out.println();
    }
    
    
    
    /**
     * Creates a blank weekly shift page
     * @return 
     */
    public static Shift createShifts(){
        Shift head = new Shift(1);
        Shift tmp = head;
        for(int i=2; i<=NumOfShifts;i++){
            Shift newShift = new Shift(i);
            tmp.next = newShift;
            tmp.next.prev = tmp;
            tmp = tmp.next;
        }
          
        System.err.println("Test shift list");
        tmp = head;
        while(tmp!=null){
            System.out.print(tmp.getID()+" ");
            tmp=tmp.next;
        }
        return head;
    }
    
    /**
     * Prints the worker shift plans
     */
    public static void printShiftWorkPrefArray(){
        System.out.println("Printing shift work plan per worker");
        System.out.format("%10s"," ");
        Shift tmp = shiftHead;
        while(tmp!=null){
            System.out.format("%8d", tmp.getID());
            tmp=tmp.next;
        }
       
        System.out.println();
        for(Worker w : Workers.keySet())
            w.printShiftPlan();
        
        System.out.println("\n\n");
    }
    
    /**
     * Prints the shift array with the employees assigned
     */
    public static void printShiftArray(){
        for(int i=1; i<=Shifts.size();i++)
            System.out.format("%4d",i);
        System.out.println();
        for(int i=1; i<=Shifts.size();i++)
            if(Shifts.get(i).getWorker()!=null)
            System.out.print(Shifts.get(i).getWorker()+" ");
            else System.out.format("%4s","x");
        
    }

    /**
     * Temporarily creates 3 workers and with preferred schedule
     * @return 
     */
    private static Map<Worker, Integer> createWorkers() {
        Map<Worker, Integer> toReturn = new TreeMap<Worker, Integer>(new Comparator<Worker>(){
            @Override
            public int compare(Worker o1, Worker o2) {
                return o1.getId()-o2.getId();
            }
            
        });
        
        Worker a = new Worker(1, "Evgeni", shiftHead);
        Worker b = new Worker(2, "Shai", shiftHead);
        Worker c = new Worker(3, "Nisan", shiftHead);
        
        Double pref = 2.0;
        Double cant = 0.0;
        //Preferneces
        a.setShiftRate(1, pref);
        a.setShiftRate(2, pref);
        a.setShiftRate(7, pref);
        b.setShiftRate(7, pref);
        b.setShiftRate(8, pref);
        b.setShiftRate(3, pref);
        c.setShiftRate(1, pref);
        c.setShiftRate(2, pref);
        c.setShiftRate(7, pref);
        
        //Cant do shift
        a.setShiftRate(3, cant);
        a.setShiftRate(5, cant);
        b.setShiftRate(2, cant);
        b.setShiftRate(3, cant);
        c.setShiftRate(11, cant);
        ;
        
        //Testing
//        Shifts.get(6).setWorker(a);;
//        Shifts.get(9).setWorker(b);;
//        Shifts.get(2).setWorker(c);;
        
        //Add to map
        toReturn.put(a, 0);
        toReturn.put(b, 0);
        toReturn.put(c, 0);
        
        
        return toReturn;
        
        
    }
    
    /**
     * Main algo to sort the shift map using the workers map
     */
    private static void sortShift(){
        System.err.println("Sorting shift array algorithem");
        Shift runShift = shiftHead;
        try{
        while(runShift!=null){
            System.out.println("===========Iterating #"+runShift+"===========");
            Worker w = getNextInOrderWorker(runShift);
            if(w==null){ //I couldn't find an employee
                //Need to try again
                runShift.prev.getWorker().setShiftRate(runShift.prev.getID(), runShift.prev.getWorker().getShiftRate(runShift.prev)-0.5);
                Workers.put(runShift.prev.getWorker(), Workers.get(runShift.prev.getWorker())-1);
                runShift.prev.setWorker(null);
                runShift = runShift.prev;
                continue;
                
            }
            
            
            runShift.setWorker(w);
            Workers.put(w, Workers.get(w)+1);
            printShiftWorkPrefArray();
            
            
            runShift = runShift.next;
        }
        }
        catch(Exception e){
            System.err.println("COULD NOT SORT SHIFT - Due to shift #"+(runShift.getID()+1));
        }
        
            
            
        
        
    }
    
    /**
     * Returns the next max worker to assign if can from workers
     * @param s
     * @return 
     */
    private static Worker getNextInOrderWorker(Shift s){
        Worker toReturn = null;
        for(Worker tmpWorker : Workers.keySet()){
            if(tmpWorker.getShiftRate(s)==0)
                continue; //Skip if cant do it
            
            if(s.prev!=null && s.prev.getWorker()!=null && s.prev.getWorker().equals(tmpWorker))
                continue; //Skip if he did recent shift
            
            if(toReturn==null){ //Assign a worker if he is the first one
                toReturn = tmpWorker;
                continue; 
            }
            
            if(tmpWorker.getShiftRate(s) > toReturn.getShiftRate(s)){ //Reassign if better option found
                toReturn = tmpWorker;
                continue;
            }
            
            if(tmpWorker.getShiftRate(s).equals(toReturn.getShiftRate(s))) //Counter
                if(Workers.get(tmpWorker)<Workers.get(toReturn)){
                    toReturn = tmpWorker;
                    continue;
                }
                    
            
            
                
            
            
            
        }
        
        
        
        return toReturn;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
