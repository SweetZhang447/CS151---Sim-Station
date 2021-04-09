package SimStation;

import mvc.Model;

import java.util.ArrayList;

public class SimStation extends Model {

    private ArrayList<Agent> list;
    private int clock =0;

    public SimStation(ArrayList<Agent> list) {
        this.list = list;
    }

    public void start(){
        this.populate();
    }

    public void suspend(){

    }

    public void resume(){

    }

    public void stop(){

    }

    public Agent getNeighbor(Agent a){

    }

    public void populate(){

    }
}
