package com.lahacks.app.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;
import com.lahacks.app.R;
import com.lahacks.app.adapters.CategoriesAdapter;
import com.lahacks.app.classes.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellFragment extends android.support.v4.app.Fragment {
    private static final int SELECT_PHOTO = 200;
    private static final int TAKE_PHOTO = 300;

    private ImageButton photoPicker, photoTaker;
    private Button listFinish;
    private ImageView photoView;
    private String photoPath;
    private Uri photoUri;
    private Bitmap galImage;
    private Spinner categorySpinner;

    public SellFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_sell, container, false);

        photoTaker = (ImageButton) rootView.findViewById(R.id.photo);
        categorySpinner = (Spinner) rootView.findViewById(R.id.category);
        photoPicker = (ImageButton) rootView.findViewById(R.id.gallery);
        listFinish = (Button) rootView.findViewById(R.id.list);
        photoView = (ImageView) rootView.findViewById(R.id.photoView);

        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(dataAdapter);

        photoPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        photoTaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    photoUri = Uri.fromFile(photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(takePictureIntent, TAKE_PHOTO);
                } else {
                    Toast.makeText(getActivity(), "Couldn't save file", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Create item from fields, send to database
                String title = ((EditText) rootView.findViewById(R.id.itemTitle)).getText().toString();
                String description = ((EditText) rootView.findViewById(R.id.description)).getText().toString();
                String category = categorySpinner.getSelectedItem().toString();

                boolean[] transType = new boolean[3];
                transType[Item.DELIVERY] = ((CheckBox) rootView.findViewById(R.id.delivery)).isChecked();
                transType[Item.PICKUP] = ((CheckBox) rootView.findViewById(R.id.pickup)).isChecked();
                transType[Item.MEETUP] = ((CheckBox) rootView.findViewById(R.id.meetup)).isChecked();

                double price;
                try {
                    price = Double.valueOf(((EditText) rootView.findViewById(R.id.price)).getText().toString());
                } catch(NumberFormatException e) {
                    price = 0;
                }

                boolean obo = ((Switch) rootView.findViewById(R.id.obo)).isChecked();
                boolean[] invalid = {false, false, false};
                if (title.isEmpty() || transType == invalid || category.isEmpty() || price == 0 || (photoUri == null
                        && galImage == null)) {
                    Toast.makeText(getActivity(), "Please enter all fields before listing", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bitmap photo;
                if (photoUri != null) {
                    photo = BitmapFactory.decodeFile(photoUri.getPath());
                } else {
                    photo = galImage;
                }

                Item item = new Item(title, description, category, transType, price, obo, photo);
                Toast.makeText(getActivity(), "Listing success!", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == Activity.RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap image = BitmapFactory.decodeStream(imageStream);
                    double ratio = image.getWidth() / image.getHeight();
                    galImage = image;
                    if (ratio < 1) {
                        Bitmap crop = Bitmap.createBitmap(image, 0, 0, image.getWidth(),
                                image.getHeight()/2);
                        ratio = crop.getWidth() / crop.getHeight();
                        photoView.setImageBitmap(Bitmap.createScaledBitmap(crop, 400, (int) (400/ratio), false));
                    } else {
                        photoView.setImageBitmap(Bitmap.createScaledBitmap(image, 400, (int) (400/ratio), false));
                    }
                }
                break;
            case TAKE_PHOTO:
                if(resultCode == Activity.RESULT_OK) {
                    Bitmap image = BitmapFactory.decodeFile(photoUri.getPath());
                    double ratio = image.getWidth() / image.getHeight();
                    if (ratio < 1) {
                        Bitmap crop = Bitmap.createBitmap(image, 0, 0, image.getWidth(),
                                image.getHeight()/2);
                        ratio = crop.getWidth() / crop.getHeight();
                        photoView.setImageBitmap(Bitmap.createScaledBitmap(crop, 400, (int) (400/ratio), false));
                    } else {
                        photoView.setImageBitmap(Bitmap.createScaledBitmap(image, 400, (int) (400/ratio), false));
                    }
                }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        photoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
