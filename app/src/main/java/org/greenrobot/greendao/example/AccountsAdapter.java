package org.greenrobot.greendao.example;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.le.www.green_dao_test.R;

import java.util.ArrayList;
import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountViewHolder> {
    private Context context;
    private AccountClickListener clickListener;
    private List<Account> dataset;

    public interface AccountClickListener {
        void onItemClick(int position);
        void onLongClick(int position);
    }

    static class AccountViewHolder extends RecyclerView.ViewHolder {
        public TextView platform_name;
        public TextView official_web;
        public TextView username;
        public TextView login_pwd;
        public TextView pay_pwd;

        public AccountViewHolder(View itemView, final AccountClickListener clickListener) {
            super(itemView);
            platform_name = (TextView) itemView.findViewById(R.id.platform_name);
            official_web = (TextView) itemView.findViewById(R.id.official_web);
            username = (TextView) itemView.findViewById(R.id.username);
            login_pwd = (TextView) itemView.findViewById(R.id.login_pwd);
            pay_pwd = (TextView) itemView.findViewById(R.id.pay_pwd);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    if (clickListener != null) {
                        clickListener.onLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }

    public AccountsAdapter(Context context, AccountClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
        this.dataset = new ArrayList<Account>();
    }

    public void setAccounts(@NonNull List<Account> notes) {
        dataset = notes;
        notifyDataSetChanged();
    }

    public Account getAccount(int position) {
        return dataset.get(position);
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        Account account = dataset.get(position);
        holder.platform_name.setText(context.getString(R.string.platform_name)+account.getPlatformName());
        holder.official_web.setText(context.getString(R.string.official_web)+account.getOfficialWeb());
        holder.username.setText(context.getString(R.string.username)+account.getUserName());
        holder.login_pwd.setText(context.getString(R.string.login_password)+account.getLoginPassword());
        holder.pay_pwd.setText(context.getString(R.string.pay_password)+account.getPayPassword());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
