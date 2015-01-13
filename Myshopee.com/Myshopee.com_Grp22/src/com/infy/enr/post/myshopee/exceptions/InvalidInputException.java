/**
 * Classname : InvalidInputException
 * Functionality Description : This class is for user defined exception
 * Known bugs : None
 * Modification Log
 * Date				Author							Description
 * -----------------------------------------------------------------------------
 * Oct 7, 2006		Dhananjay_Singh		Created
 *
 * Release no : 1.0
 */

package com.infy.enr.post.myshopee.exceptions;

/**
 * This class is for user defined exception
 *
 * @author Dhananjay Singh
 * @version 1.0
 */
@SuppressWarnings("serial")
public class InvalidInputException extends Exception{

    /**
     *This method takes input error message and pass it to super. 
     * @param strMessage Error message
     * @return strMessage Error Message to be displayed
     * @throws None  No exception is thrown
     */
    
    public InvalidInputException(String strMessage){
        super(strMessage);
    }

}



