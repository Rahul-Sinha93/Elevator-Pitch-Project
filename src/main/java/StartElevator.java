import Controller.ElevatorController;
import Entity.Elevator;

import java.util.Random;

/**
 * Classed designed to abstract methods from Runner class.
 * All code in this file was created and designed by @HanLu, @RahulSinha and @PaoloDrago
 */
public class StartElevator {
    
    public void startElevator(){

        ElevatorController elevatorController=new ElevatorController();

        elevatorController.addElevator();
        elevatorController.start();


        ElevatorFrameView elevatorFrameView=new ElevatorFrameView(3,10,elevatorController);
        Random random=new Random();

        while(true){
            int i=random.nextInt(10);
            int j=random.nextInt(10);
            int z=random.nextInt(10);
            elevatorController.getElevators().get(0).setTargetFloor(i);
            elevatorController.getElevators().get(1).setTargetFloor(j);

            elevatorController.getElevators().get(2).setTargetFloor(z);

        }


    }
}
