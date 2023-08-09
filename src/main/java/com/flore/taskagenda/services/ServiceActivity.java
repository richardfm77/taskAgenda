package com.flore.taskagenda.services;


import com.flore.taskagenda.models.Activity;
import com.flore.taskagenda.repositories.RepositoryActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceActivity {

    @Autowired
    private RepositoryActivity repositoryActivity;

    public List<Activity> getActivities(int iduser) {
        List<Activity> activities = (List<Activity>) repositoryActivity.findAll();
        List<Activity> activitiesUser = new ArrayList<>();
        for (Activity activity : activities) {
            if(activity.getIduser() == iduser)
                activitiesUser.add(activity);
        }
        return activitiesUser;
    }

    public void postActivity(Activity activity) {
        repositoryActivity.save(activity);
    }

    public  void deleteActivity(int id) {
        repositoryActivity.deleteById(id);
    }

}
