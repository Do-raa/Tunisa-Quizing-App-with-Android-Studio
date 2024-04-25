package com.example.quizingapp.ui.slideshow;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.quizingapp.R;
import com.example.quizingapp.databinding.FragmentSlideshowBinding;
import com.example.quizingapp.ui.gallery.GalleryFragment;
import com.google.android.material.navigation.NavigationView;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private ViewPager viewPager;
    private Button buttonStartQuiz;
    private int[] images = {R.drawable.ancient_carthage, R.drawable.desert, R.drawable.spring_country,
            R.drawable.landscape, R.drawable.cuisine, R.drawable.olive_oil,
            R.drawable.ruins, R.drawable.history, R.drawable.map,
            R.drawable.people};
    private String[] titles = {"Ancient Carthage", "Star Wars Filming", "Arab Spring",
            "Diverse Landscapes", "Tunisian Cuisine", "Olive Oil Production",
            "UNESCO Heritage", "Phoenician Sites", "Language Diversity",
            "Tunisian Hospitality"};
    private String[] descriptions = {"Tunisia was once the heart of the ancient Carthaginian Empire, a major power in the ancient Mediterranean.", "The desert landscapes of Tunisia were used as a filming location for several Star Wars movies, most notably as the backdrop for Luke Skywalker's home planet, Tatooine.", "Tunisia was the birthplace of the Arab Spring in 2010-2011, leading to political changes not only within Tunisia but also across the Arab world.",
            "Despite its small size, Tunisia boasts diverse landscapes ranging from lush green valleys and beaches to the Sahara Desert.", "Tunisian cuisine is a blend of Mediterranean and North African flavors, known for dishes like couscous, brik, and tajine.", "Tunisia is one of the largest producers of olive oil in the world, with olive trees covering vast areas of the countryside.",
            "The country is home to several UNESCO World Heritage Sites, including the impressive ruins of Dougga and the amphitheater of El Jem.", "Apart from Carthage, Tunisia also boasts other Phoenician archaeological sites, showcasing its rich ancient history.", "While Arabic is the official language, French is widely spoken and used in education, media, and business.",
            "Tunisians are known for their hospitality and friendliness towards tourists, making it a popular destination for travelers from around the world."};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textViewTitle = root.findViewById(R.id.textViewTitle);
        buttonStartQuiz = root.findViewById(R.id.buttonStartQuiz);

        viewPager = root.findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new SlidePagerAdapter();
        viewPager.setAdapter(pagerAdapter);

        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_gallery);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class SlidePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slide_layout, container, false);
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textViewTitle = view.findViewById(R.id.textViewTitle);
            TextView textViewDescription = view.findViewById(R.id.textViewDescription);

            imageView.setImageResource(images[position]);
            textViewTitle.setText(titles[position]);
            textViewDescription.setText(descriptions[position]);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
