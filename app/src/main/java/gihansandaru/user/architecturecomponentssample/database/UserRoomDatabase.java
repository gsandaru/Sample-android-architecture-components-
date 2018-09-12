package gihansandaru.user.architecturecomponentssample.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import gihansandaru.user.architecturecomponentssample.Daos.UserDao;
import gihansandaru.user.architecturecomponentssample.models.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserRoomDatabase extends android.arch.persistence.room.RoomDatabase {
    public abstract UserDao userDao();

    private static UserRoomDatabase INSTANCE;

    public static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "room_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

// When you modify the database schema, you'll need to update the version
// number and define how to handle migrations.

    private static UserRoomDatabase.Callback sRoomDatabaseCallback =
            new UserRoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mDao;

        PopulateDbAsync(UserRoomDatabase db) {
            mDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            User word = new User("Gihan","gihan@gmail.com","1234");
            mDao.insert(word);
            return null;
        }
    }
}