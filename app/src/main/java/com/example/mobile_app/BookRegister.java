package com.example.mobile_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.SSLEngineResult;

// USED BY GREG DO NOT CHANGE !!!
public class BookRegister extends AppCompatActivity {
    EditText title, author, edition, isbn, category, publisher, publicyear,price,email ;
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

        // Join Layout with Variables
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
        // Reference to book4sell in Fire Storage
        sref = FirebaseStorage.getInstance().getReference("books4sell");

        bookregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Reference to Database
                dbref = FirebaseDatabase.getInstance().getReference("Book4sell");
                // Creating PK
                String pk=dbref.push().getKey();

                StorageReference reference= sref.child(pk+"."+getExtension(imagePath));
                // Write Image to Fire Storage
                reference.putFile(imagePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Getting Url for written Image
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imageurl= uri.toString();
                                // Id will be contact email
                                // curent date
                                date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                                String strtitle,strauthor,strprice,stremail;
                                strtitle= title.getText().toString();
                                strauthor= author.getText().toString();
                                stremail= email.getText().toString();
                                strprice= price.getText().toString();

                                //Validation
                                if (stremail.isEmpty()){
                                    email.setError("Please enter your email");
                                    email.requestFocus();
                                } else if (strtitle.isEmpty()){
                                    title.setError("Please enter book title");
                                    title.requestFocus();
                                } else if (strauthor.isEmpty()){
                                    author.setError("Please enter book author");
                                    author.requestFocus();
                                } else if (strprice.isEmpty()){
                                    price.setError("Please enter book price");
                                    price.requestFocus();
                                } else if (!(stremail.isEmpty() && strtitle.isEmpty() && strauthor.isEmpty() && strprice.isEmpty())) {

                                    // if all field are filled
                                    // Create Book4Sell Object
                                    Book4Sell b = new Book4Sell(strtitle, strauthor,
                                            edition.getText().toString(), isbn.getText().toString(),
                                            category.getText().toString(), imageurl,
                                            publisher.getText().toString(), publicyear.getText().toString(),
                                            strprice, stremail, "4Sell", date);
                                    // Write Object to FireBase
                                    dbref.child(pk).setValue(b);

                                    Intent i = new Intent(BookRegister.this, SellBooks.class);
                                    startActivity(i);
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                imageurl="No Image";
                                reference.delete();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        imageurl="No image";

                    }
                });


            }
        });
        // Click on Picture
        bookimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,100);

            }
        });
    }

    private String getExtension(Uri _imagePath)
    {

        ContentResolver resolver = getContentResolver();
        MimeTypeMap map= MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType( _imagePath));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode==RESULT_OK && data.getData()!=null);
        {
            Picasso.get().load(data.getData()).fit().into(bookimage);
            imagePath =data.getData();
        }
    }

    // Menu part
    public void ClickMenu(View view){
            //Open drawer
            Dashboard.openDrawer(drawer);
    }

    public void ClickLogo(View view){
            // close drawer
            Dashboard.closeDrawer(drawer);
    }

        public void ClickHome(View view){
            Dashboard.redirectActivity(this, Dashboard.class);
        }

        public void ClickLibrary(View view){
            Dashboard.redirectActivity(this, Library.class);
        }

        public void ClickTimetables(View view){
            recreate();
        }

        public void ClickFloorMap(View view){
            Dashboard.redirectActivity(this,FloorMap.class);
        }

        public void ClickForum(View view){
            Dashboard.redirectActivity(this,Forum.class);
        }

        public void ClickActivities(View view){
            Dashboard.redirectActivity(this,Activities.class);
    }
    @Override
    protected void onPause(){
        super.onPause();
        Dashboard.closeDrawer(drawer);
    }







}