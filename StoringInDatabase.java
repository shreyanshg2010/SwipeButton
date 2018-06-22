package co.shrey.contacts;

/**
 * Created by shrey on 12-06-2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class StoringInDatabase extends AppCompatActivity {
    Button submit;
    EditText name;
    EditText phone;
    EditText email;
    String a;
    String b;
    String c;
    DatabaseReference ref;
    DatabaseReference pushedPostRef;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("TAG", refreshedToken + "");
        submit = (Button) findViewById(R.id.check);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        ref=database.getReference();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check:
                insert();
             /*   Intent intent = new Intent(StoringInDatabase.this,MainActivity.class);
                startActivity(intent);*/
                // User chose the "Settings" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void insert() {
        a = name.getText().toString();
        b = phone.getText().toString();
        c = email.getText().toString();
        if(a != null && !a.isEmpty() && b.length()==10) {
            pushedPostRef = ref.push();
            pushedPostRef.child("name").setValue(a);
            pushedPostRef.child("phone").setValue(b);
            pushedPostRef.child("email").setValue(c);
            if (a != null && a.length() > 0) {
                name.setText("");
                phone.setText("");
                email.setText("");
            }

            Toast.makeText(StoringInDatabase.this, "Data Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
        else{
            if(a != null && !a.isEmpty())
            Toast.makeText(StoringInDatabase.this, "Number is invalid", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Name is not specified", Toast.LENGTH_SHORT).show();

        }

    }
}
