package jljt.wangs.com.latte_core.net;

import android.content.Context;

import java.util.Map;
import java.util.WeakHashMap;

import jljt.wangs.com.latte_core.net.callback.IError;
import jljt.wangs.com.latte_core.net.callback.IFailure;
import jljt.wangs.com.latte_core.net.callback.IRequest;
import jljt.wangs.com.latte_core.net.callback.ISuccess;
import jljt.wangs.com.latte_core.net.callback.RequestCallbacks;
import jljt.wangs.com.latte_core.ui.LatteLoader;
import jljt.wangs.com.latte_core.ui.LoaderStyle;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/12/8.
 * 请求具体实现类
 * 初值赋值为null,养成一个好习惯,C语言中指针没有赋值就是随机指针
 */

public class RestClient {
    private final String URL;
    private final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAIRLURE;
    private final   RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context context;
    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest requset,
                      ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = requset;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAIRLURE = failure;
        this.BODY = body;
        this.LOADER_STYLE=loaderStyle;
        this.context=context;
    }
    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }
    private void request(HttpMethod method){
        final RestService service=RestCreator.getRestService();
        Call<String> call=null;
        if(REQUEST!=null){
            REQUEST.onRequestStart();
        }
        if(LOADER_STYLE!=null){
            LatteLoader.showLoading(context,LOADER_STYLE);
        }


        switch (method){
            case GET:
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            default:
                break;
        }
        if(call!=null){
            call.enqueue(getRequestCallback());
        }
    }
    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                ERROR,
                FAIRLURE,
                LOADER_STYLE
        );
    }
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
