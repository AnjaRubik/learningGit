/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agarest;

/**
 *
 * @author Andrew
 */
public class Character {

    private String name;
    private int level;
    private int STR;
    private int VIT;
    private int AGI;
    private int INT;
    private int WIS;
    private int LUK;
    private String notes;
    
    public Character(String name, int level, int STR, int VIT, int AGI, int INT, int WIS, int LUK, String notes) {
        this.name = name;
        this.level = level;
        this.STR = STR;
        this.VIT = VIT;
        this.AGI = AGI;
        this.INT = INT;
        this.WIS = WIS;
        this.LUK = LUK;
        this.notes = notes;
    }
    
    public void setName(String val) {
        this.name = val;
    }
    
    public void setNotes(String val) {
        this.notes = val;
    }
    
    public void levelUp(int STR, int VIT, int AGI, int INT, int WIS, int LUK) {
        this.level += 1;
        this.STR = STR;
        this.VIT = VIT;
        this.AGI = AGI;
        this.INT = INT;
        this.WIS = WIS;
        this.LUK = LUK;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getSTR() {
        return STR;
    }

    public int getVIT() {
        return VIT;
    }

    public int getAGI() {
        return AGI;
    }

    public int getINT() {
        return INT;
    }

    public int getWIS() {
        return WIS;
    }

    public int getLUK() {
        return LUK;
    }

    public String getNotes() {
        return notes;
    }
    
}


