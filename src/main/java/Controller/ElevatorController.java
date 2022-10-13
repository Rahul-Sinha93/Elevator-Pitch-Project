package Controller;

import Entity.Elevator;
import Entity.ElevatorConst;
import java.util.ArrayList;
import java.util.List;
public class ElevatorController extends Thread {
    private List<Elevator> elevators;
    private boolean[] upStatus;
    private boolean[] downStatus;

    public ElevatorController() {
        this.elevators = new ArrayList<>();
        this.upStatus = new boolean[ElevatorConst.TOTAL_FLOOR];
        this.downStatus = new boolean[ElevatorConst.TOTAL_FLOOR];
        for (int i = 0; i < ElevatorConst.TOTAL_FLOOR; i++) {
            this.upStatus[i] = false;
            this.downStatus[i] = false;
        }
    }


    public void addElevator(){
        for(int i=0;i<ElevatorConst.TOTAL_ELEVATOR;i++){
            Elevator elevator=new Elevator();
            elevator.start();
            elevators.add(elevator);
        }
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            for(int i=0;i<this.upStatus.length;i++){
                if(this.upStatus[i]){
                    this.findProperUpElevator(i);
                }

            }
            for(int i=0;i<this.downStatus.length;i++){
                if(this.downStatus[i]){
                    this.findProperDownElevator(i);
                }
            }
        }
    }

    private void findProperUpElevator(int floorNumber) {
        int distance = 100;

        int index = -1;

        for (int i = 0; i < this.elevators.size(); i++) {
            int currentFloor = this.elevators.get(i).getCurrentFloor();
            if (this.elevators.get(i).getDirection() == ElevatorConst.STATUS_UP && currentFloor <= floorNumber) {
                if (distance > (floorNumber - currentFloor)) {
                    distance = floorNumber - currentFloor;
                    index = i;
                }

            } else if (this.elevators.get(i).getDirection() == ElevatorConst.STATUS_IDEL) {
                if (distance > Math.abs(floorNumber - currentFloor)) {
                    distance = Math.abs(floorNumber - currentFloor);
                    index = i;
                }
            }
        }
        if (index != -1) {
            this.elevators.get(index).addTargetFloor(floorNumber);
            this.upStatus[floorNumber] = false;
        }
    }

    private void findProperDownElevator(int floorNumber) {
        int distance = 100;

        int index = -1;
        for (int i = 0; i < this.elevators.size(); i++) {
            int currentFloor = this.elevators.get(i).getCurrentFloor();
            if (this.elevators.get(i).getDirection() == ElevatorConst.STATUS_DOWN && currentFloor >= floorNumber) {
                if (distance > (currentFloor - floorNumber)) {
                    distance = floorNumber - currentFloor;
                    index = i;
                }
            } else if (this.elevators.get(i).getDirection() == ElevatorConst.STATUS_IDEL) {
                if (distance > Math.abs(floorNumber - currentFloor)) {
                    distance = Math.abs(floorNumber - currentFloor);
                    index = i;
                }

            }
        }
        if (index != -1) {
            this.elevators.get(index).addTargetFloor(floorNumber);
            this.downStatus[floorNumber] = false;

        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}
