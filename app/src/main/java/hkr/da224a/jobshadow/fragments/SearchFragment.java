package hkr.da224a.jobshadow.fragments;


import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.activities.offer_activities.OfferDetailActivity;
import hkr.da224a.jobshadow.activities.student_activities.StudentMainActivity;
import hkr.da224a.jobshadow.fragments.Adapters.ChildDataItem;
import hkr.da224a.jobshadow.fragments.Adapters.ParentDataItem;
import hkr.da224a.jobshadow.fragments.Adapters.RecentSearchListAdapter;
import hkr.da224a.jobshadow.model.Offer;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    ArrayList<Offer> offers = new ArrayList<>();
    SearchView mSearchView;

    TextView mEmptyView;
    ArrayAdapter<String>  mAdapter;
    ArrayList<ChildDataItem> childDataItems;
    RecyclerView.Adapter mRecycleAdapter;
    ArrayList<ParentDataItem> parentDataItems;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        Offer offer;

        for(int i = 0; i<100;i++) {
            offer = new Offer();
            offer.setOfferTitle("Job title " + i);
            offers.add(offer);
        }
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        Toolbar searchToolbar = getActivity().findViewById(R.id.toolbar);
        ((StudentMainActivity) getActivity()).setToolbar(searchToolbar);
//        ArrayList<String>  strings = new ArrayList<>();
//        strings.add("aaaaaaaa");
//        strings.add("bbbbaaaa");
//        strings.add("ccccaaaa");
//        strings.add("aaaacccc");
//        strings.add("aaaabbbb");
//        strings.add("aaaazzzz");
//        strings.add("abcaaaaa");
//        strings.add("aabbaaaa");
//        strings.add("cccaaaaa");
//        strings.add("aaaaaaaa");
//        strings.add("bbbbaaaa");
//        strings.add("ccccaaaa");
//        strings.add("aaaacccc");
//        strings.add("aaaabbbb");
//        strings.add("aaaazzzz");
//        strings.add("abcaaaaa");
//        strings.add("aabbaaaa");
//        strings.add("cccaaaaa");

        TextView emptyList = new TextView(getContext());
        emptyList.setText("The list is empty");

        mAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, offers);
        ListView mListView = (ListView) view.findViewById(R.id.result_list);
        mListView.setEmptyView(getActivity().findViewById(R.id.emptyView));
        mListView.setEmptyView(emptyList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =  new Intent(getContext(), OfferDetailActivity.class);
                intent.putExtra("selected_offer", (Offer)adapterView.getItemAtPosition(i));
                intent.putExtra("origin", "student");
                intent.putExtra("studentID",0);
                getContext().startActivity(intent);
            }
        });



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parentDataItems = new ArrayList<>();
        childDataItems = new ArrayList<>();


        mEmptyView = (TextView) getActivity().findViewById(R.id.emptyView);
        childDataItems.add(new ChildDataItem("String 1"));
        childDataItems.add(new ChildDataItem("String 2"));
        childDataItems.add(new ChildDataItem("Job title 20"));
        childDataItems.add(new ChildDataItem("Job title 9"));
        childDataItems.add(new ChildDataItem("Job title 88"));
        parentDataItems.add(new ParentDataItem("Recent searches", childDataItems));

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);


        mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recent_search_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mRecycleAdapter = new RecentSearchListAdapter(parentDataItems, mSearchView);
        recyclerView.setAdapter(mRecycleAdapter);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                childDataItems.remove(0);
                childDataItems.add(new ChildDataItem(query));
                parentDataItems.remove(0);
                mRecycleAdapter.notifyItemRemoved(0);
                parentDataItems.add(new ParentDataItem("Recent search:", childDataItems));
                mRecycleAdapter.notifyItemInserted(0);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                if (mAdapter.getCount() == 0){
                    mEmptyView.setVisibility(View.VISIBLE);
                }
                if (mAdapter.getCount() > 0 || newText.equals("")){
                    mEmptyView.setVisibility(View.GONE);
                }

                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }

}
