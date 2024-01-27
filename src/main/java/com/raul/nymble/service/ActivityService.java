package com.raul.nymble.service;

import com.raul.nymble.model.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> findAll();

    List<Activity> findByDestination(String name);

    List<Activity> findByName(String name);

    Activity findById(Long id);

    Activity save(Activity activity);

    void delete(Long id);
}
