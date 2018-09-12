package gihansandaru.user.architecturecomponentssample.Daos;

import android.app.Person;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import gihansandaru.user.architecturecomponentssample.models.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("Delete from users")
    void deleteAll();

    @Query("select * from users where name = :name")
    User selectByName(String name);

    @Query("select * from users")
    LiveData<List<User >> selectAllUsers();
}
