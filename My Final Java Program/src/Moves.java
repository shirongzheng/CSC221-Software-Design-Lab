/* CSC22100-R   Assignment 5(Final Project) - Artificial Life & GUI Simulation

    Shirong Zheng

    12/26/2016
 */

import java.io.Serializable;

public class Moves implements Serializable {
    private int age;
    private int energy;
    private int num;


    private int x;
    private int y;
    private int moved;
    private int ate;

    public Moves(int num) {
        this.num=num;
        this.age=0;
        if(num!=1) {
            this.energy=(int) (51*Math.random()+50);
            this.moved=0;
            this.ate=0;
        }
    }

    public int getAge() {return this.age;}
    public void setAge(int age) {this.age=age;}

    public int getEnergy() {return this.energy;}
    public void setEnergy(int energy) {this.energy=energy;}

    public int getNum() {return num;}
    public void setNum(int num) {this.num=num;}
    public void setMoved(int status) {this.moved=status;}
    public int getMoved() {return moved;}
    public void setAte(int status) {this.ate=status;}
    public int getAte() {return ate;}
}
