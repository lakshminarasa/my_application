package svecw.app_17b01a0558;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button studentlogin,facultylogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentlogin=findViewById(R.id.studentlogin);
        facultylogin=findViewById(R.id.facultylogin);
        studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("srikala","hii");
                Intent y=new Intent(MainActivity.this,StudentLogin.class);
                startActivity(y);
            }
        });
        facultylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x=new Intent(MainActivity.this,FacultyLogin.class);
                startActivity(x);
            }
        });
    }

    }
