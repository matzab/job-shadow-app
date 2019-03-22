package hkr.da224a.jobshadow.fragments.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.activities.offer_activities.OfferDetailActivity;
import hkr.da224a.jobshadow.model.Offer;


public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.MyViewHolder> {
    private ArrayList<Offer> mDataset;
    private Context mContext;
    private String mOrigin;
    private int mStudentID;

    public OfferListAdapter(Context context, ArrayList<Offer> myDataset, String origin) {
        mDataset = myDataset;
        mContext = context;
        mOrigin = origin;
    }

    public OfferListAdapter(Context context, ArrayList<Offer> myDataset, String origin, int studentID) {
        mDataset = myDataset;
        mContext = context;
        mOrigin = origin;
        mStudentID = studentID;
    }

    @Override
    public OfferListAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(l);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.setOfferTitle(mDataset.get(position).getOfferTitle());
        holder.setOfferLength(mDataset.get(position).getOfferLength());
        holder.setOfferLocation(mDataset.get(position).getOfferLocation());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OfferDetailActivity.class);
                intent.putExtra("selected_offer", mDataset.get(position));
                intent.putExtra("origin", mOrigin);
                intent.putExtra("studentID", mStudentID);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public TextView offerTitle;
        public TextView offerLength;
        public TextView offerLocation;

        public MyViewHolder(LinearLayout l) {
            super(l);
            linearLayout = l;

            offerTitle = l.findViewById(R.id.offer_title);
            offerLength = l.findViewById(R.id.offer_length);
            offerLocation = l.findViewById(R.id.offer_location);
        }

        public void setOfferTitle(String title) {
            offerTitle.setText(title);
        }

        public void setOfferLength(String length) {
            offerLength.setText(length);
        }

        public void setOfferLocation(String location) {
            offerLocation.setText(location);
        }
    }
}
