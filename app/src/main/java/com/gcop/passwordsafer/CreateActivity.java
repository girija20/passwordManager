package com.gcop.passwordsafer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateActivity extends AppCompatActivity {

    EditText title, userId, EPass, url;
    CircleImageView circleImageView;
    Bitmap bitmap;

    DatabaseHelper databaseHelper;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        id = getIntent().getIntExtra("id", 0);
        databaseHelper = new DatabaseHelper(this);

        title = findViewById(R.id.title);
        circleImageView = findViewById(R.id.profile_image);
        userId = findViewById(R.id.userId);
        EPass = findViewById(R.id.EPass);
        url = findViewById(R.id.url);

        if (id != 0) {
            UserInfo info = databaseHelper.getUserInfo(id + "");

            title.setText(info.title);
            userId.setText(info.userId);
            EPass.setText(info.password);
            url.setText(info.websites);


        }
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

/* Intent intent = new Intent(CreateActivity.this, IconActivity.class);
                startActivityForResult(intent, 101);*/

            }
        });


    }


    public void selectImage(){
        final CharSequence [] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Camera")){

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,101);


                }

                else if (items[which].equals("Gallery")){


                    Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intentGallery.setType("image/*");
//                    startActivityForResult(intentGallery.createChooser(intentGallery, "select file"),102);
                    startActivityForResult(intentGallery, 102);

                }

                else if (items[which].equals("Cancel")){
                    dialog.dismiss();

                }
            }
        });
        builder.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ids = item.getItemId();
        switch (ids) {
            case R.id.save:

                String titleValue = title.getText().toString();
                String userIdValue = userId.getText().toString();
                String EPassValue = EPass.getText().toString();
                String urlValue = url.getText().toString();

                if (PasswordActivity.isFieldEmpty(title)&&
                        PasswordActivity.isFieldEmpty(userId)&&
                        PasswordActivity.isFieldEmpty(EPass)&&
                        PasswordActivity.isFieldEmpty(url)) {


                    ContentValues contentValues = new ContentValues();
                    contentValues.put("title", titleValue);
                    if (bitmap != null)
                        contentValues.put("image", getBlob(bitmap));
                    contentValues.put("userId", userIdValue);
                    contentValues.put("password", EPassValue);
                    contentValues.put("websites", urlValue);


                    if (id == 0) {
                        databaseHelper.insertUser(contentValues);
                        Toast.makeText(this, "User Inserted", Toast.LENGTH_SHORT).show();


                    } else {
                        databaseHelper.updateUser(id + "", contentValues);
                        Toast.makeText(this, " User Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }


        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                bitmap = (Bitmap) intent.getExtras().get("data");
                circleImageView.setImageBitmap(bitmap);


            } else if (requestCode == 102) {

                if (resultCode == RESULT_OK && null != intent) {


                    Uri selectedImage = intent.getData();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    if (bitmap != null && !bitmap.isRecycled()) {
                        bitmap = null;
                    }

                    bitmap = BitmapFactory.decodeFile(filePath);
                    circleImageView.setBackgroundResource(0);
                    circleImageView.setImageBitmap(bitmap);
                } else {
                    Log.d("Status:", "Photopicker canceled");
                }
            }
        }
    }
                   /* Uri selectImageUri = data.getData();
                    circleImageView.setImageURI(selectImageUri);*/


                   /* Uri fullPhotoUri = data.getData();
                    circleImageView.setImageURI(fullPhotoUri);
*/
                       /* bitmap = (Bitmap) data.getExtras().get("data");
                        circleImageView.setImageBitmap(bitmap);*/


                       /* try{
                            final Uri uriImage = data.getData();
                            final InputStream inputStream = getContentResolver().openInputStream(uriImage);
                            final Bitmap imageMap = BitmapFactory.decodeStream(inputStream);
                            circleImageView.setImageBitmap(imageMap);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(CreateActivity.this, "Image was not found", Toast.LENGTH_SHORT).show();
                        }*/

                 /*   Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    circleImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
*/






//                Drawable drawable = getResources().getDrawable(R.drawable.ic_add_black_24dp);
               /* Drawable vectorDrawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_arrow_forward_black_24dp,  getContext().getTheme());
                Bitmap myLogo = ((BitmapDrawable) vectorDrawable).getBitmap();*/

//                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
//                circleImageView.setImageBitmap(bitmap);

//                int id = data.getIntExtra("imageId", R.drawable.ic_delete_black_24dp);











    public static byte[] getBlob(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        return bArray;
    }

    public static Bitmap getBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }




}
