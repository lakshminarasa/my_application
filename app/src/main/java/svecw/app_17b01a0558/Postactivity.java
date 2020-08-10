package svecw.app_17b01a0558;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Postactivity extends AppCompatActivity {
    private ImageButton mselectimage;
    private static final int gallery=1;

    private EditText myposttitle,mypostdes,mypostname;
    private Button mysubmitbtn;
    private  Uri myImageuri=null;
    private StorageReference myStrorage;
    private  FirebaseAuth mAuth;
    private FirebaseUser muser;
    private DatabaseReference myDatabase,myDatabaseUser;
    private ProgressDialog myprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postactivity);
        myposttitle=findViewById(R.id.posttitleid);
        mypostdes=findViewById(R.id.postdecription);
        mysubmitbtn=findViewById(R.id.postbtn);
        myStrorage= FirebaseStorage.getInstance().getReference();
        myDatabase= FirebaseDatabase.getInstance().getReference().child("blog");
        mAuth=FirebaseAuth.getInstance();
        muser=mAuth.getCurrentUser();
        myDatabaseUser=FirebaseDatabase.getInstance().getReference().child("users").child(muser.getUid());


        myprogress=new ProgressDialog(this);
       /* mselectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryintent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent,gallery);

            }
        });*/
        mysubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startposting();
            }
        });

    }

    private void startposting() {
        myprogress.setMessage("posting");
        final String title_val=myposttitle.getText().toString().trim();
        final String des=mypostdes.getText().toString().trim();
        //final String name=mypostname.getText().toString().trim();
      /* if(!TextUtils.isEmpty(title_val)&&!TextUtils.isEmpty(des)&&myImageuri!=null) {
            myprogress.show();
            final StorageReference filepath = myStrorage.child("blog_images").child(myImageuri.getLastPathSegment());
            filepath.putFile(myImageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  filepath.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                      @Override
                      public void onComplete(@NonNull final Task<Uri> task) {
                          Uri uri=task.getResult();
                          final DatabaseReference newpost=myDatabase.push();

                          myDatabaseUser.addValueEventListener(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  newpost.child("titleval").setValue(title_val);
                                  newpost.child("titledes").setValue(des);
                                  newpost.child("image").setValue(task.getResult().toString());
                                  newpost.child("Uid").setValue(muser.getUid());
                                  Log.v("postactivity",dataSnapshot.child("name").getValue().toString());
                                  newpost.child("Username").setValue(dataSnapshot.child("name").getValue());

                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError databaseError) {

                              }
                          });



                      }
                  });

                    myprogress.dismiss();
                    startActivity(new Intent(Postactivity.this,Welcome.class));


                }
            });

        }


*/     final DatabaseReference newpost=myDatabase.push();
      myDatabaseUser.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              newpost.child("titleval").setValue(title_val);
              newpost.child("titledes").setValue(des);
              //newpost.child("image").setValue(task.getResult().toString());
              newpost.child("Uid").setValue(muser.getUid());
              //newpost.child("Username").setValue(FirebaseAuth.getInstance().getCurrentUser());
              //Log.v("postactivity",dataSnapshot.child("name").getValue().toString());
              //newpost.child("Username").setValue(dataSnapshot.child("name").getValue().toString());




          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==gallery&&resultCode==RESULT_OK)
        {
             myImageuri=data.getData();
             Log.v("sri",myImageuri.toString());
            mselectimage.setImageURI(myImageuri);
        }
    }*/
}
