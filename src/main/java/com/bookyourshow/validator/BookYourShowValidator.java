package com.bookyourshow.validator;

import com.bookyourshow.dto.BookYourShowDTO;
import com.bookyourshow.exception.BookYourShowException;

public class BookYourShowValidator {

	
	private BookYourShowValidator() {
	}


	public static void validate(BookYourShowDTO bookYourShowDTO) throws BookYourShowException
	{
		if(Boolean.FALSE.equals(isValidPaymentType(bookYourShowDTO.getPaymentType())))
			throw new BookYourShowException("Validator.INVALID_PAYMENT_TYPE");
		if(Boolean.FALSE.equals(isValidCustomerPhoneNumber(bookYourShowDTO.getCustomerPhoneNumber())))
			throw new BookYourShowException("Validator.INVALID_CUSTOMER_PHONE_NUMBER");
	}
	
	
	public static Boolean isValidPaymentType(String paymentType)
	{
	     if(paymentType.equals("card") || paymentType.equals("UPI"))
	    	 return true;
	     return false;
	}
	
	public static Boolean isValidCustomerPhoneNumber(Long customerPhoneNumber)
	{
		String s = customerPhoneNumber.toString();
		if(s.matches("[3-9][\\d]{9}"))
		   return true;
		return false;
		
	}
	
	
	
	
	
}
