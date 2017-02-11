/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Main.IShift;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author nisan
 */
public class Worker {
    private int id;
    private String name;
    private Map<Shift, Double> favoriteShifts;

    public Worker(int id, String name, Shift shiftHead) {
        this.id = id;
        this.name = name;
        this.favoriteShifts = createShiftArr(shiftHead);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getShiftRate(Shift shiftNum) {
        return favoriteShifts.get(shiftNum);
    }
  
    public void setShiftRate(Integer shft, Double dbl){
        
        favoriteShifts.put(new Shift(shft), dbl);
    }
    
    public Map<Shift, Double> createShiftArr(Shift shiftHead){
        Map<Shift, Double> toReturn = new TreeMap<Shift, Double>();
        while(shiftHead!=null){
            toReturn.put(shiftHead, 1.0);
            shiftHead = shiftHead.next;
        }
        
        return toReturn;
    }
    
    public void downgradeShift(Shift shift){
        favoriteShifts.put(shift, favoriteShifts.get(shift)-0.5);
    }
    
    public void printShiftPlan(){
        System.out.format("%10s", getName()+"("+IShift.Workers.get(this)+")");
        for(Shift s : favoriteShifts.keySet()){
            if(this.equals(s.getWorker()))
            System.out.format("%7.1f*",getShiftRate(s));
            else
                System.out.format("%8.1f",getShiftRate(s));
        }
        System.out.println();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Worker other = (Worker) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+getName(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
}
