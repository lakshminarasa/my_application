package svecw.app_17b01a0558;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {
    ImageButton msetImage;
    EditText mnamefield;
    Button msubmitbtn;
    String x;
    //static  final int gallery=1;
    Uri myImageuri=null;
    DatabaseReference databaseReference,mdatabseCurrentuser;
    Query mqueryCuurentuser;
    //Blog x=new Blog();
   FirebaseAuth mauth;
   FirebaseAuth.AuthStateListener mauthlistener;
   DatabaseReference muserdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //msetImage=findViewById(R.id.profile);
        mnamefield = findViewById(R.id.setupname);
        msubmitbtn = findViewById(R.id.setupprofile);
        mauth=FirebaseAuth.getInstance();
        mauthlistener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
            }
        };
        muserdatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(mauth.getCurrentUser().getUid()).child("username");
       muserdatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               x=dataSnapshot.getValue().toString();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
       //Log.v("svecw",x);
       mnamefield.setText("srikala");
        msubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });

       /* mdatabseCurrentuser=FirebaseDatabase.getInstance().getReference().child("blog");
        String currentuserid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mqueryCuurentuser=mdatabseCurrentuser.orderByChild("Uid").equalTo(currentuserid);*/



       /* msubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSetupaccount();
                Intent it=new Intent(Settings.this,Welcome.class);
                startActivity(it);
            }
        });
    }

    private void startSetupaccount()
    {
        String name=mnamefield.getText().toString().trim();
        if(!TextUtils.isEmpty(name)&&myImageuri!=null)
        {
            final DatabaseReference x=FirebaseDatabase.getInstance().getReference().child("blog").child(firebaseUser.getUid());
            //x.child("image").setValue(myImageuri.toString());
            x.child("name").setValue(name);
            FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();


        }


    }*/

    }

    private void saveUser() {
        FirebaseDatabase.getInstance().getReference().child("Users").child(mauth.getCurrentUser().getUid()).child("username").setValue(mnamefield.getText().toString());

    }
}
