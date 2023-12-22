package model.entities;

import model.entities.exception.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(){

    }

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
        if(checkIn.after(checkOut)){
        throw new DomainException("Data de check-in não pode ser depois data de checkout");
    }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }


    public Date getCheckOut() {
        return checkOut;
    }

    public long duration(){
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }


    public void updateDates(Date checkIn, Date checkOut) throws DomainException {
        Date now = new Date();
        if(checkIn.before(now) || checkOut.before(now)){
            throw new DomainException("Somente datas futuras");
        }else if(checkIn.after(checkOut)){
            throw new DomainException("Data checkin não pode ser depois data de checkout");
        }
        this.checkOut = checkOut;
        this.checkIn = checkIn;
    }

    @Override
    public String toString(){
        return "Room number: " + roomNumber + "\ncheck-in: " + sdf.format(checkIn) + "\ncheck-out: "
                + sdf.format(checkOut) + " - " + duration() + " nights.";
    }
}
