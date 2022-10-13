package Entity;

public class Elevator extends Thread {
    private int currentFloor;
    private int targetFloor;
    private int direction;
    private boolean[] floorStatus;

    public Elevator() {
        this.direction = ElevatorConst.STATUS_IDEL;
        this.currentFloor = 0;
        this.targetFloor = 0;
        this.floorStatus = new boolean[ElevatorConst.TOTAL_FLOOR];
        for (int i = 0; i < this.floorStatus.length; i++) {
            this.floorStatus[i] = false;
        }

    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }


    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(700);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            if(this.direction==ElevatorConst.STATUS_UP||this.direction==ElevatorConst.STATUS_DOWN){
                try{
                    Thread.sleep(700);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                this.direction=ElevatorConst.STATUS_IDEL;
            }
            if(this.targetFloor>this.currentFloor){


                this.direction=ElevatorConst.STATUS_UP;
                this.moveUp();
                this.direction=ElevatorConst.STATUS_IDEL;


            }else if(this.targetFloor<this.currentFloor){


                this.direction=ElevatorConst.STATUS_DOWN;
                this.moveDown();
                this.direction=ElevatorConst.STATUS_IDEL;

            }else if(this.targetFloor==this.currentFloor&&this.floorStatus[this.targetFloor]){
                this.direction=ElevatorConst.STATUS_IDEL;

            }
        }

    }


    private void moveUp(){
        for(int i=this.currentFloor;i<=this.targetFloor;i++){
            this.currentFloor=i;
            try{
                Thread.sleep(300);
                if(this.floorStatus[i]){
                    this.floorStatus[i]=false;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    private void moveDown(){
        for(int i=this.currentFloor;i>=this.targetFloor;i--){
            this.currentFloor=i;
            try{
                Thread.sleep(300);
                if(this.floorStatus[i]){
                    this.floorStatus[i]=false;
                }
            }catch(Exception e){
                e.printStackTrace();

            }
        }

    }

    public void addTargetFloor(int t) {
        if (this.direction == ElevatorConst.STATUS_IDEL) {
            this.targetFloor = t;
            this.floorStatus[t] = true;
        } else if (this.direction == ElevatorConst.STATUS_UP) {
            if (t >= this.targetFloor) {
                this.targetFloor = t;
                this.floorStatus[t] = true;
            } else if (t >= this.currentFloor) {
                this.floorStatus[t] = true;
            }
        } else if (this.direction == ElevatorConst.STATUS_DOWN) {
            if (t <= this.targetFloor) {
                this.targetFloor = t;
                this.floorStatus[t] = true;
            } else if (t <= this.currentFloor) {
                this.floorStatus[t] = true;
            }
        }
    }


    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDirection() {
        return direction;
    }

}
