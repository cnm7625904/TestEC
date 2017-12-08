package jljt.wangs.com.latte_core.net;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import jljt.wangs.com.latte_core.net.callback.IError;
import jljt.wangs.com.latte_core.net.callback.IFailure;
import jljt.wangs.com.latte_core.net.callback.IRequest;
import jljt.wangs.com.latte_core.net.callback.ISuccess;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/12/8.
 * final修饰不能修改
 * 因为每次都要创建所以修改为全局变量
 */

public class RestClientBuilder {
    private  String mUrl;
    private static final  Map<String,Object> PARAMS=RestCreator.getParams();
    private  IRequest mIRequest;
    private  ISuccess mISuccess;
    private  IError mIError;
    private  IFailure mIFailure;
    private RequestBody mBody;

    public RestClientBuilder() {
    }
    public final RestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }
    public final RestClientBuilder params(HashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }
    /**
     * 重载
     */
    public final RestClientBuilder params(String key,Object value){

        PARAMS.put(key,value);
        return this;
    }
    public final RestClientBuilder raw(String raw){
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess=iSuccess;
        return this;
    }
    public final RestClientBuilder error(IError iError){
        this.mIError=iError;
        return this;
    }
    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure=iFailure;
        return this;
    }
    public final RestClientBuilder onRequst(IRequest iRequest){
        this.mIRequest=iRequest;
        return this;
    }
//    /**
//     * 检查
//     */
//    private Map<String,Object> cheackParams(){
//        if(PARAMS==null){
//            return  new WeakHashMap<>();
//        }
//        return PARAMS;
//    }
    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIError,mIFailure,mBody);
    }
}
