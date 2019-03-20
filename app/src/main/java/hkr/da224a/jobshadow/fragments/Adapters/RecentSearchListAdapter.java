package hkr.da224a.jobshadow.fragments.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hkr.da224a.jobshadow.R;

public class RecentSearchListAdapter extends RecyclerView.Adapter<RecentSearchListAdapter.MyViewHolder> {

    private ArrayList<ParentDataItem> parentDataItems;



    public RecentSearchListAdapter(ArrayList<ParentDataItem> parentDataItems) {
        this.parentDataItems = parentDataItems;
    }

    @Override
    public RecentSearchListAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        ParentDataItem parentDataItem = parentDataItems.get(position);
        holder.textView_parentName.setText(parentDataItem.getParentName());
        //
        int noOfChildTextViews = holder.linearLayout_childItems.getChildCount();
        for (int index = 0; index < noOfChildTextViews; index++) {
            TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(index);
            currentTextView.setVisibility(View.VISIBLE);
        }

        int noOfChild = parentDataItem.getChildDataItems().size();
        if (noOfChild < noOfChildTextViews) {
            for (int index = noOfChild; index < noOfChildTextViews; index++) {
                TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(index);
                currentTextView.setVisibility(View.GONE);
            }
        }
        for (int textViewIndex = 0; textViewIndex < noOfChild; textViewIndex++) {
            TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(textViewIndex);
            currentTextView.setText(parentDataItem.getChildDataItems().get(textViewIndex).getChildName());
        }

    }




    @Override
    public int getItemCount() {
        return parentDataItems.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private TextView textView_parentName;
        private LinearLayout linearLayout_childItems;

        MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            textView_parentName = itemView.findViewById(R.id.parentName);
            linearLayout_childItems = itemView.findViewById(R.id.child_items);
            linearLayout_childItems.setVisibility(View.GONE);
            int intMaxNoOfChild = 0;
            for (int index = 0; index < parentDataItems.size(); index++) {
                int intMaxSizeTemp = parentDataItems.get(index).getChildDataItems().size();
                if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
            }
            for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {
                TextView textView = new TextView(context);
                textView.setId(indexView);
                textView.setPadding(0, 20, 0, 20);
                textView.setGravity(Gravity.CENTER);
            //    textView.setBackground(ContextCompat.getDrawable(context, R.drawable.background_sub_module_text));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setOnClickListener(this);
                linearLayout_childItems.addView(textView, layoutParams);
            }
            textView_parentName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.parentName) {
                if (linearLayout_childItems.getVisibility() == View.VISIBLE) {
                    linearLayout_childItems.setVisibility(View.GONE);
                } else {
                    linearLayout_childItems.setVisibility(View.VISIBLE);
                }
            } else {
                TextView textViewClicked = (TextView) view;
                Toast.makeText(context, "" + textViewClicked.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
