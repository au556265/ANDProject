package com.github.au556265.myprojectapplication.UI.Bookings;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.au556265.myprojectapplication.Adapter.AvailableSlotsAdapter;
import com.github.au556265.myprojectapplication.Models.Booking;
import com.github.au556265.myprojectapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class BookingFragment extends Fragment implements AvailableSlotsAdapter.OnListItemClickListener {

    private EditText date_time;
    private Spinner time_spinner;

    private BookingViewModel viewModel;
    private RecyclerView recyclerView;
    private ArrayList<AvailableSlotsAdapter.Slot> slotsList = new ArrayList<AvailableSlotsAdapter.Slot>();
    private AvailableSlotsAdapter.Slot selectedSlot;
    private AvailableSlotsAdapter slotsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        viewModel = new ViewModelProvider(this).get(BookingViewModel.class);

        viewModel.init();
        date_time = view.findViewById(R.id.date_time_input);
        date_time.setInputType(InputType.TYPE_NULL);
        date_time.setOnClickListener(v -> showDateTime(date_time));
        recyclerView = view.findViewById(R.id.rv_appointments);
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

        //Setting up recyclerview with adapter and gridlayout manager
        //grid layout manager is user to display 2 colomns in one row.
        slotsAdapter = new AvailableSlotsAdapter(slotsList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(slotsAdapter);

        //after initializing recyclerview calling this method to fetch available slots data
        getSlots();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * this method is responsible for returning all slots calling respective method in
     * respective repository and after that on any updates in value I am calling method
     *of adapter which will update and notify adapter on value changed.
     */
    private void getSlots() {
        viewModel.slotLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<AvailableSlotsAdapter.Slot>>() {
            @Override
            public void onChanged(ArrayList<AvailableSlotsAdapter.Slot> slots) {
                slotsAdapter.setItems(slots);

            }
        });

    }

    /**
     * This is a utility method which will display date picker dialog
     * checking if already picked a date then should start from that date.
     * @param date_time
     */
    public void showDateTime(EditText date_time) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
            String selectedDate = simpleDateFormat.format(calendar.getTime());
            date_time.setText(selectedDate);
            viewModel.fetchSlots(selectedDate);
        };
        new DatePickerDialog(getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * This onClick will be triggered for create booking action
     * and is calling createBooking method in View model
     */
    private void OnClicked() {
        String mDateTime = date_time.getText().toString();
        if (selectedSlot == null) {
            Toast.makeText(getContext(), "Please pick up a slot", Toast.LENGTH_LONG).show();
            return;
        }
        String mTimeSpinner = selectedSlot.getTime();
        viewModel.createBooking(mDateTime, mTimeSpinner);
        new Handler().postDelayed(() -> {
            //This is method will be used to reset adapter selection
            slotsAdapter.setSelectedIndex(-1);
        },1000);
    }

    /**
     * This method is a callback from adapter items which a specific
     * item is clicked it is setting a value in adapter which is responsible
     * for updating selection on adapter
     * @param position
     */
    @Override
    public void onSlotClick(int position) {
        if(slotsAdapter.selectedIndex == position){
            selectedSlot = null;
            slotsAdapter.setSelectedIndex(-1);
        }else{
            slotsAdapter.setSelectedIndex(position);
            selectedSlot = slotsAdapter.getItemAt(position);
        }

    }
}
