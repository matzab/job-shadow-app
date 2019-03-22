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
import hkr.da224a.jobshadow.model.Student;


public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MyViewHolder> {
    private ArrayList<Offer> mDataset;
    private Context mContext;
    private String mOrigin;
    private int mStudentID;

    public NotificationListAdapter(Context context, ArrayList<Offer> myDataset, String origin, int studentID) {
        mDataset = myDataset;
        mContext = context;
        mOrigin = origin;
        mStudentID = studentID;
    }

    @Override
    public NotificationListAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(l);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.setstudentFirstName("A new offer was added!");
        holder.setstudentLastName(mDataset.get(position).getOfferTitle());
        holder.setstudentEmail(mDataset.get(position).getDateCreated().toString());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mContext, OfferDetailActivity.class);
                intent.putExtra("selected_offer", mDataset.get(position));
                intent.putExtra("origin",mOrigin);
                intent.putExtra("studentID",mStudentID);
                mContext.startActivity(intent);
            }
        });
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public TextView notificationTitle;
        public TextView notificationMessage;
        public TextView notificationTime;

        public MyViewHolder(LinearLayout l) {
            super(l);
            linearLayout = l;

            notificationTitle = l.findViewById(R.id.offer_title);
            notificationMessage = l.findViewById(R.id.offer_length);
            notificationTime = l.findViewById(R.id.offer_location);
        }

        public void setstudentFirstName(String title) {
            notificationTitle.setText(title);
        }
        public void setstudentLastName(String length) {
            notificationMessage.setText(length);
        }
        public void setstudentEmail(String location) {
            notificationTime.setText(location);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
