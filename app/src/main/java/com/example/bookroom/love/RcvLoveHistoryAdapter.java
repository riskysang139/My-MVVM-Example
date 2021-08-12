package com.example.bookroom.love;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookroom.Common.Social;
import com.example.bookroom.R;
import com.example.bookroom.home.SocialDatabase;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RcvLoveHistoryAdapter extends RecyclerView.Adapter<RcvLoveHistoryAdapter.ViewHoder> {
    List<Social> socialList;
    Context context;
    public static String URI = "file:///android_asset/yyyy/";

    public RcvLoveHistoryAdapter(List<Social> socialList, Context context) {
        this.socialList = socialList;
        this.context = context;
    }

    @NonNull
    @Override
    public RcvLoveHistoryAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_home, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvLoveHistoryAdapter.ViewHoder holder, int position) {
        Social social = socialList.get(position);
        Log.e("MyTag", socialList.get(position)+"" );
        holder.link.setText(social.getLink());
        holder.txtName.setText(social.getName());
        Glide.with(context).load(URI + social.getIcon() + ".png").into(holder.imgIcon);
        holder.btn_love.setImageResource(R.color.mainColor);
    }

    @Override
    public int getItemCount() {

        return socialList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        CircleImageView imgIcon, btn_love;
        TextView txtName, link;
        RelativeLayout relativeLayout;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.icon_img);
            btn_love = itemView.findViewById(R.id.btn_love);
            txtName = itemView.findViewById(R.id.txt_name);
            link = itemView.findViewById(R.id.txt_link);
            relativeLayout = itemView.findViewById(R.id.layout);
        }
    }
}

