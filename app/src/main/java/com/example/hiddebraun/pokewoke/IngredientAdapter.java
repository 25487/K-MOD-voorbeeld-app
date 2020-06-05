package com.example.hiddebraun.pokewoke;

import android.content.Context;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    private Context context;
    private List<Ingredient> list;

    public IngredientAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_ingredient, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ingredient ingredient = list.get(position);
        holder.ingredientName.setText(ingredient.getName());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setCurrency(Currency.getInstance("EUR"));
        holder.ingredientPrice.setText(formatter.format(ingredient.getPrice()));
        Glide.with(this.context).load(
                ingredient.getImageUrl()
        ).into(
                holder.ingredientImage
        );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredientName;
        public ImageView ingredientImage;
        public TextView ingredientPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.ingredient_name);
            ingredientImage = itemView.findViewById(R.id.ingredient_image);
            ingredientPrice = itemView.findViewById(R.id.ingredient_price);
        }
    }

}