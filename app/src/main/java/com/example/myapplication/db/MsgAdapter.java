package com.example.myapplication.db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    //展示数据源存放
    private List<Message> mMsgList;
    @NonNull
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(view);
    }

    //    对recyclerview子项数据进行赋值，在子项滚动到屏幕内时执行。通过position参数得到当前项具体内容
    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder holder, int position) {
        Message msg = mMsgList.get(position);
                 if (msg.getType() == Message.TYPE_RECEIVED) {
                         //收到消息，用左边的格式 右边格式隐藏
                         holder.leftLayout.setVisibility(View.VISIBLE);
                         holder.rightLayout.setVisibility(View.GONE);
                         holder.leftMsg.setText(msg.getContent());
                     } else if (msg.getType() == Message.TYPE_SEND) {
                         //发送消息，用右边格式 左边格式隐藏
                         holder.rightLayout.setVisibility(View.VISIBLE);
                         holder.leftLayout.setVisibility(View.GONE);
                         holder.rightMsg.setText(msg.getContent());
                     }
    }


    //    返回recyclerview有多少子项
    @Override
    public int getItemCount() {
        return mMsgList.size();
    }


         //内部类 将子项的所有布局进行传入匹配
         static class ViewHolder extends RecyclerView.ViewHolder {
        //       布局元素
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;

        //        内部类的构造函数传入recycler子项的布局
        public ViewHolder(View view) {
            super(view);
            leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            leftMsg = (TextView) view.findViewById(R.id.left_msg);
            rightMsg = (TextView) view.findViewById(R.id.right_msg);
        }
     }

    //    传入要展示的数据源
     public MsgAdapter(List<Message> msgList) {
            mMsgList = msgList;
     }
}
