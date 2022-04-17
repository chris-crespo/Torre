package models;

import java.time.LocalDateTime;

public record Authorization(Operation operation, LocalDateTime date) {}
