/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author nisan
 */
public class Worker {
    private int id;
    private String name;
    private HashMap<Shift, Double> favoriteShifts;

    public Worker(int id, String name) {
        this.id = id;
        this.name = name;
        this.favoriteShifts = new HashMap<Shift, Double>();
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
    
    public void addShiftRate(Shift shiftNum, Double shiftRate) {
        favoriteShifts.put(shiftNum, shiftRate);
    }
}
