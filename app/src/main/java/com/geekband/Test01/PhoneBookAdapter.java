package com.geekband.Test01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MirQ on 16/7/6.
 */
public class PhoneBookAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private List<UserInfo> mUserInfoList;

    public PhoneBookAdapter(Context context, List<UserInfo> list) {

        mUserInfoList = list;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mUserInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 每一行数据显示在界面,用户能够看到时
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 返回一个视图
        // 解析一个layout.xml文件  得到一个视图
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_phine_book_friend, null);
            viewHolder = new ViewHolder();
            // 获取控件
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.name_text_view);
            viewHolder.ageTextView = (TextView) convertView.findViewById(R.id.age_text_view);
            viewHolder.avatarImageView = (ImageView) convertView.findViewById(R.id.avatar_imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 和 数据之间进行关联
        viewHolder.nameTextView.setText(mUserInfoList.get(position).getUserName());
        viewHolder.ageTextView.setText(String.valueOf(mUserInfoList.get(position).getAge()));
        viewHolder.avatarImageView.setImageResource(R.drawable.weixin);
        return convertView;
    }

    class ViewHolder {
        TextView nameTextView;
        TextView ageTextView;
        ImageView avatarImageView;
    }

    public void refreshData(List<UserInfo> userInfos) {
        mUserInfoList = userInfos;
        notifyDataSetChanged();
    }
}
