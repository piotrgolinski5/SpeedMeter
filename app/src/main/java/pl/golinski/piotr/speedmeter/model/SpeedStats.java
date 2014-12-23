package pl.golinski.piotr.speedmeter.model;

public class SpeedStats extends PGModel{
    public int mLastSpeed;
    public int mMaxSpeed;
    public int mAvgSpeed;
    public long mBestTimeTo100;
    public long mLastTimeTo100;
    public long mBestTimeTo200;
    public long mLastTimeTo200;

    public void setSpeed(int speed){
        mLastSpeed = speed;
        if(speed > mMaxSpeed){
            mMaxSpeed = speed;
        }
    }

    public void setTimeTo100(long time){
        time = time/1000;
        mLastTimeTo100 = time;
        if(time > mBestTimeTo100){
            mBestTimeTo100 = time;
        }
    }

    public void setTimeTo200(long time){
        time = time/1000;
        mLastTimeTo200 = time;
        if(time > mBestTimeTo200){
            mBestTimeTo200 = time;
        }
    }

    public void setAvgSpeed(int speed){
        if(mAvgSpeed == 0){
            mAvgSpeed = speed;
        } else{
            mAvgSpeed = (mAvgSpeed + speed)/2;
        }
    }
}
