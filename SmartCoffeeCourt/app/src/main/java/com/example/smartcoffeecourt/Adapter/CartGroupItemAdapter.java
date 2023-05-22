package com.example.smartcoffeecourt.Adapter;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Database.Database;
import com.example.smartcoffeecourt.Model.CartItem;
import com.example.smartcoffeecourt.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class CartGroupItemAdapter extends RecyclerView.Adapter<CartGroupItemAdapter.ViewHolder> {

    private List<CartItem> cartItemList;
    private Context context;

    private CartAdapter.CartGroupItemListener listener;

    public CartGroupItemAdapter(List<CartItem> cartItemList, CartAdapter.CartGroupItemListener listener) {
        this.cartItemList = cartItemList;
        this.listener = listener;
        this.context = listener.getContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_item_layout, parent, false);
        return new ViewHolder(itemView,  listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        Locale locale = new Locale("vi", "VN");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        Integer price = (int) (Float.parseFloat(cartItem.getPrice())*(1 - Float.parseFloat(cartItem.getDiscount())/100)*Float.parseFloat(cartItem.getQuantity()));
        holder.txtPrice.setText(fmt.format(price));
        holder.txtQuantity.setText(cartItem.getQuantity());
        holder.txtName.setText(cartItem.getName());
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        CartAdapter.CartGroupItemListener listen;
        TextView txtName, txtPrice ;

        EditText txtQuantity;
        public ViewHolder(@NonNull View itemView, CartAdapter.CartGroupItemListener listen) {
            super(itemView);
            this.listen = listen;
            txtName= (TextView)itemView.findViewById(R.id.cart_item_name);
            txtPrice = (TextView)itemView.findViewById(R.id.cart_item_price);
            txtQuantity = (EditText) itemView.findViewById(R.id.cart_item_quantity);
            txtQuantity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        int newQuantity = 0;
                        String quantity = txtQuantity.getText().toString();
                        if(!quantity.equals("")){
                            newQuantity = Integer.parseInt(quantity);
                            listen.onChangeQuantity(getAdapterPosition(),newQuantity);
                            return true;
                        }
                    return false;
                }
            });
        }

    }
}