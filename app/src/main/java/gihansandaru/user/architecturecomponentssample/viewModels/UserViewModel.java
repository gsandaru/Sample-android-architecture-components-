package gihansandaru.user.architecturecomponentssample.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import gihansandaru.user.architecturecomponentssample.Repos.UserRepository;
import gihansandaru.user.architecturecomponentssample.models.User;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> listLiveData;

    public UserViewModel (Application application) {
        super(application);
        userRepository = new UserRepository(application);
        listLiveData = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return listLiveData;
    }

    public void insert(User user) { userRepository.insert(user); }
}
