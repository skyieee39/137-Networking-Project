package app;

public final class SecTimer {
    private long startMillis = System.currentTimeMillis();

    public double getElapsed() {
        return (System.currentTimeMillis() - this.startMillis) / 1000.0;
    }

    public void reset() {
        this.startMillis = System.currentTimeMillis();
    }

}