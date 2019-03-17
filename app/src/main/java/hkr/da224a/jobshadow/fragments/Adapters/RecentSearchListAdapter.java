package hkr.da224a.jobshadow.fragments.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import hkr.da224a.jobshadow.R;

public class RecentSearchListAdapter extends RecyclerView.Adapter<RecentSearchListAdapter.MyViewHolder> {

    private String[] queries;
    private Context mContext;

    public RecentSearchListAdapter(Context context, String[] queries) {
         this.queries= queries;
         this.mContext = context;
    }

    @Override
    public RecentSearchListAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(l);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.setSearchWord(queries[position]);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // initiate search for the selected query
            }
        });

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public TextView searchWordTextView;


        public MyViewHolder(LinearLayout l) {
            super(l);
            linearLayout = l;

            searchWordTextView = l.findViewById(R.id.search_word);
        }

        public void setSearchWord(String searchWord) {
            searchWordTextView.setText(searchWord);
        }

    }


    @Override
    public int getItemCount() {
        return queries.length;
    }
}
