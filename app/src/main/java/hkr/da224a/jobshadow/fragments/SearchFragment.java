package hkr.da224a.jobshadow.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.activities.student_activities.StudentMainActivity;
import hkr.da224a.jobshadow.fragments.Adapters.ChildDataItem;
import hkr.da224a.jobshadow.fragments.Adapters.ParentDataItem;
import hkr.da224a.jobshadow.fragments.Adapters.RecentSearchListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        Toolbar searchToolbar = getActivity().findViewById(R.id.toolbar);
        ((StudentMainActivity) getActivity()).setToolbar(searchToolbar);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         ArrayList<ParentDataItem> parentDataItems = new ArrayList<>();
        ArrayList<ChildDataItem> childDataItems = new ArrayList<>();

        childDataItems.add(new ChildDataItem("String 1"));
        childDataItems.add(new ChildDataItem("String 2"));
        childDataItems.add(new ChildDataItem("String 3"));
        childDataItems.add(new ChildDataItem("String 4"));
        childDataItems.add(new ChildDataItem("String 5"));
        parentDataItems.add(new ParentDataItem("Recent searches", childDataItems));

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recent_search_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new RecentSearchListAdapter(parentDataItems);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);


        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }

}
