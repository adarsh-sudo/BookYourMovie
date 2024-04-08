package com.bookyourshow.dto;

import java.time.LocalDate;

import com.bookyourshow.entity.BookYourShow;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BookYourShowDTO {
	
	
	
	private Integer bookingId;
	
	@NotNull(message = "{bookmovie.moviename.notpresent}")
	private String movieName;
	
	@NotNull(message = "{bookmovie.seattype.notpresent}")
	@Pattern(regexp = "(Silver|Gold)" , message = "{bookmovie.seattype.invalid}")
	private String seatType;
	
	@NotNull(message = "{bookmovie.showdate.notpresent}")
	@FutureOrPresent(message = "{bookmovie.showdate.invalid}")
	private LocalDate showDate;
	
	@NotNull(message = "{bookmovie.noofseats.notpresent}")
	@Max(value = 10 , message = "{bookmovie.noofseats.invalid}")
	private Integer noOfSeats;
	private Double bookingAmount;
	private String paymentType;
	private Long customerPhoneNumber;
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String screenName) {
		this.seatType = screenName;
	}
	public LocalDate getShowDate() {
		return showDate;
	}
	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}
	public Integer getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public Double getBookingAmount() {
		return bookingAmount;
	}
	public void setBookingAmount(Double bookingAmount) {
		this.bookingAmount = bookingAmount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Long getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	public void setCustomerPhoneNumber(Long customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	
	
	public BookYourShowDTO prepareDTO(BookYourShow bookYourShow)
	{
		
		BookYourShowDTO bookYourShowDTO = new BookYourShowDTO();
		bookYourShowDTO.setBookingId(bookYourShow.getBookingId());
		bookYourShowDTO.setCustomerPhoneNumber(bookYourShow.getCustomerPhoneNumber());
		bookYourShowDTO.setMovieName(bookYourShow.getMovieName());
		bookYourShowDTO.setNoOfSeats(bookYourShow.getNoOfSeats());
		bookYourShowDTO.setPaymentType(bookYourShow.getPaymentType());
		bookYourShowDTO.setSeatType(bookYourShow.getSeatType());
		bookYourShowDTO.setShowDate(bookYourShow.getShowDate());
		bookYourShowDTO.setBookingAmount(bookYourShow.getBookingAmount());
		
		
		return bookYourShowDTO;
		
		
	}
	
	public BookYourShow prepareEntity(BookYourShowDTO bookYourShowDTO)
	{
		
		BookYourShow bookYourShow = new BookYourShow();
		bookYourShow.setCustomerPhoneNumber(bookYourShowDTO.getCustomerPhoneNumber());
		bookYourShow.setMovieName(bookYourShowDTO.getMovieName());
		bookYourShow.setNoOfSeats(bookYourShowDTO.getNoOfSeats());
		bookYourShow.setPaymentType(bookYourShowDTO.getPaymentType());
		bookYourShow.setSeatType(bookYourShowDTO.getSeatType());
		bookYourShow.setShowDate(bookYourShowDTO.getShowDate());
		
		
		return bookYourShow;
		
		
	}

}
