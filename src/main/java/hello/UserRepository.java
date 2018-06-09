package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import hello.User;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, String> {

    @Query("SELECT password FROM User e WHERE e.email = :email")
    public String findPasswordByEmail(@Param("email") String Email);

    @Query("SELECT email FROM User e WHERE e.email = :email")
    public String emailCheck(@Param("email") String Email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findUser(@Param("email") String Email);
}
