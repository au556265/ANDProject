package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.au556265.myprojectapplication.R;
import com.github.au556265.myprojectapplication.UI.User.RegisterUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class BookingFragment extends Fragment {

    private EditText date_time;
    private Spinner time_spinner;

    private BookingViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        viewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        viewModel.init();
        date_time=view.findViewById(R.id.date_time_input);
        date_time.setInputType(InputType.TYPE_NULL);
        date_time.setOnClickListener(v ->showDateTime(date_time));

        time_spinner = view.findViewById(R.id.time_array);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.time_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(adapter);

        Button button = view.findViewById(R.id.addbooking);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClicked();
            }
        });


        return view;
    }

    public void showDateTime(EditText date_time) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH,day);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                date_time.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };
        new DatePickerDialog(getContext(),dateSetListener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void OnClicked() {
        String mDateTime =date_time.getText().toString();
        String mTimeSpinner = time_spinner.getSelectedItem().toString();
        //check if booking avaible
        boolean isBookingAvailable = viewModel.isBookingAvailable(mDateTime, mTimeSpinner);
        //can only book in future
        boolean isBookingInFuture = viewModel.isBookingInFuture(mDateTime, mTimeSpinner);
        if(isBookingAvailable && isBookingInFuture){
        viewModel.createBooking(mDateTime, mTimeSpinner);
            Toast.makeText(getContext(), "The booking was successfull", Toast.LENGTH_LONG).show();
        }else if(!isBookingAvailable){
            Toast.makeText(getContext(), "Booking is not avaible please pick another time", Toast.LENGTH_LONG).show();
        }else if(!isBookingInFuture){
            Toast.makeText(getContext(), "Please select a correct date and time for your booking", Toast.LENGTH_LONG).show();
        }
        //toast that shows bookingstatus

        }

    }
