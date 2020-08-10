package svecw.app_17b01a0558;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TextActivity extends AppCompatActivity {
    EditText posttitle,postdescription;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        posttitle=findViewById(R.id.texttitleid);
        postdescription=findViewById(R.id.textdecription);
        post=findViewById(R.id.textbtn);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();

            }
        });

    }

    private void startPosting() {
       final String x=posttitle.getText().toString().trim();
        final String y=postdescription.getText().toString().trim();
        final DatabaseReference mytext=FirebaseDatabase.getInstance().getReference().child("Text");
        mytext.removeValue();
    }
}
