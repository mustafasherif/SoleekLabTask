package com.task.soleeklab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.task.soleeklab.Data.Country;
import com.task.soleeklab.HomeActivity.HomeActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private ArrayList<Country> mCountries;
    public CountryAdapter(ArrayList<Country> countries){
        mCountries=countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_item,viewGroup,false);
        CountryViewHolder viewHolder=new CountryViewHolder(row);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int i) {
        countryViewHolder.textView.setText(mCountries.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public  class CountryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.country_name)TextView textView;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public void changeSorting(ArrayList<Country> countries) {
        mCountries = countries;
        notifyDataSetChanged();
    }
}
