package hkr.da224a.jobshadow.activities.offer_activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.model.Offer;


public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.MyViewHolder> {
    private Offer[] mDataset;
    private Context mContext;

    public OfferListAdapter(Context context, Offer[] myDataset) {
        mDataset = myDataset;
        mContext = context;
    }

    @Override
    public OfferListAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(l);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.setOfferTitle(mDataset[position].getOfferTitle());
        holder.setOfferCompany("OFFER COMPANY");
        holder.setOfferLocation(mDataset[position].getOfferLocation());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mContext, OfferDetailActivity.class);
                intent.putExtra("selected_offer", mDataset[position]);
                mContext.startActivity(intent);
            }
        });

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public TextView offerTitle;
        public TextView offerCompany;
        public TextView offerLocation;

        public MyViewHolder(LinearLayout l) {
            super(l);
            linearLayout = l;

            offerTitle = l.findViewById(R.id.offer_title);
            offerCompany = l.findViewById(R.id.offer_company);
            offerLocation = l.findViewById(R.id.offer_location);
        }

        public void setOfferTitle(String title) {
            offerTitle.setText(title);
        }
        public void setOfferCompany(String company) {
            offerCompany.setText(company);
        }
        public void setOfferLocation(String location) {
            offerLocation.setText(location);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
