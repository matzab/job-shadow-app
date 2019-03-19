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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    ArrayAdapter<String>  mAdapter;
    ArrayList<ChildDataItem> childDataItems;
    RecyclerView.Adapter mRecycleAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        Toolbar searchToolbar = getActivity().findViewById(R.id.toolbar);
        ((StudentMainActivity) getActivity()).setToolbar(searchToolbar);
        ArrayList<String>  strings = new ArrayList<>();
        strings.add("aaaaaaaa");
        strings.add("bbbbaaaa");
        strings.add("ccccaaaa");
        strings.add("aaaacccc");
        strings.add("aaaabbbb");
        strings.add("aaaazzzz");
        strings.add("abcaaaaa");
        strings.add("aabbaaaa");
        strings.add("cccaaaaa");
        strings.add("aaaaaaaa");
        strings.add("bbbbaaaa");
        strings.add("ccccaaaa");
        strings.add("aaaacccc");
        strings.add("aaaabbbb");
        strings.add("aaaazzzz");
        strings.add("abcaaaaa");
        strings.add("aabbaaaa");
        strings.add("cccaaaaa");

        mAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, strings);
        ListView mListView = (ListView) view.findViewById(R.id.result_list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         ArrayList<ParentDataItem> parentDataItems = new ArrayList<>();
        childDataItems = new ArrayList<>();



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
        mRecycleAdapter = new RecentSearchListAdapter(parentDataItems);
        recyclerView.setAdapter(mRecycleAdapter);
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
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }

}
