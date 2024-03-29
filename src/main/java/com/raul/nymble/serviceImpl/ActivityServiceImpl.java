package com.raul.nymble.serviceImpl;

import com.raul.nymble.model.Activity;
import com.raul.nymble.model.Destination;
import com.raul.nymble.model.Enrollment;
import com.raul.nymble.repository.ActivityRepository;
import com.raul.nymble.repository.DestinationRepository;
import com.raul.nymble.repository.EnrollmentRepository;
import com.raul.nymble.service.ActivityService;
import com.raul.nymble.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public List<Activity> findByDestination(String name) {
        List<Activity> activities = new ArrayList<>();
        List<Destination> destinations = destinationRepository.findByNameLike("%"+name+"%");
        destinations.forEach(destination -> activities.addAll(activityRepository.findByDestinationId(destination.getId())));
        return activities;
    }

    @Override
    public List<Activity> findByName(String name) {
        return activityRepository.findByNameLike("%"+name+"%");
    }

    @Override
    public Activity findById(Long id) {
        return activityRepository.findById(id).get();
    }

    @Override
    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void delete(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public void addPassengerToActivity(Long id, Long passengerId) {
        Activity activity = findById(id);
        if (activity.getBookedCapasity()<activity.getCapasity());{
            activity.setBookedCapasity(activity.getBookedCapasity()+1);
            save(activity);
            Boolean valid = passengerService.validatePassengerForActivity(passengerId, activity.getCost(), activity.getDestinationId());
            if (valid){
                enrollmentRepository.save(new Enrollment(passengerId,id));
            }
            else {
                activity.setBookedCapasity(activity.getBookedCapasity()+1);
                save(activity);
            }
        }
    }
}
