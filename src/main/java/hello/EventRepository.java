package hello;        import org.springframework.data.jpa.repository.Modifying;        import org.springframework.data.jpa.repository.Query;        import org.springframework.data.repository.CrudRepository;        import hello.Event;        import org.springframework.data.repository.query.Param;        import org.springframework.transaction.annotation.Transactional;        import org.springframework.web.bind.annotation.CrossOrigin;        import java.sql.Date;        import java.util.List;// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository// CRUD refers Create, Read, Update, Delete@CrossOrigin(origins = "file:///C:/Users/hakan/Desktop/Database/complete/WebsiteText.html")public interface EventRepository extends CrudRepository<Event, String> {        @Query("SELECT Capacity FROM Event e WHERE e.Name = :EventName")        public Integer findCapacityByEventName(@Param("EventName") String EventName);	@Query("SELECT c FROM Event c WHERE c.Name =:EventName")	public Event findByEventName(@Param("EventName") String EventName);        @Query("SELECT seatsLeft FROM Event e WHERE e.Name = :EventName")        public Integer findSeatsLeftByEventName(@Param("EventName") String EventName);        @Query("SELECT eventDate FROM Event e WHERE e.Name = :EventName")        public Date findDateByEventName(@Param("EventName") String EventName);	@Query("SELECT e FROM Event e WHERE e.location = :Location")	public List<Event> findByLocation(@Param("Location") String Location);        @Modifying        @Transactional        @Query("UPDATE Event e SET seatsLeft = :seatsLeft WHERE e.Name =:EventName")        public void setSeatsLeft(@Param("EventName") String EventName, @Param("seatsLeft") Integer seatsLeft);}