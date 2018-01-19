package com.example.xi.notebook4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xi.notebook4.Check.Check_login;
import com.example.xi.notebook4.Overall.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class RegisterActivity extends FragmentActivity {

    private EditText username;

    private EditText password;

    private String usernamestring;

    private String passwordstring;

    private String confirmstring;

    private  EditText confirmpassword;

    private Spinner school;

    private String schoolstring;

    private Spinner interest;

    private String intereststring;

    private Button button_register_back;

    private Button button_register_register;

    private TextView text_view_register_login;

    private Handler handler;

    private  String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username_reigster);
        password = (EditText) findViewById(R.id.password_register);
        confirmpassword = (EditText) findViewById(R.id.password_register_confirm);
        school = (Spinner) findViewById(R.id.school_register);
        interest =(Spinner)findViewById(R.id.interest_register);

        button_register_back = (Button)findViewById(R.id.register_back_button);
        button_register_register = (Button)findViewById(R.id.register_register_button);
        text_view_register_login = (TextView)findViewById(R.id.register_login_text_view);

        handler = new Handler(Looper.getMainLooper())
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what)
                {
                    case 1:
                        Toast.makeText(RegisterActivity.this,msg.getData().getString("msg"),Toast.LENGTH_SHORT).show();
                        RegisterActivity.this.finish();
                        break;
                    case 2:
                        Toast.makeText(RegisterActivity.this,msg.getData().getString("msg"),Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        button_register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });

        button_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernamestring = username.getText().toString();
                passwordstring = password.getText().toString();
                confirmstring = confirmpassword.getText().toString();
                schoolstring = school.getSelectedItem().toString();
                intereststring = interest.getSelectedItem().toString();

                if(!usernamestring.isEmpty())
                {
                    if(!passwordstring.isEmpty())
                    {
                        if(passwordstring.equals(confirmstring))
                        {
                            if(Check_login.formCheck(usernamestring) && Check_login.formCheck(passwordstring))
                            {//满足6-10位数字、字母或下划线
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            String url = "http://47.94.13.128:8888/accept/insertUser?username="+
                                                    usernamestring+"&password="+passwordstring+"&school="+
                                                    URLDecoder.decode(schoolstring,"UTF-8")+"&interest="+
                                                    URLDecoder.decode(intereststring,"UTF-8");
                                            URL url1 = new URL(url);
                                            if(url1 != null)
                                            {
                                                HttpURLConnection connection = (HttpURLConnection)url1.openConnection();
                                                connection.setRequestMethod("GET");
                                                connection.setConnectTimeout(8000);

                                                //接收
                                                if(connection.getResponseCode() == 200)
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
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
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
                                //用户名或密码不正确
                                Toast.makeText(RegisterActivity.this,"incorrect username or password",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            //两次输入的密码不一致
                            Toast.makeText(RegisterActivity.this,"password is different",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        //密码为空
                        Toast.makeText(RegisterActivity.this,"password is null",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    //用户名为空
                    Toast.makeText(RegisterActivity.this,"username is null",Toast.LENGTH_SHORT).show();
                }

            }
        });

        text_view_register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                RegisterActivity.this.finish();
            }
        });
    }

    private void analyse(String data) {
        System.out.println(data);
        String msg = null;
        Message message = new Message();
        if(data.equals("success"))
        {
            //给全局变量赋值
            Variable.setUsername0(usernamestring);
            Variable.setInterest0(intereststring);
            Variable.setSchool0(schoolstring);

            msg = data;
            message.what=1;
            Bundle temp = new Bundle();
            temp.putString("msg",msg);
            message.setData(temp);
            handler.sendMessage(message);
        }
        else if(data.equals("username exist"))
        {
            msg = data;
            message.what = 2;
            Bundle temp = new Bundle();
            temp.putString("msg",msg);
            message.setData(temp);
            handler.sendMessage(message);
        }
    }
}
