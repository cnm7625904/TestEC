package jljt.wangs.com.testec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import jljt.wangs.com.latte_core.delegates.LatteDelegate;
import jljt.wangs.com.latte_core.net.RestClient;
import jljt.wangs.com.latte_core.net.callback.IError;
import jljt.wangs.com.latte_core.net.callback.IFailure;
import jljt.wangs.com.latte_core.net.callback.ISuccess;

/**
 * Created by Administrator。 on 2017/12/7.
 */

public class ExampleDelegate extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
       testRestClient();
    }
    private void testRestClient(){
        RestClient.builder()
                .url("http://news.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                }).error(new IError() {
            @Override
            public void onError(int code, String msg) {

            }
        })
                .build()
                .get();


    }
}
