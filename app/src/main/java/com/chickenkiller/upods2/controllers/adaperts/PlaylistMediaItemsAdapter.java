package com.chickenkiller.upods2.controllers.adaperts;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chickenkiller.upods2.R;
import com.chickenkiller.upods2.controllers.player.Playlist;
import com.chickenkiller.upods2.controllers.player.UniversalPlayer;
import com.chickenkiller.upods2.interfaces.INowPlayingItemPosiontGetter;
import com.chickenkiller.upods2.models.MediaItem;

import java.util.List;

/**
 * Created by alonzilberman on 10/7/15.
 */
public class PlaylistMediaItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements INowPlayingItemPosiontGetter {

    private int layaoutId;
    private UniversalPlayer universalPlayer;
    private List<? extends MediaItem> mediaItems;
    private Playlist playlist;


    private static class PlaylistViewHolder extends RecyclerView.ViewHolder {
        public ImageButton btnPlPlay;
        public TextView tvPlTrackTitle;
        public TextView tvPlTrackSubTitle;
        public TextView tvPlTrackDuration;
        public RelativeLayout rlPlaylistItem;

        public PlaylistViewHolder(View view) {
            super(view);
            btnPlPlay = (ImageButton) view.findViewById(R.id.btnPlPlay);
            tvPlTrackTitle = (TextView) view.findViewById(R.id.tvPlTrackTitle);
            tvPlTrackSubTitle = (TextView) view.findViewById(R.id.tvPlTrackSubTitle);
            tvPlTrackDuration = (TextView) view.findViewById(R.id.tvPlTrackDuration);
            rlPlaylistItem = (RelativeLayout) view.findViewById(R.id.rlPlaylistItem);
        }
    }

    public PlaylistMediaItemsAdapter(Playlist playlist, int layaoutId, List<MediaItem> mediaItems) {
        super();
        this.layaoutId = layaoutId;
        this.universalPlayer = UniversalPlayer.getInstance();
        this.mediaItems = mediaItems;
        this.playlist = playlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layaoutId, parent, false);
        PlaylistViewHolder viewHolder = new PlaylistViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        PlaylistViewHolder viewHolder = (PlaylistViewHolder) holder;
        MediaItem currentItem = mediaItems.get(position);
        viewHolder.tvPlTrackTitle.setText(currentItem.getName());
        viewHolder.tvPlTrackSubTitle.setText(currentItem.getSubHeader());
        if (universalPlayer.isPlaying() && universalPlayer.isCurrentMediaItem(currentItem)) {
            viewHolder.btnPlPlay.setImageResource(R.drawable.ic_pause_white);
        } else {
            viewHolder.btnPlPlay.setImageResource(R.drawable.ic_play_white);
        }
        viewHolder.rlPlaylistItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        playlist.changeTrack(position);
                    }
                }, 300);
            }
        });

        holder.itemView.setTag(currentItem);
    }

    @Override
    public int getItemCount() {
        return mediaItems.size();
    }


    @Override
    public int getNowPlayingItemPosition() {
        for (int i = 0; i < mediaItems.size(); i++) {
            MediaItem iPlayableMediaItem = mediaItems.get(i);
            if (iPlayableMediaItem.getName().equals(universalPlayer.getPlayingMediaItem().getName())) {
                return i;
            }
        }
        return 0;
    }

    public MediaItem getItem(int position) {
        return mediaItems.get(position);
    }
}
