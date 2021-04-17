package PDT;

import SimStation.Agent;

public class Prisoner extends Agent {
    private int fitness;
    private boolean partnerCheated;
    private Strategy strategy;

    public Prisoner() {
        super();
        fitness = 0;
        partnerCheated = false;
    }

    public int getFitness() {
        return fitness;
    }

    public String getStrategy() {
        return strategy.toString();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public boolean cooperate() {
        return strategy.cooperate();
    }

    public void updateFitness(int amt) {
        fitness += amt;
    }

    public void setPartnerCheated(boolean bool) {
        partnerCheated = bool;
    }

    public boolean getPartnerCheated() {
        return partnerCheated;
    }

    public Prisoner getPrisoner() {
        return this;
    }

    public void update() {
        // Gets random neighbor, sets bounds to be whole board size
        Prisoner neighbor = (Prisoner) this.world.getNeighbor(this, 250);
        // If neighbor is not null, play a game of prisoner's dilemma
        if (neighbor != null) {
            boolean neighborS = neighbor.strategy.cooperate();
            if (this.cooperate() && neighborS) {
                neighbor.updateFitness(3);
                this.updateFitness(3);
                neighbor.setPartnerCheated(false);
                this.setPartnerCheated(false);
            } else if (!this.cooperate() && neighborS) {
                this.updateFitness(5);
                neighbor.setPartnerCheated(true);
                this.setPartnerCheated(false);
            } else if (this.cooperate() && !neighborS) {
                neighbor.updateFitness(5);
                neighbor.setPartnerCheated(false);
                this.setPartnerCheated(true);
            } else {
                neighbor.updateFitness(1);
                this.updateFitness(1);
                neighbor.setPartnerCheated(true);
                this.setPartnerCheated(true);
            }

        }
    }
}
