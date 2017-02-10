/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author nisan
 */
public class Shift {
    Integer ID;
    Worker chosenWorker;
    public Shift(Integer ID){
        this.ID = ID;
        chosenWorker = null;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }
    
    public Worker getWorker(){
        return chosenWorker;
    }
    
}
