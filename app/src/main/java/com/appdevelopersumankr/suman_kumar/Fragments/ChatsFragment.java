package com.appdevelopersumankr.suman_kumar.Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appdevelopersumankr.suman_kumar.Adapter.UserAdapter;
import com.appdevelopersumankr.suman_kumar.Model.Chatlist;
import com.appdevelopersumankr.suman_kumar.Model.User;
import com.appdevelopersumankr.suman_kumar.Notifications.Token;
import com.appdevelopersumankr.suman_kumar.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ChatsFragment extends Fragment {

    private RecyclerView recyclerView;

    private UserAdapter userAdapter;
    private List<User> mUsers;

    FirebaseUser fuser;
    DatabaseReference reference;

    private List<Chatlist> usersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chats_fragment, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager=new LinearLayoutManager ( getActivity ());
        recyclerView.setLayoutManager ( manager );
        recyclerView.setHasFixedSize(true);
//        adapter = new MyAdapter();
//        recyclerView.setAdapter(adapter);
        userAdapter=new UserAdapter ( getActivity (),mUsers,false);
        recyclerView.setAdapter ( userAdapter );

       // recyclerView.setLayoutManager(new LinearLayoutManager (getActivity ()));

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        usersList = new ArrayList<> ();

        reference = FirebaseDatabase.getInstance().getReference("Chatlist").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chatlist chatlist = snapshot.getValue(Chatlist.class);
                    usersList.add(chatlist);
                }

                chatList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //updateToken( FirebaseInstanceIdService.getInstance().getToken());
       // updateToken ( FirebaseMessagingService.token );


        return view;
    }

    private void updateToken(String token){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        reference.child(fuser.getUid()).setValue(token1);
    }

    private void chatList() {
        mUsers = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue( User.class);
                    for (Chatlist chatlist : usersList){
                        if (user.getId().equals(chatlist.getId())){
                            mUsers.add(user);
                        }
                    }
                }
                //below change
                userAdapter = new UserAdapter (getActivity (), mUsers, true);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}