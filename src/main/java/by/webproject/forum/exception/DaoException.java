package by.webproject.forum.exception;

public class DaoException extends Exception{
    public DaoException(){

    }
    public DaoException(String message){
        super(message);
    }
    public DaoException(String massage,Throwable cause){
        super(massage,cause);
    }
}
