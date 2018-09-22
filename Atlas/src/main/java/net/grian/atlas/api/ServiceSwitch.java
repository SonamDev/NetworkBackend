package net.grian.atlas.api;

public abstract class ServiceSwitch {

    private boolean on;

    public ServiceSwitch(boolean initial) {
        this.on = initial;
    }

    public boolean isOn() {
        return on;
    }

    public void switchOn() {
        this.on = true;
        switchedOn();
    }

    public void switchOff() {
        this.on = false;
        switchedOff();
    }

    protected abstract void switchedOn();

    protected abstract void switchedOff();


}
