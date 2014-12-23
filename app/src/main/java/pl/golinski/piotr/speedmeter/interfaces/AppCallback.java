package pl.golinski.piotr.speedmeter.interfaces;


public interface AppCallback {
    public void setGPSEnabled();
    public void setGPSDisabled();
    public void setLocationCallback(LocationCallback locationCallback);
    public void saveStats();
}
