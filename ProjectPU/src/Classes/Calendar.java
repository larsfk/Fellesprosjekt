package Classes;

import java.util.ArrayList;

public class Calendar {
    public ArrayList<Appointment> appointments;
    
    public Calendar(){
        appointments = new ArrayList<Appointment>();
    }
    
    public void hideAppointment(){
        //BLABLA
   }
    
    public void deleteAppointment(Appointment appoint){
        appointments.remove(appoint);
    }
    
    public String test(){
        return "HEI!";
    }

}