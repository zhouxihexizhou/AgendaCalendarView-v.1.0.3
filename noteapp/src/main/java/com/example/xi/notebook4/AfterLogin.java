package com.example.xi.notebook4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xi.notebook4.Overall.Variable;

/**
 * Created by xi on 2017/12/4.
 */

public class AfterLogin extends android.support.v4.app.Fragment {

    private View view;

    private LinearLayout imageView_change_information;

    private LinearLayout imageView_login_change_calendar;

    private Button button_quit_login;

    private BeforeLogin beforeLogin;

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.after_login, container, false);

        imageView_change_information = (LinearLayout) view.findViewById(R.id.login_change_information);
        imageView_login_change_calendar = (LinearLayout) view.findViewById(R.id.login_change_calendar);
        button_quit_login = (Button)view.findViewById(R.id.quit_login);
        textView = (TextView)view.findViewById(R.id.username_text);

        textView.setText(Variable.getUsername0());

        imageView_change_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        imageView_login_change_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangeSchoolCalendar.class);
                startActivity(intent);
            }
        });

        button_quit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("登出提示")
                        .setMessage("您确定要退出登录吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Variable.setUsername0(null);
                                beforeLogin = new BeforeLogin();
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frame_content2, beforeLogin);
                                fragmentTransaction.commit();
                            }
                        })
                        .create();
                dialog.show();
            }
        });

        return view;
    }
}
