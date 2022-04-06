package app.tests;

import java.time.LocalDateTime;

import org.junit.*;
import static org.junit.Assert.*;

import app.Control;
import models.*;

public class ControlTest {
    @Test
    public void takeOffTest() {
        var control = new Control();
        var takeOff = new TakeOff("A", LocalDateTime.now(), "A");
        control.requestTakeOff(takeOff);

        var op = control.auth();

        op.ifPresentOrElse(
            o -> assertEquals(o, takeOff),
            () -> fail());
    }

    @Test 
    public void nonEmergencyLandingTest() {
        var control = new Control();
        var landing = new Landing("A", LocalDateTime.now(), "A", SpecialCause.None);
        control.requestLanding(landing);

        var op = control.auth();

        op.ifPresentOrElse(
            o -> assertEquals(o, landing),
            () -> fail());
    }

    @Test 
    public void emergencyLandingTest() {
        var control  = new Control();
        var landing  = new Landing("A", LocalDateTime.now(), "A", SpecialCause.None);
        var landing2 = new Landing("B", LocalDateTime.now(), "B", SpecialCause.LackOfFuel);

        control.requestLanding(landing);
        control.requestLanding(landing2);

        var op = control.auth();

        op.ifPresentOrElse(
            o -> assertEquals(o, landing2),
            () -> fail());
    }
}
