package app;

import java.util.Optional;

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
        var last = main.peekLast();
        if (last.isPresent() && last.get() instanceof Landing)
            landings.add(landing);
        else {
            main.add(landing);
            takeOffs.remove().ifPresent(main::add);
        }
    }

    public void add(TakeOff takeOff) {
        var last = main.peekLast();
        if (last.isPresent() && last.get() instanceof TakeOff)
            takeOffs.add(takeOff);
        else {
            main.add(takeOff);
            landings.remove().ifPresent(main::add);
        }
    }

    private <T> Operation cast(T operation) {
        return (Operation) operation;
    }

    public Optional<Operation> next() {
        return main.remove().or(landings::remove).or(takeOffs::remove);
    }
}
