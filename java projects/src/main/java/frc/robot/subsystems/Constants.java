package frc.robot;

import com.ctre.phoenix6.controls.Follower;

public final class Constants {
  
    // master-> Follower_> GEARATIO-> STATOR-> SUPPLY
    public static final class shooterConstants {
      public static final double Gear_Ratio = 2.0;
      public static final double STATOR_CURRENT_LIMIT = 30.0;
      public static final double PEAK_CURRENT_LIMIT = 60.0;
        }

    public static final class FeederConstants {
        public static final int DIO_EMPTY_SENSOR_CHANNEL = 0;
        public static final double GEAR_RATIO = 3.0 / 5.0;
        public static final double STATOR_CURRENT_LIMIT = 30.0;
        public static final double PEAK_CURRENT_LIMIT = 60.0;
    }


    public static final class HoodConstants {
        public static final double GEAR_RATIO = 1.0 / 16.0; 
        public static final double STATOR_CURRENT_LIMIT = 15.0;
        public static final double PEAK_CURRENT_LIMIT = 30.0;
      }

    
  }

