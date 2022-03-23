package app;

import java.util.Optional;

import view.*;
import adt.*;

public class Control {
    private Queue<String> takeOffs;
    private Queue<String> landings;
    private Stream<Optional<String>> operations;

    public Control() {
        this.takeOffs = new Queue<String>();
        this.landings = new Queue<String>();

        var takeOffsStream = Stream.infinite(takeOffs::remove);
        var landingsStream = Stream.infinite(landings::remove);

        this.operations = takeOffsStream.interleave(landingsStream);
    }

    public void takeOff() {
        
    }

    public void land() {

    }

    public boolean auth() {
        return operations.next().isPresent();
    }
}
