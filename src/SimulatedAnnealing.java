
/**
 *
 * @author NERO CHANIAGO
 */
import static java.lang.Math.*;//library
import java.util.*; //library


public class SimulatedAnnealing {
    
    static final double PI = Math.PI; // definisi phi
    Random Random = new Random(); // librari random

    public double CurrentStateRandom(double x1, double x2){  //fungsi random
        double CurrentStateRandom;
        CurrentStateRandom = x1 + (x2-x1) * Random.nextDouble();
        return CurrentStateRandom;
    }
    
    public double CurrentState(double x1, double x2) {  //fungsi cs
        double fungsi;
        fungsi = (-1) * (Math.abs(sin(x1)*cos(x2)*Math.exp(Math.abs(1-(sqrt(pow(x1,2)+pow(x2,2))/Math.PI)))));
        return fungsi;
    }
    
    public static void main(String[] args) {
        double P = 0;
        SimulatedAnnealing s = new SimulatedAnnealing();
        double Temperatur = 100000;  //temperatur
        double CoolingRate = 0.8;    //cooling rate
        //masuk fungsi
        double x1 = s.CurrentStateRandom(-10,10);
        double x2 = s.CurrentStateRandom(-10,10);
        double BSF = s.CurrentState(x1,x2);
        
        //ulangi temperatur
        for (int i=1; i<=100000; i++) {
            double x1_NextState =  s.CurrentStateRandom(-10,10);
            double x2_NextState =  s.CurrentStateRandom(-10,10);
            double NS = s.CurrentState(x1_NextState,x2_NextState);
            double delta = NS - BSF;
            
                if (delta < 0) {
                    if (NS < BSF) { 
                       BSF = NS;
                    }
               }
                else {
                    P = Math.exp( (-1*delta) / Temperatur );
                    double R = s.CurrentStateRandom(0,1);
                   
                    
                    if (P > R) {
                        BSF = NS;
                    }
                }
                Temperatur = Temperatur * CoolingRate;
            //jika solusi lebih baik dari sebelumnya maka ambil
            //jika tidak hitung probabilitasnya.
        }
        System.out.println("BEST SO FAR (BSF) : " + BSF);
    }
}
