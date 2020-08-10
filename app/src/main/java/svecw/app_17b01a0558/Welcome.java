package svecw.app_17b01a0558;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Welcome extends AppCompatActivity {
    RecyclerView mybloglist;
    DatabaseReference mydatabase,mydatabselikes;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthlistener;
    private  boolean myprocesslike=false;
    private  DatabaseReference mdatabseCurrentuser;
    private Query mqueryCuurentuser;
    String username;
    DatabaseReference x;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        mAuth=FirebaseAuth.getInstance();
        mydatabase= FirebaseDatabase.getInstance().getReference().child("blog");
        mydatabselikes=FirebaseDatabase.getInstance().getReference().child("Likes");
        x=FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        /*mdatabseCurrentuser=FirebaseDatabase.getInstance().getReference().child("blog");
        String currentuserid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mqueryCuurentuser=mdatabseCurrentuser.orderByChild("Uid").equalTo(currentuserid);*/

        mydatabselikes.keepSynced(true);
        mybloglist=findViewById(R.id.blog_list);
        mybloglist.setHasFixedSize(true);
        mybloglist.setLayoutManager(new LinearLayoutManager(this));
        //mAuth=FirebaseAuth.getInstance();
        mAuthlistener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    Intent x=new Intent(Welcome.this,MainActivity.class);
                    x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(x);
                }

            }
        };
        mAuth.addAuthStateListener(mAuthlistener);


    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog,Blogviewholder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter
                <Blog, Blogviewholder>(
                Blog.class,
                R.layout.blog_row,
                Blogviewholder.class,
                mydatabase) {
            @Override
            protected void populateViewHolder(final Blogviewholder blogviewholder, Blog blog, int i) {
              final String s=getRef(i).getKey().toString();

                blogviewholder.setTitle("Title:"+blog.getTitleval());
                blogviewholder.setDescription("Description:"+blog.gettitledes());
                blogviewholder.setUsername(username);
               // blogviewholder.setImage(getApplicationContext(),blog.getImage());
                //Log.v("image",blog.getImage());
                blogviewholder.setLikebutton(s);
                Log.v("welcome",s);
                blogviewholder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();I
                        Intent intent=new Intent(Welcome.this,BlogSingle.class);
                        intent.putExtra("blog_id",s);
                        startActivity(intent);
                    }
                });
                blogviewholder.fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myprocesslike=true;

                            mydatabselikes.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (myprocesslike) {
                                        if (dataSnapshot.child(s).hasChild(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                            mydatabselikes.child(s).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                                            myprocesslike = false;

                                        } else {
                                            mydatabselikes.child(s).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue("Randomvalue");
                                            myprocesslike = false;
                                            Toast.makeText(getApplicationContext(),dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getChildrenCount()+"liked",Toast.LENGTH_LONG).show();
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }


                });

            }
        };
        mybloglist.setAdapter(firebaseRecyclerAdapter);
        FirebaseRecyclerAdapter<Blog,Blogviewholder> firebaseRecyclerAdapter1=new FirebaseRecyclerAdapter<Blog, Blogviewholder>() {
            @Override
            protected void populateViewHolder(Blogviewholder blogviewholder, Blog blog, int i) {

            }
        };
    }

    static class Blogviewholder extends RecyclerView.ViewHolder
    {
        View myview;
        TextView posttitle;
        ImageView fav;
        DatabaseReference databaselikes;
        FirebaseAuth firebaseAuth;

        public Blogviewholder(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
            fav=(ImageView) myview.findViewById(R.id.likebutton);
            databaselikes=FirebaseDatabase.getInstance().getReference().child("Likes");
            databaselikes.keepSynced(true);

            posttitle=(TextView) myview.findViewById(R.id.posttitle);
            posttitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

        }


        public void setTitle(String title)
        {
            //TextView posttitle=(TextView) myview.findViewById(R.id.posttitle);
            posttitle.setText(title);
        }
        public void setDescription(String description)
        {
            TextView des=(TextView) myview.findViewById(R.id.posttext);
            des.setText(description);
        }
        public void setUsername(String description)
        {
            TextView des=(TextView) myview.findViewById(R.id.username);
            des.setText(description);
        }
        public  void setLikebutton(final String s)
        {
           databaselikes.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(s).hasChild(firebaseAuth.getInstance().getCurrentUser().getUid()))
                    {
                        fav.setImageResource(R.drawable.ic_favorite_black_24dp);

                    }
                    else
                    {
                        fav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        /*public void setImage(Context ctx,String image)
        {
            ImageView postimag=(ImageView) myview.findViewById(R.id.postimg);
            Picasso.with(ctx).load(image).into(postimag);


        }*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_add)
        {
            startActivity(new Intent(Welcome.this,Postactivity.class));
        }
        if(item.getItemId()==R.id.logout)
        {
            logout();
        }
        if (item.getItemId()==R.id.action_settings)
        {
            startActivity(new Intent(Welcome.this,Settings.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mAuth.signOut();
    }
}
