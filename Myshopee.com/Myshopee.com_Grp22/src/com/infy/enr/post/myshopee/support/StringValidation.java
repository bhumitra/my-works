/*=============================================================================
  Copyright © 2006 Infosys Technologies Limited 
  StringValidation	1.0 	03-1-2007
  All rights Reserved. The contents of this file are a part of the Infosys 
  Reusable Assets Repository. You get a nonexclusive, limited right to use 
  this file for the purpose for which it is delivered, solely in accordance 
  with the written agreement we have with you in respect of the same.
=============================================================================*/

/**
 * Classname : StringValidation
 * Functionality Description : This class provide methods for string validation
 *
 * Known bugs : None
 * Modification Log
 * Date				Author				Description
 * -----------------------------------------------------------------------------
 * Mar 21, 2006		Vimilsaju_Varghese  Created
 * Oct 31, 2006		Dhananjay_Singh		Modified
 * Nov 30, 2006     Deeksha C Shetty    Modified
 * June 27,2007     maryarpana_f        Modified
 * Release no :
 */

package com.infy.enr.post.myshopee.support;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

import com.infy.enr.post.myshopee.exceptions.InvalidInputException;


/**
 * this class provide general purpose method for string validation
 *
 * @author Dhananjay Singh,MaryArpana_f
 * @version 1.1
 */
@SuppressWarnings("unused")
public class StringValidation {

	/**
	 * This method returns true if the string strIn is null, empty or contains
	 * only a sequence of spaces else return false.
	 *
	 * @param strIn Input string
	 * @return boolean
	 */
	
	public static boolean isEmptyString(String strIn) {
	    return (strIn == null) || (strIn.trim().length() == 0);
	}
	
	
	/**
	 * This method checks Alpha Numeric charactor's if the string
	 * have both alpah numeric it return true else it return false.
	 * Currently it is applicable only for ASCII.
	 * It may not work for EBCDIC .Segement range in EBCDIC for
	 * comparision on lower, upper case separately (a-i,j-r,s-z,0-9).
	 * @param strAlphaNum input string
	 * @return boolean
	 */
	
	public static boolean isAlphaNumeric(String strAlphaNum) {
	        boolean isChar=false;
	        boolean isNumb=false;
	        boolean isAlphaNum = false;
	        char chTemp = '\u0000';
	        char[] charArray = strAlphaNum.toCharArray();
	
	        for (int i = 0; i < charArray.length; i++) {
	            chTemp = charArray[i];
	            if (((chTemp >= 'a') && (chTemp <= 'z')) ||((chTemp >= 'A') &&
	                (chTemp <= 'Z'))){
	               isChar=true;
	               
	
	               continue;
	            }else if ((chTemp >= '0') && (chTemp <= '9')){
	                isNumb=true;
	                continue;
	            }else{
					isChar=false;
					isNumb=false;	
				}	
	        }	
	        if(isChar && isNumb)
	            {
	            isAlphaNum = true;
	            }
	        return isAlphaNum;	
	    }

    /**
     * This method reads the string strIn and check for special character
     * passed in char[] charSplChars.
     * if any one of the character in the input array is present it returns true
     * when strIn or charSplChars is null it returns false
     * @param charSplChars special character array
     * @param strIn input string
     * @return boolean
     */

    public static boolean isSpecialCharPresent(char[] charSplChars,
        String strIn) {
       boolean isSplChar = false;
       if(strIn!=null && charSplChars != null){
        for (int iCounter = 0; iCounter < charSplChars.length; iCounter++) {
            if (strIn.indexOf(charSplChars[iCounter]) != -1) {
                isSplChar = true;               
                break;
               
            }
        }
        
       }
        return isSplChar;
    }
   
    /**
     *This function checks if the given string is a number or not.
     *@param strInput - The string that has to be validated.
     *@return boolean indicating if String is present
     */
    
    public static boolean isNumeric(String strInput)
    {
    	
    	boolean bResult = false;
        try
        {
            if(strInput != null)
            {
            	strInput = strInput.trim();
             
               Double.parseDouble(strInput);
                bResult =  true;
               
            }
            else
            {
            	bResult = false;
            }
        }
        catch(NumberFormatException nfe)
        {
        	bResult = false;
        }
        
        return bResult;
    }    

    /**
     *This function checks if the given string consists of only
     *zeros.
     *@param strInput - The string that needs to be validated.
     *@return boolean indicating if the number has all zeros
     */
    
    public static boolean isAllZeroes(String strInput)
    {
    	boolean bResult = false;
    	int iNumber = 0;
        try
        {
            if(strInput != null)
            {
            	strInput = strInput.trim();
                iNumber = Integer.parseInt(strInput);
                if(iNumber == 0)
                {
                	bResult = true;
                }
                else
                {
                	bResult = false;
                }
            }
           
        }
        catch(NumberFormatException nfe)
        {
        	bResult = false;
        }
        
        return bResult;
    }
    
    /**
     *This function checks if the length of the given string is
     *atleast equal to the given length. Returns false if
     *the String does not meet the minimum length criteria.
     *@param strInput - The string that has to be checked.
     *@param iMinLength - The minimum required length.
     *@return boolean indicating whether input string satisfies
     * criteria of length.
     */
    
    public static boolean checkMinLength(String strInput, int iMinLength)
    {
    	boolean bResult = false; 
        if(strInput != null)
        {
            final int ilength = strInput.trim().length();
            if(ilength >= iMinLength)
            {
            	bResult = true;
            }
            
        }
         return bResult;
    }
    
    /**
     *This function checks if the length of the given string 
     *is less than or equal to the given length.
     *Returns true if the String satisfies the maximum criteria.
     *@param strInput - The string that has to be checked.
     *@param iMaxLength - The required length.
     *@return boolean - Returns true if the string length exceeds the given
     *length.
     */
    
    public static boolean checkMaxLength(String strInput, int iMaxLength)
    {
    	boolean bResult = false;
        if(strInput != null)
        {
            final int ilength = strInput.trim().length();
            if(ilength <= iMaxLength)
            {
            	bResult = true;
            }            
        }
        return bResult;
    }
    
    /**
     *This function checks if the length of the given string is
     *equal to the specified length. If not it returns false
     *@param strInput - The string that has to be checked.
     *@param iReqLength - The required length.
     *@return boolean - Returns true if the string length is equal to the given
     *length.
     */
    
    public static boolean checkLength(String strInput, int iReqLength)
    {
    	boolean bResult = false ;
        if(strInput != null)
        {
            final int ilength = strInput.trim().length();
            if(ilength == iReqLength)
            {
            	bResult = true;
            }
        }
       return bResult;
    }    
    
    /**
     *This function checks if the given string contains only
     *alphabets or Space.
     *@param strInput - The string that has to be validated.
     *@return boolean - returns true if input comtains only 
     *alphabets or space else false
     */
    
    public static boolean isValidString(String strInput)
    {
        char cValue = 0;
        int iNumericValue = 0;
        int iLength = 0;
        boolean bIsValid = false;       
        
        if(!StringValidation.isEmptyString(strInput))
        {
        	iLength = strInput.trim().length();
            for(int i=0; i<iLength; i++)
            {
                cValue = strInput.charAt(i);
                iNumericValue = cValue;                               
                
                /**
                 *ASCII character:
                 * 65-90 represent A-Z
                 * 97-122 represent a-z
                 * 32- space
                 */
                
                if (((iNumericValue >= 65 &&
                		iNumericValue <= 90) || (iNumericValue >= 97
                				&& iNumericValue <= 122)
                					|| iNumericValue == 32)) {
					bIsValid = true;
                }
				else{
					bIsValid=false;
					break;
				}
			}
        }
 		
 		return bIsValid;
    }
    
    /**
     *This function checks if the given string contains alphabets
     * along with the permitted special characters or numbers.
     *A valid string satisfies the following conditions:
     *  1)Contains alphabets or space.
     *  2)It can contain any character present in the permitted characters  
     *  	array.
     *@param strInputString  The string that has to be validated.
     *@param arrPermittedCharacters Array of permitted special characters
     *		 or numbers.
     *@return boolean 
     */
    
    public static boolean isValidString(String strInputString,
			char[] arrPermittedCharacters) {
    	char cValue = 0;
        int iNumericRepresentation = 0;
        int iLength = 0;
        boolean bIsValid = false;
        boolean bIsPermitted = false;
        final boolean bCheck = (arrPermittedCharacters!= null) &&
        (arrPermittedCharacters.length > 0);

        if((strInputString != null) &&
        	(strInputString.trim().length() > 0))
        {
        	iLength = strInputString.trim().length();
            for(int i=0; i<iLength; i++)
            {
                cValue = strInputString.charAt(i);
                iNumericRepresentation = cValue;

                /**
				 * ASCII character: 65-90 represent A-Z 97-122 represent a-z
				 */

				if (((iNumericRepresentation >= 65 &&
						iNumericRepresentation <= 90)
						|| (iNumericRepresentation >= 97
								&& iNumericRepresentation <= 122) || 
								iNumericRepresentation == 32)) {
					bIsValid = true;
				}							
				else if(bCheck){
					
					// Checking for the permitted special characters or numbers
					
					bIsPermitted = StringValidation.checkPermittedCharacters(cValue,
							arrPermittedCharacters);
					if (bIsPermitted) {
						bIsValid = true;
						break;
					}
					else{
						bIsValid = false;
					}
                }
				else{
					bIsValid = true;
					break;
				}       
            }
        }
        return bIsValid;

    }
    
    /**
	 * This function checks if the given string is a valid amount. A valid
	 * amount satisfies the following conditions: 1)Can contain decimal values.
	 * 2)It should not contain any other special character(apart from decimal)
	 * or alphabet.
	 * 
	 * @param strInput - The string that has to be checked.
	 * @return boolean - Returns true if the string contains only alphabets or
	 *         alphabets with permitted special characters or numbers.
     * @throws InvalidInputException 
	 */
    
    public static boolean isValidAmount(String strInput) throws InvalidInputException
    {
    	boolean bValid = false;
    	//final double dNumber;
        if((strInput != null) && (strInput.trim().length() > 0))
        {        	
            try
            {
            	final double dNumber = Double.parseDouble(strInput);
            	if(dNumber > 0){
            		bValid =  true;
            		
            		
            	}
            	else{
            		throw new InvalidInputException("Enter positive number");
            	}
            }
            catch(NumberFormatException nfe)
            {
            	
            	bValid = false;
            }
            
            
        }
         return bValid;
    }
    
    /**
     *This function checks if the given string is a valid amount.
     *A valid amount satisfies the following conditions:
     *1)Can contain decimal values.
     *2)Can preceed with the given currency symbol.
     *3)It should not contain any other special character(apart from currency
     * symbol and decimal) or alphabet.
     *
     *@param strInput - The string that has to be checked.
     *@param strSymbol - The symbol for the currency.
     *@return boolean 
     * @throws InvalidInputException 
     */
    
    public static boolean isValidAmount(String strInput, String strSymbol) throws InvalidInputException
    {
        String strCurrency = null;
        boolean bIsValid = false;
        
        if((strInput != null) && 
        	(strInput.trim().length() > 0) &&
            (strSymbol != null) && 
            (strSymbol.trim().length() > 0))
        {
        	strInput = strInput.trim();
            
            //Truncating the currency symbol part from the amount if it matches 
            //the given symbol.
        	
            strCurrency = new String(strInput.substring(0,strSymbol.length()));
            if(strSymbol.equals(strCurrency))
            {
            	strInput = strInput.substring(strSymbol.length());
            }
            
            bIsValid = StringValidation.isValidAmount(strInput);
        }
       return bIsValid;
    }    
    
    
  
    /**
	 * Method to check that the string contains only characters and space and no
	 * other special characters or numbers 
	 * @param string - The input string that is to be validated for characters 
	 *                 only.
	 * @return boolean - True is only characters are present else false.
     * @throws InvalidInputException if the input is invalid
	 */
    
	public static boolean isCharacters(String string) 
	throws InvalidInputException {
		int intVal= 0 ;
		if (string == null || string.equals("")) {
			throw new InvalidInputException("Enter Valid Input String");
		}
		for (int i = 0; i < string.length(); i++) {
			intVal = (int) string.charAt(i);
			if (intVal != 32) {
				if (intVal < 65
					|| (intVal > 90 && intVal < 97)
					|| intVal > 122) {
					return false;
				}
			}
		}
		return true;
	} //end of method isCharacters

	
		
	
	/**
	 * Method to check that string contains numbers and decimal only and no other 
	 * characters
	 * @param number - The input string that is to be validated for numbers 
	 * 				  and decimal only.
	 * @return boolean - True if the input string contains only numbers 
	 *                   else false.
	 */
	
	public static boolean isNumber(String number) {
		boolean isDecimal = true;
		int intVal = 0;
		
		if (number == null || number.equals("")) {
			return false;
		}
		else{
		for (int i = 0; i < number.length(); i++) {
			 intVal = (int) number.charAt(i);
			if (intVal == 46) {
				if (isDecimal == true) {
					isDecimal = true;
				} else {
					return false;
				}
			} else if (intVal < 48 || intVal > 57) {
				return false;
			}
		}
		
		return true;
		}
	}//end of method isNumber
	/**
	 * Method to check that string contains numbers only and no other 
	 * characters
	 * @param number - The input string that is to be validated for numbers
	 * 				   only.
	 * @return boolean - True if the input string contains only numbers 
	 *                   else false.
	 */
	public static boolean isNaturalNumber(String number) {
		int intVal = 0;
		if (number == null ||number.equals("") ) {
			return false;
		}		

		for (int i = 0; i < number.length(); i++) {
			 intVal = (int) number.charAt(i);
			if (intVal < 48 || intVal > 57) {
				return false;
			}
		}
		return true;

	} //end of method isNaturalNumber
	

	
	
	  /**
     * checkPermittedCharacters is a method which checks if the character is
     *		present in character Array
     * @param cValue the character
     * @param arrPermittedCharacters array of characters
     * @return boolean indicating true or false
     */
   
   private static boolean checkPermittedCharacters(char cValue,
		   char[] arrPermittedCharacters){
   	
   	boolean bIsPermitted = false;
   	for (int j = 0; j < arrPermittedCharacters.length; j++) {
			if (cValue == arrPermittedCharacters[j]) {
				bIsPermitted = true;
				break;
			}
		}
   	return bIsPermitted;
   }
  
}
