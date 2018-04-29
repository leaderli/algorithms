package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyFairLock extends AbstractQueuedSynchronizer implements Lock {
    private volatile Thread owner;

    @Override
    public void lock() {
        acquire(1);
    }

    @Override
    protected boolean tryAcquire(int aquires) {
        final Thread current = Thread.currentThread();
        int state = getState();
        if (state == 0) {
            if (!hasQueuedPredecessors() && compareAndSetState(0, aquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        }else if(current ==getExclusiveOwnerThread()){
            state +=aquires;
            if(state<0)
               throw new  Error("Maximum lock count exceeded");
            setState(state);
        }
        return false;
    }

    @Override
    public void lockInterruptibly() {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
