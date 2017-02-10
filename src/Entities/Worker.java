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

/**
 *
 * @author nisan
 */
public class Worker {
    private int id;
    private String name;
    private Map<Shift, Double> favoriteShifts;

    public Worker(int id, String name, Map<Integer, Shift> shiftMap) {
        this.id = id;
        this.name = name;
        this.favoriteShifts = createShiftArr(shiftMap);
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
  
    public void setShiftRate(Shift shft, Double dbl){
        favoriteShifts.put(shft, dbl);
    }
    
    public Map<Shift, Double> createShiftArr(Map<Integer, Shift> hm){
        Map<Shift, Double> toReturn = new HashMap<Shift, Double>();
        for(int i=0; i<IShift.NumOfShifts;i++){
            toReturn.put(hm.get(i), 1.0);
        }
        
        return toReturn;
    }
}
