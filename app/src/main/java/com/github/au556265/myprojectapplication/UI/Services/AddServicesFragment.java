package com.github.au556265.myprojectapplication.UI.Services;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.au556265.myprojectapplication.R;
import com.github.au556265.myprojectapplication.UI.Bookings.BookingViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddServicesFragment extends Fragment {

    private AddServicesViewModel viewModel;

    private EditText name;
    private EditText price;
    private Button serviceButton;
    private Button addImageButton;

    private byte[] stringImage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_services, container, false);
        viewModel = new ViewModelProvider(this).get(AddServicesViewModel.class);
        viewModel.init();
        name = view.findViewById(R.id.service_name);
        price = view.findViewById(R.id.service_price);
        
        serviceButton = view.findViewById(R.id.service_add_service_button);
        addImageButton = view.findViewById(R.id.service_select_image_button);
        
        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClicked();
            }
        });
        
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        return view;
    }

    private void selectImage() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent , "Select Picture"), 200);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 200) {
                // Get the uri
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    InputStream iStream = null;
                    try {
                        iStream = getContext().getContentResolver().openInputStream(selectedImageUri);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        stringImage = getBytes(iStream);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }



    private void OnClicked() {
        String mName = name.getText().toString();
        double mPrice = 0;

        try{
            mPrice = Double.parseDouble(price.getText().toString());
            viewModel.addService(mName, mPrice, stringImage);
            Toast.makeText(getContext(), "The service was successfull added succesfully", Toast.LENGTH_LONG).show();
        }
        catch (NullPointerException | NumberFormatException e){
            Toast.makeText(getContext(), "Please add a valid number for price", Toast.LENGTH_LONG).show();
        }


    }


}