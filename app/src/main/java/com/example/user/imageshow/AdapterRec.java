package com.example.user.imageshow;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by user on 12/7/17.
 */

public class AdapterRec extends RecyclerView.Adapter<AdapterRec.ViewHolder> {

    private Context context;
    private ImageView imageView;
    private int [] image;

    private RecyclerView rv;

    public AdapterRec(Context context, ImageView imageView, int[] image) {
        this.context = context;
        this.imageView = imageView;
        this.image = image;
    }

    public AdapterRec(Context context, ImageView imageView,RecyclerView rv) {
        this.context = context;
        this.imageView = imageView;
        this.rv=rv;
        image = new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher,R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher_round,R.mipmap.ic_launcher,R.mipmap.ic_launcher_round};
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.image_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Bitmap bm = decodeSampledBitmapFromResource(context.getResources(),
                image[position],150,150);
        final Bitmap bm1 = decodeSampledBitmapFromResource(context.getResources(),
                image[position],50,50);
        holder.iv.setImageBitmap(bm1);
        //imageView.setImageBitmap(bm);
        if(MainActivity.flag==position){
            imageView.setImageBitmap(bm);
            holder.iv.setColorFilter(android.R.color.transparent);
        }else {
            holder.iv.setColorFilter(R.color.colorPrimary);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.flag=position;
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return image.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv ;
        public ViewHolder(View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.ivImageItem);


        }

    }

    public void setBacgroundInImage(){

    }


    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                  int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}