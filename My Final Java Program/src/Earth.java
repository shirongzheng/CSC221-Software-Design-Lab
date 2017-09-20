/* CSC22100-R   Assignment 5(Final Project) - Artificial Life & GUI Simulation

    Shirong Zheng

    12/26/2016
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Earth implements Serializable{
    private Moves[][] earth;
    int directions[]=new int[4];
    int cells[]=new int[4];
    int grass[]=new int[30];


    public int energy(int min) {
        int consumption=min;
        int rand=(int) (6*Math.random());
        return consumption+rand;
    }

    public ArrayList<String> printEarth() {   //print the matrix
        String[][]temp = new String[30][30];
        ArrayList<String> temp1 = new ArrayList();
        System.out.print("\n");
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                if(earth[i][j].getNum()==0) {
                    temp[i][j]=(". ");
                }
                if(earth[i][j].getNum()==1) {
                    temp[i][j]=("* ");
                }
                if(earth[i][j].getNum()==2) {
                    temp[i][j]=("& ");
                }
                if(earth[i][j].getNum()==3) {
                    temp[i][j]=("@ ");
                }
                temp1.add(temp[i][j]);

            }



        }

        return temp1;
    }

    public void resetAttribute() {
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                if(earth[i][j].getNum()!=1) {
                    earth[i][j].setMoved(0);
                    earth[i][j].setAte(0);
                }
            }
        }
    }

    public void growGrass() {
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                if(earth[i][j].getNum()==0) grass[j]=1;
            }
        }
        for(int i=0; i<30; i++) {
            int rand=(int) (30*Math.random());
            for(int j=rand-1; j<-1; j--) {
                if(grass[j]==1) earth[i][j]=new Grass();
                break;
            }
            for(int j=rand+1; j<30; j++) {
                if(grass[j]==1) earth[i][j]=new Grass();
                break;
            }
        }
    }

    public Earth() {this.earth=new Moves[30][30];}

    public void initializeEarth() {
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                this.earth[i][j]=new Moves(0);
            }
        }
        for(int i=0; i<30; i++) {
            int rand=(int) (30*Math.random());
            int percent=(int) (2*Math.random());
            if(earth[i][rand].getNum()==0 && percent==1) earth[i][rand].setNum(2);
        }
        for(int i=0; i<30; i++) {
            //if(i%2==0) continue;
            int rand=(int) (30*Math.random());
            int percent=(int) (2*Math.random());
            if(earth[i][rand].getNum()==0 && percent==1) earth[i][rand].setNum(3);
        }
    }

    public boolean isFree(int x, int y) {
        boolean ans=false;
        if(earth[x][y].getNum()==0) ans=true;
        return ans;
    }


    public void Move() {
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                if(earth[i][j].getNum()>=2 && earth[i][j].getMoved()==0) {
                    int temp=energy(5);
                    if(earth[i][j].getEnergy()-temp>=0) {
                        getCells(i,j);
                        int rand=0;
                        for(int b=0; b<4; b++) {
                            if(cells[b]!=0) rand+=1;
                        }
                        rand=(int) (rand*Math.random());
                        if(cells[rand]==1) {
                            earth[i-1][j].setNum(earth[i][j].getNum());
                            earth[i-1][j].setAge(earth[i][j].getAge());
                            earth[i-1][j].setEnergy(earth[i][j].getEnergy()-temp);

                            earth[i][j].setNum(0);
                            earth[i-1][j].setMoved(1);
                        }
                        if(cells[rand]==2) {
                            earth[i+1][j].setNum(earth[i][j].getNum());
                            earth[i+1][j].setAge(earth[i][j].getAge());
                            earth[i+1][j].setEnergy(earth[i][j].getEnergy()-temp);
                            earth[i][j].setNum(0);
                            earth[i+1][j].setMoved(1);
                        }
                        if(cells[rand]==3) {
                            earth[i][j-1].setNum(earth[i][j].getNum());
                            earth[i][j-1].setAge(earth[i][j].getAge());
                            earth[i][j-1].setEnergy(earth[i][j].getEnergy()-temp);
                            earth[i][j].setNum(0);
                            earth[i][j-1].setMoved(1);
                        }
                        if(cells[rand]==4) {
                            earth[i][j+1].setNum(earth[i][j].getNum());
                            earth[i][j+1].setAge(earth[i][j].getAge());
                            earth[i][j+1].setEnergy(earth[i][j].getEnergy()-temp);
                            earth[i][j].setNum(0);
                            earth[i][j+1].setMoved(1);
                        }
                    }
                }
            }
        }
    }


    public void target(int num, int i, int j) {
        for(int a=0; a<4; a++) {
            directions[a]=0;
        }
        int index=0;
        if(i==0) {
            if(j==0) {
                if(earth[i+1][j].getNum()==num-1) {directions[index]=2; index++;}
                if(earth[i][j+1].getNum()==num-1) {directions[index]=4;}
            } else if(j==29) {
                if(earth[i+1][j].getNum()==num-1) {directions[index]=2; index++;}
                if(earth[i][j-1].getNum()==num-1) {directions[index]=3;}
            } else {
                if(earth[i+1][j].getNum()==num-1) {directions[index]=2; index++;}
                if(earth[i][j-1].getNum()==num-1) {directions[index]=3; index++;}
                if(earth[i][j+1].getNum()==num-1) {directions[index]=4; }
            }
        } else if(i>0 && i<29) {
            if(j==0) {
                if(earth[i-1][j].getNum()==num-1) {directions[index]=1; index++;}
                if(earth[i+1][j].getNum()==num-1) {directions[index]=2; index++;}
                if(earth[i][j+1].getNum()==num-1) {directions[index]=4;}
            } else if(j==29) {
                if(earth[i-1][j].getNum()==num-1) {directions[index]=1; index++;}
                if(earth[i+1][j].getNum()==num-1) {directions[index]=2; index++;}
                if(earth[i][j-1].getNum()==num-1) {directions[index]=3;}
            } else {
                if(earth[i-1][j].getNum()==num-1) {directions[index]=1; index++;}
                if(earth[i+1][j].getNum()==num-1) {directions[index]=2; index++;}
                if(earth[i][j-1].getNum()==num-1) {directions[index]=3; index++;}
                if(earth[i][j+1].getNum()==num-1) {directions[index]=4;}
            }
        } else if(i==29) {
            if(j==0) {
                if(earth[i-1][j].getNum()==num-1) {directions[index]=1; index++;}
                if(earth[i][j+1].getNum()==num-1) {directions[index]=4;}
            } else if(j==29) {
                if(earth[i-1][j].getNum()==num-1) {directions[index]=1; index++;}
                if(earth[i][j-1].getNum()==num-1) {directions[index]=3;}
            } else {
                if(earth[i-1][j].getNum()==num-1) {directions[index]=1; index++;}
                if(earth[i][j-1].getNum()==num-1) {directions[index]=3; index++;}
                if(earth[i][j+1].getNum()==num-1) {directions[index]=4;}
            }
        }
    }


    public void Eat() {
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                if(earth[i][j].getAte()==0 && earth[i][j].getNum()>=2) {
                    int temp=energy(5);
                    int add=energy(10);
                    if(earth[i][j].getEnergy()-temp>=0) {
                        target(earth[i][j].getNum(),i,j);
                        int rand=0;
                        for(int b=0; b<4; b++) {
                            if(directions[b]!=0) rand+=1;
                        }
                        rand=(int) (rand*Math.random());
                        if(directions[rand]==1) {
                            earth[i-1][j].setNum(earth[i][j].getNum());
                            earth[i-1][j].setAge(earth[i][j].getAge());
                            if((earth[i-1][j].getEnergy()-temp+add)>100) earth[i-1][j].setEnergy(100);
                            else earth[i-1][j].setEnergy(earth[i][j].getEnergy()-temp+add);
                            earth[i-1][j].setAte(1);
                            earth[i][j].setNum(0);
                        }
                        if(directions[rand]==2) {
                            earth[i+1][j].setNum(earth[i][j].getNum());
                            earth[i+1][j].setAge(earth[i][j].getAge());
                            if((earth[i+1][j].getEnergy()-temp+add)>100) earth[i+1][j].setEnergy(100);
                            else earth[i+1][j].setEnergy(earth[i][j].getEnergy()-temp+add);
                            earth[i+1][j].setAte(1);
                            earth[i][j].setNum(0);
                        }
                        if(directions[rand]==3) {
                            earth[i][j-1].setNum(earth[i][j].getNum());
                            earth[i][j-1].setAge(earth[i][j].getAge());
                            if((earth[i][j-1].getEnergy()-temp+add)>100) earth[i][j-1].setEnergy(100);
                            else earth[i][j-1].setEnergy(earth[i][j].getEnergy()-temp+add);
                            earth[i][j-1].setAte(1);
                            earth[i][j].setNum(0);
                        }
                        if(directions[rand]==4) {
                            earth[i][j+1].setNum(earth[i][j].getNum());
                            earth[i][j+1].setAge(earth[i][j].getAge());
                            if((earth[i][j+1].getEnergy()-temp+add)>100) earth[i][j+1].setEnergy(100);
                            else earth[i][j+1].setEnergy(earth[i][j].getEnergy()-temp+add);
                            earth[i][j+1].setAte(1);
                            earth[i][j].setNum(0);
                        }
                    }
                }
            }
        }
    }


    public void getCells(int i, int j) {
        for(int a=0; a<4; a++) {
            cells[a]=0;
        }
        int index=0;
        if(i==0) {
            if(j==0) {
                if(earth[i+1][j].getNum()==0) {cells[index]=2; index++;}
                if(earth[i][j+1].getNum()==0) {cells[index]=4;}
            } else if(j==29) {
                if(earth[i+1][j].getNum()==0) {cells[index]=2; index++;}
                if(earth[i][j-1].getNum()==0) {cells[index]=3;}
            } else {
                if(earth[i+1][j].getNum()==0) {cells[index]=2; index++;}
                if(earth[i][j-1].getNum()==0) {cells[index]=3; index++;}
                if(earth[i][j+1].getNum()==0) {cells[index]=4; }
            }
        } else if(i>0 && i<29) {
            if(j==0) {
                if(earth[i-1][j].getNum()==0) {cells[index]=1; index++;}
                if(earth[i+1][j].getNum()==0) {cells[index]=2; index++;}
                if(earth[i][j+1].getNum()==0) {cells[index]=4;}
            } else if(j==29) {
                if(earth[i-1][j].getNum()==0) {cells[index]=1; index++;}
                if(earth[i+1][j].getNum()==0) {cells[index]=2; index++;}
                if(earth[i][j-1].getNum()==0) {cells[index]=3;}
            } else {
                if(earth[i-1][j].getNum()==0) {cells[index]=1; index++;}
                if(earth[i+1][j].getNum()==0) {cells[index]=2; index++;}
                if(earth[i][j-1].getNum()==0) {cells[index]=3; index++;}
                if(earth[i][j+1].getNum()==0) {cells[index]=4;}
            }
        } else if(i==29) {
            if(j==0) {
                if(earth[i-1][j].getNum()==0) {cells[index]=1; index++;}
                if(earth[i][j+1].getNum()==0) {cells[index]=4;}
            } else if(j==29) {
                if(earth[i-1][j].getNum()==0) {cells[index]=1; index++;}
                if(earth[i][j-1].getNum()==0) {cells[index]=3;}
            } else {
                if(earth[i-1][j].getNum()==0) {cells[index]=1; index++;}
                if(earth[i][j-1].getNum()==0) {cells[index]=3; index++;}
                if(earth[i][j+1].getNum()==0) {cells[index]=4;}
            }
        }
    }

    public boolean isFull() {
        boolean notFull=true;
        for(int i=0; i<30; i++) {
            for (int j = 0; j < 30; j++) {
                if(earth[i][j].getNum()==0) return false;
            }
        }
        return notFull;
    }
    public boolean type(int x, int y) {
        if(earth[x][y].getNum()==2) return true;
        else return false;
    }

    public void helpReproduce(int i, int j) {
        getCells(i,j);
        int rand=0;
        for(int b=0; b<4; b++) {
            if(cells[b]!=0) rand+=1;
        }
        rand=(int) (rand*Math.random());
        if(cells[rand]==1) {
            if(type(i,j)) earth[i-1][j]=new Herbivore();
            else earth[i-1][j]=new Carnivore();
        }
        if(cells[rand]==2) {
            if(type(i,j)) earth[i+1][j]=new Herbivore();
            else earth[i+1][j]=new Carnivore();
        }
        if(cells[rand]==3) {
            if(type(i,j)) earth[i][j-1]=new Herbivore();
            else earth[i][j-1]=new Carnivore();
        }
        if(cells[rand]==4) {
            if(type(i,j)) earth[i][j+1]=new Herbivore();
            else earth[i][j+1]=new Carnivore();
        }
    }

    public void Reproduce() {
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                int temp=energy(10);
                if(earth[i][j].getEnergy()-temp>=0) {
                    if((earth[i][j].getAge()>7 ) && earth[i][j].getNum()==2) {
                        helpReproduce(i,j);
                        helpReproduce(i,j);
                        if((int) (2*Math.random())==1) helpReproduce(i,j);
                        if(earth[i][j].getEnergy()-temp>0) earth[i][j].setEnergy(earth[i][j].getEnergy()-temp);
                        else earth[i][j].setEnergy(0);
                    }
                    if(earth[i][j].getAge()>7 && earth[i][j].getNum()==3) {
                        helpReproduce(i,j);
                        if((int) (4*Math.random())==1) helpReproduce(i,j);
                        if(earth[i][j].getEnergy()-temp>0) earth[i][j].setEnergy(earth[i][j].getEnergy()-temp);
                        else earth[i][j].setEnergy(0);
                    }
                }
            }
        }
    }

    public void Die() {
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                if(earth[i][j].getNum()==1 && earth[i][j].getAge()==5) earth[i][j].setNum(0);
                if(earth[i][j].getNum()==2 && earth[i][j].getAge()==15) earth[i][j].setNum(0);
                if(earth[i][j].getNum()==3 && earth[i][j].getAge()==10) earth[i][j].setNum(0);
            }
        }
    }

    public void Time() {
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                earth[i][j].setAge(earth[i][j].getAge()+1);
            }
        }
    }

    public boolean Count() {
        boolean end=false;
        int a=0,b=0,c=0;
        for(int i=0; i<30; i++) {
            for(int j=0; j<30; j++) {
                if(earth[i][j].getNum()==1) a++;
                if(earth[i][j].getNum()==2) b++;
                if(earth[i][j].getNum()==3) c++;
            }
        }
        if(b==0 || c==0) end=true;
        System.out.println("Carnivore: "+c+" | "+"Herbivore: "+b+" | "+"Grass: "+a);
        return end;
    }

}
