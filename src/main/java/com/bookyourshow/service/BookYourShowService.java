package com.bookyourshow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookyourshow.dto.BookYourShowDTO;
import com.bookyourshow.exception.BookYourShowException;

@Service
public interface BookYourShowService {
	
	public BookYourShowDTO bookMovie(BookYourShowDTO bookYourShowDTO) throws BookYourShowException;
	public List<BookYourShowDTO> getBookingBySeatType(String seatType) throws BookYourShowException;
	public Double calculateTotalBookingAmount(Integer noOfSeats, String seatType);

}
