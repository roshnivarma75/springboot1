import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.controller.MyController.UserLocation;

public interface UserLocationResitory extends JpaRepository<UserLocation, Long> {
    @Query(value = "SELECT * FROM user_location WHERE excluded = false ORDER BY POWER(latitude, 2) + POWER(longitude, 2) ASC LIMIT :count", nativeQuery = true)
    List<UserLocation> findNearestUsers(@Param("count") int count);
    @Modifying
    @Query(value = "CREATE TABLE user_location (id IDENTITY PRIMARY KEY, name VARCHAR(255), latitude DOUBLE, longitude DOUBLE, excluded BOOLEAN)", nativeQuery = true)
    void createTable();
} 
    

