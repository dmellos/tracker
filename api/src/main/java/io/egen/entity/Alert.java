package io.egen.entity;

import javax.persistence.*;

public class Alert {

    public enum Priority {
        HIGH, MEDIUM, LOW;
    }

    public enum AlertType {
        FUELVOLUME, SPEED, ENGINEHP, CHECKENGINELIGHTON, ENGINECOOLANTLOW, CRUISECONTROLON, ENGINERPM, TIRES;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long alertId;

    @OneToOne
    private Reading reading;

    @OneToOne
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private AlertType alertType;

    public long getAlertId() {
        return alertId;
    }

    public void setAlertId(long alertId) {
        this.alertId = alertId;
    }

    public Reading getReading() {
        return reading;
    }

    public void setReading(Reading reading) {
        this.reading = reading;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "alertId=" + alertId +
                ", reading=" + reading +
                ", vehicle=" + vehicle +
                ", priority=" + priority +
                ", alertType=" + alertType +
                '}';
    }
}
