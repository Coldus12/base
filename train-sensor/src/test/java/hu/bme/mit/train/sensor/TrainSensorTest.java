package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import hu.bme.mit.train.controller.*;
import hu.bme.mit.train.sensor.*;
import hu.bme.mit.train.user.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {


    TrainController ctrl;
    TrainUser user;

    TrainSensor sensor;


    @Before
    public void before() {
        this.ctrl = mock(TrainContoller.class);
        this.user = mock(TrainUser.class);
        this.sensor = new TrainSensorImpl(this.ctrl, this.user);
    }

    @Test
    public void SpeedLimitIsNegativeTest() {
        this.sensor.overrideSpeedLimit(-10);
        when(this.user.getAlarmState()).thenReturn(true);
    }

    @Test
    public void SpeedLimitIsTooLargeTest() {
        this.sensor.overrideSpeedLimit(501);
        when(this.user.getAlarmState()).thenReturn(true);
    }

    @Test
    public void SpeedLimitRelativeMarginExceededTest() {
        this.ctrl.setReferenceSpeed(100);
        this.sensor.overrideSpeedLimit(40);
        when(this.user.getAlarmState()).thenReturn(true);
    }

    @Test
    public void SpeedLimitRelativeMarginNotExceededTest() {
        this.ctrl.setReferenceSpeed(100);
        this.sensor.overrideSpeedLimit(60);
        when(this.user.getAlarmState()).thenReturn(false);
    }
}
