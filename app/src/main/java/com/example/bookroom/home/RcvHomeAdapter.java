package com.example.bookroom.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookroom.Common.Social;
import com.example.bookroom.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RcvHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Social> socialList;
    Context context;
    public static String URI = "file:///android_asset/yyyy/";
    OnItemClickListener onItemClickListener;

    public RcvHomeAdapter(List<Social> socialList, Context context, OnItemClickListener onItemClickListener) {
        this.socialList = socialList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }
    public RcvHomeAdapter(List<Social> socialList, Context context) {
        this.socialList = socialList;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
            return new ViewHoder2(view);

        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_home, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            ViewHoder2 viewHoder2 = (ViewHoder2) holder;
            viewHoder2.imgIcon.setImageResource(R.drawable.messi);
        } else {
            ViewHoder viewHoder = (ViewHoder) holder;
            Social social = socialList.get(position);
            viewHoder.link.setText(social.getLink());
            viewHoder.txtName.setText(social.getName());
            Glide.with(context).load(URI + social.getIcon() + ".png").into(viewHoder.imgIcon);
//            viewHoder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.OnClick(social);
//                }
//            });
            SocialDatabase socialDatabase = SocialDatabase.getInstance(context);
            List<Social> socialList = socialDatabase.getAll();

            Social social1 = socialList.get(position);
            if (social1.getFlag() == 1) {
                viewHoder.btn_love.setImageResource(R.color.mainColor);
            } else {
                viewHoder.btn_love.setImageResource(R.color.white);
            }
            viewHoder.btn_love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (social1.getFlag() == 1) {
                        socialDatabase.updateData(social1, 0);
                        viewHoder.btn_love.setImageResource(R.color.white);
                    } else {
                        socialDatabase.updateData(social1, 1);
                        viewHoder.btn_love.setImageResource(R.color.mainColor);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return socialList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 5 == 0) {
            return 0;
        }
        return 1;
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
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnClick(socialList.get(getAdapterPosition()));
                }
            });
        }
    }

    public class ViewHoder2 extends RecyclerView.ViewHolder {
        ImageView imgIcon;

        public ViewHoder2(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.image);

        }
    }

    public interface OnItemClickListener {
        void OnClick(Social social);
    }
}
