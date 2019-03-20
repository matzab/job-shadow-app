package hkr.da224a.jobshadow.fragments.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hkr.da224a.jobshadow.R;
import hkr.da224a.jobshadow.model.Student;


public class ApplicantListAdapter extends RecyclerView.Adapter<ApplicantListAdapter.MyViewHolder> {
    private ArrayList<Student> mDataset;
    private Context mContext;
    private String mOrigin;
    private int mStudentID;

    public ApplicantListAdapter(Context context, ArrayList<Student> myDataset, String origin) {
        mDataset = myDataset;
        mContext = context;
        mOrigin = origin;
    }

    @Override
    public ApplicantListAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LinearLayout l = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(l);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.setstudentFirstName(mDataset.get(position).getFirstName());
        holder.setstudentLastName(mDataset.get(position).getLastName());
        holder.setstudentEmail(mDataset.get(position).getEmailAddress());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public TextView studentFirstName;
        public TextView studentLastName;
        public TextView studentEmail;

        public MyViewHolder(LinearLayout l) {
            super(l);
            linearLayout = l;

            studentFirstName = l.findViewById(R.id.offer_title);
            studentLastName = l.findViewById(R.id.offer_length);
            studentEmail = l.findViewById(R.id.offer_location);
        }

        public void setstudentFirstName(String title) {
            studentFirstName.setText(title);
        }
        public void setstudentLastName(String length) {
            studentLastName.setText(length);
        }
        public void setstudentEmail(String location) {
            studentEmail.setText(location);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
