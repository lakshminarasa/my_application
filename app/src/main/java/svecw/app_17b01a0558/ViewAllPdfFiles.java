package svecw.app_17b01a0558;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAllPdfFiles extends AppCompatActivity {
ListView mypdflist;
DatabaseReference databaseReference;
List<Uploadpdf> uploadpdfs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_pdf_files);
        mypdflist=findViewById(R.id.mylistview);
        uploadpdfs=new ArrayList<>();
        viewAllFiles();
        mypdflist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uploadpdf uploadpdf1=uploadpdfs.get(i);
                Log.v("view",uploadpdf1.getUri().toString());
                Uri uri=Uri.parse(uploadpdf1.getUri());

                Intent intent=new Intent();
                intent.setDataAndType(uri,"pdf/*");
                startActivity(intent);
            }
        });

    }

    private void viewAllFiles() {
        databaseReference= FirebaseDatabase.getInstance().getReference("Uploadpdf");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Uploadpdf uploadpdf=postSnapshot.getValue(Uploadpdf.class);
                    uploadpdfs.add(uploadpdf);
                }
                String[] uploads=new String[uploadpdfs.size()];
                for(int i=0;i<uploads.length;i++)
                {
                    uploads[i]=uploadpdfs.get(i).getName();

                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view=super.getView(position,convertView,parent);
                        TextView mytext=view.findViewById(android.R.id.text1);
                        mytext.setTextColor(Color.BLACK);
                        return view;

                    }
                    };
                mypdflist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
