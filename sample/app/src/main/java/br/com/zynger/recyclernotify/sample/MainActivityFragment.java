package br.com.zynger.recyclernotify.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivityFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<String> items = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            items.add("Item " + String.valueOf(i));
        }
        SampleAdapter adapter = new SampleAdapter(items);
        mRecyclerView.setAdapter(adapter);

        RecyclerNotify recyclerNotify = new RecyclerNotify(getContext());
        recyclerNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "RecyclerNotify clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerNotify.setText("Novas histórias");
        RecyclerNotifyAttacher.attach(recyclerNotify, mRecyclerView);
        return view;
    }

    private class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {
        private List<String> mItems;

        public SampleAdapter(List<String> items) {
            this.mItems = items;
        }

        @Override
        public SampleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemLayoutView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout, null);
            return new ViewHolder(itemLayoutView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.mTextView.setText(mItems.get(position));
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);
                mTextView = (TextView) itemLayoutView.findViewById(R.id.item_title);
            }
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }
}