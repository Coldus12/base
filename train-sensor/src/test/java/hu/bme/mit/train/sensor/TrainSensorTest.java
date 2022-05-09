package hu.bme.mit.train.sensor;
 
import org.junit.Assert;
import org.junit.Before;
import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Test;
import static org.mockito.Mockito.*;
 
public class TrainSensorTest {

     
 
   TrainController ctrl;
   TrainUser user;
 
   TrainSensor sensor;
 
 
   @Before
   public void before() {
       this.ctrl = mock(TrainControllerImpl.class);
       this.user = new TrainUserImpl(ctrl);
       this.sensor = new TrainSensorImpl(this.ctrl, this.user);
   }
 
   @Test
   public void SpeedLimitIsNegativeTest() {
       this.sensor.overrideSpeedLimit(-10);
       Assert.assertTrue(this.user.getAlarmState());
   }
 
   @Test
   public void SpeedLimitIsTooLargeTest() {
       this.sensor.overrideSpeedLimit(501);
       Assert.assertTrue(this.user.getAlarmState());
   }
 
   @Test
   public void SpeedLimitRelativeMarginExceededTest() {
       //this.ctrl.setReferenceSpeed(100);
   when(ctrl.getReferenceSpeed()).thenReturn(100);
       this.sensor.overrideSpeedLimit(40);
       Assert.assertTrue("asd",this.user.getAlarmState());
   }
 
   @Test
   public void SpeedLimitRelativeMarginNotExceededTest() {
       //this.ctrl.setReferenceSpeed(100);
       when(ctrl.getReferenceSpeed()).thenReturn(100);
   this.sensor.overrideSpeedLimit(60);
       Assert.assertFalse(this.user.getAlarmState());
   }
}
