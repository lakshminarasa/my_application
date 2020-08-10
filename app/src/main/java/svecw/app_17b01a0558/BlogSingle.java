package svecw.app_17b01a0558;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class BlogSingle extends AppCompatActivity {
    private String post_key=null;
    private DatabaseReference mdatabase;
    private ImageView imageView;
    private TextView titleval,titledes;
    Button removebtn;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_single);
         post_key= getIntent().getExtras().getString("blog_id");
        //Toast.makeText(getApplicationContext(),post_key,Toast.LENGTH_LONG).show();
        mdatabase= FirebaseDatabase.getInstance().getReference().child("blog");
        titleval=findViewById(R.id.singleblogposttitle);
        titledes=findViewById(R.id.singleblogposttext);
        removebtn=findViewById(R.id.remove_btn);
        mAuth=FirebaseAuth.getInstance();
        mdatabase.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String posttitle=dataSnapshot.child("titleval").getValue().toString();
                String postdes=dataSnapshot.child("titledes").getValue().toString();
                //String postimg=dataSnapshot.child("image").getValue().toString();
                String uid=dataSnapshot.child("Uid").getValue().toString();
                titleval.setText(posttitle);
                titledes.setText(postdes);
                //Picasso.with(getApplicationContext()).load(postimg).into(imageView);

                if(mAuth.getCurrentUser().getUid().equals(uid))
                {
                    removebtn.setVisibility(View.VISIBLE);
                }







            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("remove",post_key);
                mdatabase.child(post_key).removeValue();

            }
        });
    }
}
