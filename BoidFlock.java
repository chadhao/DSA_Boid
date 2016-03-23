package DSA_Boid;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
/**
 *
 * @author gpz1505
 */
public class BoidFlock {
    public static int DETECTRADIUS = 75;
    private int frameWidth;
    private int frameHeight;
    private List<Boid> boidList;
    private Map<Boid, Thread> boidThreads;
    private static Random RAND = new Random();
    
    public BoidFlock() {
        boidList = new ArrayList<>();
    }
    
    public BoidFlock(int numBoidsToStart, int frameWidth, int frameHeight) {
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        boidList = new ArrayList<>();
        boidThreads = new HashMap<>();
        for (int i = 0; i < numBoidsToStart; i++) {
            boidList.add(new Boid(this, RAND.nextInt(frameWidth), RAND.nextInt(frameHeight), RAND.nextInt(Boid.MAX_SPEED), RAND.nextFloat()*Boid.MAX_SPEED, frameWidth, frameHeight));
            boidThreads.put(boidList.get(i), new Thread(boidList.get(i)));
        }
        startAllThreads();
    }
    
    private void startAllThreads() {
        for( Map.Entry<Boid, Thread> e : boidThreads.entrySet() ) {
            e.getValue().start();
        }
    }
    
    public void addBoidToFlock() {
        boidList.add(new Boid(this, RAND.nextInt(frameWidth), RAND.nextInt(frameHeight), RAND.nextInt(Boid.MAX_SPEED), RAND.nextInt(Boid.MAX_SPEED), frameWidth, frameHeight));
        boidThreads.put(boidList.get(boidList.size()-1), new Thread(boidList.get(boidList.size()-1)));
        boidThreads.get(boidList.get(boidList.size()-1)).start();
    }
    
    public void removeBoidFromFlock() {
        int randIndex = RAND.nextInt(boidList.size());
        boidThreads.get(boidList.get(randIndex)).interrupt();
        boidThreads.remove(boidList.get(randIndex));
        boidList.remove(randIndex);
    }
    
    public void destroyAllBoids() {
        for( Map.Entry<Boid, Thread> e : boidThreads.entrySet() ) {
            e.getValue().interrupt();
        }
        boidThreads.clear();
        boidList.clear();
    }
    
    public int getNumberOfBoids() {
        return boidList.size();
    }
    
    private synchronized boolean isNeighbour(Boid from, Boid to) {
        return Math.pow(from.getxPos()-to.getxPos(), 2)+Math.pow(from.getyPos()-to.getyPos(), 2)<Math.pow(DETECTRADIUS, 2);
    }
    
    public synchronized List<Boid> getNeighbours(Boid boidToTest) {
        List<Boid> tempList = new ArrayList<>();
        synchronized(this) {
            for( Boid aBoid : boidList ) {
                if ( isNeighbour(boidToTest, aBoid) ) {
                    tempList.add(aBoid);
                }
            }
        }
        return tempList;
    }
    
    public void drawAllBoids(Graphics g) throws InterruptedException {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, frameWidth, frameHeight);
        for( Boid aBoid : boidList ) {
            aBoid.drawShip(g);
        }
    }
}
