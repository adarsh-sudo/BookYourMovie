package com.bookyourshow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookyourshow.dto.BookYourShowDTO;
import com.bookyourshow.entity.BookYourShow;
import com.bookyourshow.exception.BookYourShowException;
import com.bookyourshow.repository.BookYourShowRepository;
import com.bookyourshow.validator.BookYourShowValidator;

@Service("movieBookingService")
@Transactional
public class BookYourShowServiceImplementation implements BookYourShowService {

	@Autowired
	private BookYourShowRepository bookYourShowRepository;
	
	
	@Override
	public Double calculateTotalBookingAmount(Integer noOfSeats , String seatType)
	
	
	{
		Double bookingAmount = 0.0;
		
		if(seatType.equals("Silver"))
			bookingAmount = 100.0 * noOfSeats;
		else if(seatType.equals("Gold"))
			bookingAmount = 300.0 * noOfSeats;
		
		
		
		return bookingAmount;
		
	}
	
	@Override
	public BookYourShowDTO bookMovie(BookYourShowDTO bookYourShowDTO) throws BookYourShowException
	{
		BookYourShowValidator.validate(bookYourShowDTO);
		List<BookYourShow> bookYourShows = bookYourShowRepository.getBookingDetails(bookYourShowDTO.getCustomerPhoneNumber() , bookYourShowDTO.getShowDate());
		
		if(Boolean.FALSE.equals(bookYourShows.isEmpty()))
			throw new BookYourShowException("BookYourShow.BOOKING_EXISTS");
		
		BookYourShow bookYourShow = new BookYourShowDTO().prepareEntity(bookYourShowDTO);
		
		bookYourShow.setBookingAmount(calculateTotalBookingAmount( bookYourShowDTO.getNoOfSeats() ,  bookYourShowDTO.getSeatType()));
		
		Integer id = bookYourShowRepository.save(bookYourShow).getBookingId();
		
		bookYourShowDTO.setBookingId(id);
		
		bookYourShowDTO.setBookingAmount(bookYourShow.getBookingAmount());
		
		
		
		return bookYourShowDTO;
		
	}
	
	@Override
	public List<BookYourShowDTO> getBookingBySeatType(String seatType) throws BookYourShowException
	{
		
		List<BookYourShow> bookYourShow = bookYourShowRepository.findBySeatType(seatType);
		if(bookYourShow.isEmpty()) {
			throw new BookYourShowException("BookYourShow.NO_BOOKING");
		}
		
		List<BookYourShowDTO> bookYourShowDTOs = new ArrayList<>();
		
		bookYourShow.stream().forEach(x-> bookYourShowDTOs.add(new BookYourShowDTO().prepareDTO(x)));
		
		
		return bookYourShowDTOs;
		
	}
	
	
}
