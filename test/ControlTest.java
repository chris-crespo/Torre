package app.tests;

import java.util.Date;

import org.junit.*;
import static org.junit.Assert.*;

import app.Control;
import models.*;

public class ControlTest {
    @Test
    public void takeOffTest() {
        var control = new Control();
        var takeOff = new TakeOff("A", new Date(), "A");
        control.requestTakeOff(takeOff);

        var op = control.auth();

        op.ifPresentOrElse(
                o -> assertEquals(o, takeOff),
                () -> fail());
    }

    @Test 
    public void nonEmergencyLandingTest() {
        var control = new Control();
        var landing = new Landing("A", new Date(), "A", SpecialCause.None);
        control.requestLanding(landing);

        var op = control.auth();

        op.ifPresentOrElse(
                o -> assertEquals(o, landing),
                () -> fail());
    }

    @Test 
    public void emergencyLandingTest() {
        var control  = new Control();
        var landing  = new Landing("A", new Date(), "A", SpecialCause.None);
        var landing2 = new Landing("B", new Date(), "B", SpecialCause.LackOfFuel);

        control.requestLanding(landing);
        control.requestLanding(landing2);

        var op = control.auth();

        op.ifPresentOrElse(
                o -> assertEquals(o, landing2),
                () -> fail());
    }
}
