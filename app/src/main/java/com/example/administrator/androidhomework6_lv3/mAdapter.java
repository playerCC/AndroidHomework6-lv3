package com.example.administrator.androidhomework6_lv3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 */

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> {
    private List<Contact> contactList;
    private Context context;
    public mAdapter(Context context,List<Contact> contactList) {
        this.contactList = contactList;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        View contactView;
        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            textView = view.findViewById(R.id.name);
            this.contactView = view;
        }
    }

    public void addData(int position,Contact contact) {
        contactList.add(position,contact);
        //添加动画
        notifyItemInserted(position);
    }
    public void removeData(int position) {
        contactList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,parent,false);
        final ViewHolder vh = new ViewHolder(view);
        vh.contactView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = vh.getAdapterPosition();
                Contact contact = contactList.get(position);
                Intent intent = new Intent(context,Information.class);
                intent.putExtra("imageId",contact.getImageId());
                intent.putExtra("name",contact.getName());
                context.startActivity(intent);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.imageView.setImageResource(contact.getImageId());
        holder.textView.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
