package org.udg.pds.todoandroid.fragment;

//import android.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import org.udg.pds.todoandroid.R;
import org.udg.pds.todoandroid.TodoApp;
import org.udg.pds.todoandroid.entity.Group;
import org.udg.pds.todoandroid.rest.TodoApi;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by imartin on 12/02/16.
 */
public class OwnedGroupsFragment extends Fragment {

    TodoApi mTodoService;

    RecyclerView mRecyclerView;
    private OWAdapter mAdapter;
    public OwnedGroupsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.fragment_owned_grups, container, false);
    }

    @Override
    public void onStart() {

        super.onStart();
        mTodoService = ((TodoApp) this.getActivity().getApplication()).getAPI();

        mRecyclerView = getView().findViewById(R.id.ownGroups_rv);
        mAdapter = new OWAdapter(this.getActivity().getApplication());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume ", "entroo?");
        this.updateGroupList();
    }

    public void showGroupList(List<Group> gl) {
        System.out.println("///////deokdoajdsoajdosajdosa////");
        mAdapter.clear();
        for (Group g : gl) {
            Log.d("afegeixo?", g.name);
            mAdapter.add(g);
        }
    }


    public void updateGroupList() {
        Log.d("UpdateGroup ", "entroo aqu?");
        Call<List<Group>> call = mTodoService.getOwnedGroups();
        call.enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                if (response.isSuccessful()) {
                    Log.d("eeeeeeeeeooooo", "res" +((response.body()).size()));
                    System.out.println("Grouppsssssss       aqui si que entroooo?????");
                    OwnedGroupsFragment.this.showGroupList(response.body());
                } else {
                    Log.d("OnResponse", "else ");
                    Toast.makeText(OwnedGroupsFragment.this.getContext(), "Error reading groups", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {
                Log.d("oonFailure", t.getMessage());
            }
        });
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;
        View view;

        GroupViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            description = itemView.findViewById(R.id.itemDescription);
            name = itemView.findViewById(R.id.itemName);
            Log.d("constrictor viewHolder ", description.toString());
        }
    }

    static class OWAdapter extends RecyclerView.Adapter<OwnedGroupsFragment.GroupViewHolder> {

        List<Group> list = new ArrayList<>();
        Context context;

        public OWAdapter(Context context) {
            this.context = context;
        }

        @Override
        public OwnedGroupsFragment.GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_layout, parent, false);
            Log.d("OncreateViewHolder ", "da! "+R.layout.group_layout);
            OwnedGroupsFragment.GroupViewHolder holder = new OwnedGroupsFragment.GroupViewHolder(v);

            return holder;
        }

        @Override
        public void onBindViewHolder(OwnedGroupsFragment.GroupViewHolder holder, final int position) {
            holder.description.setText(list.get(position).description);
            holder.name.setText(list.get(position).name);
            Log.d("onbind", "name "+ list.get(position).name);

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, String.format("Hey, I'm item %1d", position), duration);
                    toast.show();
                }
            });

            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, "adios!", duration);
                    toast.show();
                }
            });

            animate(holder);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {

            super.onAttachedToRecyclerView(recyclerView);
        }

        // Insert a new item to the RecyclerView
        public void insert(int position, Group data) {
            list.add(position, data);
            notifyItemInserted(position);
        }

        // Remove a RecyclerView item containing the Data object
        public void remove(Group data) {
            int position = list.indexOf(data);
            list.remove(position);
            notifyItemRemoved(position);
        }

        public void animate(RecyclerView.ViewHolder viewHolder) {
            final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate_overshoot_interpolator);
            viewHolder.itemView.setAnimation(animAnticipateOvershoot);
        }

        public void add(Group t) {
            list.add(t);
            Log.d("dins un add", t.name);
            this.notifyItemInserted(list.size() - 1);
        }

        public void clear() {
            int size = list.size();
            list.clear();
            this.notifyItemRangeRemoved(0, size);
        }
    }
}
