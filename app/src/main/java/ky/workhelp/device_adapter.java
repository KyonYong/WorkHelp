package ky.workhelp;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class device_adapter extends RecyclerView.Adapter<device_adapter.ViewHolder> {

    private String[] mTextView;
    private String[] mEditText;
    String[] texts;

    device_adapter(String[] mTextView, String[] mEditText) {
        this.mTextView = mTextView;
        this.mEditText = mEditText;
        this.texts = mEditText;
    }

    @NonNull
    @Override
    public device_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_detail_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.textView.setText(mTextView[holder.getAdapterPosition()]);
        holder.editText.setText(texts[position]);
        holder.editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                //setting data to array, when changed
                texts [position] = s.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                //blank
            }

            @Override
            public void afterTextChanged(Editable s) {
                //blank
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mTextView.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        EditText editText;

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.text_view_value);
            this.editText = view.findViewById(R.id.edit_text_value);
        }
    }
}