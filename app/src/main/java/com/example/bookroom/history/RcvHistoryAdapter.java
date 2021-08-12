package com.example.bookroom.history;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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


public class RcvHistoryAdapter extends RecyclerView.Adapter<RcvHistoryAdapter.ViewHoder> {
    List<Social> socialList;
    Context context;
    public static String URI = "file:///android_asset/yyyy/";

    public RcvHistoryAdapter(List<Social> socialList, Context context) {
        this.socialList = socialList;
        this.context = context;
    }

    @NonNull
    @Override
    public RcvHistoryAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_home, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RcvHistoryAdapter.ViewHoder holder, int position) {
        Social social = socialList.get(position);
        holder.link.setText(social.getLink());
        holder.txtName.setText(social.getName());
        Glide.with(context).load(URI + social.getIcon() + ".png").into(holder.imgIcon);
        SocialDatabase socialDatabase = SocialDatabase.getInstance(context);
        List<Social> socialList = socialDatabase.getAll();
        Social social1 = socialList.get(position);
        if (social1.getFlag() == 1) {
            holder.btn_love.setImageResource(R.color.mainColor);
        } else {
            holder.btn_love.setImageResource(R.color.white);
        }

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

