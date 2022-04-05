package models;

import java.util.Date;

import models.view.*;

public record TakeOff(String planeCode, Date date, String destination) 
        implements Operation {
    public OperationVM viewModel() {
        return new OperationVM(OperationKind.Despegue, "00:00 h", planeCode);
    }
}
