package com.example.waterresource.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.location.Location;

import com.example.waterresource.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class InputDataPage3 extends AppCompatActivity {

    private StorageReference mStorageRef;
    FirebaseAuth mAuth;
    ImageView imageViewModel;
    ImageView afterphototaken;
    Bitmap bitmap;
    LinearLayout content;
    Button btntakephoto;
    Button btngallery,btnSubmit;
    int selectedSourcetype;
    LocationTrack locationTrack;
    double longitude, latitude;
    public static final int RequestPermissionCode = 1;
    public static final int RequestPermissionCodeGPS = 2;
    public static final int REQUESTCODE = 999;
    String fulladdress;
    byte[] byteArray;

        Information info3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_page3);

        btntakephoto = (Button)findViewById(R.id.btnimage);
        content = (LinearLayout)findViewById(R.id.content);
        afterphototaken = (ImageView)findViewById(R.id.imageView);
        selectedSourcetype = getIntent().getExtras().getInt("selectedSource");
        info3              = (Information) getIntent().getParcelableExtra("info2");
        btnSubmit = (Button)findViewById(R.id.btnFinalSubmit);
        EnableRuntimePermission();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //idhar tu use kaar longi and lati
                //longi= something
                //lati= something
//                DatabaseReference ref;
//                ref = FirebaseDatabase.getInstance().getReference().child(info3.getTypeOfSource());
//                String uidForNewResource = ref.push().getKey();
//                ref.child(uidForNewResource).setValue(info3);

                info3.setLati(latitude);
                info3.setLongi(longitude);


                //ithe phtoto upload hotoy---------------------------------------------------------------
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byteArray = stream.toByteArray();

                //String path = "Resources_photos/" + System.currentTimeMillis() + ".jpg";
                //mStorageRef = FirebaseStorage.getInstance().getReference(path);
                mStorageRef=FirebaseStorage.getInstance().getReference().child("Photo").child(System.currentTimeMillis()+".jpg");

                final UploadTask uploadTask = mStorageRef.putBytes(byteArray);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(getApplicationContext(), "fail to upload", Toast.LENGTH_SHORT).show();

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "Successful to upload", Toast.LENGTH_SHORT);
                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                info3.setPhotoUid(uri.toString());

                                DatabaseReference ref;
                                ref = FirebaseDatabase.getInstance().getReference().child(info3.getTypeOfSource());
                                String uidForNewResource = ref.push().getKey();
                                ref.child(uidForNewResource).setValue(info3);
                                Intent intent = new Intent(InputDataPage3.this,HomePage.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        });
                    }
                });
                //ithe samptay---------------------------------------------------------------------
            }
        });

        btntakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ithe location ghetoy-----------------------------------------------------------------------
                locationTrack = new LocationTrack(InputDataPage3.this);
                if (locationTrack.canGetLocation()) {
                    longitude = locationTrack.getLongitude();
                    latitude = locationTrack.getLatitude();
                    Toast.makeText(InputDataPage3.this, "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
                    Geocoder geocoder = new Geocoder(InputDataPage3.this);
                    try {
                        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        fulladdress=address;
                        Toast.makeText(InputDataPage3.this,fulladdress,Toast.LENGTH_LONG).show();
                    }catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(InputDataPage3.this, "Exception at geo coder", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    locationTrack.showSettingsAlert();
                }
                //ithe samptoy location vala-----------------------------------------------------------------
                //idhar camera se photo lete hai-----------------------------------------------------------
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 7);
                //idhar khtama hota hai ------------------------------------------------------------------

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            afterphototaken.setImageBitmap(bitmap);
        }
    }
    public void EnableRuntimePermission() {
        Log.i("shubham","in gps permission");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "You have already given GPS permission", Toast.LENGTH_SHORT).show();
        } else {
          /*  if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(this.getActivity()), Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Permission needed!").setMessage("To access your location")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, RequestPermissionCodeGPS);

                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, RequestPermissionCodeGPS);
            }*/
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION},REQUESTCODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int RC, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(RC, permissions, grantResults);
        switch (RC) {
            case RequestPermissionCode:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                    Log.i("shubham","here3");
                } else {
                    Toast.makeText(this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                    Log.i("shubham","here4");
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission Canceled", Toast.LENGTH_LONG).show();
                }
        }
    }
}

//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//

//        });