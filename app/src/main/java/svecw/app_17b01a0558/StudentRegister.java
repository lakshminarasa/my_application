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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class StudentRegister extends AppCompatActivity {
    EditText email,password;
    Button register;

    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        email=findViewById(R.id.studentregisteremail);
        password=findViewById(R.id.studentregisterpassword);
        register=findViewById(R.id.studentregisterbtn);

        mAuth=FirebaseAuth.getInstance();

        mAuthlistener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null)
                {

                }

            }
        };
        mAuth.addAuthStateListener(mAuthlistener);




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginuser();

                Toast.makeText(getApplicationContext(),"registered",Toast.LENGTH_LONG).show();
            }
        });
    }




    private void loginuser()
    {
        String x=email.getText().toString();
        String y=password.getText().toString();

        mAuth.createUserWithEmailAndPassword(x,y).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                    Intent x=new Intent(StudentRegister.this,StudentLogin.class);
                    x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(x);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"failure",Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    protected void onStop()
    {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthlistener);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistener);
    }


}

