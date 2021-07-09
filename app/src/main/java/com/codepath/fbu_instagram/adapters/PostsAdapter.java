package com.codepath.fbu_instagram.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.fbu_instagram.R;
import com.codepath.fbu_instagram.models.Post;
import com.codepath.fbu_instagram.models.PostParcel;
import com.codepath.fbu_instagram.ui.fragments.PostDetailFragment;
import com.codepath.fbu_instagram.ui.viewmodels.PostDetailViewModel;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private static final String TAG = "PostsAdapter";
    private Context context;
    private List<Post> posts;
    private FragmentManager fragmentManager;

    public PostsAdapter(Context context, List<Post> posts, FragmentManager fragmentManager) {
        this.context = context;
        this.posts = posts;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvUsername;
        private TextView tvDescription;
        private TextView tvLikes;
        private TextView tvTimeSincePosted;
        private ImageView ivImage;
        private ImageView ivAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivPost);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            tvTimeSincePosted = itemView.findViewById(R.id.tvTimeSincePosted);
            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            tvTimeSincePosted.setText(PostDetailViewModel.calculateTimeAgo(post.getCreatedAt()));
            //for avatars
            RequestOptions circleAvi = new RequestOptions();
            circleAvi = circleAvi.transforms(new CircleCrop());
            // for post images
            RequestOptions imgOptions = new RequestOptions();
            imgOptions = imgOptions.transforms(new CenterCrop(), new RoundedCorners(20));
            if (image != null) {
                Glide.with(context).load(image.getUrl()).apply(imgOptions).into(ivImage);
            }
            ParseFile avatar = post.getUser().getParseFile("userAvi");
            if(avatar != null) {
                Glide.with(context).load(avatar.getUrl()).apply(circleAvi).into(ivAvatar);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Post post = posts.get(position);
                PostParcel postParcel = new PostParcel(post.getDescription(), post.getImage(), post.getUser(), post.getCreatedAt());
                PostDetailFragment postDetailFragment = new PostDetailFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable(PostParcel.class.getSimpleName(), Parcels.wrap(postParcel));
                postDetailFragment.setArguments(bundle);
                // create intent for the new activity
                fragmentManager.beginTransaction().replace(R.id.flContainer, postDetailFragment)
                    .setReorderingAllowed(true)
                    .addToBackStack(null) // name can be null
                    .commit();
            }
        }
    }
}