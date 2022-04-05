package models;

import models.view.*;

public sealed interface Operation permits TakeOff, Landing { 
    public OperationVM viewModel();
}
