package com.world_tech_point.calling_app.CallingDetails;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class PageViewerAdapter extends FragmentPagerAdapter {

    private int position;

    public PageViewerAdapter(@NonNull FragmentManager fm, int position) {
        super(fm, position);
        this.position = position;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fm = null;

        switch (position){
            case 0:
                fm = new RecentFragment();
                break;

                case 1:
                fm = new ContactsFragment();
                break;

                case 2:
                fm = new FriendsFragment();
                break;
        }

        return fm;
    }

    @Override
    public int getCount() {
        return position;
    }
}
