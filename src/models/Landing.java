package models;

import java.util.Date;

import models.view.*;

public record Landing(String planeCode, Date date, String origin, SpecialCause cause) 
        implements Operation {
    public OperationVM viewModel() {
        return new OperationVM(OperationKind.Aterrizaje, "00:00 h", planeCode);
    }
}
