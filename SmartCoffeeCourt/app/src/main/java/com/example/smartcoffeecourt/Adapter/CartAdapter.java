package com.example.smartcoffeecourt.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcoffeecourt.Common;
import com.example.smartcoffeecourt.Database.Database;
import com.example.smartcoffeecourt.Model.CartItem;
import com.example.smartcoffeecourt.Model.CartGroupItem;
import com.example.smartcoffeecourt.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartGroupItem> cartGroupItemList;
    private CartGroupItemListener listener;

    public CartAdapter(List<CartGroupItem> cartGroupItemList, CartGroupItemListener listener) {
        this.cartGroupItemList = cartGroupItemList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(listener.getContext());
        View itemView = inflater.inflate(R.layout.cart_group_item_layout, parent, false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Locale locale = new Locale("vi", "VN");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        Integer total = cartGroupItemList.get(position).getTotal();
        holder.txtTotal.setText(String.format("Tổng tiền: %s", fmt.format(total)));
        holder.txtName.setText(String.format("Stall: %s", cartGroupItemList.get(position).getSupplierID()));
        final List<CartItem> cartItemList = cartGroupItemList.get(position).getCartItemList();
        holder.foodList.setLayoutManager(new LinearLayoutManager(listener.getContext()));
        holder.foodList.setAdapter(new CartGroupItemAdapter(cartItemList,listener));
        holder.btnChangeType.setText(Common.convertCodeToType(cartGroupItemList.get(position).getType()));

    }

    @Override
    public int getItemCount() {
        return cartGroupItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtTotal;
        RecyclerView foodList;
        Button btnChangeType;
        ImageButton btnCancel;
        CartGroupItemListener listener;
        String deliveryAddress;
        public ViewHolder(@NonNull View itemView, CartGroupItemListener listener) {
            super(itemView);
            this.listener = listener;
            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtTotal = (TextView)itemView.findViewById(R.id.txtTotal);
            btnChangeType = itemView.findViewById(R.id.btnChangeType);
            foodList = (RecyclerView)itemView.findViewById(R.id.foodList);
            btnChangeType.setOnClickListener(this);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btnChangeType){
                AlertDialog orderDialog;
                final String[] list;
                list = new String[]{"Dùng tại quán", "Mang đi"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(listener.getContext());
                mBuilder.setTitle("Đặt hàng bằng ???");
                mBuilder.setIcon(R.drawable.ic_baseline_table_chart_24);
                mBuilder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 1) {
                            showAddressDialog();
                        }
                    }
                });
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int index = ((AlertDialog)dialogInterface).getListView().getCheckedItemPosition();
                        listener.onTypeChangeClick(getAdapterPosition(), String.valueOf(index), deliveryAddress);
                    }
                });
                orderDialog = mBuilder.create();
                orderDialog.show();
            }
            if(view.getId() == R.id.btnCancel) listener.onDeleteOrder(getAdapterPosition());
        }

        private void showAddressDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle("Nhập địa chỉ giao hàng");

            final EditText input = new EditText(itemView.getContext());
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    deliveryAddress = input.getText().toString();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            builder.show();
        }
    }
    public interface CartGroupItemListener{
        void onTypeChangeClick(int position, String newType, String address);
        void onDeleteOrder(int position);
        void onChangeQuantity(int position, int newQuantity);
        Context getContext();
    }
}
