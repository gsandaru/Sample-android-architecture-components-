package gihansandaru.user.architecturecomponentssample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gihansandaru.user.architecturecomponentssample.models.User;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    LayoutInflater mInflater;
    List<User> userList;

    public UserAdapter(Context context) { mInflater = LayoutInflater.from(context); };


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cell_recyclerview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.username.setText(user.getName());
    }


    void setWords(List<User> users){
        userList = users;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (userList != null)
            return userList.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.txtName);
        }
    }
}
