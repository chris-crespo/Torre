package app;

import adt.Queue;
import models.*;

public class Operations {
    Queue<Operation> main;
    Queue<Landing>   landings;
    Queue<TakeOff>   takeOffs;

    public Operations() {
        this.main     = new Queue<>();
        this.landings = new Queue<>();
        this.takeOffs = new Queue<>();
    }

    public void add(Landing landing) {

    }
}
