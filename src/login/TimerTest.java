package login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.util.Duration;

public class TimerTest {
	 
    public static void main(String[] args) {
    	
        Timer timer = new Timer();
        timer.schedule(new RepeatTask(timer, 2), 0, 2500); // REPETER 5 FOIS UNE TACHE à 2500ms d'intervalle
        
//        ZonedDateTime zonedDateTime = ZonedDateTime.now();
//        System.out.println(zonedDateTime);

        
        
        var currentTime = LocalTime.now();

        LocalTime before = LocalTime.parse("07:00");
        LocalTime after = LocalTime.parse("18:00");

        if (currentTime.isBefore(after) && currentTime.isAfter(before) ) {
          // grant access
        	System.out.println("Hello");

        } else {
        	System.out.println("NOPE");
        }

    }
 
    static class RepeatTask extends TimerTask { //UNE TACHE A REPETER
       
        private Timer timer;  // LE TIMER A ARRETER
        private int repeats;  // NOMBRE DE REPETITION
 
        public RepeatTask(Timer timer, int repeats) {
            this.timer = timer;
            this.repeats = repeats;
        }
 
        @Override   
        public void run() { // L'ACTION A EFFECTUEE A L'INTERVALLE REGULIER
            System.out.println(repeats);
            
            --repeats; // ON DECREMENTE LE NOMBRE DE REPETITIONS
            
            if (repeats == 0) // PLUS DE REPETITIONS, ON STOPELE TEMPS
                timer.cancel();
        }
    }
     
}
