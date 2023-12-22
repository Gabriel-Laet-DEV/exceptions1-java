package model.entities.exception;

public class DomainException extends Exception{

    private String msg;

    public DomainException(String msg){
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }

}
