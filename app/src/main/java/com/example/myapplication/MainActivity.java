package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LoginRequest("user","2333"); //登录并返回对应的用户id
        //RegisterRequest("wowShadow2","199925"); //注册，注册后会自动生成一个对应的个人信息表
        //SearchPersonInfoRequest(19); //根据登录后的用户id得到所有的个人信息
        //UpdatePersonInfoRequest(19,5.5,5.5,100,100,100,100,20,1,100);  //根据登录后的用户id和新数据修改所有的个人信息
        //SearchStepByIdRequest(1);     //根据用户id得到他所有的步数情况，包括步数和日期
        //SearchSomeStepRequest(1,"2019-12-22");   //根据用户id和日期得到对应的步数
        //InsertStepRequest(1,"2019-4-18",2000);   //插入对应id和日期
        //UpdateStepRequest(1,"2019-4-18",200);    //更新对应id和日期下的步数
        //SearchPersonSportsInfoByIdRequest(1);    //查找某个用户所有的运动情况
        //SearchPersonSportsInfoOnSomedayRequest(1,"2019-12-20");    //查找某一天某个用户所有的运动情况，这时返回的数据带运动情况的编号
        //InsertPersonSportsInfoRequest(1,"2017-10-8",1000,200.0,"play");  //插入对应信息的运动情况
        //UpdatePersonSportsInfoRequest(1,"2017-12-8",100,200.0,"play");   //根据运动情况的编号修改对应的数据，不能修改用户id
        //DeletePersonSportsInfoRequest(1);   //根据运动情况编号删除对应的数据
    }
    public void LoginRequest(final String accountNumber, final String password) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/LoginServlet";    //注①
        String tag = "Login";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                String id = jsonObject.getString("userId");      //登录成功返回当前用户的id，根据id完成后续的操作
                                if (result.equals("success")) {  //注⑤
                                    textView1.setText("Response is: "+ result+id);

                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("AccountNumber", accountNumber);  //注⑥
                params.put("Password", password);
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void RegisterRequest(final String accountNumber, final String password) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/RegisterServlet";    //注①
        String tag = "Register";    //任意，只是标记的tag

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //注④
                                if (result.equals("success")) {  //注⑤
                                    textView1.setText("Response is: "+ result);
                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("AccountNumber", accountNumber);  //注⑥
                params.put("Password", password);
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void SearchPersonInfoRequest(final int Id) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/SearchPersonInfoServlet";    //注①
        String tag = "SearchPersonInfo";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                String height = jsonObject.getString("Height");
                                String weight = jsonObject.getString("Weight");
                                String blood = jsonObject.getString("Blood");
                                String sitUpNumber = jsonObject.getString("SitupNumber");
                                String pushUpNumber = jsonObject.getString("PushupNumber");
                                String pullUp=jsonObject.getString("PullUp");
                                String age=jsonObject.getString("Age");
                                String gender=jsonObject.getString("Gender");
                                String heartBeat=jsonObject.getString("HeartBeat");
                                if (result.equals("success")) {  //注⑤
                                    textView1.setText("Response is: "+ result+height+weight+blood+sitUpNumber+pushUpNumber+pullUp+gender+age+heartBeat);

                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(Id));  //注⑥
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void UpdatePersonInfoRequest(final int Id,final double height,final double weight,final int blood,final int sitUpNumber,final int pushUpNumber,final int pullUp,final int age,final int gender,final int heartBeat) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/UpdatePersonInfoServlet";    //注①
        String tag = "UpdatePersonInfo";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                if (result.equals("success")) {  //注⑤
                                    textView1.setText("Response is: "+ result);

                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(Id));
                params.put("Height", String.valueOf(height));
                params.put("Weight", String.valueOf(weight));
                params.put("Blood", String.valueOf(blood));
                params.put("SitupNumber", String.valueOf(sitUpNumber));
                params.put("PushupNumber", String.valueOf(pushUpNumber));
                params.put("PullUp", String.valueOf(pullUp));
                params.put("Age", String.valueOf(age));
                params.put("Gender", String.valueOf(gender));
                params.put("HeartBeat", String.valueOf(heartBeat));
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void SearchStepByIdRequest(final int id) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/QueryStepByIdServlet";    //注①
        String tag = "QueryStepById";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                if (result.equals("success")) {  //注⑤
                                    String count = jsonObject.getString("Number");
                                    int counts=Integer.valueOf(count);
                                    String s="Response is: "+ result+counts;
                                    for(int i=0;i<counts;i++){
                                        String date=jsonObject.getString("Date"+i);
                                        String stepCount=jsonObject.getString("StepCountNumber"+i);
                                        s=s+" "+date+stepCount;
                                    }
                                    textView1.setText(s);
                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(id));  //注⑥
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void SearchSomeStepRequest(final int id,final String date) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/SearchSomeStepServlet";    //注①
        String tag = "QuerySomeStep";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                if (result.equals("success")) {  //注⑤
                                    String date = jsonObject.getString("Date");
                                    String stepCount = jsonObject.getString("StepCount");
                                    textView1.setText(date+stepCount);
                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(id));
                params.put("Date",date);
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void InsertStepRequest(final int Id,final String date,final int count) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/InsertStepServlet";    //注①
        String tag = "InsertStep";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                if (result.equals("success")) {
                                    textView1.setText("Response is: "+ result);

                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(Id));
                params.put("Date",date);
                params.put("StepCount",String.valueOf(count));
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void UpdateStepRequest(final int Id,final String date,final int count) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/UpdateStepServlet";    //注①
        String tag = "UpdateStep";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                if (result.equals("success")) {
                                    textView1.setText("Response is: "+ result);

                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(Id));
                params.put("Date",date);
                params.put("StepCount",String.valueOf(count));
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void SearchPersonSportsInfoByIdRequest(final int id) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/QueryPersonSportsInfoServlet";    //注①
        String tag = "QueryPersonSportsInfoById";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                if (result.equals("success")) {  //注⑤
                                    String count = jsonObject.getString("Number");
                                    int counts=Integer.valueOf(count);
                                    String s="Response is: "+ result+counts;
                                    for(int i=0;i<counts;i++){
                                        String date=jsonObject.getString("Date"+i);
                                        String duration=jsonObject.getString("Duration"+i);
                                        String calorie=jsonObject.getString("Calorie"+i);
                                        String type=jsonObject.getString("Type"+i);
                                        s=s+" "+date+duration+calorie+type;
                                    }
                                    textView1.setText(s);
                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(id));  //注⑥
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }
    public void SearchPersonSportsInfoOnSomedayRequest(final int id,final String date) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/QueryPersonSportsInfoOnSomeDayServlet";    //注①
        String tag = "QueryPersonSportsInfoOnSomeDay";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                if (result.equals("success")) {  //注⑤
                                    String count = jsonObject.getString("Number");
                                    int counts=Integer.valueOf(count);
                                    String s="Response is: "+ result+counts;
                                    for(int i=0;i<counts;i++){
                                        String date=jsonObject.getString("Date"+i);
                                        String duration=jsonObject.getString("Duration"+i);
                                        String calorie=jsonObject.getString("Calorie"+i);
                                        String type=jsonObject.getString("Type"+i);
                                        String sNo=jsonObject.getString("SNo"+i);
                                        s=s+" "+date+duration+calorie+type+sNo;
                                    }
                                    textView1.setText(s);
                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(id));
                params.put("Date",date);
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void InsertPersonSportsInfoRequest(final int Id,final String date,final int duration,final double calorie,final String type) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/InsertPersonSportsInfoServlet";    //注①
        String tag = "InsertPersonSportsInfo";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                if (result.equals("success")) {
                                    textView1.setText("Response is: "+ result);

                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id", String.valueOf(Id));
                params.put("Date",date);
                params.put("Duration",String.valueOf(duration));
                params.put("Type",type);
                params.put("Calorie",String.valueOf(calorie));
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void DeletePersonSportsInfoRequest(final int sNo) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/DeletePersonSportsInfoServlet";    //注①
        String tag = "InsertPersonSportsInfo";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                if (result.equals("success")) {
                                    textView1.setText("Response is: "+ result);

                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("SNo", String.valueOf(sNo));
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }

    public void UpdatePersonSportsInfoRequest(final int sNo,final String date,final int duration,final double calorie,final String type) {
        //请求地址
        String url = "http://49.235.33.137:8080/myFirstWebApp/UpdatePersonSportsInfoServlet";    //注①
        String tag = "InsertPersonSportsInfo";    //注②

        //取得请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //防止重复请求，所以先取消tag标识的请求队列
        requestQueue.cancelAll(tag);
        final TextView textView1 = (TextView) findViewById(R.id.text);
        textView1.setText("Response is: ");
        //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
        final StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != "") {
                            try {
                                JSONObject jsonObject = (JSONObject) new JSONObject(response).get("params");  //注③
                                String result = jsonObject.getString("Result");  //成功或者失败
                                if (result.equals("success")) {
                                    textView1.setText("Response is: "+ result);

                                } else {
                                    //做自己的登录失败操作，如Toast提示
                                    textView1.setText("Response is: "+ result);
                                }
                            } catch (Exception e) {
                                //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                Log.e("TAG", e.getMessage(), e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                Log.e("TAG", error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("SNo", String.valueOf(sNo));
                params.put("Date",date);
                params.put("Duration",String.valueOf(duration));
                params.put("Type",type);
                params.put("Calorie",String.valueOf(calorie));
                return params;
            }
        };

        //设置Tag标签
        request.setTag(tag);

        //将请求添加到队列中
        requestQueue.add(request);
    }
}
