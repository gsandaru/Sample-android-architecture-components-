package gihansandaru.user.architecturecomponentssample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import gihansandaru.user.architecturecomponentssample.models.User;
import gihansandaru.user.architecturecomponentssample.viewModels.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UserAdapter adapter = new UserAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> users) {
                adapter.setWords(users);
            }
        });
    }
}
