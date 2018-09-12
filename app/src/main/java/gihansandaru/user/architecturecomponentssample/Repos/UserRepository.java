package gihansandaru.user.architecturecomponentssample.Repos;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import gihansandaru.user.architecturecomponentssample.Daos.UserDao;
import gihansandaru.user.architecturecomponentssample.database.UserRoomDatabase;
import gihansandaru.user.architecturecomponentssample.models.User;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> listLiveData;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        listLiveData = userDao.selectAllUsers();
    }

    public LiveData<List<User>> getAllUsers (){
        return this.listLiveData;
    }

    public void insert (User user) {
        new insertAsyncTask(userDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
