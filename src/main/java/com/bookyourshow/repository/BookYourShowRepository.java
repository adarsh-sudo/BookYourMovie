package com.bookyourshow.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookyourshow.entity.BookYourShow;

@Repository
public interface BookYourShowRepository extends CrudRepository<BookYourShow, Integer>{
	
	
	  List<BookYourShow> findBySeatType(String seatType);
	  
	  
	  @Query("select m from BookYourShow m where customerPhoneNumber=?1 and showDate=?2")
	  List<BookYourShow> getBookingDetails(Long customerPhoneNumber ,  LocalDate showDate);

}
