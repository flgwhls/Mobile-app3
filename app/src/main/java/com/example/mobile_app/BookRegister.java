package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
    USED BY GREG DO NOT CHANGE !!!
    Class to register new book for sell
*/

public class BookRegister extends AppCompatActivity {
    EditText title, author, edition, isbn, category, publisher, publicyear, price, email;
    String status, date, imageurl;
    ImageView bookimage;
    Button bookregister;
    Uri imagePath;
    //Dbase reference
    DatabaseReference dbref;
    // Storage Reference
    StorageReference sref;
    //Firebase User Auth


    // Drawer Layout
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_register);

        // join layout with variables
        drawer = findViewById(R.id.drawer_layout);
        title = findViewById(R.id.et_bookreg_title);
        author = findViewById(R.id.et_bookreg_author);
        edition = findViewById(R.id.et_bookreg_edition);
        isbn = findViewById(R.id.et_bookreg_ISBN);
        category = findViewById(R.id.et_bookreg_category);
        bookimage = findViewById(R.id.iv_bookreg_image);
        bookregister = findViewById(R.id.btn_bookreg_register);
        publisher = findViewById(R.id.et_bookreg_publisher);
        publicyear = findViewById(R.id.et_bookreg_publicyear);
        price = findViewById(R.id.et_bookreg_price);
        email = findViewById(R.id.et_bookreg_email);
        // FireStorqage reference
        sref = FirebaseStorage.getInstance().getReference("books4sell");
        // react on click
        bookregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Firebase Reference
                dbref = FirebaseDatabase.getInstance().getReference("Book4sell");
                // Get PK
                String pk = dbref.push().getKey();
                // create url
                StorageReference reference = sref.child(pk + "." + getExtension(imagePath));
                // write picture to Storage
                reference.putFile(imagePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // get url
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imageurl = uri.toString();
                                Toast.makeText(BookRegister.this, imageurl, Toast.LENGTH_SHORT).show();
                                //Getting email
                                // Id will be contact email
                                // date is curent date
                                date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                                Toast.makeText(BookRegister.this, date, Toast.LENGTH_SHORT).show();

                                //Write Object to Database

                                Book4Sell b = new Book4Sell(title.getText().toString(), author.getText().toString(),
                                        edition.getText().toString(), isbn.getText().toString(),
                                        category.getText().toString(), imageurl,
                                        publisher.getText().toString(), publicyear.getText().toString(),
                                        price.getText().toString(), email.getText().toString(), "4Sell", date);
                                dbref.child(pk).setValue(b);
                                Intent i = new Intent(BookRegister.this, SellBooks.class);
                                startActivity(i);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            // in case of failure
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                reference.delete();

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    // in case of Failure
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
            }
        });
        // Click on Picture
        bookimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 100);
            }
        });
    }

    // getting extention
    private String getExtension(Uri _imagePath) {

        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(_imagePath));
    }

    // put image to ImageView after selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data.getData() != null) ;
        {
            Picasso.get().load(data.getData()).fit().into(bookimage);
            imagePath = data.getData();
        }
    }

    // Menu part
    public void ClickMenu(View view) {
        //Open drawer
        Dashboard.openDrawer(drawer);
    }

    public void ClickLogo(View view) {
        // close drawer
        Dashboard.closeDrawer(drawer);
    }

    public void ClickHome(View view) {
        Dashboard.redirectActivity(this, Dashboard.class);
    }

    public void ClickLibrary(View view) {
        Dashboard.redirectActivity(this, Library.class);
    }

    public void ClickTimetables(View view) {
        recreate();
    }

    public void ClickFloorMap(View view) {
        Dashboard.redirectActivity(this, FloorMap.class);
    }

    public void ClickForum(View view) {
        Dashboard.redirectActivity(this, ForumRecycleView.class);
    }

    public void ClickActivities(View view) {
        Dashboard.redirectActivity(this, Activities.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Dashboard.closeDrawer(drawer);
    }


}