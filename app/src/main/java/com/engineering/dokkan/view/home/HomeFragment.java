package com.engineering.dokkan.view.home;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.engineering.dokkan.R;
import com.engineering.dokkan.data.models.HomeItemModel;
import com.engineering.dokkan.view.base.BaseFragment;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initializeViews(View view) {
        RVAdapterSimilarView.ItemClickListener listener = new RVAdapterSimilarView.ItemClickListener() {
            @Override
            public void onItemClick(HomeItemModel item) {

                Toast.makeText(getActivity(), "item Clicked", Toast.LENGTH_SHORT).show();
            }
        };


        RVAdapterCategory.ImageClickListener listenerImage = new RVAdapterCategory.ImageClickListener() {
            @Override
            public void onItemClick(int item) {

                Toast.makeText(getActivity(), "image Clicked", Toast.LENGTH_SHORT).show();
            }
        };

        RVAdapterRecentlyView.ImageClickListener listenerRecView = new RVAdapterRecentlyView.ImageClickListener() {
            @Override
            public void onItemClick(int item) {

                Toast.makeText(getActivity(), "image Clicked", Toast.LENGTH_SHORT).show();
            }
        };

        RVAdapterRecentFavorite.ImageClickListener listenerRecFav = new RVAdapterRecentFavorite.ImageClickListener() {
            @Override
            public void onItemClick(int item) {

                Toast.makeText(getActivity(), "image Clicked", Toast.LENGTH_SHORT).show();
            }
        };

        RVAdapterOtherCategory.ImageClickListener listenerotherCateg = new RVAdapterOtherCategory.ImageClickListener() {
            @Override
            public void onItemClick(int item) {

                Toast.makeText(getActivity(), "image Clicked", Toast.LENGTH_SHORT).show();
            }
        };



        RVAdapterSimilarView adapter = new RVAdapterSimilarView(getList(), listener);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.similarItem_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);

        RVAdapterCategory adapterImages = new RVAdapterCategory(getListofImages(), listenerImage);
        RecyclerView recyclerViewImages = (RecyclerView) view.findViewById(R.id.rv_images);
        recyclerViewImages.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewImages.setAdapter(adapterImages);

        RVAdapterRecentlyView adapterRecView = new RVAdapterRecentlyView(getListofRecentlyView(), listenerRecView);
        RecyclerView recyclerViewRecView = (RecyclerView) view.findViewById(R.id.recently_view_recyclerView);
        recyclerViewRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewRecView.setAdapter(adapterRecView);

        RVAdapterRecentFavorite adapterRecFav = new RVAdapterRecentFavorite(getListofRecentlyFav(), listenerRecFav);
        RecyclerView recyclerViewRecFav = (RecyclerView) view.findViewById(R.id.recently_fav_recyclerView);
        recyclerViewRecFav.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewRecFav.setAdapter(adapterRecFav);

        RVAdapterOtherCategory adapterOtherCategory = new RVAdapterOtherCategory(getListofOtherCateg(), listenerotherCateg);
        RecyclerView recyclerViewOtherCateg = (RecyclerView) view.findViewById(R.id.rv_other_categ);
        recyclerViewOtherCateg.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewOtherCateg.setAdapter(adapterOtherCategory);


    }

    @Override
    public void setListeners() {

    }

    private ArrayList<HomeItemModel> getList() {
        final ArrayList<HomeItemModel> item = new ArrayList<HomeItemModel>();
        item.add(new HomeItemModel("Item Full Name", "$150", R.drawable.grid_one));
        item.add(new HomeItemModel("Item Full Name", "$150", R.drawable.grid_two));
        item.add(new HomeItemModel("Item Full Name", "$150", R.drawable.grid_three));
        item.add(new HomeItemModel("Item Full Name", "$150", R.drawable.grid_four));

        return item;
    }

    private ArrayList<Integer> getListofImages() {
        final ArrayList<Integer> image = new ArrayList<Integer>();
        image.add(R.drawable.grid_three);
        image.add(R.drawable.test_image);
        image.add(R.drawable.grid_three);
        image.add(R.drawable.grid_one);
        return image;
    }

    private ArrayList<Integer> getListofOtherCateg() {
        final ArrayList<Integer> image = new ArrayList<Integer>();
        image.add(R.drawable.grid_three);
        image.add(R.drawable.test_image);
        image.add(R.drawable.grid_three);
        image.add(R.drawable.grid_one);
        return image;
    }

    private ArrayList<Integer> getListofRecentlyView() {
        final ArrayList<Integer> image = new ArrayList<Integer>();
        image.add(R.drawable.grid_two);
        image.add(R.drawable.grid_three);
        image.add(R.drawable.grid_four);
        image.add(R.drawable.test_image);
        image.add(R.drawable.grid_one);
        image.add(R.drawable.test_image);

        return image;
    }

    private ArrayList<Integer> getListofRecentlyFav() {
        final ArrayList<Integer> image = new ArrayList<Integer>();
        image.add(R.drawable.grid_two);
        image.add(R.drawable.grid_three);
        image.add(R.drawable.grid_four);
        image.add(R.drawable.test_image);
        image.add(R.drawable.grid_one);
        image.add(R.drawable.test_image);


        return image;
    }
}