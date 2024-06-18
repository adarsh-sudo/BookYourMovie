package com.bookyourshow.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookyourshow.dto.BookYourShowDTO;
import com.bookyourshow.exception.BookYourShowException;
import com.bookyourshow.service.BookYourShowService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/api")
@Validated
public class BookYourShowAPI {
	
	/*** important ***/
	
	@Autowired
    private BookYourShowService bookYourShowService;
    
    /*** important ***/
	
	@PostMapping(value = "/movie")
	ResponseEntity<BookYourShowDTO> bookMovie(@RequestBody @Valid BookYourShowDTO bookYourShowDTO) throws BookYourShowException
	{
		
		
		
		return new ResponseEntity<>(bookYourShowService.bookMovie(bookYourShowDTO) , HttpStatus.CREATED);
		
	}
	
	@GetMapping(value = "/movie/{seatType}")
	ResponseEntity<List<BookYourShowDTO>> getBookingBySeatType(@PathVariable String seatType) throws BookYourShowException
	{
		return new ResponseEntity<>(bookYourShowService.getBookingBySeatType(seatType) , HttpStatus.OK);
		
	}
	
	
}
