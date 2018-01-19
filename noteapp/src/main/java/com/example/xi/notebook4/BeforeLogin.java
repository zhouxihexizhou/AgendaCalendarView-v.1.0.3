package com.example.xi.notebook4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by xi on 2017/12/4.
 */

public class BeforeLogin extends android.support.v4.app.Fragment {

    private View view;

    private Button button_login;

    private Button button_register;

    private LinearLayout imageView_arrow2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.before_login, container, false);

        button_login = (Button)view.findViewById(R.id.button_login);
        button_register = (Button)view.findViewById(R.id.button_register);
        imageView_arrow2 = (LinearLayout) view.findViewById(R.id.image_view_arrow2);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        imageView_arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangeSchoolCalendar.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
