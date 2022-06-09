package by.webproject.forum.exception;

import java.sql.SQLException;

public class DaoException extends Exception{
    public DaoException(SQLException e){

    }
    public DaoException(String message){
        super(message);
    }
    public DaoException(String massage,Throwable cause){
        super(massage,cause);
    }
}
