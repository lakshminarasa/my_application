package svecw.app_17b01a0558;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FacultyLogin extends AppCompatActivity {
    EditText regdno,password;
    Button login,createaccount;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
        regdno=findViewById(R.id.facultyemail);
        password=findViewById(R.id.facultypassword);
        login=findViewById(R.id.facultylogin1);
        createaccount=findViewById(R.id.facultyregister);
        mAuth=FirebaseAuth.getInstance();
        mAuthlistener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    Intent x=new Intent(FacultyLogin.this,Navigation.class);
                    x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(x);
                }

            }
        };
        mAuth.addAuthStateListener(mAuthlistener);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
                loginuser();
            }
        });
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FacultyLogin.this,FacultyRegister.class);
                startActivity(it);
            }
        });
    }

    private void loginuser() {
        String x=regdno.getText().toString();
        String y=password.getText().toString();
        mAuth.signInWithEmailAndPassword(x,y).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Intent x=new Intent(FacultyLogin.this,Navigation.class);
                    x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(x);
                    Toast.makeText(getApplicationContext(),"login succesfully",Toast.LENGTH_LONG).show();
                    Log.v("aspirants","login");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"login failure",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthlistener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistener);
    }

}
