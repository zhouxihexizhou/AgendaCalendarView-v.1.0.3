package com.example.xi.notebook4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xi.notebook4.Check.Check_login;
import com.example.xi.notebook4.Overall.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class LoginActivity extends FragmentActivity {

    private EditText username;

    private String usernamestring;

    private EditText password;

    private String passwordstring;

    private Button button_login_login;

    private Button button_login_register;

    private Button button_login_back;

    private Handler handler;

    private String data;

    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username_login);
        password = (EditText)findViewById(R.id.password_login);
        button_login_login = (Button)findViewById(R.id.login_login_button);
        button_login_register = (Button)findViewById(R.id.login_register_button);
        button_login_back = (Button)findViewById(R.id.login_back_button);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        handler = new Handler(Looper.getMainLooper())
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what)
                {
                    case 1:
                        Toast.makeText(LoginActivity.this,msg.getData().getString("msg"),Toast.LENGTH_SHORT).show();

                        Variable.setUsername0(usernamestring);
                        Intent intent = new Intent("com.example.xi.notebook4.LOCAL_BROADCAST");
                        intent.putExtra("login", 2);
                        localBroadcastManager.sendBroadcast(intent);

                        LoginActivity.this.finish();
                        break;
                    case 2:
                        Toast.makeText(LoginActivity.this,msg.getData().getString("msg"),Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(LoginActivity.this,msg.getData().getString("msg"),Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        button_login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });

        button_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernamestring = username.getText().toString();
                passwordstring = password.getText().toString();
                if(Check_login.formCheck(usernamestring) && Check_login.formCheck(passwordstring))
                {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String url = "http://47.94.13.128:8888/accept/login?username=" +
                                        usernamestring+"&password="+passwordstring;
                                URL url1 =  new URL(url);
                                if(url1!=null)
                                {
                                    HttpURLConnection connection = (HttpURLConnection)url1.openConnection();
                                    connection.setRequestMethod("GET");
                                    connection.setConnectTimeout(8000);
                                    //接受
                                    if(connection.getResponseCode()==200)
                                    {
                                        InputStream inputStream = connection.getInputStream();
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                                        data = reader.readLine();
                                        System.out.println(data);
                                        reader.close();
                                        inputStream.close();
                                        analyse(data);

                                    }
                                }
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"请输入正确的账号密码",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }

    private void analyse(String data) {
        System.out.println(data);
        String msg=null;
        Message message = new Message();
        if(data.equals("success"))
        {
            //登陆成功，更新数据库中的用户名
            Variable.setUsername0(usernamestring);
            msg = data;
            message.what=1;
            Bundle temp = new Bundle();
            temp.putString("msg",msg);
            message.setData(temp);
            handler.sendMessage(message);
        }
        else if(data.equals("password fail"))
        {
            msg = data;
            message.what = 2;
            Bundle temp = new Bundle();
            temp.putString("msg",msg);
            message.setData(temp);
            handler.sendMessage(message);
        }
        else if(data.equals("username fail"))
        {
            msg = "username not exist";
            message.what=3;
            Bundle temp = new Bundle();
            temp.putString("msg",msg);
            message.setData(temp);
            handler.sendMessage(message);
        }
    }
}
