

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.controller.MyController.UserLocation;

public class UserController {
    @RestController
public class UserController {
    @Autowired
    private UserLocationRepository userLocationRepository;

    @PostMapping("/create_data")
    public ResponseEntity<String> createTable() {
        userLocationRepository.createTable();
        return ResponseEntity.ok("Table created successfully.");
    }

    @PatchMapping("/update_data")
    public ResponseEntity<String> updateTable(@RequestBody UserLocation userLocation) {
        UserLocation existingUserLocation = userLocationRepository.findById(userLocation.getId())
            .orElseThrow(() -> new NotFoundException("User location not found"));
        
        existingUserLocation.setName(userLocation.getName());
        existingUserLocation.setLatitude(userLocation.getLatitude());
        existingUserLocation.setLongitude(userLocation.getLongitude());
        existingUserLocation.setExcluded(userLocation.isExcluded());

        userLocationRepository.save(existingUserLocation);
        return ResponseEntity.ok("User location updated successfully.");
    }

    @GetMapping("/get_users/{count}")
    public ResponseEntity<List<UserLocation>> getNearestUsers(@PathVariable int count) {
        List<UserLocation> nearestUsers = userLocationRepository.findNearestUsers(count);
        return ResponseEntity.ok(nearestUsers);
    }
}

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
}



