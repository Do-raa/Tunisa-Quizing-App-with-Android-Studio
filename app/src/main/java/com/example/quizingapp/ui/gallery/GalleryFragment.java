package com.example.quizingapp.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.quizingapp.MusicActivity;
import com.example.quizingapp.FoodActivity;
import com.example.quizingapp.ReligionActivity;
import com.example.quizingapp.SightseeingActivity;
import com.example.quizingapp.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set OnClickListener for culture_card
        binding.cultureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(getActivity(), MusicActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for religion_card
        binding.religionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(getActivity(), ReligionActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for sight_card
        binding.sightCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(getActivity(), SightseeingActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for food_card
        binding.foodCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity
                Intent intent = new Intent(getActivity(), FoodActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
