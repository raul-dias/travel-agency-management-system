package com.raul.nymble.controller;

import com.raul.nymble.model.Activity;
import com.raul.nymble.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityController {

    @Autowired
    ActivityService activityService;
    @GetMapping("/activity")
    public List<Activity> getActivity() {
        return activityService.findAll();
    }

    @GetMapping("/activity/{id}")
    public Activity getActivity(@PathVariable Long id){
        return activityService.findById(id);
    }

    @GetMapping("/activity/name/{name}")
    public List<Activity> getActivityByName(@PathVariable String name){
        return activityService.findByName(name);
    }

    @GetMapping("/activity/destination/{name}")
    public List<Activity> getActivityByDestination(@PathVariable String name){
        return activityService.findByDestination(name);
    }

    @PostMapping("/activity")
    public void saveActivity(@RequestBody Activity activity){
        activityService.save(new Activity(activity.getName(), activity.getDescription(), activity.getCost(), activity.getCapasity(), activity.getDestinationId()));
    }

    @PutMapping("/activity/{id}")
    public void updateActivity(@PathVariable Long id, @RequestBody Activity activity){
        activity.setId(id);
        activityService.save(activity);
    }

    @DeleteMapping("/activity/{id}")
    public void deleteActivity(@PathVariable Long id){
        activityService.delete(id);
    }

    @PostMapping("/activity/{id}/enroll")
    public void addPassengerToActivity(@PathVariable Long id, @RequestBody Long passengerId){
        activityService.addPassengerToActivity(id, passengerId);
    }
}

