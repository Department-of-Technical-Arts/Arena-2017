package bphc.tech.com.arena17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.adapter.FeedItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    RecyclerView feedRecycler;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        feedRecycler = (RecyclerView) view.findViewById(R.id.feed_recycler);
        feedRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        feedRecycler.setAdapter(new FeedItemAdapter(getActivity()));
    }
}
