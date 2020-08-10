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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FacultyRegister extends AppCompatActivity {
    EditText email,password,user;
    Button register;
    FirebaseAuth mAuth;
    DatabaseReference reff;
    FirebaseAuth.AuthStateListener mAuthlistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_register);
        email=findViewById(R.id.facultyregisteremail1);
        password=findViewById(R.id.facultyregisterpassword1);
        register=findViewById(R.id.facultyregister1);
        user=findViewById(R.id.facultyregisterusername);
        String s=user.getText().toString();
        Log.v("user",s);
        mAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();

                Toast.makeText(getApplicationContext(),"registered success",Toast.LENGTH_LONG).show();

            }
        });
    }

   private void loginuser() {
        String x=email.getText().toString();
        String y=password.getText().toString();
        mAuth.createUserWithEmailAndPassword(x,y).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                    Intent x=new Intent(FacultyRegister.this,FacultyLogin.class);
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
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


}
