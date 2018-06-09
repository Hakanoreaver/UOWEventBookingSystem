package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import hello.Booking;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Arrays;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@CrossOrigin(origins = "file:///C:/Users/hakan/Desktop/Database/complete/WebsiteText.html")
@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking b WHERE b.email = :Email")
    public List<Booking> findByEmail(@Param("Email") String Email);

    @Query("SELECT b FROM Booking b WHERE b.email = :Email AND b.EventName = :EventName")
    public List<Booking> findByEmailsandBookingName(@Param("Email") String Email, @Param("EventName") 	String EventName);

@Query("SELECT EventName FROM Booking b WHERE b.id = :id")
public String getEventById(@Param("id")int id);
	
    @Query("SELECT id FROM Booking ORDER BY id ASC")
    public Integer[] findIds();

	@Query("SELECT b FROM Booking b WHERE b.id = :id")
	public Booking findById(@Param("id") int id);
}

